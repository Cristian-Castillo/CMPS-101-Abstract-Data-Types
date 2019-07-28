
/*
 Course: CMPS 101 Abstract Data Types
 Professor: Patrick Tantalo
 Assignment: PA2
 Date: April 17, 2019
 Name: Cristian C. Castillo Jr
 Cruz ID: ccarri11
 Student ID: #1576341
 */
//Header File
#ifndef _LIST_H_INCLUDE_
#define _LIST_H_INCLUDE_
//Exported type
typedef struct ListObj* List;
//Constructor-Destructor
//---------------------------
//newList()
//Returns ref to new empty List object,
List newList(void);
//freeList()
//Frees all heap memory associate with List
//*pQ, and set *pQ to Null
void freeList(List* pQ);
//Access functions
//------------------------------------
//returns the nunmber of elements in this list
int length(List Q);
//returns index of the list element
int index(List Q);
//Front()
// Returns the value at the front of Q
// Pre: !isEmpty(Q) length must be greater then 0
int front(List Q);
//Returns the value at the back of Q.
//Precondtion length must be greater then 0
int back(List Q);
//returns cursor element
int get(List Q);
// equals()
// returns true (1) if A is identical to B, false (0) otherwise
int equals(List A, List B);
// Manipulation procedures ----------------------------------------------------
// Resets this List to its original empty state.
void clear(List L);
//moves cursor to the front of the list
// If List is non-empty, places the cursor under the front element,
// otherwise does nothing.
void moveFront(List Q);
// If List is non-empty, places the cursor under the back element,
// otherwise does nothing.
//moves cursor to the back of the list
void moveBack(List Q);
// If cursor is defined and not at front, moves cursor one step toward 
// front of this List, if cursor is defined and at front, cursor becomes
void movePrev(List Q);
//moves cursor forward by one on the list
void moveNext(List Q);
void prepend(List Q,int data); // attaches integers to front of list; head
void append(List Q, int data); // appends (attaches) integers to tail
///insert before cursor element
void insertBefore(List Q,int data);
//insert after cursor element
void insertAfter(List Q,int data);
//copies list
List copyList(List Q);
//delets front element
void deleteFront(List Q);
// delets. back element
void deleteBack(List Q);
// deletes current elmeent
void delete(List Q);
// Other operations-----------------------------------------------------------
//. prints list
void printList(FILE* out,List L);
// Returns true (1) if Q is empty, otherwise returns false (0)
int isEmpty(List Q);
#endif

