/*
 Course: CMPS 101 Abstract Data Types
 Professor: Patrick Tantalo
 Assignment: PA4
 Date: May 22, 2019
 Name: Cristian C. Castillo Jr
 Cruz ID: ccarri11
 Student ID: #1576341
 */
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "Graph.h"
#include "List.h"

#define BLACK 'b' // marked out alg index complete
#define GRAY 'g'  // discovered
#define WHITE 'w' // undiscovered
#define NIL -1    // negative means nothing, and suggested by professor to be used
#define INF -2

typedef struct GraphObj   // GraphObj Struct - this is used in Graph.h to export the (Graph) type to this as well as other clients.
{
    List *myNeighbor;
    char *color;      // white = > undiscovered, gray = if source color gray, else if visited color grey, if 1 more visit after gray color black.
    int verticyOrder; // number of vertices in graph
    int maxSize;      // number of edges in graph
    int reference;    // refers to the source
    int *distance;    // infinity conceptually, but we add 1 each time, and this depends on our parent during our bfs alg.
    int *parent;      // parent vertex,child will descend from parents!
    
}GraphObj;
//Constructors-Destructors------------------------------------------------------------------------------------------------------------------------------
Graph newGraph(int n)
{
    // malloc allocates memory block of given size (in bytes) and returns a pointer
    // to the beginning of the block. malloc() doesn't initialize the allocated memory. If we try to acces content of mem. block
    // then we will obtain garbage values.
    Graph myGraph = malloc(sizeof(GraphObj)); // In terms of verticyOrder an creation, when dynamically being established, the verticyOrder of number of List is started.
    myGraph->verticyOrder = n;                        // Per instructions, always utilize and create the (verticyOrder+1) verticies, not limited and followed by
    myGraph->reference = NIL;                // the maxSize of the array. This is for all the other graph attributes that require being attached to a single vertex.
    myGraph->maxSize = 0;                        // Ergo, each Lists performs as the adjancey list corresponding to each one of the verticies of the graph!
    myGraph->color = (char*)malloc((myGraph->verticyOrder + 1) * sizeof(char));
    myGraph->myNeighbor = malloc((myGraph->verticyOrder + 1) * sizeof(List)); //does the same for List type.
    myGraph->distance = (int*)malloc((myGraph->verticyOrder + 1) * sizeof(int)); //allocates sufficient mem. holding up to:(n+1) times four bytes or (n+1) times thirty-two bits.
    myGraph->parent = (int*)malloc((myGraph->verticyOrder + 1) * sizeof(int));
    /* of int type, get # of parents dynamically increase, usually in 4 bytes*myGraph->verticyOrder+1 is then allocated to mem.*/
    
    
    for(int count = 0; count < (myGraph->verticyOrder+1); count++)
    {
        myGraph->myNeighbor[count] = newList();//Also  dynamically allocates memory for (verticyOrder+1) Lists, newList() derives from my List.c
        myGraph->color[count] = WHITE;
        myGraph->distance[count] = INF; // INF values doent matter per instruction lecture
        myGraph->parent[count] = NIL;   // NIL values doesnt matter per instruction as long as neg num
    }
    return myGraph;
}

void freeGraph(Graph *pN) // modified handout freeNode() from the Queue.c file
{
    int order = (*pN)->verticyOrder;
    Graph myGraph = *pN;
    
    for(int count = 0; count < (order+1); count++)
    {
        freeList(&myGraph->myNeighbor[count]);
        // freeList()
        // frees all heap memory associated with List *pN
        // and sets *pN to NULL
    }
    free(myGraph->myNeighbor);
    free(myGraph->color);
    free(myGraph->distance);
    free(myGraph->parent);
    free(*pN); // free : deallocates the memory previously allocated by an invocation of malloc, aka no memory leaks :)
    
    *pN = NULL;
}
//Access Functions --------------------------------------------------------------------------------------------------------------------------------------------------------------------------
int getOrder(Graph G)
{
    return G->verticyOrder;
}

int getSize(Graph G)
{
    return G->maxSize;
}

int getSource(Graph G)
{
    return G->reference;
}

int getDist(Graph G, int u)
{
    
    return G->distance[u];
}

