#include <stdio.h>
#include "Graph.h"

int main(){
	
	Graph myGraph = newGraph(8);
	
	
	addArc(myGraph, 1, 2); //parameter 2 is going into parameter 1's adj. list
	addArc(myGraph, 2, 3);
	addArc(myGraph, 2, 5);
	addArc(myGraph, 2, 6);
	addArc(myGraph, 3, 4);
	addArc(myGraph, 3, 7);
	addArc(myGraph, 4, 3);
	addArc(myGraph, 4, 8);
	addArc(myGraph, 5, 1);
	addArc(myGraph, 5, 6);
	addArc(myGraph, 6, 7);
	addArc(myGraph, 7, 6);
	addArc(myGraph, 7, 8);
	addArc(myGraph, 8, 8);
	addArc(myGraph, 3, 7);
	addArc(myGraph, 4, 3);
	addArc(myGraph, 4, 8);
	addArc(myGraph, 5, 1);
	addArc(myGraph, 5, 6);
	addArc(myGraph, 6, 7);
	addArc(myGraph, 7, 6);
	addArc(myGraph, 7, 8);
	addArc(myGraph, 8, 8);
	
	
	printGraph(stdout, myGraph);
	
	printf("\n");
	
	printGraph(stdout, copyGraph(myGraph));
	
	printf("\n");
	
	printGraph(stdout, transpose(myGraph));
	
	printf("\n");
	
	List S = newList();
	
	for(int x = 1; x < 9; x++){
		
		append(S, x);
	}
	
	printf("Original contents of the List:\n");
	
	printList(stdout, S);
	
	printf("\n");	
	
	DFS(myGraph, S);
	
	printDFSLists(myGraph); // 1st DFS run
	
	printf("\n");
	
	printf("Contents of the List after the first DFS:\n");
	
	printList(stdout, S);
	
	printf("\n");
	
	Graph transposeG = transpose(myGraph);
	
	printf("The transposeG adjancency Lists are:\n");
	
	printGraph(stdout, transposeG);
	
	printf("\n");
	
	DFS(transposeG, S); // 2nd DFS run
	
	printf("The contents of the List after the transpose(myGraph) DFS run is:\n");
	
	printList(stdout, S);
	
	printf("\n");
	
	printf("The contents of the parent, color, discover, and finish lists for transpose(myGraph) is:\n");
	
	printDFSLists(transposeG);
    
    List S2 = newList();
		
	for(int x = 1; x < getOrder(myGraph) + 1; x++){
		
		append(S2, x);
	}
	
	DFS(transposeG, S2);
	
	printf("The proper parent list should be below:\n");
	
	printDFSLists(transposeG);
	
	printf("\n");
	
	for(int x = 1; x < getOrder(myGraph); x++){
		
		printf("%d%s", transposeG->parent[x]);
	}
	
	
	
	
	
	
	
	
	

	
	
	
	
	
}
