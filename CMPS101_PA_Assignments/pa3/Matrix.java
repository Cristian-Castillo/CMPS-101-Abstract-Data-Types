// Cristian Carrillo Castillo Jr
// CMPS101 Abstract Data Types
// Professor Patrick Tantalo
// Spring 2019 
// PA3 List.java
public class Matrix
{
    List[] listArray; // create an empty arr List by name of listArray; aka array of list!
    int matrixDimension;// Used these fields which has a single instance for the whole class that defines it
    int noneZeroIns; // integer variable will be set by the access mutator for duration class Matrix
     // Entry: private class Entry to store
    private class Entry 
    {
        double value;
        int column;
        //constructor: initialize column,value
        public Entry() 
        {
            this.column = column = 1;
            this.value = value;
        }
        //toString(): overrides Objet's toStri44ng()
        public String toString() 
        {
            return ("(" + column + ", " + value + ")");
        }
    }
//Constructor
//-------------------------------------------------------------------------------------------------------------------------------------------------------------------------
//// Makes a new n x n zero Matrix. pre: n>=1
    public Matrix(int n)
    {
        matrixDimension = n; // we are initializing are matrix to whatever n is here
        int count = 0;
        int noneZeroIns = 0;
        
        if((matrixDimension < 1)) // checking pre condition
        {
            throw new RuntimeException("Invoked Matrix Constructor(); Referenced empty Matricy\n");
        }
        else if(matrixDimension >= 1)                 //else if conditions are met in terms of matricy size execute following code
        {
            listArray = new List[count+(matrixDimension)];// due to the size being greater or equal to 1 we are 
            
            while(matrixDimension > count)           // initialize our size to began @ 1
            {
                listArray[count] = new List();              // created listArray[0,1,.....,n] populated with the List()
                count++;                              // increment counter and check condition, if false break out of loop
            }
        }
    }
// Access functions
//------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------  
// getSize(): Returns n, the number of rows and columns of this Matrix
    int getSize()
    {
        return matrixDimension; 
    }
// getNNZ(): Returns the number of non-zero entries in this matrix
//           added utility function to keep track of non-zero entries-->See Below
    int getNNZ()
    {   
        return noneZeroIns;
    }
    // overrides Object's equals() method
    public boolean equals(Object x)
    {
            Matrix listOne = (Matrix)x;     // automatic type conversion: identifier2 = (type)identifier1
            Matrix listTwo = listOne.copy();
            Entry entryMatrixA = new Entry();
            Entry entryMatrixB = new Entry();
            boolean flag = false;           // flag (similar to sentinel trigger (-1) value concept), however will be used if both lengths while iteraing are equal
            int count = 0;

            if(listOne.getNNZ() != this.getNNZ()) // if ther none zero entries does not match "This" none zero entries in terms of matrcies, then evidently its false
            {
                return flag;
            }
            else if(listOne.getSize() != this.getSize()) // if the size of both are not on par, then evidently the flag remains false
            {
                return flag;
            }
            else if((this.getNNZ() == 0 && listOne.getNNZ() == 0)) //if both noneZero entries are equivalent, or in this case no zero none entries
            {
                if(this.getSize() == listOne.getSize()) // then the size amongst both are equal, and evidently this is true statement, flag is then true
                {
                    return !flag;
                }
            }
            else
            {
                while(count < listOne.getSize())
                {
                    if(this.listArray[count].length() != listOne.listArray[count].length()) //checks both lengths to ensure equivalency
                    {
                         return flag; // if none equivalent then are flag is false and set off
                    }
                    count++; // continue checking each indexing point for both 
                }
                count = 1;  // if we successfully traversed both and both  are determined equivalent at this point

                if(count == 1) // then this will trigger, our equivalency like a light switch, being reset
                {
                    while(count < listOne.getSize()) // while we once again check by iterating through each indicy on both list
                    {
                        listArray[count-1].moveBack(); // we place cursor on the duplicate listArray[An+1]
                        listTwo.listArray[count-1].moveBack(); // we place cursor on the other list Array

                        while(listArray[count-1].index() != -1) //  we continue checking until we reach an undefined state.. Usually the e.o.l.
                        {
                            entryMatrixA = (Entry)(listArray[count-1].get());                                          // copy values into M.A by .get() obj return value 
                            entryMatrixB = (Entry)(listTwo.listArray[count-1].get());                                  // copy values into M.B

                            if(entryMatrixA.value != entryMatrixB.value || entryMatrixA.column != entryMatrixB.column) // If M.A is not equal to M.B's val 
                            {
                                return flag;                                                                           // while traversing both, then return false
                            }
                            listArray[count-1].movePrev(); // continue down our list cursor arr1[An+1,....An1] until we walk off n = n-1
                            listTwo.listArray[count-1].movePrev(); // stated above in our comment for arr2[An+1....An1]
                        }
                        count++;
                    }
                }
                else // else if our count was not triggered, I covered my loose in case by setting the flag as is, false
                {
                    return flag; // meaning that the count was never set off to begin with, due to the 1st while loop being false from else the list teh comparision of listArray & listOne.listArray not being equal
                }
            }
             return !flag; //ele if all condiitons are met, and the flag is yet to be triggered then the flag is true
    }
// Manipulation Procedures ----------------------------------------------------------------------------------------------------------------------------------------------------------------