int getParent(Graph G, int u)
{
    return G->parent[u];
}
// getPath appends to the List myTempList the verticies of a shortest path in G from source u, or appends
// myTempList the value Nil if no path exist
void getPath(List myTempList, Graph G, int u)
{
    int temp_U = u;
    
    if(getSource(G) == NIL) // destination vertex: u
    {
        printf("Invoked getPath(); Referenced invalid sequence of order, referenced shortest path, BFS holds senority.");
    }
    if(u < 1)
    {
        printf("Invoked getPath(); Referenced shortest path, unestablished vertex.\n");
    }
    if(u > G->verticyOrder)
    {
        printf("Invoked getPath(); Referenced shortest path, unestablished vertex.\n");
    }
    if(getDist(G, u) == INF) // doesnt matter here wheter to prepend/append, if nothing exist
    {
        prepend(myTempList, NIL);     // just append/prepend|| Nil indication of no path existing
    }
    if(getDist(G,u) != INF)
    {
        append(myTempList, u);
        moveBack(myTempList);
        
        for(;getParent(G, temp_U) != -1;)
        {
            prepend(myTempList, getParent(G, get(myTempList)));
            
            if(getParent(G,temp_U) != -1)
            {
                movePrev(myTempList);
                temp_U = get(myTempList);
            }
        }
    }
}
// Manipulation Procedures------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
// Function makeNull()
// Per notes: deletes all edges of G, restoring it to its original
// (no edge) state. (This is called a null graph in graph theory literature).
void makeNull(Graph G)
{
    int setSize = (G->verticyOrder-1);
    for(;setSize >= 1;)
    {
        clear(G->myNeighbor[setSize]);
        setSize--;
    }
    G->verticyOrder = G->maxSize = 0;
    G->reference = NIL;
}
// addEdge() inserts a new edge joining u to v, i.e u is added to the adjacency List of v,
// and v to the adjacency List of u.
void addEdge(Graph G, int u, int v)
{
    if(u > getOrder(G) || u < 1) // check pre conditions!
    {
        printf("%s", "Invoked addEdge(); Referenced, undefined vertex see add-on status.\n");
    }
    if(v < 1  || v > getOrder(G))
    {
        printf("%s", "Invoked addEdge(); Referenced, undefined vertex see add-on status.\n");
    }
    //Cn = check nth condition comment, FYI.
    if(length(G->myNeighbor[v]) == 0 && length(G->myNeighbor[u]) == 0) // C3: List[u] & List[v] are empty!
    {
        prepend(G->myNeighbor[u], v);
        prepend(G->myNeighbor[v], u);
        G->maxSize++;
    }
    else if(length(G->myNeighbor[v]) > 0 && length(G->myNeighbor[u]) > 0) // C4: List[u] NOR List[v] are empty
    {
        moveFront(G->myNeighbor[u]); // move the cursor to both the Front of the list aka neighbors of u & v
        moveFront(G->myNeighbor[v]);
        
        if(v < back(G->myNeighbor[u])) // if v < returning the back element of of myNeighbor aka adjacency List
        {
            while(v > get(G->myNeighbor[u])) // else if v is greater, then get the neighbors value which cursor at this point is on front of list aka adjacency list aka neighbor
            {
                moveNext(G->myNeighbor[u]); // move down until condition becomes false n = n + 1 positions away, enabling correct positioning!
            }
            if(v < back(G->myNeighbor[u])) // if v is finally less then the back returning value of neighbor u...
            {
                insertBefore(G->myNeighbor[u], v);    // we already have our pos from n = n+1 above, and thus just insert before to add the edge!
            }
        }
        else                             // else v > G->myNeighbor and juust append the data!
        {
            append(G->myNeighbor[u], v);
        }
        if(u < back(G->myNeighbor[v]))
        {
            while(u > get(G->myNeighbor[v]))
            {
                moveNext(G->myNeighbor[v]);
            }
            if(u < back(G->myNeighbor[v]))
            {
                insertBefore(G->myNeighbor[v], u);
            }
        }
        else
        {
            append(G->myNeighbor[v], u);
        }
        G->maxSize++;
    }
    else if(length(G->myNeighbor[v]) == 0 && length(G->myNeighbor[u]) > 0) // C2: List[v] is empty, however List[u] is not!
    {
        append(G->myNeighbor[v], u);
        moveFront(G->myNeighbor[u]);
        
        if(v < back(G->myNeighbor[u]))
        {
            while(v > get(G->myNeighbor[u]))
            {
                moveNext(G->myNeighbor[u]);
            }
            if(v < back(G->myNeighbor[u]))
            {
                insertBefore(G->myNeighbor[u], v);
            }
        }
        else
        {
            append(G->myNeighbor[u], v);
        }
        G->maxSize++;
    }
    else if(length(G->myNeighbor[u]) == 0 && length(G->myNeighbor[v]) > 0) // C1: List[u] is empty, List[v] not
    {
        append(G->myNeighbor[u], v);
        moveFront(G->myNeighbor[v]);
        
        if(u < back(G->myNeighbor[v]))
        {
            while(u > get(G->myNeighbor[v]))
            {
                moveNext(G->myNeighbor[v]);
            }
            if(u < back(G->myNeighbor[v]))
            {
                insertBefore(G->myNeighbor[v], u);
            }
        }
        else
        {
            append(G->myNeighbor[v], u);
        }
        G->maxSize++;
    }
}
// addArc() inserts new directed edge from u to v, i.e. v is added
// to the adjacency List of u (adjaceny list being our neighbors), but not  to u.
// Both addEdge() and addArc() have the precondition that their two int arguments must lie in the range 1 to getOrder(G).
void addArc(Graph G, int u, int v)
{
    if(u > getOrder(G) || u < 1)
    {
        printf("%s", "Invoked addArc(): Referenced add edge to undefined vertex.\n");
        exit(1);
    }
    if(v > getOrder(G) || v < 1 )
    {
        printf("%s", "Invoked addArc(): Referenced add edge to undefined vertex.\n");
        exit(1);
    }
    else
    {
        moveBack(G->myNeighbor[u]);
        
        if(length(G->myNeighbor[u]) > 0)
        {
            if(v < back(G->myNeighbor[u]))
            {
                while(v > get(G->myNeighbor[u]))
                {
                    moveNext(G->myNeighbor[u]);
                }
                if(v < back(G->myNeighbor[u]))
                {
                    insertBefore(G->myNeighbor[u], v);
                }
                G->maxSize++;
            }
            else
            {
                append(G->myNeighbor[u], v);
                G->maxSize++;
            }
        }
        else
        {
            append(G->myNeighbor[u], v);
            G->maxSize++;
        }
    }
}
/*BFS() implements the breadth first search (BFS) algorithm deriving from class lecture pseudo code
 BFS() does the following : (given) G,s and element of V(G)
 1) "Discovers" every vertex x
 reachable from s. In other words all the vertices in the component containing s
 2) Compute 'delta'(s,x) for every x is an ELement of V(G).
 
 3) Creates a tree T (BFS tree, or Predecessor-subgraph) whose vertex
 set consists of all vertices reachable from s. The unique s-x path in T is a
 a shortest s-x Path in G.
 */
