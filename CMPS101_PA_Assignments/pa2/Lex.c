/*
 Course: CMPS 101 Abstract Data Types
 Professor: Patrick Tantalo
 Assignment: PA2
 Date: April 17, 2019
 Name: Cristian C. Castillo Jr
 Cruz ID: ccarri11
 Student ID: #1576341
 */
#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include"List.h"

#define MAX_LEN 255 //Simliar to const, but allws value to be used throughout code usage

int main(int argc, char * argv[])
{
 	int lineAccumulator = 0;
    FILE *in;
    FILE *out;
    char str[MAX_LEN]; // created char array of Max_Len of 255
    char** arrayLine; // pointer to a pointer (indirection)

    if(argc != 3) // if 2 args are not present then display error message
    {
    	printf("Usage: %s <input file> <output file>\n", argv[0]);
        exit(1);
    }
    in = fopen(argv[1], "r"); //opening file in to read; opens an existing file for reading
    out = fopen(argv[2], "w"); //opeing file out.txt file to write, if it doesnt exist then a new file is created
    
    if (in == NULL || out == NULL) //If either one is undefined then prompt error
    {
        printf("File inaccessible cannot read file %s\n", argv[1]);
        exit(1);
    }
    //fgets(char *str, int length, FILE* stream)
    while(fgets(str, MAX_LEN,in) != NULL)
    {
        lineAccumulator++; // continue to count the lines
    }
    fseek(in,0,SEEK_SET); //used to move file pointer associated with a given file to a specific pos. The SEEK_Set denots the starting of the file!
    arrayLine = ((int*)(malloc((lineAccumulator)*sizeof(char*)))); // allocate requested memory and casted as int
    
    for(int j = 0; j < lineAccumulator; j++)
    {
        *(arrayLine+j) = ((int*)(malloc((MAX_LEN)*sizeof(char)))); // assigning arrayLine allocated memory requested
    }
    int lineAccumulatorTwo;

    while(fgets(str,(int*)sizeof(str),in) != NULL) //while we havent reached the End of List
    {
        strcpy(*(arrayLine+lineAccumulatorTwo),str); //copying from source str (2nd arg), into arrayLine (1st arg)
        lineAccumulatorTwo++;
    }

    List start = newList(); // Same implementation as in PA 1
    prepend(start,0);
    // (create list object->current state;
    // empty state, also this will be our
    // initialization of outer for loop
    // this is insert 0 and we may use
    // prepend/append, it doesnt matter!
    // Now we insert, but how?
    
    /* We start by placing cursor at beginning, so moveFront(). Then we ask??? Does 1 belong to the left of the 0. in other words what we are trying to depict is the following:
     
     0    1    2    3    <- indicies; concept applied
     A = ("c"  "a"  "b"  "d")
     
     We ask does "a" belong to the left of "c"! Yes!! So we use compareTo which is a function compares strings lexicographically.
     
     /* insertion sort implementation Pseudo
     
     1) for j = 2 to n
     2) temp = A[j]
     3) i = j-1                      will ctrl the while and help with the walk!
     4) while i > 0 and temp < Ai    barometer aka basic step
     5)     Ai+1  = A[i]             pull "file" forward, and insert
     6)     i = i-1                  in sorted list. Get next file i = i-1
     7) A[i+1] = temp                else if while condition is false
     the integer in question is already sorted
     temp
     A1.......Aj-1  Aj  Aj+1........An
     [sorted]               [unsorted, like cards on a table waiting to be sorted]
     */
    // Modified Insortion Sort

    for(int j = length(start); j < lineAccumulator;j++) // outer for loop with start
    {
    	moveFront(start); // place cusor in front of list!
    	char *tempStr = *(arrayLine + j);  // store integers into char array
    	int i = j-1;   // walk down the unsorted list until you fall off

    	while((i >= 0) && (0 < (strcmp(tempStr,*(arrayLine+get(start))))))
    	{
    		moveNext(start);//for above i >= 0 if i < 0 then you have walked off the list,else start comparing
    		i = i-1;// continue to get new file
    	}
    	if(0 <= index(start))
    	{
    		insertBefore(start,j); // if we found position insertBefore using list ADT
    	}
    	else
    	{
    		append(start,j);// The idea is if cursor becomes undefined by moveNext() then we have walked off
            // the "Cliff", but in reality cursor becomes undefined, which is an indication that
    	}// you to append();
    }
    //       0    1    2    3    <- indicies; concept applied
    // A = ("c"  "a"  "b"  "d")    E.G. of whats happening
    
    //                 _
    // L = ( 1    2    0)         after moveFront(), and invoking append()
    // L = ( 1    2    0    3)
    // L = ("a"  "b"  "c"  "d")
    moveFront(start);//places cursor back in front
  
    for(int j = index(start); j < lineAccumulator;j++)
    {
    	  fprintf(out, "%s", *(arrayLine+get(start)));//returns list cursor element
    	  moveNext(start); // walk down until you have fallen off the list!
    }

    fclose(in); // close the input file!
    fclose(out);// close the output file!
  
    freeList(&start); //deallocate the memory which was called at beginning of program->List
    for (int j = 0; j < lineAccumulator; j++)
    {
        free(*(arrayLine+j));
    } 
    return 0;
}