    // sets this Matrix to the zero state 
    void makeZero()
    {
        for(int count = 0; count < matrixDimension; count++)
        {
            listArray[count].clear();// population An+1 biggest element, essentially enable its default state with clear() for each element An-1.....An1 
        }
        noneZeroIns = 0;  // Reset the NON-EMPTY inputs to zero
    }
    //returns a new Matrix having the same entries as this Matrix 
    public Matrix copy()
    {
        Matrix copyMatricy = new Matrix(getSize());    // listOneMatricy now has correct size initiated
        int count = getSize()-1;                         // get the max size for our count to walk down list
        Entry newEntryTemp;                            // create a new instianted Entry containing 
        
        while(count >= 0)                               // start loop condition at max Size
        {
            listArray[count].moveBack();               // listArray[An+1].moveBack places cursor on back of list
            while(((listArray[count].index()) != -1) && (listArray[count].length() != 0)) //check two conditions, since our cursor is defined and we know length
            {                                                                 /*we continue to iterate from subA[An+1.......An1]           
            // if either the length & (or) index makes condition go false then we stop the duplicate process*/
                newEntryTemp = (Entry)(listArray[count].get());                     // copy the values by using ADT get() into our Temp
                copyMatricy.changeEntry(count+1, newEntryTemp.column, newEntryTemp.value); // pass in A:ith listArray,jth col, # (num) 
                listArray[count].movePrev();                                        // move our cursor one spot down, if we do NOT invoke movePrev() then the Matricy values are incorrect!
            }
            count--;
        }
        return (copyMatricy);
    }   
// end of copy-------------------------------------------------------------------------------------------------------------------------------------------------------------
// changes ith listArray, jth column of this Matrix to x
// pre: 1<=i<=getSize(), 1<=j<=getSize()
    void changeEntry(int i, int j, double x) 
    {
        // our row representation conceptually may be thought of as i  = row[n = n+1]  
        Entry entryTwo = new Entry(); // used for the comparision
        Entry entryOne = new Entry(); //the object to be inserted.
        entryOne.column = j;
        entryOne.value = x;

        if(i > matrixDimension || i < 1)
        {
            throw new RuntimeException("Invoked changeEntry(); Referenced undefined row\n");
        }
        if(j > matrixDimension || j < 1)
        {

            throw new RuntimeException("Invoked changeEntry(); Referenced undefined row\n");
        }

        if(listArray[i-1].length() != 0) // if length exist and is not equal to zero
        {

            listArray[i-1].moveFront(); // place our cursor at the front of the row, i = arbitrary n                                          
            entryTwo = (Entry)(listArray[i - 1].get()); // get the value associated at this index element and store it into entryTwo, will be used as comparision

            while(entryTwo != (Entry)listArray[i - 1].back() && entryTwo.column < entryOne.column) // keep iteratiing while we dont reach the last element in the row &&  jth column from the parameeter must bee
            {                                                                                      // jth col of EntryOne must be greateer then that of entryTwo's col
                listArray[i-1].moveNext();                                                         // if both conditions are true
                entryTwo = (Entry) listArray[i-1].get();                                           // continue to store whatever is in row = listArray[i-1] = some value
            }

            if(entryOne.column > entryTwo.column)                       // if jth col, our oen received from the parameter is greatre then entryTwo's 
            {
                if(entryOne.value != 0)                                 // and no value exist
                {
                    listArray[i-1].append(entryOne);                    // Once again, we have our position. and from the if comparison. Simply invoke insertAfter and the jth column is now the x as stated
                    noneZeroIns++;                                      // incremenent the number of non zero entries
                }                                                       
            }
            else if(entryOne.column < entryTwo.column)                  // if vice versa, 
            {
                if(entryOne.value != 0) 
                {
                    listArray[i-1].insertBefore(entryOne);              // then instead this indicates our column EntryTwo is greater b > a, and thus we insert Entry1 in our row before, and the jth column is now the x
                    noneZeroIns++;
                }
            }
            else                                                        // else if entryOne == EntryTwo                                  
            {
                if(entryOne.value != 0)                                 // if entryOne has a value
                {
                    listArray[i-1].insertAfter(entryOne);               // then at listArray[n = n-1] pos(n)away, or our conceptual row we insert it at n = n+1, and the jth col is now the x
                    listArray[i-1].delete();                            // then at position n, we delete it.. notice the placing n = n+1 for insertAfter! and not for delete
                }
                else if(entryOne.value == 0)                            // else if no value exist
                {
                    listArray[i-1].delete();                          
                    noneZeroIns--;
                }
            }
        }
        else                            // Note**** else logically this means that we have no length, and since there is nothing we can do the following
        {
             if(entryOne.value != 0.0)  // and their exist a value that was received from our paremter
             {
                listArray[i - 1].prepend(entryOne); // then at row[0].append()/prepend doesnt matter nothing exist here so one can just choose whatever
                noneZeroIns++;
             }
        }
    }
//-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
// public Matrix scalarMult(double x):
// returns a new matrix that is the scalar
// product of this matrix with x
/* Note: Functionality will be demonstrated as so:


Ar[ An........................An+1] outer while loop ctrls: listArray [n = start]
                ||                      .
                ||                  listArray[next listArray in iteration]
                \/                      .
                                        .  (read downwards)
                                        .

        ArSub[j.......j+1].  inner loop will traverse the listArray in progress
     
    visual Ar[ index(0), (1), (2), ..... index(n = n+1)] 
    once listArray is cleared then outer while loop is = listArray[n = n+1] (Next Row)

                ||                      .
                ||                  listArray[next listArray in iteration]
                \/
Ar[ An........................An+1] outer while loop listArray[An+1] end

This is the overall concept of how the iteration functions pivots
and exchanges data accordingly! */
    public Matrix scalarMult(double x)
    {
        Matrix matrixA = new Matrix(matrixDimension); // instiante new Matrix
        int count = 0; // matrixA is now size N,

        while(count < matrixDimension) // conditional is checked, if true continue
        {
            listArray[count].moveFront(); // place cursor on front of listArray 1: 

                /*                                   0                */
                /*                                  /|\               */
                /* While the cursor does not walk    |\_ _ _ >,does not 
                fall off the end continue to traverse through THIS particular
                listArray in this case */
                while(listArray[count].index() != -1)  
                {  
                    //Store the value into newEntry by list ADT get() 
                    // => returns index element, newEntry = #
                    // line: matrixA.changeEntry.... ith listArray, jth col, double #
                    Entry newEntry = (Entry) listArray[count].get(); 
                    double scalarMult = x * newEntry.value; // Scale
                    matrixA.changeEntry(count+1, newEntry.column, scalarMult); 
                    listArray[count].moveNext(); // take a step forward
                }
            count++;
        } // End of while
        if(count != 0) // If true, 1 more listArray to traverse
        {
            count = 0; // lets restart our ccount

            listArray[count].moveBack(); // place cursor at back of list

            while(listArray[count].index() != -1) // walk until you fall off
            {
                Entry newEntry = (Entry) listArray[count].get(); 
                double scalarMult = x * newEntry.value; // same concept above
                matrixA.changeEntry(count+1, newEntry.column, scalarMult);
                listArray[count].movePrev(); // n = n -1 in our cursor positioning
                count++;
            } // End of while
        }
        return matrixA;
    }
// Matrix add(Matrix M)
// returns a new Matrix that is the sum of this Matrix with M
// pre: getSize()==M.getSize()
    public Matrix add(Matrix M)                             // see sub for comments, similar excluding the scaling of -1
    {
        Matrix Add = new Matrix(matrixDimension);
        boolean flag = true;
        int count = 0;


        if(this.getSize() != M.getSize())
        {
            throw new RuntimeException(
                  "Invoked function Matrix add(Matrix M): Referenced unequivalent matricies. Addition not supported.\n");
        }
        else if(flag == equals(M))
        {
            return scalarMult(2.0);
        }

       while(count < matrixDimension)
       {

           if(listArray[count].length() != 0 && M.listArray[count].length() != 0)
           {
               // addUtilityOut represents new updated list of A+B
               List addUtilityOut = addUtility(listArray[count], M.listArray[count]);
               addUtilityOut.moveBack(); // updated list with correct values, but here we place the cursor on the back of the new updated list 
               Entry listOne = new Entry(); // create a new l1

               for(int j = 0; j < addUtilityOut.length(); j++)
               {
                    listOne = (Entry)addUtilityOut.get(); // store the new values of the updated list received from addUtility
                    Add.changeEntry(count + 1, listOne.column, listOne.value); // simply make the changes for ith,jth,updated val of A+B
                    addUtilityOut.movePrev(); // move down n = n-1 positions from the list
               }
           } // l1 not equl to 0, but l2 is in length
           if(listArray[count].length() != 0 && M.listArray[count].length() == 0) // now if l1 is not empty, but the other list is
           {
               Entry listOne = new Entry(); // instiante empty list
               listArray[count].moveBack(); // move to the end of it by placing the cursor in the back

               while(listArray[count].index() != -1) // and while we dont walk off the list
               {
                    listOne = (Entry)listArray[count].get(); //store the lis entrys from "THIS" list into our instianted Entry col,val
                    Add.changeEntry(count + 1, listOne.column, listOne.value); // make adjustments to ith,l1.col, l1.val
                    listArray[count].movePrev();
               }
           }
           if(listArray[count].length() == 0) //  else ifl1 is empty, but
           {    
                if(M.listArray[count].length() != 0) // our other list is
                {
                   Entry listOne = new Entry();
                   M.listArray[count].moveBack();

                   while(M.listArray[count].index() != -1) // repeat the same process
                   {
                       listOne = (Entry)M.listArray[count].get();
                       Add.changeEntry(count + 1, listOne.column, listOne.value);
                       M.listArray[count].movePrev();
                   }
               }
           }
           count++;
       }
        return(Add);
    }

public Matrix sub(Matrix M)
{
        Matrix subMatrix = new Matrix(matrixDimension);

        if(this.getSize() != M.getSize())
        {
            throw new RuntimeException("Invoked Matrix Sub(Matrix M); Referenced uniequivalent matricies\n");
        }
        else if(M == this)
        {
            return(add(scalarMult(-1)));
        }

        for(int x = 0; x < matrixDimension; x++) // while we traverse both list, until the end 
        {

            if(listArray[x].length() != 0 && M.listArray[x].length() != 0)// Checking to see if there are two actually list in existence, else no list (both conditions must hold true)
            {               
                List subUtilityOut = subUtility(listArray[x], M.listArray[x]);   // thus if both list exisit pass to helper function see comments @ bottom for details
                subUtilityOut.moveBack();                                        // place Cursor on back of the updated list that was returned from helper function
                Entry listOne = new Entry();
               
                for(int j = 0; j < subUtilityOut.length(); j++)                  // from j = 0 to n
                {
                    listOne = (Entry)subUtilityOut.get();                        // listOne know holds the data that was updated from our utility function
                    subMatrix.changeEntry(x + 1, listOne.column, listOne.value); // place these new changes accordingly by ith row (x+1), jth col, and value
                    subUtilityOut.movePrev();                                    // move down n = n-1 posiitons
                }
            }
            if(listArray[x].length() != 0 && M.listArray[x].length() == 0)  // else if (A > B) == (# != 0 && #==0 ) logically  in this case while our original list has a value and the other M.listArray, 2nd list doesnt have a list
            {
                Entry listOne = new Entry();                                      
                listArray[x].moveBack();                                           //we take our original list and place the cursor on the back of our list
                while(listArray[x].index() != -1)                                  // while we walk toward the front of our list, or until undefined better yet!
                {

                    listOne = (Entry)listArray[x].get();                           // load up the values into listOne
                    subMatrix.changeEntry(x + 1, listOne.column, listOne.value);   // make the row,jth,value adjustments by passing in the data
                    listArray[x].movePrev();                                       // n = n-1 steps foward to A[1], which then our index becomes undefined!
                }       
            }
            if(listArray[x].length() == 0 && M.listArray[x].length() != 0)    // else do the oppoosite of the above stated, but keep moving from right to left on our list or
            {
                Entry listOne = new Entry();
                M.listArray[x].moveBack();
                double inputEntry = 0.0;

                while(M.listArray[x].index() != -1)
                {
                    listOne = (Entry)M.listArray[x].get();
                    inputEntry = (listOne.value * -1.0);
                    subMatrix.changeEntry((x + 1), listOne.column, inputEntry);
                    M.listArray[x].movePrev();
                }
            }
        }
        return(subMatrix);
    }
    // transpose:returns a new Matrix that is the transpose of this Matrix
public Matrix transpose()
{
    Matrix matrixA = new Matrix(matrixDimension); // instiante new MatrixA with size
    int count = 0;

    while(count < matrixDimension) // test condition against non zero entries (Size) that is!
    {
        listArray[count].moveBack(); // place cursor on the back of the list

        for(int i = 0; i < listArray[count].length(); i++)
        {                                                    // newEntry storing values from invoking get(), which gets the elements from listArray[n]
            Entry newEntry = (Entry) listArray[count].get(); // automatic type conversion: identifier2 = (type)identifier1

            if(newEntry.value != 0.0) //if there is a value that exisit
            {   
                matrixA.changeEntry(newEntry.column,count+1,newEntry.value); // change entry (jth col, the iteration count, and the value associated)
            }
            listArray[count].movePrev(); // proceed n = n-1 down our list
        }
       count++; // increment count, until condition is falsee
    }
    return (matrixA); // return the moded changed matrixA, altered by changeEntry
}
//mult(): The returning matrix is "this = > " matrix, with M's matricy
    Matrix mult(Matrix M)
    {
        Matrix newMultMatrix = new Matrix(matrixDimension); // instiante new matricy of size n

        for(int i = 0; i < matrixDimension; i++)            // from 1 to n
        {
            int accumulator = 1;                              // we will check the condition starting from the front
                                                              // if "THIS" listArr[max]  && the transpose are both valid in terms of length , or exist during the process
            while((accumulator-1) < matrixDimension)
            {
                if(M.transpose().listArray[i].length() != 0)
                {
                    if(listArray[accumulator-1].length() != 0)
                    {                                                                                  // Entry will contain the dot("THIS" list array element index,and "THIS" listArray tranpose
                        double entryInput = dot(listArray[accumulator-1], M.transpose().listArray[i]); // if when dotted, the value is not zero
                        
                        if(entryInput != 0)
                        {
                            newMultMatrix.changeEntry(accumulator,i+1,entryInput);                  //invoke the instianted matricy from above, and change the entries accordingly with ith count, jth listArray, and the dotted result
                        }
                    }
                }
                accumulator++;
            }
        }
        return newMultMatrix; // return the result of the new instianted from the beginning of the func., but with updated changes if applicable
    }
// Helper Functions (Utility) & Miscellanous:
// ---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
/* dot(): Utility function that will return dot product of a matricy */
    private static double dot(List P, List Q)
    {
        P.moveBack();           // Place cursor on back of list P
        Q.moveBack();           // Place cursor on back of List Q
        double accumulator = 0; // accumulator will hold sum when columns are equivalent

        if(P.index() != -1 || Q.index() != -1)   // If either (or) is not empty
        {
            while(P.index() != -1 && Q.index() != -1) // then we will iterate through both list
            {
                Entry newEntryA = (Entry) P.get(); // until you reach the undefine state invoking
                Entry newEntryB = (Entry) Q.get(); // get(), to store values into newEntry

                if(newEntryA.column == newEntryB.column) // if both col's are equal
                {
                    Q.movePrev(); // adjacently move both 
                    P.movePrev(); // cursors n = n-1 on our list
                    accumulator = accumulator + ((newEntryA.value)*(newEntryB.value)); // The accumulator will store the product of the two values
                }
                else if((newEntryA.column > newEntryB.column) && (Q.index() != -1))
                {
                    P.movePrev(); // if Col A. is greater then Col B. the move only the cursor on list P n = n-1
                }
                else
                {
                    if((newEntryA.column < newEntryB.column) && (P.index() != -1))
                    {
                        Q.movePrev(); // Else, Col B.'s cursor is moved, and not P's
                    }
                }
            }
        }
        return (accumulator); // return the accumulator value containing the product of the two values newEntryA/B
    }
// Helper function for Add
// toString()
// overrides Object's toString() method
    public String toString(){

        String stringBuff = "";

        for(int count = 0; count < matrixDimension; count++)
        {

            if(listArray[count].length() != 0){

                stringBuff = stringBuff + ((count+1) + ":" + listArray[count] + "\n");
            }
        }

        return(stringBuff);
    }