void BFS(Graph G, int s)
{
    int x = 1, k = 0, j = -1;
    for (; x <= getOrder(G); x++) // for all x other then the source vertex
    {
        G->color[x] = WHITE; // initialize the color attribute
        G->distance[x] = INF; // distance to be infinite
        G->parent[x] = NIL; // Parent is Nil, special value meaning nothing
    }
    // below initializes for the source itself
    G->reference= s;
    G->color[s] = GRAY; // source vertex initially gray, thus source is discovered
    G->distance[s] = 0; // distance from the source is 0
    G->parent[s] = NIL; // has no parent,
    List myTempList = newList(); // empty queue constructor
    prepend(myTempList, s); // Enqueue the source vertex, in this case we append/prepend doesnt matter
    // Note
    // going to create an array of those list objects below
    // we also identify vertex by its integer label, integer labels serve as array indicies
    for(; length(myTempList)!= k;) // main loop of bfs, conditional as long as the queue is not empty
    {
        int x = front(myTempList); // deques what ever is at the front, and we will be discovering all vertices that are adjacent to x
        deleteFront(myTempList);   // deleted from
        List outNeighbor = G->myNeighbor[x];
        moveFront(outNeighbor);
        
        // continue until you walk off the list!
        for(; index(outNeighbor) != j;)
        {
            int y = get(outNeighbor); // we do a loop and get the values to iterate over a list
            
            if(G->color[y] == WHITE) // test and see if neighbor has been discovered
            {
                G->color[y] = GRAY; // if white, discover it and color it grey
                G->distance[y] = (G->distance[x]+1); // set the distance n =  n + 1
                G->parent[y] = x; // set its predecesor to be where we are discovering from
                append(myTempList, y); // push that newly discovered vertex onto the back of the queue.
            }
            moveNext(outNeighbor);
        }
        G->color[x] = BLACK; // if marked grey, then it has been discovered with color Black and no more neighbors
    }
    freeList(&myTempList);
}
//Other operations --------------------------------------------------------------------------------------------------------------------------------------------------------------------------
void printGraph(FILE *out, Graph G)
{
    for(int count = 1; count < (getOrder(G)+1);count++)
    {
        fprintf(out, "%d%s", count, " : ");
        printList(out, G->myNeighbor[count]);
        fprintf(out, "%s", "\r\n");
    }
    fprintf(out, "%s", "\r\n");
}






