/*
 Course: CMPS 101 Abstract Data Types
 Professor: Patrick Tantalo
 Assignment: PA5
 Date: May 28, 2019
 Name: Cristian C. Castillo Jr
 Cruz ID: ccarri11
 Student ID: #1576341
 */
#include <stdio.h>
#include "Graph.h"

int main()
{
	for(int count = 0; count < 5; count++)
	{
		int x = 1;
		Graph myNewGraph = newGraph(8);
		addArc(myNewGraph, 1, 2); // par. # 2 is going into par. # 1's Adjacency List
		addArc(myNewGraph, 2, 3);
		addArc(myNewGraph, 2, 5);
		addArc(myNewGraph, 2, 6);
		addArc(myNewGraph, 3, 4);
		addArc(myNewGraph, 3, 7);
		addArc(myNewGraph, 4, 3);
		addArc(myNewGraph, 4, 8);
		addArc(myNewGraph, 5, 1);
		addArc(myNewGraph, 5, 6);
		addArc(myNewGraph, 6, 7);
		addArc(myNewGraph, 7, 6);
		addArc(myNewGraph, 7, 8);
		addArc(myNewGraph, 8, 8);
		
		printGraph(stdout, myNewGraph);
		printf("\n");
		printGraph(stdout, copyGraph(myNewGraph));
		printf("\n");
		printGraph(stdout, transpose(myNewGraph));
		printf("\n");
		
		List S = newList();
		
		for(; x < 9;)
		{	
			append(S, x);
			x++;
		}
		x = 1;
		
		printf("List original numbers:\n");
		printList(stdout, S);
		printf("\n");	
		DFS(myNewGraph, S);
		
		printDFSLists(myNewGraph); // This will be the first DFS we run
		
		printf("\n");
		printf("The contents of the List after the first DFS run is:\n");
		printList(stdout, S);
		
		printf("\n");
		Graph transposeG = transpose(myNewGraph);
		tranpose G adjancy List:
		printf("tranpose G adjancy List:\n");
		
		printGraph(stdout, transposeG);
		printf("\n");
		
		DFS(transposeG, S); // This is the second frun to DFS
		
		printf("List after the transpose(myNewGraph) DFS ran:\n");
		printList(stdout, S);
		printf("\n");
		
		printf("Parent, color, discover, and finish lists for transpose(myNewGraph):\n");
		printDFSLists(transposeG);
	    
	    List temp2 = newList();
			
		for(;(getOrder(myNewGraph)+1) > x;)
		{
			append(temp2, x);
			x++;
		}
		x = 1;
		DFS(transposeG, temp2);
		printf("Parent List is Underneath: ");
		printDFSLists(transposeG);
		
		printf("\n");
		
		for(; x < getOrder(myNewGraph);)
		{
			printf("%d%s", transposeG->parent[x]);
			x++;
		}
		x = 1;

		printf("\n");

		Graph myNewGraph = newGraph(10);
		addArc(myNewGraph, 1, 4); // parameter 2 is going into parameter 1's adj. list
		addArc(myNewGraph, 7, 23);
		addArc(myNewGraph, 7, 35);
		addArc(myNewGraph, 7, 46);
		addArc(myNewGraph, 8, 94);
		addArc(myNewGraph, 8, 7);
		addArc(myNewGraph, 8, 3);
		addArc(myNewGraph, 8, 98);
		addArc(myNewGraph, 9, 1);
		addArc(myNewGraph, 9, 6);
		addArc(myNewGraph, 10, 7);
		addArc(myNewGraph, 7, 6);
		addArc(myNewGraph, 7, 8);
		addArc(myNewGraph, 8, 8);
		
		printf("\n");
		printGraph(stdout, myNewGraph);
		printf("\n");
		printGraph(stdout, copyGraph(myNewGraph));
		printf("\n");
		printGraph(stdout, transpose(myNewGraph));
		printf("\n");
		
		List S = newList();
		
		for(; x < 9;)
		{	
			append(S, x);
			x++;
		}
		x = 1;

		printf("List original numbers:\n");
		printList(stdout, S);
		printf("\n");	
		DFS(myNewGraph, S);
		
		printDFSLists(myNewGraph); // This will be the first DFS we run
		
		printf("\n");
		printf("The contents of the List after the first DFS run is:\n");
		printList(stdout, S);
		
		printf("\n");
		Graph transposeG = transpose(myNewGraph);
		tranpose G adjancy List:
		printf("tranpose G adjancy List:\n");
		
		printGraph(stdout, transposeG);
		printf("\n");
		
		DFS(transposeG, S); // This is the second frun to DFS
		
		printf("List after the transpose(myNewGraph) DFS ran:\n");
		printList(stdout, S);
		printf("\n");
		
		printf("Parent, color, discover, and finish lists for transpose(myNewGraph):\n");
		printDFSLists(transposeG);
	    
	    List temp2 = newList();
			
		for(;(getOrder(myNewGraph)+1) > x;)
		{
			append(temp2, x);
			x++;
		}
		x = 1;
		
		DFS(transposeG, temp2);
		printf("Parent List is Underneath: ");
		printDFSLists(transposeG);
		printf("\n");
		
		for(; x < getOrder(myNewGraph);)
		{
			printf("%d%s", transposeG->parent[x]);
			x++;
		}
		x = 1;
	}
	return 0;
}
