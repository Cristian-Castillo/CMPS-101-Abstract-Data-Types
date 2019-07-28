/*
 Course: CMPS 101 Abstract Data Types
 Professor: Patrick Tantalo
 Assignment: PA4-GraphTest
 Date: May 22, 2019
 Name: Cristian C. Castillo Jr
 Cruz ID: ccarri11
 Student ID: #1576341
 */
#include<stdio.h>
#include "Graph.h"

int main(int argc, char *argv[])
{

        for(int count = 0; count < 2; count++)
        {
                Graph tempGraph = newGraph(100);
                List B = newList();
                List C = newList();
                
                addEdge(tempGraph, 34, 4);
                addEdge(tempGraph, 34, 3);
                addEdge(tempGraph, 22, 2);
                addEdge(tempGraph, 1, 74);
                addEdge(tempGraph, 3, 4);
                addEdge(tempGraph, 5, 40);
                addEdge(tempGraph, 6, 71);
                addEdge(tempGraph, 4, 9);
                addEdge(tempGraph, 65, 40);
                
                BFS(tempGraph, 3);
                getPath(B, tempGraph, 64);
                append(C, 3);
                append(C, 64);
                
                printf("Pathlist is: \n");
                printMe(B);
                printf("\n");
                printf("C is: \n");
                printMe(C);
                printf("\n");
                
                moveFront(B);
                BFS(tempGraph, 2);
                getPath(B, tempGraph, 5);
                append(C, 2);
                
                printf("Pathlist is: \n");
                printMe(B);
                printf("\n");
                printf("C is: \n");
                printMe(C);
                printf("\n");
                
                getPath(B, tempGraph, 100);
                printf("Pathlist is: \n");
                printMe(B);
                printf("\n");
                printf("C is: \n");
                printMe(C);
                printf("\n");
                
                clear(B);
                clear(C);
                append(C, NIL);
                BFS(tempGraph, 99);
                getPath(B, tempGraph, 2);
                
                printf("Pathlist is: \n");
                printMe(B);
                printf("\n");
                printf("C is: \n");
                printMe(C);
               
                }
        return 0;
}



