// Cristian Carrillo Castillo Jr
// CMPS101 Abstract Data Types
// Professor Patrick Tantalo
// Spring 2019 
// PA3 ListTest.java

public class ListTest
{
    public static void main(String[] args)
    {
        List A = new List();
        List B = new List();
        int counter = 0;

        while(counter < 2)
        {

            for(int count = 1; count <= 20; count++)
            {
                A.append(count);
                B.prepend(count);
            }
            System.out.println(A);
            System.out.println(B);

            for(A.moveFront(); A.index() >= 0; A.moveNext())
            {
                System.out.print(A.get()+ " ");
            }
            System.out.println();
            
            for(B.moveBack(); B.index() >= 0; B.movePrev())
            {
                System.out.print(B.get() + " ");
            }
            System.out.println();

            System.out.println(A.equals(B));
            System.out.println(B.equals(A));        

            A.moveFront();
            for(int count1 = 0; count1 < 10; count1++)
            { 
                A.moveNext(); 
            }

            A.insertBefore(-1); 
            for(int count2 = 0; count2 < 9; count2++)
            { 
                A.moveNext(); 
            }

            A.insertAfter(-2);
            for(int count3=0; count3<10; count3++)
            {
                A.movePrev(); 
            }

            A.deleteFront();
            System.out.println(A);
            System.out.println(A.length());        

            A.deleteBack();
            System.out.println(A);
            System.out.println(A.length());

            A.delete();
            System.out.println(A);
            System.out.println(A.length());
            A.clear();
            System.out.println(A.length());

            for(int count4 = 1; count4 < 100; count4++)
            {
                A.append(count4);
                B.prepend(count4);
            }
            System.out.println(A);
            System.out.println(B);

            for(A.moveFront(); A.index() >= 0; A.moveNext())
            {
                System.out.print(A.get()+ " ");
            }
            System.out.println();
            
            for(B.moveBack(); B.index() >= 0; B.movePrev())
            {
                System.out.print(B.get() + " ");
            }
            System.out.println();

            System.out.println(A.equals(B));
            System.out.println(B.equals(A));        

            A.moveFront();
            for(int count5 = 0; count5 < 50; count5++)
            { 
                A.moveNext(); 
            }

            A.insertBefore(-1); 
            for(int count6 = 0; count6 < 8; count6++)
            { 
                A.moveNext(); 
            }

            A.insertAfter(-2);
            for(int count7=0; count7< 50; count7++)
            {
                A.movePrev(); 
            }

            A.deleteFront();
            System.out.println(A);
            System.out.println(A.length());        

            A.deleteBack();
            System.out.println(A);
            System.out.println(A.length());

            A.delete();
            System.out.println(A);
            System.out.println(A.length());
            A.clear();
            System.out.println(A.length());


            for(int count8 = 1; count8 <= 30; count8++)
            {
                A.append(count8);
                B.prepend(count8);
            }
            System.out.println(A);
            System.out.println(B);

            for(A.moveFront(); A.index() >= 0; A.moveNext())
            {
                System.out.print(A.get()+ " ");
            }
            System.out.println();
            
            for(B.moveBack(); B.index() >= 0; B.movePrev())
            {
                System.out.print(B.get() + " ");
            }
            System.out.println();

            System.out.println(A.equals(B));
            System.out.println(B.equals(A));        

            A.moveFront();
            for(int count9 = 0; count9 < 3; count9++)
            { 
                A.moveNext(); 
            }

            A.deleteFront();
            System.out.println(A);
            System.out.println(A.length());        

            A.deleteBack();
            System.out.println(A);
            System.out.println(A.length());

            A.delete();
            System.out.println(A);
            System.out.println(A.length());
            A.clear();
            System.out.println(A.length());

            counter++;
         }
    }
}