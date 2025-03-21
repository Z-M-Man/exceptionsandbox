package edu.guilford;

import java.util.Scanner;

public class ExceptionSandbox {
    public static void main(String[] args) {
        double[] values = {1.2, 3.4, 5.5, 6.6};
        // setting the index beyond the last element of the array is often a logical error
        // that leads to a runtime error
        int index = 3;
        values[index] = 7.7;

        Scanner scan = new Scanner(System.in);
        String entered = "";
        boolean valid = false; // will set to true once we have valid input
        int n = 0;
        do {
            // get a string from the user at least once so that we can determine valid input
            try {
                System.out.print("Enter an integer: ");
                entered = scan.next();
                n = Integer.parseInt(entered);
                valid = true;
            } catch (NumberFormatException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                // print out an error to the error stream
                System.err.println("Invalid input. Please enter a valid integer.");
                System.err.flush();
            }
        } while (!valid);
        System.out.println("The valid number entered is: " + n);


    }
}