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

package com.shockk.part3;

import com.shockk.common.Input;

public class FamilyTreeTest
{
	public static void main(String[] args)
	{
		FamilyTree tree = new FamilyTree();
		
		for(boolean running=true; running;)
		{
			System.out.println();
			System.out.println("1. add partner");
			System.out.println("2. add child");
			System.out.println("3. display tree");
			System.out.println("4. display member");
			System.out.println("0. exit");
			
			Integer choice = Input.getInteger("choice> ");
			if(choice == null)
			{
				System.out.println("ERROR: please enter a number");
				continue;
			}
			
			switch(choice)
			{
			case 1:
				tree.addPartner();
				break;
			case 2:
				tree.addChild();
				break;
			case 3:
				tree.displayAll();
				break;
			case 4:
				tree.display(0, tree.choose());
				break;
			case 0:
				running = false;
				break;
			default:
				System.err.println("ERROR: please enter a valid choice");
				break;
			}
		}
	}
}
