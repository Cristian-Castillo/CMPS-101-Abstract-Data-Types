// Cristian Carrillo Castillo Jr
// CMPS101 Abstract Data Types
// Professor Patrick Tantalo
// Spring 2019 
// PA3 ListTest.java
import java.util.*;
import java.lang.*;
import java.io.*;
import java.util.regex.Pattern; // used for token
import java.util.StringTokenizer;

@SuppressWarnings("overrides") //for the terminal to oveerride

public class Sparse
{
     public void setMatricy(Scanner in,PrintWriter out)
    {
        int size = 0;
        int matricyA = 0;
        int ithA = 0; //        row A
        int jthA = 0; //        col B
        int ithB = 0; //         .
        int jthB = 0; //         .
        int matricyB = 0; //     .
        double valueOfA = 0.0; // values Of Matricy
        double valueOfB = 0.0;
        
        Matrix A = null;
        Matrix B = null;
        String space = " ";
        // StringTokenizer tokenizer = new StringTokenizer(space);
        Scanner checkScan = new Scanner(space);
        boolean flag = false;
        int count = 1, i = 0, j = 0;
        
        /*  nextInt()
         used nextInt()/nextDouble which reads objects of string of digits (ch) and converts them to ints/double. Scanner reads them
         one by one, then converted toa 32 bit numeric val.
         
         System.out     int num     Scanner
         stream of charaters  -   println()       12         nextInt()  <  -- 1 -- 2
         Stream
         */
        
        while(in.hasNextInt()) // provided by instructor from handout
        {
            size = in.nextInt();
            matricyA = in.nextInt();
            matricyB = in.nextInt();
            A = new Matrix(size);
            B = new Matrix(size);
            
            if((checkScan.hasNext("  ") == true) && (A.equals(A) == A.equals(B)))
            {
                count++;
                if((count == 2) && (in.hasNextInt() == !flag))
                {
                    flag = true;
                }
            }
            else if(flag == true)
            {
                break;
            }
            else
            {
                while(matricyA > i) // traverse through matricies A
                {
                    ithA = in.nextInt();
                    jthA = in.nextInt();
                    valueOfA = in.nextDouble();
                    A.changeEntry(ithA, jthA, valueOfA);
                    i++;
                }
                while(j < matricyB) // traverse through matrices B
                {
                    ithB = in.nextInt();
                    jthB = in.nextInt();
                    valueOfB = in.nextDouble();
                    B.changeEntry(ithB,jthB, valueOfB);
                    j++;
                }
            }
            count++;
        }
        printMatricy(in,out,A,B,matricyA,matricyB);
    }
    public static void main (String[] args) throws IOException
    {
        Sparse link = new Sparse();
        Scanner in = null;
        PrintWriter out = null;
                                // Note recycled concept from PA 1, and implemented Sparse in simliar fashion
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
        in.close(); // closing file!
        try      //Else,we reopen the file
        {
            in = new Scanner(new File(args[0])); // store the first argument in
        }
        catch (IOException e)
        {
            e.printStackTrace(); // Read above to see concept, already stated!
        }
        link.setMatricy(in,out); // if try/catch bypassed, then pass Scanner & Print Writer
    }
    public void printMatricy(Scanner in,PrintWriter out,Matrix A, Matrix B,int a, int b)
    {
        out.println("A has " + a + " non-zero entries:\n" + A); //Results received
        out.println("B has " + b + " non-zero entries:\n" + B);
        out.println("(1.5)*A = \n" + A.scalarMult(1.5));
        out.println("A+B = \n" + A.add(B));
        out.println("A+A = \n" + A.add(A));
        out.println("B-A = \n" + B.sub(A));
        out.println("A-A = \n" + A.sub(A));
        out.println("Transpose(A) = \n" + A.transpose());
        out.println("A*B = \n" + A.mult(B));
        out.print("B*B = \n" + B.mult(B));

        in.close();
        out.close();
    }
}
