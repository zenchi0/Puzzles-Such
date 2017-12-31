package gubenia;
import java.util.*;

/*******************************
 * CMIS 242 Project 3 Recursion*
 * @author Steve Gubenia       *
 *******************************/

public class RecursiveMethods {

    public static void main(String[] args) {
        
        double base, exp, result;
        int a, b, gcd;

        Scanner scan = new Scanner(System.in);

        System.out.print("Welcome to the Recursive Methods Program");

        System.out.println("First we will use recursion to compute" +
                " a number X raised to the nth power.");

        System.out.println("Then we will compute the greates common "
                + "divisor of two positive integers a and b.");

        //get base

        System.out.print("Enter base X: ");
        base = scan.nextDouble();

        //get power

        System.out.print("Enter power n: ");
        exp = scan.nextDouble();

        // get a

        System.out.print("Enter number a: ");
        a = scan.nextInt();

        //get b

        System.out.print("Enter number b: ");
        b = scan.nextInt();

        //compute results

        result = powerN(base, exp);
        gcd = gcd(a,b);

        //display result

        System.out.println(base + " raised to the " + exp + " power is "
                + result);
        System.out.println("The greatest common divisor for the two numbers "
                + a + " and " + b + " is " + gcd);
        
    } // end main method
    
    // Recursive method to compute number raised to a power

    public static double powerN(double base, double exp){

        if (exp == 0)
            return 1;

        else if (exp == 1)
            return base;

        else if (exp % 2 == 0)
            /**************************************************
             * This only seems to evaluate powers of 2 such as*
             * 2, 4, 8, 16, 32, etc. and not all even numbers *
             *************************************************/

            return powerN(base, exp/2) * powerN(base, exp/2);

        else
            /**************************************************************
             * I tried this code but the program seemed to be stuck       *
             * in a loop.  Never got error message but never got          *
             * result either                                              *
             * return base * powerN(base, exp/2.0) * powerN(base, exp/2.0)*
             * had also tried exp/2, not sure why didn't work but the     *
             * below code works                                           *
             *************************************************************/
            return base * powerN(base, exp - 1);

    } // end method for computing number raised to a power n

    
    // Recursive method to compute greates common divisor

    public static int gcd(int a, int b){

        if (b == 0)
            return a;

        else
            return gcd(b,a%b);

    }//end method to compute greatest common divisoer
} // end class
