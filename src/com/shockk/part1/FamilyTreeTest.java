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

import com.shockk.common.Input;

public class FamilyTreeTest
{
	public static void main(String[] args)
	{
		FamilyTree tree = new FamilyTree(
				Input.getString("name of ancestor: "),
				Input.getString("name of partner: "));
		
		for(boolean running=true; running;)
		{
			System.out.println("");
			System.out.println("1. add child");
			System.out.println("2. display tree");
			System.out.println("0. exit");
			System.out.println("");
			
			Integer choice = Input.getInteger("> ");
			if(choice == null) continue;
			
			switch(choice)
			{
			case 0:
				running = false;
				break;
			case 1:
				tree.addChild(Input.getString("name of child: "));
				break;
			case 2:
				tree.display();
				break;
			}
		}
	}
}
