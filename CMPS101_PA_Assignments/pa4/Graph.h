/*
 Course: CMPS 101 Abstract Data Types
 Professor: Patrick Tantalo
 Assignment: PA4
 Note: Modded PA2
 Date: April 17, 2019
 Name: Cristian C. Castillo Jr
 Cruz ID: ccarri11
 Student ID: #1576341
 */
#include "List.h"
#ifndef Graph_H
#define Graph_H
#define INF -2
#define NIL -1
/*** Transfered to Graph.c ***/
/*****************************/
typedef struct GraphObj *Graph; 
/*** Constructors-Destructors ***/
Graph newGraph(int n);
void freeGraph(Graph *pG);
/*** Access functions ***/
/************************/
int getOrder(Graph G);
int getSize(Graph G);
int getSource(Graph G);
int getParent(Graph G, int u);
int getDist(Graph G, int u);
void getPath(List L, Graph G, int u);
/*** Manipulation procedures ***/
/*******************************/
void makeNull(Graph G);
void addEdge(Graph G, int u, int v);
void addArc(Graph G, int u, int v);
void BFS(Graph G, int s);
/*** Other operations ***/
/************************/
void printGraph(FILE *out, Graph G);
#endif // End of include guard
