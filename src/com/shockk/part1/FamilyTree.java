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

package com.shockk.part1;

public class FamilyTree
{
	private class FamilyTreeNode
	{
		public String name;
		public FamilyTreeNode partner;
		public FamilyTreeNode sibling;
		public FamilyTreeNode child;
		
		public FamilyTreeNode(String name) { this.name = name; }
		
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
			if(!(child instanceof FamilyTreeNode)) return false;
			if(child.name.compareToIgnoreCase(childName) == 0) return true;
			return child.hasSibling(childName);
		}
		
		@Override public String toString()
		{
			return partner instanceof FamilyTreeNode
					? name + " partner " + partner.name
					: name;
			
		}
	}
	
	private FamilyTreeNode _ancestor;
	
	public FamilyTree(String ancestorName, String partnerName)
	{
		_ancestor = new FamilyTreeNode(ancestorName);
		_ancestor.partner = new FamilyTreeNode(partnerName);
		_ancestor.partner.partner = _ancestor;
	}
	
	public void addChild(String childName)
	{
		if(_ancestor.child instanceof FamilyTreeNode == false)
		{
			_ancestor.child = new FamilyTreeNode(childName);
		}
		else if(!_ancestor.hasChild(childName))
		{
			FamilyTreeNode ref = _ancestor.child;
			while(ref.sibling instanceof FamilyTreeNode) ref = ref.sibling;
			ref.sibling = new FamilyTreeNode(childName);
		}
	}
	
	private void display(int depth, FamilyTreeNode node)
	{
		for(int i=0; i<depth; ++i) System.out.print("  "); // indent based on depth
		System.out.println(node);
		
		if(node.child instanceof FamilyTreeNode) display(depth + 1, node.child);
		if(node.sibling instanceof FamilyTreeNode) display(depth, node.sibling);
	}
	
	public void display() { display(0, _ancestor); }
}