    private List addUtility(List P,List Q)
    {
        // Functionality
        /*
                P =  | | | | | | | | | 
                Q =  | | | | | | | | |

                addOutput =  | | | | | | | (new updated list from P & Q) that gets everything concanted @ end, 
                but is updated with the new data accordingly


                while P or Q, doesnt matter as long as one of these list are not empty we will do the following
                if P.index() != -1 or P.index() != -1, we presume by getting the values in P and Q
                On line 565, if P has values and is not empty, but Q is. we get P which is List One and
                append it to our new Empty list called AddOutput.

                else vice versa for P.index() == -1, but Q is not. Also note as these conditions are set off.
                We get the next value by placing our cursor n = n+1 positions away!


                for line 580, if w have the L.1 col == L.2 col
                
                1)
                Then we create a new entry that willl hold listOne's column and the sum
                of A+B, and we check our condiiton that if the new Entry is not equal to 0 on line 592,
                then we proceed to append it to our new List aka addOutput and move both cursors down both the list in P & Q
                
                2)
                if P.col < Q.col and both list are not at end, then append P's data to the new list aka
                addOutput, else do Q
        
        */
       List addOutput = new List(); // create a new list
       P.moveFront();               // listArray[count] is P 
       Q.moveFront();               // M.listArray[count] is the object that was passed from the parameter argument

       Entry listOne = (Entry)P.get();      // load up the first value in front of L1
       Entry listTwo = (Entry)Q.get();      // load up the 2nd value in front of L2 by invoking cursor return of index element

        while(P.index() != -1 || Q.index() != -1)   // while either or of our list have not walked off the end of the list
            {
                if(P.index() != -1)                 // if list one has not walked off our list
                {
                    listOne = (Entry) P.get();      // store the element, and continue doing so until we have reached the end of our original list
                }
                if(Q.index() != -1)           // replicate the same process with list 2
                {
                    listTwo = (Entry) Q.get();
                }
                if(P.index() != -1 && Q.index() == -1) // now if my first list is not at the end, but my second list is M.listArray[count] that is!
                {
                    addOutput.append(listOne);              // we append to our List a.(1st list tacking it on)
                    P.moveNext();                           // move n = n+1 until this condition in ? becomes false, or no longer valid
                }
                if(P.index() == -1 && Q.index() != -1) // else, if its the other way around, then do the same process but with M.ListArray[count]
                {
                    addOutput.append(listTwo);
                    Q.moveNext();
                }
                if(listOne.column == listTwo.column)    // now if both of them are equal column wise
                {
                    Entry newEntryC = new Entry();            // create a new entry
                    newEntryC.column = listOne.column;        // store into new Entry the original arr[ (this being listArray)]'s column
                    newEntryC.value = listOne.value + listTwo.value;    // add listOne+listTwo's values pertaining to this pos and store it into our new entry

                    if(newEntryC.value != 0.0) 
                    {
                        addOutput.append(newEntryC);            // we continue to store our values that were sumed
                    }
                    P.moveNext();                               // and we traverse both our list L 1 n = n + 1
                    Q.moveNext();                               // and we traverse both our list L 2 n = n + 1
                }
                if(listOne.column < listTwo.column && P.index() != -1 && Q.index() != -1) // if listOne's col is smaller then list 2
                {                                                                              // and our 1st (original) & M.listArray list has not reached the end
                    addOutput.append(listOne);                                                 // take the smaller list col output
                    P.moveNext();                                                              //  and append it to back * [list addOutput]-[append list one] visual *
                }
                if(listTwo.column < listOne.column && P.index() != -1 && Q.index() != -1)
                {
                    addOutput.append(listTwo);                                                 // else you take column's two and append this to the back of list AddOutput
                    Q.moveNext();                                                              // continue to do so until you hve walked off the list.
                }
            }
            return addOutput; // end of AddOutput                               
    }
    //Helper function for sub -- > Sub similar concept as add, but we scale by -1 to do the following visual...
            /*                                      
                listOne.moveFront()                     
                
                             ||  
                             \/
                             ___________________________________
             P:   listone:  | [a1]->[a2], .......,->[an]-> NULL |      Note* doubly linked list (2x) , 
                            |     <-              <-            | 
                             -----------------------------------

                listTwo.moveFront();
                listTwo.moveNext();

                                   ||  
                                   \/
                              ___________________________________
            Q:      listTwo: | [b1]<-[b2], .......,->[bn]-> NULL |            
                             |     ->              <-            |
                              -----------------------------------


                     e.g Matrix is in a1 of listOne:

                         | b1 b2 |    
                     Ck1*| b3 b4 |  
                         | b5 b6 |     

            Matricies:      [b1]
            CK1 be the scalar, but for my example the 2 matrices below CK1 = -1*value


                        matricA.       .matricyB  
                    
                         | a1 a2 |       | b1 b2 |
                         | a3 a4 |  - CK1| b3 b4 |
                         | a5 a6 |       | b5 b6 |
            Matricies:     "This"           M
                
                

                    ||
                    ||
                    ||
                    ||
                   VVVVV
                    VVV
                     v

        */
    private List subUtility(List P, List Q)
    {

       List subOutput = new List();
       P.moveFront();
       Q.moveFront();

       Entry listOne = (Entry)P.get();
       Entry listTwo = (Entry)Q.get();

         while(P.index() != -1 || Q.index() != -1)
         {

                if(P.index() != -1) 
                {
                    listOne = (Entry) P.get(); // gets the values of listArray[count] while we dont reach the end
                }
                if(Q.index() != -1) 
                {
                    listTwo = (Entry) Q.get(); // gets the entries of M.listArray[count]  while it doesnt reach the end
                }
                if(P.index() != -1 && Q.index() == -1) // if there are still numbers in P, and Q is at the end
                {
                    subOutput.append(listOne);     // with our new List aka sub - append it [sub]-[appended P]- > End of list
                    P.moveNext();                  // keep momving down the list
                }
                if(P.index() == -1 && Q.index() != -1) // while we dont reeach the end of both are list
                {
                    Entry inputEntry = new Entry();     // instinte a new Entry      
                    inputEntry.column = listTwo.column; // store the instianted entry with list's two's column numbers
                    inputEntry.value = (-1.0 * listTwo.value); // scale the value by ck1 = -1, and scale it with list twos value as shown in the above matricies visual
                    subOutput.append(inputEntry);              // append the result to our List that was initialized at beginning 
                    Q.moveNext();
                }
                if(listOne.column == listTwo.column)
                {

                    Entry newEntryC = new Entry();
                    newEntryC.column = listOne.column;
                    newEntryC.value = listOne.value + (-1.0 * listTwo.value);

                    if(newEntryC.value != 0.0) 
                    {
                        subOutput.append(newEntryC);
                    }
                    P.moveNext();
                    Q.moveNext();
                }
                if(listOne.column < listTwo.column && P.index() != -1 && Q.index() != -1)
                {
                    subOutput.append(listOne);
                    P.moveNext();
                }
                if(listTwo.column < listOne.column && P.index() != -1 && Q.index() != -1)
                {
                    Entry inputEntry = new Entry();
                    inputEntry.column = listTwo.column;
                    inputEntry.value = (-1.0 * listTwo.value);
                    subOutput.append(inputEntry);
                    Q.moveNext();
                }
            }
        return subOutput;
    }

}