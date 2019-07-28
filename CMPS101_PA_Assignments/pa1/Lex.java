// Cristian Carrillo Castillo Jr
// CMPS101 Abstract Data Types
// Professor Patrick Tantalo
// Spring 2019 
// PA1 Lex.java
import java.io.*;
import java.util.*;

public class Lex
{
    public static void main(String[] args) throws IOException
    {
        int lineAccumulator = 0; // will count the number of lines
        Scanner in = null;
        PrintWriter out = null;
      
        if (args.length != 2) // check if args is 2, else we will
        {
            System.err.println("Invoked System Error(); Reference arguments\n");
            System.exit(1); // exit out of system 
        } 
        try // lets try to read the file
        {   // also provided by instructor in handouts
            in = new Scanner(new File(args[0])); // store args[0], which is first argument, which is are input!
            out = new PrintWriter(new FileWriter(args[1])); // store 2nd argument in args[0], which is are output
        }
        catch (IOException e)
        {
            e.printStackTrace(); //Helps to determine and understand where the actual probelm occurred. Prints about 7 lines
        }                        //with throwable sub-class & pkg Info. From line 2 and onwards, describes error position/line # beginning with at!
        // while you read, the accumulator is used to count the number of lines!
        while (in.hasNextLine()) // also provided by instructor from handout
        {
            lineAccumulator++;
            in.nextLine();
        }
        in.close(); // closing file!
      
        try      //Else,we reopen the file
        {
            in = new Scanner(new File(args[0])); // store the first argument in
        }
        catch (IOException e)
        {
            e.printStackTrace(); // Read above to see concept, already stated!
        }
    
        String arrayLine[] = new String[lineAccumulator];//We will begin by storing into an array
        for (int j = 0; j < lineAccumulator; j++) // using nextLine method which advances the scanner past the current line and returs the input that was skipped. Basically looking for a line separator 
        {                                            
              arrayLine[j] = in.nextLine(); // store these integers into array container
        }
        /* How do you insert array indicies into a list? Answer-> In away that indirectly sorts the list. From Tantalo's lecture you do not sort the array of strings, but instead you do it indirectly using your List ADT. So what does one want? We want a List of ints, and these ints pertain to the indicies a[0], a[1],...... a[n].

        Things to Note:
        * I will use insertion @ points they belong, and how do we find it... 
          We find them by doing something that is equivalent to a single step in insertion sort.

        * Recall, insertion sort will have an outer loop, and on each iteration
          of outer for-loop it expands the unsorted section by taking an element in unsorted section & walks through the sorted section and finds the position it belongs and inserts it there
                   
                        temp
          A1.......Aj-1  Aj  Aj+1........An 
        * We will use insertBefore,InsertAfter, moveFront/movePrev 
        * The walk can be conducted left->right, or right<-left
        * NOTE README: This Lex is a step by step guide given precisely by
                       professor Tantalo from lecture is followed precisely.

        */            
          List start = new List(); // (create list object->current state; 
          start.prepend(0);        // empty state, also this will be our 
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
          for(int j = start.length(); j < arrayLine.length;j++) // outer for loop with start
          {
            start.moveFront();                                  // place cusor in front of list!
            String tempStr = arrayLine[j];                      // store integers into string 
            int i = j-1;                                        // walk down the unsorted list until you fall off                       
            while((i >= 0) && ((0 < tempStr.compareTo(arrayLine[start.get()])))) //compareTo determines lexigraphic and here we are returning list element 
            {
              start.moveNext(); //for above i >= 0 if i < 0 then you have walked off the list,else start comparing
              i = i-1; // continue to get new file
            }
            if(0 <= start.index())
            {
              start.insertBefore(j); // if we found position insertBefore using list ADT
            }
            else
            {
              start.append(j); // The idea is if cursor becomes undefined by moveNext() then we have walked off
            }                  // the "Cliff", but in reality cursor becomes undefined, which is an indication that
          }                    // you to append();
                               // 
          //       0    1    2    3    <- indicies; concept applied
          // A = ("c"  "a"  "b"  "d")    E.G. of whats happening
          
          //                 _
          // L = ( 1    2    0)         after moveFront(), and invoking append()
          // L = ( 1    2    0    3)  
          // L = ("a"  "b"  "c"  "d")               
          start.moveFront(); //places cursor back in front

          for(int j = start.index(); j < arrayLine.length;j++)
          {
               out.println(arrayLine[start.get()]); //returns list cursor element
               start.moveNext(); // walk down until you have fallen off the list!
          }
          in.close(); // close the input file!
          out.close(); // close the output file!
    }
}

