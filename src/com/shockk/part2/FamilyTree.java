/*
* Copyright 2014 David Farrell
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
* http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/

package com.shockk.part2;

import com.shockk.common.Input;

public class FamilyTree
{
	private class FamilyTreeNode
	{
		public int identifier;
		public String name;
		public FamilyTreeNode partner;
		public FamilyTreeNode sibling;
		public FamilyTreeNode child;
		
		public FamilyTreeNode(int identifier, String name)
		{
			this.identifier = identifier;
			this.name = name;
		}
		
		public boolean hasSibling(String siblingName)
		{
			FamilyTreeNode ref = this;
			while(ref.sibling instanceof FamilyTreeNode)
			{
				if(ref.sibling.name.compareToIgnoreCase(siblingName) == 0) return true;
				ref = ref.sibling;
			}
			return false;
		}
		
		public boolean hasChild(String childName)
		{
			if(child instanceof FamilyTreeNode == false) return false;
			if(child.name.compareToIgnoreCase(childName) == 0) return true;
			return child.hasSibling(childName);
		}
		
		@Override public String toString()
		{
			return name + "(id " + identifier + ")";
			
		}
	}
	
	private FamilyTreeNode _ancestor;
	private int currentIdentifier = 1;
	
	public FamilyTree()
	{
		_ancestor = create(Input.getString("name of ancestor> "));
	}
	
	public FamilyTreeNode create(String name)
	{
		name = name.trim();
		if(name.length() == 0)
		{
			System.out.println("ERROR: family member requires name");
			return null;
		}
		
		name = Character.toUpperCase(name.charAt(0)) + name.substring(1);
		return new FamilyTreeNode(currentIdentifier++, name);
	}
	
	public FamilyTreeNode get(int id, FamilyTreeNode root)
	{
		if(root == null) return null;
		if(root.identifier == id) return root;
		
		if(root.partner instanceof FamilyTreeNode)
		{
			if(root.partner.identifier == id) return root.partner;
			
			FamilyTreeNode ref = get(id, root.child);
			if(ref != null) return ref;
		}
		
		return get(id, root.sibling);
	}
	
	public FamilyTreeNode get(int id) { return get(id, _ancestor); }
	
	public void addChild()
	{
		String childName = Input.getString("name of child: ");
		
		if(_ancestor.partner instanceof FamilyTreeNode == false)
		{
			System.out.println("ERROR: ancestor has no partner");
		}
		else if(_ancestor.child instanceof FamilyTreeNode == false)
		{
			_ancestor.child = create(childName);
			_ancestor.partner.child = _ancestor.child;
		}
		else if(!_ancestor.hasChild(childName))
		{
			FamilyTreeNode ref = _ancestor.child;
			while(ref.sibling instanceof FamilyTreeNode) ref = ref.sibling;
			ref.sibling = create(childName);
		}
		else
		{
			System.out.println("ERROR: ancestor already has child with the name `" + childName + "`");
		}
	}
	
	public FamilyTreeNode choose()
	{
		displayAll();
		System.out.println();
		
		Integer id = Input.getInteger("id> ");
		if(id == null)
		{
			System.out.println("ERROR: please enter a number");
			return null;
		}
		
		FamilyTreeNode node = get(id);
		if(node == null)
		{
			System.out.println("ERROR: please enter a valid id");
			return null;
		}
		
		return node;
	}
	
	public void addPartner()
	{
		FamilyTreeNode node = choose();
		if(node == null) return;
		
		if(node.partner instanceof FamilyTreeNode)
		{
			System.out.println("ERROR: member already has a partner");
			return;
		}
		
		node.partner = create(Input.getString("name of partner> "));
		node.partner.partner = node;
	}
	
	public void display(int depth, FamilyTreeNode node)
	{
		if(node == null) return;
		
		for(int i=0; i<depth; ++i) System.out.print("  "); // indent based on depth
		
		if(node.partner instanceof FamilyTreeNode)
		{
			System.out.println(node + " partner " + node.partner);
			
			if(node.child instanceof FamilyTreeNode)
			{
				display(depth + 1, node.child);
				
				FamilyTreeNode ref = node.child;
				while(ref.sibling instanceof FamilyTreeNode)
				{
					display(depth + 1, ref.sibling);
					ref = ref.sibling;
				}
			}
			else
			{
				for(int i=0; i<depth+1; ++i) System.out.print("  ");
				System.out.println("no children");
			}
		}
		else System.out.println(node + " has no partner");
	}
	
	public void displayAll() { display(0, _ancestor); }
}
