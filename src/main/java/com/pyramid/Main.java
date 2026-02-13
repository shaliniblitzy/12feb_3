package com.pyramid;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Main — Application entry point for the Asterisk Pyramid Generator.
 *
 * <p>This class serves as the executable entry point for the console application.
 * When launched, it defaults to printing a centered 15-row asterisk pyramid.
 * The user is optionally prompted to enter a custom row count at runtime,
 * overriding the default value.</p>
 *
 * <p>Input validation ensures only positive integers are accepted. Non-integer
 * input triggers an {@link InputMismatchException} handler, while non-positive
 * integers produce a descriptive error message. In both cases the application
 * exits with a non-zero status code.</p>
 *
 * <p>All pyramid rendering is delegated to
 * {@link PyramidGenerator#printPyramid(int)}, keeping the entry-point logic
 * focused solely on user interaction and validation.</p>
 *
 * <p>Usage examples:</p>
 * <pre>
 *   // Default 15-row pyramid (press Enter with '15' or type a custom value)
 *   java -jar target/pyramid-generator-1.0.0.jar
 * </pre>
 *
 * @version 1.0.0
 * @see PyramidGenerator
 */
public class Main {

    /**
     * Application entry point that handles user interaction and delegates
     * pyramid rendering to {@link PyramidGenerator#printPyramid(int)}.
     *
     * <p>Execution flow:</p>
     * <ol>
     *   <li>Initialize the default row count to 15.</li>
     *   <li>Display a welcome banner and prompt the user for input.</li>
     *   <li>Read the user's response via {@link Scanner#nextInt()}.</li>
     *   <li>Validate that the entered value is a positive integer (&gt; 0).</li>
     *   <li>Delegate to {@link PyramidGenerator#printPyramid(int)} for output.</li>
     * </ol>
     *
     * <p>Error handling:</p>
     * <ul>
     *   <li>Non-integer input: caught by {@link InputMismatchException}, prints
     *       an error message, and exits with code 1.</li>
     *   <li>Non-positive integer: prints an error message and exits with
     *       code 1.</li>
     * </ul>
     *
     * @param args command-line arguments (currently unused)
     */
    public static void main(String[] args) {
        // Default number of rows for the pyramid
        int rows = 15;

        // Display a welcome banner describing the application
        System.out.println("========================================");
        System.out.println("   Asterisk Pyramid Generator");
        System.out.println("========================================");
        System.out.println();
        System.out.println("This program generates a centered pyramid");
        System.out.println("pattern made of asterisks (*).");
        System.out.println();

        // Prompt the user to enter a custom row count or use the default
        System.out.println("Default number of rows: " + rows);
        System.out.print("Enter the number of rows (or press Enter for default): ");

        // Create a Scanner wrapping System.in to read user input
        Scanner scanner = new Scanner(System.in);

        try {
            // Check if the user has provided any input on the current line
            if (scanner.hasNextInt()) {
                // Read the user-supplied integer value
                int userInput = scanner.nextInt();

                // Validate that the entered value is a positive integer (> 0)
                if (userInput <= 0) {
                    System.out.println("Error: Number of rows must be a positive integer.");
                    scanner.close();
                    System.exit(1);
                }

                // Override the default row count with the user-supplied value
                rows = userInput;
            } else if (scanner.hasNext()) {
                // The user entered non-integer input — read and discard it,
                // then report the error
                String invalidInput = scanner.next();
                System.out.println("Error: Invalid input '" + invalidInput
                        + "'. Please enter a positive integer.");
                scanner.close();
                System.exit(1);
            }
            // If no input is available (e.g., piped empty stdin or Enter pressed
            // without data), the default value of 15 rows is used.

        } catch (InputMismatchException e) {
            // Handle the case where Scanner.nextInt() encounters non-integer data
            System.out.println("Error: Invalid input. Please enter a positive integer.");
            scanner.close();
            System.exit(1);
        }

        // Close the Scanner to release the System.in resource
        scanner.close();

        // Inform the user about the pyramid being generated
        System.out.println();
        System.out.println("Generating a " + rows + "-row pyramid:");
        System.out.println();

        // Delegate pyramid rendering to PyramidGenerator.printPyramid()
        // This is the primary integration point: Main -> PyramidGenerator
        PyramidGenerator.printPyramid(rows);
    }
}
