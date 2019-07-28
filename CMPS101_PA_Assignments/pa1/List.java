// Cristian Carrillo Castillo Jr
// CMPS101 Abstract Data Types
// Professor Patrick Tantalo
// Spring 2019 
// PA1 List.java
public class List
{   // class node will hold data
    private class Node                  // class Node will be a skeleton for each new node containing 2 pointers
    {
        int data;                       //inner node initialized integer variable data
        Node next;
        Node prev;
        //constructor for ListNode
        public Node()                  
        {
            next = null;                // next and prev will be used for the cursor to traverse
            prev = null;                // through the nodes in the list
        }
        public String toString()        // toString() returns the string itself
        {
            return String.valueOf(data); // returns string representation of int argument
        }
    }
    private Node cursor;                // Node cursor when invoked will be placed on the list,depends on command
    private Node head;                  // head will be assigned to the front of the list
    private Node tail;                  // tail will be assigned to the end of the list
    private int length;                 // length will track the size of our list, and will be incremented/decremented accordingly
    private int index;                  // index will be conceptually used as arr[n], as so.. | 1 | ->  | 2 | ->   | N |   and so on
    //class constructor                 // n = n+1                                           arr[n] <-  arr[n]<- arr[n+1]
    public List() // Creates a new empty list & intialize everything to a default state
    {
            cursor = null;
            head = null;
            tail = null;
            length = 0;
            index = -1;
    }
    // Access functions ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
    // Returns the number of elements in this List.
    int length()
    {
        return length;
    }
    // If cursor is defined, returns the index of the cursor element,
    // otherwise returns -1.
    int index()
    {
        if(cursor != null)
        {
            return index;
        }
        else
        {
            return -1;
        }
    }
    // Returns front element. Pre: length()>0
    public int front()
    {
        if(length() <= 0)
        {
            throw new RuntimeException("Invoked front(); Referenced empty list");
        }
        else if(length() > 0)
        {
            return head.data;       // return the front of the list a[0] -> node 1
        }
        else
        {
            return 0;               // if no list exsist then return nothing
        }
    }
    //Returns back element. Pre: length()>0
    public int back()
    {
        if(length() <= 0) // if length <= 0 throw 
        {
            throw new RuntimeException("Invoked back(); Referenced empty list");
        }
        else if(length() > 0)
        {
            return tail.data;       // return the end of the list a[n+1],last node that is
        }
        else
        {
            return 0;               // else if no list node exsist then return nothing
        }
    }
    // Returns cursor element. Pre: length()>0, index()>=0
    public int get()
    {
        if(length() < 1)
        {
            throw new RuntimeException("Invoked get();Referenced Empty List");
        }
        if(index() < 0)
        {
            throw new RuntimeException("Invoked get();Referenced undefined index");
        }
        else
        {
            return cursor.data; // access the node->data, its data in the node
        }
    }
    // Returns true if and only if this List and L are the same
    // integer sequence. The states of the cursors in the two Lists
    // are not used in determining equality.
     public boolean equals(List L)      // passed List an instiante blank object
     {
        Node listOne = new Node();      //created empty list one
        Node listTwo = new Node();      // created empty list two

        listOne = head;                 //will be assign list one
        listTwo = L.head;               // will be assign list two

        if(length == L.length())        // if length equal, then continue
        {
            for (int count = 0; count < length(); count++)
            {
                if(listOne.data != listTwo.data) // the list sequentially and numerically are not aligned
                {
                    return false;       // then return false
                }
                listOne = listOne.next; //else the list must be equal and we reassign
                listTwo = listTwo.next; // both to continue to tierate through node by node
            }
            return true;                // if both are equal, and data matches return true
        }
        return false;                   // else return false if they are not equal, which means if the numbers dont match
    }
    // Manipulation procedures ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
    void clear()
    {   //resetting List to its initial default state 
        head = null; 
        tail = null;
        length = 0;
        index = -1;
        cursor = null;
    }
    // If List is non-empty, places the cursor under the front element,
    // otherwise does nothing.
    public void moveFront()
    {
        if(length > 0)                  // if we have atleast one node in our list 
        {
            cursor = head;              // then our cursor is now assigned the head
            index = 0;                  // and our index under this position becmoes 0
        }
    }
    // If List is non-empty, places the cursor under the back element,
    // otherwise does nothing.
    public void moveBack()
    {
        if(length > 0)                  // if we hav atleast one node in our list
        {
            cursor = tail;              // then the last node becomes our tail
            index = (length()-1);       // and we have n = n-1 be the size of the list
        }
    }
    // If cursor is defined and not at front, moves cursor one step toward
    // front of this List, if cursor is defined and at front, cursor becomes
    // undefined, if cursor is undefined does nothing.
    public void movePrev()
    {
        if(cursor == head)              // if cursor is at the front of the list
        {
            cursor = null;              // cursor becomes undefined
        }
        else if(cursor == null)         // else if cursor is undefined
        {
            return;                     // return back to the main menu, do nothing
        }
        else
        {
            cursor = cursor.prev;       // continue to go down the list in previous direction
            index--;                    // and decrement our index
        }
    }
    // If cursor is defined and not at back, moves cursor one step toward
    // back of this List, if cursor is defined and at back, cursor becomes
    // undefined, if cursor is undefined does nothing.
    public void moveNext()
    {
        if(cursor == tail)              // if cursor is at the end of the list
        {
            cursor = null;              // cursor when moved next will become undefined
        }
        else if(cursor == null)         // else if cursor is undefined
        {
            return;                     // return back from the list, do nothing
        }
        else
        {
            cursor = cursor.next;       // else, move down 1 node in our list
            index++;                    // and increment our index
        }
    }
    // Insert new element into this List. If List is non-empty,
     // insertion takes place before front element.
    void prepend(int num)
    {
        Node nodePtr = new Node();      // create new node and sticks number in front of list
        nodePtr.data = num;             // assign this new node with the argument passed and access its data to 
        nodePtr.next = null;            //assign "This -> data" = to the num arg , and nodePtr.next is now end of list

        if(head == null)                //if there is no list, which by the head indicates that we essentially have no list
        {
            head = nodePtr;             // head is assigned the new empty nodoe 
            tail = nodePtr;             // since this is the only node in the list, we wil also assign it to be the tail
            nodePtr = null;             // until it is incremented
            length = 1;                 // since we have one node in our list, size will now be 1
        }
        else
        {
            head.prev = nodePtr;        // else, if a node exist, then there are more nodes in our list
            nodePtr.next = head;        // so head.prev is reasign to now nodePtr which indicates that
            head = nodePtr;             // we will be making nodePtr the new leading node in our list,
            length++;                   // or better yet, it becomes our head
                                        // we tie nodePtr.next to head, and head = nodePtr is now the new head
            if(cursor != null)          // in addition we now take into consideration our cusor
            {                           // if cursor is defined, then we just increment our index to match
                index++;                // our positioning for each node
            }
        }
    }
    // Insert new element into this List. If List is non-empty,
    // insertion takes place after back element.
    void append(int num)
    {
        Node nodePtr = new Node();       // create new empty node
        nodePtr.data = num;              // assign this->data = to the argument num passed from append(int num)
        nodePtr.next =null;              // initialize the next node in line to be the end of list

        if(head == null)                 // if our head is not defined, then we have empty list
        {
            head = nodePtr;              //  head and tail will be assigned to th sam node since there
            tail = nodePtr;              // is only one node in the list
            nodePtr = null;
            length = 1;                  // and our length now becomes 1, due to 1 node in the list
        }
        else
        {
            tail.next = nodePtr;         // else if we have atleast one node in the list
            nodePtr.prev = tail;         // we access the end of the list, and the end of list
            tail = nodePtr;              // is now attached to the new empty node containing the Integer
            length++;                    // tail = nodePtr, just means that we reassign the tail, and we 
        }                                // we increment our list size, because we added a node to our list

    }
    // Insert new element before cursor.
    // Pre: length()>0, index()>=0
    void insertBefore(int num)
    {
        if(length() == 0)                // if we have no list, then we cannot insert anything into an empty list
        {
            throw new RuntimeException("Invoked insertBefore(); Referenced empty list");
        }
        else if(index() < 0)             // if we have no index, then this means we have no list 
        {
            throw new RuntimeException("Invoked insertBefore(); Referenced undefined index");
        }
        else if(index() == 0)            // if our index is equal to 0, then a[0] will be prepended
        {                                // meaning an integer inside a node is inserted at the front of the list
            prepend(num);                // the num argument is inserted in front of our existing list
        }
        else
        {
            Node nodePtr = new Node();   // create new empty node
            cursor.prev.next = nodePtr;  // cursor position+1 is now being tied to nodePtr
            nodePtr.prev = cursor.prev;  // the new Node that isnt tied, is now tied to cursor = cursor-1 position
            nodePtr.next = cursor;       // nodePtr.next is being tied (cursor) and linked to list 
            cursor.prev = nodePtr;       // the final tie is connecting cursor prev to nodePtr, and completing
            nodePtr.data = num;          // the insert before, and the the list is connected
            length++;                    // now we assign the arugment integer num to "this->data", and the new node
            index++;                     // that was added to the list will have this new integer, thus the list
         }                               // size grows and so does the index, because one node has been added
    }
    // Inserts new element after cursor.
    // Pre: length()>0, index()>=0
    void insertAfter(int num)
    {
        if(length() == 0)                  // if we have nothing in our list, then we cannot insert anything after!
        {
            throw new RuntimeException("Invoked function insertAfter(); Referenced empty list");
        }
        else if(index() < 0)               // if our index a[0] dne, then evidently there is no list
        {
            throw new RuntimeException("Invoked function insertAfter(); Referenced undefined index");
        }
        else if(index() == (length()-1))   // if a[n=n+1], or last node is equal to the end of our list, then
        {
            append(num);                   // then just append this new integer at the end of list.
        }
        else
        {
            Node nodePtr = new Node();     // create new empty node to be placed in list
            cursor.next.prev = nodePtr;    // so at cursor pos: cursor = cursor -1 (prev node) we will tie to the new empty node
            nodePtr.prev = cursor.next;    // the new empty node will be tied (prev pointer) to position cursor = cursor+1
            nodePtr.next = cursor.next;    // nodePtr will now be linked to cursor = cursor+1 position, next node after essentially 
            cursor.next = nodePtr;         // cursor.next is linked and officially tied to nodePtr, completing linkage
            nodePtr.data = num;            // the integer argument passed is assigned and stored in the new empty node in the list
            length++;                      // and we increment the index and the size becausee we added new node
            index++;
        }
    }
    // Deletes the front element. Pre: length()>0
    public void deleteFront()
    {
        if(length < 1)                      // we cant delete anything if there isnt anything in the list
        {
            throw new RuntimeException("Invoked deleteFront(); Referenced empty List.");
        }
        else if(length() == 1)
        {
            if(cursor == head || cursor == tail)        
            {
                cursor = null;              // if we only have 1 node in our list, then delete it
                index = -1;                 // which means we will have no nodes and our cursor and index becomes undefined and defaulted to initial state; empty
            }
            head = tail = null;             // our head and tail will be reinitailzed to null as well due to the deletion
            length = 0;
        }
            else if(length() > 1)           // if we have more then one node in our list
            {
                if(cursor == head)          // if by chance our cursor is at front of list, or better known as the head
                {
                    cursor = null;          // then when deleted the cursor becomes undefined, and the index is set to -1
                    index = -1;
                }
                if(cursor != head && cursor != null)
                {
                    index = (index-1);      // else if the cursor is not at the head, and the cursor is defined, then mitigate the index due to deletion
                }
                head = head.next;           // our head will now be reassigned
                head.prev = null;           // and since it is a bidirectional queue, we assign the head's previous to null
                length = (length-1);        // and evidently if the list is decremented, then we lost a node and so does the size decrement
            }
    }
    // Deletes the back element. Pre: length()>0
    public void deleteBack() // work on this function
    {
        if(length < 1)                           // if there is nothing in our list, then error
        {
            throw new RuntimeException("Invoked deleteBack(); Referenced empty list");
        }
        else if(length == 1)                     // if our length is 1, meaning one node in our list
        {
            if(cursor == tail || cursor == head) // and that node is either at the tail or the head end
            {
                cursor = null;                   // then when deleted the cursor will become undefined
                index = -1;                      // and our index also becomnes undefined
            }
            head = tail = null;                  // we also reinitialize our head and tail to null
            length = 0;                          // and since there is nothing in our list, we have a length of size 0; 0 nodes!
        }
        if(cursor == tail && length() > 1)       // if the cursor is at the tail, and there is more then one node
        {
            cursor = null;                       // then when deleted the cursor is set to null, undefined!
            index = -1;                          // index also becomes undefined
        }
        else if(length() > 1)                    // else,if the list has more then one node
        {
            tail = tail.prev;                    // tail, will be reassigned to the position node right before the last node
            tail.next = null;                    // once reassignment is complele, the new end of list is reassigned as well
            length = (length-1);                 // since we lost a node after reassignment we decrement the node list
        }
    }
    // Deletes cursor element, making cursor undefined.
    // Pre: length()>0, index()>=0
    void delete()                                
     {
        if(length() == 0)                        // if there is nothing in the list, then we cant delete anything!
        {
            throw new RuntimeException("Invoked delete(); Referenced empty List.\n");
        }
        if(length() == 1)
        {
             head = null;  // list essentially now becomes empty
             tail = null;  // reset list to default empty set state
             length = 0;   // and this is done, because we only essentially have 1 node in our list
             index = -1;   
             cursor = null;
        }
        if(index() == 0 && 0 != (length() - 1)) // if our index is arr[0] position and not at the end then we are at the head
        {       
            head = head.next;                   // and since we are at position arr[0], then we reassign the head to the 
            length = (length-1);                // second node as the new head, and decrement the list size
            cursor = null;                      // and our cursor never moved, and thus becomes undefined
            index = -1;
        }
        else if(index() != 0 && index() != (length - 1)) // if we arent at the beginning of our list Node (arr[0]) and not at the end arr[n+1]
        {
            cursor.next.prev = cursor.prev;              // we are tying cursor position cursor=cursor+1 with cursor pos at cursor= cursor -1
            cursor.prev.next = cursor.next;              // so envision | 1 |-> | 2 | -> | 3 |, and 3 is being tied to 1
            cursor = null;                               // and 1 is now pointing to 3, and thus node | 2 | is now being deleted
            index = -1;                                  // and since the node was deleted, then our index becomes undefined
            length = (length()-1);                       // and evidently the loss of a node, means to decrement the list
        }
        else if(index() == (length()-1) && (length() != 1)) // else if the index is position at a[n+1]; end of list and not at the beginning
        {
            tail = tail.prev;                            // our tail will be reassigned to the second to last node on the list
            tail.next = null;                            // and this node will now cut off. the last node by setting tail.next to the End of list
            length = (length() - 1);                     // losing a node indicates that we decrement the size of the list
            cursor = null;                               // since our cursor was position at the end, and never moved; our cursor becomes undefined with the index
            index = -1;
        }
    }
    // Returns a new List representing the same integer sequence as this
    // List. The cursor in the new list is undefined, regardless of the
    // state of the cursor in this List. This List is unchanged.
    List copy() 
    {
        List list = new List();                          // instiante new empty list

        if(length() > 0)                                 // if we atleast 1 node in our list
        {
            Node nodePtr = head;                         // then we use our nodePtr to start at the head 
            while(nodePtr != null)                       // and while it is not equal to the end of the list
            {
                list.append(nodePtr.data);               // begin to access the member variable list.append(integer argument) sequentially appending each one
                nodePtr = nodePtr.next;                  // once the integer is appended, then move our nodePtr to the next node in the list until end of list is reached
            }
            list.cursor = null;                          // once the iteration is complete, then our cursor becomes undefined
        }
        return list;                                     // and return the copy of our list
    }
    //Helper Functions ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
    //String Function Reader
    public String toString()
    {
        Node nodePtr = head;                             // set our temp nodePtr to the front of the list
        int counter = 1;                                 // have a counter to keep track 
        
        if(length() == 0)                                // if our length is equal to 0 we want to space
        {
            return(" ");
        }
        String reader = nodePtr.toString() + " ";        // read in the string
        nodePtr = nodePtr.next;                          // move down the node once

        while(counter != length())                       // continue to iterate and and read data until length is reached
        {
            reader = reader + nodePtr.toString() + " ";
            nodePtr = nodePtr.next;
            counter++;
        }
        return reader;
    }
    //created to assist in determing if length is 0.
    public boolean isEmpty()
    {
        return length == 0;
    }

}//end of program list