/*
 Course: CMPS 101 Abstract Data Types
 Professor: Patrick Tantalo
 Assignment: PA4-FindPath.c
 Date: May 26, 2019
 Name: Cristian C. Castillo Jr
 Cruz ID: ccarri11
 Student ID: #1576341
 */
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "List.h"
#include "Graph.h"

void close(Graph myGraph, FILE *in, FILE *out); // function proto

void printAttributes(Graph myGraph,FILE *in, FILE *out,int src, int ds)
{
	// Note:
	// The source vertex will print out the distance to ds vertex. Lastly we will find
    // and print the shortest path, "iff" it exist!
	 for(; fgetc(in) != -1;)
	 {
        fscanf(in, "%d", &src);
        fscanf(in, "%d", &ds);
        List myList = newList(); 		// instiante a new list and pass graph & src to conduct BFS
       
        if(src == 0)   					// if our source does not equal 0 isnt counting in BFS
        {
            if(ds == 0) 				// our destination does not equal 0 is also not counting in BFS
            {
            	break; 					// then we break out of our while loop
            }
            else
            {
            	continue;
            }
        }
        else
        {
	       	BFS(myGraph, src); 				        // After BFS we will send myGraph to getPath, which entails to the following ...	

	        if(getDist(myGraph, ds) != -2)          // Now, if myGraph is not empty by checking its distance per say, i.e getDist(), and some destination does exist, i.e parent with children
	        {
                getPath(myList, myGraph, ds);       // ...Where function getPath() appends to the List myList the 
                                                    // vertices of a shortest path in G from source to 
                                                    // u, or appends to L the value NIL if no such path exists. 
	            fprintf(out, "The distance from %d to %d is %d\n", src,ds, (length(myList)-1));    // then print out the source, destionation, and the length
	            fprintf(out, "A shortest %d - %d path is: ", src, ds); 							   // print out shortest path
	            
	            printList(out, myList); 
	            fprintf(out, "\n");
	        }
	        if(getDist(myGraph,ds) == -2)
	        {
                getPath(myList, myGraph, ds);      // ...Where function getPath() appends to the List myList the 
                                                   // vertices of a shortest path in G from source to 
                                                   // u, or appends to L the value NIL if no such path exists. 
	            fprintf(out, "The distance from %d to %d is infinity\n", src, ds);
	            fprintf(out, "No %d-%d path exists\n", src, ds);
	        }
	        freeList(&myList);
	    }
    }
  	close(myGraph,in,out);
}

int main(int argc, char* argv[]) 
{

    FILE *in = fopen(argv[1], "r");
    FILE *out = fopen(argv[2], "w");
    int size = 0;
    int tempSource = 0; 	 // source vertex for myGraph
    int tempDestination = 0; // destination vertex for myGraph
    int src = 0;  			 // src for BFS
    int ds = 0;  			 // ds for BFS
   
    if(argc != 3) 			 // Check if 3 valid arguments exist!
    {
        fprintf(stderr, "Usage: %s <input file> <output file>\n", argv[0]);
        exit(1);
    }
    if(in == NULL || out == NULL) 
    {
        printf("File inaccessible cannot read/write file %s\n", argv[1]);
        exit(1);
    }
    fscanf(in, "%d", &size); 			                       // search by scanning for new verticies
    Graph myGraph = newGraph(size); 						   // instiante new myGraph of size n
									    					   /* Read -> Store (myGraph) & print out its neighbors. */
    for(int j = -1; fgetc(in) != j;) 
    {
        fscanf(in, "%d", &tempSource); 						   // scan tempSource
        fscanf(in, "%d", &tempDestination); 				   // scan tempDestination
        													   // if src or ds is 0 don't add to myGraph
        if(tempSource != 0) 								   // if my temporary source is not equal to 0
        {
        	if(tempDestination != 0) 						   // and destination isnt either
        	{
        		addEdge(myGraph, tempSource, tempDestination); //then proceed by adding edge
        	}
        }
   		else
   		{
   			break;
   		}
    }
    printGraph(out, myGraph); 
    fprintf(out, "\n");
    /* We will continue our process by continuing on to a loop & process the input. 
    Each iteration of loop shall read in 1 pair. These will
    be of source, destination, verticies, and we shall run BFS once we continue to our next function printAttributes. 
    */
    printAttributes(myGraph,in,out,src,ds);
    return(0);
}

void close(Graph myGraph, FILE *in, FILE *out)
{
	freeGraph(&myGraph); // free the graph
    fclose(in);  // close FILE *in
    fclose(out); // close FILE *out
}






