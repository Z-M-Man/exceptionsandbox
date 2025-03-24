package edu.guilford;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class ExceptionSandbox {
    public static void main(String[] args) {
        double[] values = { 1.2, 3.4, 5.5, 6.6 };
        // setting the index beyond the last element of the array is often a logical
        // error
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
                n = Integer.parseInt(entered); // If an exception happens here,
                // we jump to the catch block and valid is still false
                if (n == 13) {
                    throw new BadNumberException("13 is a bad number");
                }
                valid = true;
            } catch (NumberFormatException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                // print out an error to the error stream
                System.err.println("Invalid input. Please enter a valid integer.");
                System.err.flush();
            } catch (BadNumberException e) {
                System.err.println(e.getMessage());
                System.err.flush();
                System.exit(n); // exit the program with the bad number as the exit code
            }
        } while (!valid);
        System.out.println("The valid number entered is: " + n);

        Scanner scanFile = null; // Scanner object to read the file
        String dataLocation = null; // The file name given by the user
        boolean validDataFile = false;
        boolean validData = false;
        double[][] data = null;

        try {
            System.out.println("Enter the name of the data file (e.g., data.txt): ");
            dataLocation = scan.next();
            String dataPlace = "target/classes/" + dataLocation; // path to the file in the target/classes directory

            // Use a FileReader object to open the file for reading
            // Anything that is a reader reads characters as compared to bits
            // and bytes. A FileReader reads characters from a file.

            FileReader dataFile = new FileReader(dataPlace);
            BufferedReader dataBuffer = new BufferedReader(dataFile);
            scanFile = new Scanner(dataBuffer); // create a Scanner object to read the file
            validDataFile = true;
            data = readData(scanFile);
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + dataLocation);
            System.err.flush();
            System.exit(1); // exit the program with an error code
        } catch (ScannerException e) {
            System.err.println(e.getMessage());
            System.err.flush();
            System.exit(2); // exit the program with an error code
        }

    }

    public static double[][] readData(Scanner scan) throws ScannerException {
        double[][] inputValues = null;
        // This method reads a file with number of rows and columns in the first row
        // and then the data in the remaining rows
        int i = 0;
        int j = 0;

        try {
            int rows = scan.nextInt();
            int columns = scan.nextInt();
            inputValues = new double[rows][columns];
            for (i = 0; i < rows; i++) {
                for (j = 0; j < columns; j++) {
                    inputValues[i][j] = scan.nextDouble(); // read the data into the array
                }
            }
        } catch (InputMismatchException e) {

            throw new ScannerException("Improper data at " + i + " " + j);
        } catch (NoSuchElementException e) {
            throw new ScannerException("Not enough data at " + i + " " + j);
        }

        return inputValues;
    }

    public static class ScannerException extends Exception {
        public ScannerException(String message) {
            super(message);
        }
    }
}