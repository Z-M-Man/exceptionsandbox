package edu.guilford;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
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

        Scanner scanFile = null; // Scanner object to read the file
        String dataLocation = null; // The file name given by the user
        boolean validDataFile = false;
        boolean validData = false;
        double[][] data = null;

        try {
            System.out.println("Enter the name of the data file: ");
            dataLocation = scan.next();
            String dataPlace = "target/classes/" + dataLocation; // Path to the file

            // Use a FileReader object to open the file for reading
            // A reader reads characters rather than bits and bytes
            FileReader dataFile = new FileReader(dataPlace);
            BufferedReader dataBuffer = new BufferedReader(dataFile);
            double[][] inputValues = null;

            int i = 0;
            int j = 0;

            int rows = scan.nextInt();
            int columns = scan.nextInt();
            inputValues = new double[rows][columns];
            for (i = 0; i < rows; i++) {
                for (j = 0; j < columns; j++) {
                    inputValues[i][j] = scan.nextDouble();
                }
            }

        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + dataLocation);
            System.err.flush();
            System.exit(1); // Exit the program with an error code
        }

    }
}