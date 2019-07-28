/*
 Course: CMPS 101 Abstract Data Types
 Professor: Patrick Tantalo
 Assignment: PA5
 Date: May 28, 2019
 Name: Cristian C. Castillo Jr
 Cruz ID: ccarri11
 Student ID: #1576341
 */
#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include<inttypes.h>
#include "Graph.h"
#define MAX 100

void closeWriter(FILE *p2_F1,FILE *p2_F2,Graph myGraph,Graph myTranspose,List setTempList)
{
    freeGraph(&myGraph);
    freeGraph(&myTranspose);
    freeList(&setTempList);
    fclose(p2_F1);
    fclose(p2_F2);
}

void setNewGraph(Graph myGraph,FILE *p2_F2,FILE *p2_F1); // function prototype

int main(int argc, char *argv[])
{
    char input[MAX];
    int l_CountZero = 0;
    int vOne = 0;
    int vTwo = 0;
    int l_CountOne = 0;
    int order = 0;

    if(argc != 3) // check arguments for error
    {
        printf("%s", "Invoked Error: Reference parameter arguments.\n");
        exit(1);
    }
    FILE *pToFile0 = fopen(argv[1], "r");
    
    while(fgets(input, MAX, pToFile0))
    {
        l_CountZero++;
    }
    fclose(pToFile0);

    FILE *p2_F1 = fopen(argv[1], "r");
    FILE *p2_F2 = fopen(argv[2], "w");
    fgets(input, MAX, p2_F1);
    sscanf(input, "%d", &order);
    Graph myGraph = newGraph(order); // Complete!!!
    // Note:
    // l_CountZero: Number total of lines in the file
    // l_CountOne = var incr. in the while
    
    while(fgets(input, MAX, p2_F1))
    {
        sscanf(input, "%d %d", &vOne, &vTwo);
        
        if(vOne != 0 && vTwo != 0)
        {    
            addArc(myGraph, vOne, vTwo);
            
        }
        l_CountOne = (l_CountOne+1);
    }   
    setNewGraph(myGraph,p2_F2,p2_F1);    // Graph is now loaded with vaues and will be passed on to next function for processing
}

void setNewGraph(Graph myGraph,FILE *p2_F2,FILE *p2_F1)
{
    List setTempList = newList();
    int lastCount = getOrder(myGraph);
    int components_Strong = 0;
    int tempHold;
    int numOfComponents = 1;
    int count = 1;
    int counter = 0;

    for(int counter = 1; counter < (getOrder(myGraph)+1); counter++)
    {
        append(setTempList, counter);
    }

    fprintf(p2_F2, "%s\r\n", "Adjacency list representation of G:");
    printGraph(p2_F2, myGraph); //   Adj List of graph printed into output file.
    DFS(myGraph, setTempList);  //   Ran DFS with List of Vertices in descending order.
    Graph myTranspose = transpose(myGraph); //myTranspose = to the transpose of G.
    moveBack(setTempList);
    
    for(; counter < getOrder(myGraph);)
    { 
        deleteBack(setTempList);   // Delete latter "order" # of elements from list to satisfy DFS pre-condition.
        counter++;
    }
    counter = 0;
    DFS(myTranspose, setTempList); // Running DFS on the transpose of G with setTempList which is now list of descending finishing times.
                                   // Delete latter "order" # of elements of setTempList again to obtain
                                   // a List containing just the strongly connected components.
    for(; counter < getOrder(myGraph);)
    {  
        deleteBack(setTempList);
        counter++;
    }
    counter++;

    for(; count < (getOrder(myGraph)+1);)
    {   
        if(getParent(myTranspose, count) == -1) // Obtain # of strongly connected components from parent list.
        {    
            components_Strong = components_Strong + 1;
        }
        count++;
    }

    fprintf(p2_F2, "%s%d%s\r\n", "G contains ", components_Strong, " strongly connected components:");
    moveBack(setTempList);

    for(; lastCount != 0;)
    {
        if(getParent(myTranspose, get(setTempList)) == -1)
        {
            int num = 0;
            fprintf(p2_F2, "%s%d%s", " Component ", numOfComponents, " : ");
            tempHold = index(setTempList);
            fprintf(p2_F2, "%d%s", get(setTempList), " ");   
            moveNext(setTempList);

            if((index(setTempList) != -1))
            {
                while((index(setTempList) != -1) && (getParent(myTranspose, get(setTempList)) != -1))
                {
                    fprintf(p2_F2, "%d%s", get(setTempList), " ");
                    moveNext(setTempList);
                }
            }
            fprintf(p2_F2,"%s", "\r\n");
            numOfComponents++;
            moveFront(setTempList);
          
            for(;num < tempHold;)
            {
                moveNext(setTempList);
                num++;
            }
            num = 0;
        }
        if(front(setTempList) != -1)
        {
            if(get(setTempList) != front(setTempList))
            {
                movePrev(setTempList);
            }
        }
        lastCount--;
    }
    closeWriter(p2_F1,p2_F2,myGraph,myTranspose,setTempList);
}

