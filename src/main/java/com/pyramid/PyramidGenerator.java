package com.pyramid;

/**
 * PyramidGenerator — Core pyramid generation class for the Asterisk Pyramid Generator application.
 *
 * <p>This class provides two public static methods for generating centered asterisk pyramid
 * patterns: {@link #printPyramid(int)} for direct console output, and
 * {@link #generatePyramid(int)} for returning the pattern as a {@code String} for testability.</p>
 *
 * <p>The pyramid pattern is constructed algorithmically using nested {@code for} loops.
 * Each row contains leading spaces for centering alignment followed by an odd number of
 * asterisks, growing by 2 per row (1, 3, 5, 7, ..., 2n−1).</p>
 *
 * <p>Example output for 5 rows:</p>
 * <pre>
 *     *
 *    ***
 *   *****
 *  *******
 * *********
 * </pre>
 *
 * <p>Algorithm summary per row {@code i} (1-based index):</p>
 * <ul>
 *   <li>Leading spaces: {@code rows - i}</li>
 *   <li>Asterisks: {@code 2 * i - 1}</li>
 * </ul>
 *
 * <p>For a 15-row pyramid: the first row contains 1 asterisk with 14 leading spaces,
 * and the last row contains 29 asterisks with 0 leading spaces.</p>
 *
 * @version 1.0.0
 */
public class PyramidGenerator {

    /**
     * Prints a centered asterisk pyramid directly to {@code System.out}.
     *
     * <p>Uses nested {@code for} loops to compute and print each row of the pyramid:</p>
     * <ul>
     *   <li><strong>Outer loop</strong>: iterates {@code i} from 1 to {@code rows} (inclusive),
     *       representing each row of the pyramid.</li>
     *   <li><strong>First inner loop</strong>: prints {@code (rows - i)} leading space characters
     *       to achieve centering alignment.</li>
     *   <li><strong>Second inner loop</strong>: prints {@code (2 * i - 1)} asterisk ({@code *})
     *       characters for the current row.</li>
     * </ul>
     *
     * <p>After both inner loops complete for a given row, {@code System.out.println()} is
     * called to move the cursor to the next line.</p>
     *
     * @param rows the number of rows in the pyramid; must be a non-negative integer.
     *             A value of 0 produces no output. Positive values produce a centered pyramid.
     * @throws IllegalArgumentException if {@code rows} is negative
     */
    public static void printPyramid(int rows) {
        // Validate input: negative row counts are not permitted
        if (rows < 0) {
            throw new IllegalArgumentException(
                    "Number of rows must be non-negative, but received: " + rows);
        }

        // If rows is 0, there is nothing to print — return immediately
        if (rows == 0) {
            return;
        }

        // Outer loop: iterate through each row of the pyramid (1-based indexing)
        for (int i = 1; i <= rows; i++) {

            // First inner loop: print leading spaces for centering alignment.
            // Row i needs exactly (rows - i) leading spaces so the asterisks
            // are centered relative to the widest (bottom) row.
            for (int j = 0; j < rows - i; j++) {
                System.out.print(" ");
            }

            // Second inner loop: print asterisks for the current row.
            // Row i contains exactly (2 * i - 1) asterisks, producing the
            // odd-number sequence: 1, 3, 5, 7, ..., (2 * rows - 1).
            for (int k = 0; k < 2 * i - 1; k++) {
                System.out.print("*");
            }

            // Move to the next line after completing the current row
            System.out.println();
        }
    }

    /**
     * Generates a centered asterisk pyramid and returns it as a {@code String}.
     *
     * <p>This method provides the same pyramid pattern as {@link #printPyramid(int)},
     * but builds the output using a {@link StringBuilder} and returns the result as a
     * {@code String}. This design enables unit testing without the need to capture
     * {@code System.out} output.</p>
     *
     * <p>The algorithm uses the same nested loop structure as {@code printPyramid}:</p>
     * <ul>
     *   <li><strong>Outer loop</strong>: iterates {@code i} from 1 to {@code rows} (inclusive),
     *       representing each row of the pyramid.</li>
     *   <li><strong>First inner loop</strong>: appends {@code (rows - i)} leading space
     *       characters for centering alignment.</li>
     *   <li><strong>Second inner loop</strong>: appends {@code (2 * i - 1)} asterisk ({@code *})
     *       characters for the current row.</li>
     *   <li>Each row is terminated by a newline character ({@code '\n'}).</li>
     * </ul>
     *
     * @param rows the number of rows in the pyramid; must be a non-negative integer.
     *             A value of 0 returns an empty string. Positive values return a
     *             centered pyramid pattern.
     * @return a {@code String} containing the complete centered asterisk pyramid,
     *         or an empty string if {@code rows} is 0
     * @throws IllegalArgumentException if {@code rows} is negative
     */
    public static String generatePyramid(int rows) {
        // Validate input: negative row counts are not permitted
        if (rows < 0) {
            throw new IllegalArgumentException(
                    "Number of rows must be non-negative, but received: " + rows);
        }

        // If rows is 0, return an empty string — no pyramid to generate
        if (rows == 0) {
            return "";
        }

        // Use StringBuilder for efficient string concatenation across all rows
        StringBuilder sb = new StringBuilder();

        // Outer loop: iterate through each row of the pyramid (1-based indexing)
        for (int i = 1; i <= rows; i++) {

            // First inner loop: append leading spaces for centering alignment.
            // Row i needs exactly (rows - i) leading spaces so the asterisks
            // are centered relative to the widest (bottom) row.
            for (int j = 0; j < rows - i; j++) {
                sb.append(" ");
            }

            // Second inner loop: append asterisks for the current row.
            // Row i contains exactly (2 * i - 1) asterisks, producing the
            // odd-number sequence: 1, 3, 5, 7, ..., (2 * rows - 1).
            for (int k = 0; k < 2 * i - 1; k++) {
                sb.append("*");
            }

            // Append a newline character to terminate the current row
            sb.append("\n");
        }

        // Return the complete pyramid pattern as a single String
        return sb.toString();
    }
}
