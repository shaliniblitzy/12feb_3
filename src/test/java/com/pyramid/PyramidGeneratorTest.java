package com.pyramid;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

/**
 * JUnit 5 unit test class for the {@link PyramidGenerator} class.
 *
 * <p>This test class exercises the {@link PyramidGenerator#generatePyramid(int)} method,
 * which returns the pyramid pattern as a {@code String}, enabling assertions against
 * the returned value without the need to capture {@code System.out} output.</p>
 *
 * <p>Test coverage includes:</p>
 * <ul>
 *   <li>Default 15-row pyramid dimensions (line count, first row, last row)</li>
 *   <li>Custom row counts (1, 5, 10)</li>
 *   <li>Centering alignment verification (leading spaces per row)</li>
 *   <li>Asterisk count formula validation (2 * row - 1)</li>
 *   <li>Edge-case handling (0 rows, negative input)</li>
 * </ul>
 *
 * @version 1.0.0
 * @see PyramidGenerator
 */
class PyramidGeneratorTest {

    // ========================================================================
    // Test Group 1: Default 15-Row Pyramid Dimensions
    // ========================================================================

    /**
     * Verifies that a 15-row pyramid produces exactly 15 lines of output.
     * The generatePyramid method appends a newline after each row, so splitting
     * the result by newline should yield exactly 15 non-empty line elements.
     */
    @Test
    @DisplayName("Default 15-row pyramid should produce exactly 15 lines")
    void testDefault15RowPyramidLineCount() {
        // Generate the default 15-row pyramid
        String result = PyramidGenerator.generatePyramid(15);

        // Verify the result is not null before further assertions
        assertNotNull(result, "generatePyramid(15) should not return null");

        // Split by newline and verify exactly 15 lines are produced
        String[] lines = result.split("\n");
        assertEquals(15, lines.length,
                "A 15-row pyramid must produce exactly 15 lines of output");
    }

    /**
     * Verifies that the first row of a 15-row pyramid contains exactly 1 asterisk.
     * The first row should have 14 leading spaces followed by a single asterisk.
     * Trimming the line should yield just "*".
     */
    @Test
    @DisplayName("First row of 15-row pyramid should contain exactly 1 asterisk")
    void testDefault15RowFirstRowHasOneAsterisk() {
        // Generate the default 15-row pyramid
        String result = PyramidGenerator.generatePyramid(15);
        String[] lines = result.split("\n");

        // The first line, when trimmed of leading spaces, should be a single asterisk
        assertEquals("*", lines[0].trim(),
                "The first row of a 15-row pyramid must contain exactly 1 asterisk");
    }

    /**
     * Verifies that the last row of a 15-row pyramid contains exactly 29 asterisks.
     * The formula for the last row is 2 * 15 - 1 = 29 asterisks with 0 leading spaces.
     */
    @Test
    @DisplayName("Last row of 15-row pyramid should contain exactly 29 asterisks (2*15-1)")
    void testDefault15RowLastRowHas29Asterisks() {
        // Generate the default 15-row pyramid
        String result = PyramidGenerator.generatePyramid(15);
        String[] lines = result.split("\n");

        // Count asterisk characters in the last line
        String lastLine = lines[lines.length - 1];
        long asteriskCount = lastLine.chars().filter(c -> c == '*').count();

        // The last row must have exactly 29 asterisks per the formula 2*15-1
        assertEquals(29, asteriskCount,
                "The last row of a 15-row pyramid must contain exactly 29 asterisks");
    }

    // ========================================================================
    // Test Group 2: Custom Row Counts
    // ========================================================================

    /**
     * Verifies that a single-row pyramid produces exactly "*\n".
     * With 1 row, there are 0 leading spaces and 1 asterisk (2*1-1 = 1),
     * followed by a newline character.
     */
    @Test
    @DisplayName("Single row pyramid should produce exactly '*\\n' with no leading spaces")
    void testSingleRowPyramid() {
        // Generate a 1-row pyramid
        String result = PyramidGenerator.generatePyramid(1);

        // A single-row pyramid has 0 leading spaces and 1 asterisk followed by newline
        assertEquals("*\n", result,
                "A 1-row pyramid must produce exactly \"*\\n\" with no leading spaces");
    }

    /**
     * Verifies that a 5-row pyramid produces exactly 5 lines, with the first line
     * containing 1 asterisk and the last line containing 9 asterisks (2*5-1).
     */
    @Test
    @DisplayName("Five-row pyramid should produce 5 lines with 1 asterisk on first and 9 on last row")
    void testFiveRowPyramid() {
        // Generate a 5-row pyramid
        String result = PyramidGenerator.generatePyramid(5);
        String[] lines = result.split("\n");

        // Verify the line count is exactly 5
        assertEquals(5, lines.length,
                "A 5-row pyramid must produce exactly 5 lines");

        // First row should have exactly 1 asterisk when trimmed
        assertEquals("*", lines[0].trim(),
                "The first row of a 5-row pyramid must contain exactly 1 asterisk");

        // Last row should have exactly 9 asterisks (2*5-1 = 9)
        long lastLineAsterisks = lines[4].chars().filter(c -> c == '*').count();
        assertEquals(9, lastLineAsterisks,
                "The last row of a 5-row pyramid must contain exactly 9 asterisks");
    }

    /**
     * Verifies that a 10-row pyramid produces exactly 10 lines, with the last line
     * containing 19 asterisks (2*10-1).
     */
    @Test
    @DisplayName("Ten-row pyramid should produce 10 lines with 19 asterisks on last row")
    void testTenRowPyramid() {
        // Generate a 10-row pyramid
        String result = PyramidGenerator.generatePyramid(10);
        String[] lines = result.split("\n");

        // Verify the line count is exactly 10
        assertEquals(10, lines.length,
                "A 10-row pyramid must produce exactly 10 lines");

        // Last row should have exactly 19 asterisks (2*10-1 = 19)
        long lastLineAsterisks = lines[9].chars().filter(c -> c == '*').count();
        assertEquals(19, lastLineAsterisks,
                "The last row of a 10-row pyramid must contain exactly 19 asterisks");
    }

    // ========================================================================
    // Test Group 3: Centering Alignment Verification
    // ========================================================================

    /**
     * Verifies that each row of the pyramid has the correct number of leading spaces
     * for centering alignment. For a pyramid with N rows, row i (0-based index)
     * should have exactly (N - i - 1) leading spaces. The position of the first
     * asterisk character in each line is used to determine the actual leading space count.
     */
    @Test
    @DisplayName("Centering alignment: row i should have exactly (rows - i - 1) leading spaces")
    void testCenteringAlignment() {
        // Use a 5-row pyramid for alignment verification
        int rows = 5;
        String result = PyramidGenerator.generatePyramid(rows);
        String[] lines = result.split("\n");

        // Iterate through each line and verify leading space count
        for (int i = 0; i < lines.length; i++) {
            // Expected leading spaces for row i (0-based): rows - i - 1
            int expectedSpaces = rows - i - 1;

            // The index of the first '*' character gives the actual leading space count
            int actualSpaces = lines[i].indexOf('*');

            assertEquals(expectedSpaces, actualSpaces,
                    "Row " + (i + 1) + " of a " + rows + "-row pyramid should have "
                            + expectedSpaces + " leading spaces, but had " + actualSpaces);
        }
    }

    // ========================================================================
    // Test Group 4: Asterisk Count Formula Validation
    // ========================================================================

    /**
     * Verifies the asterisk count formula for each row of the pyramid.
     * Row i (1-based) should contain exactly (2 * i - 1) asterisks.
     * This test iterates through all rows of a 5-row pyramid and counts
     * the asterisk characters in each line.
     */
    @Test
    @DisplayName("Asterisk count formula: row i (1-based) should contain exactly (2*i - 1) asterisks")
    void testAsteriskCountFormula() {
        // Use a 5-row pyramid for formula verification
        int rows = 5;
        String result = PyramidGenerator.generatePyramid(rows);
        String[] lines = result.split("\n");

        // Iterate through each line and verify the asterisk count
        for (int i = 0; i < lines.length; i++) {
            // Convert 0-based index to 1-based row number
            int rowNumber = i + 1;

            // Expected asterisks per the formula: 2 * rowNumber - 1
            long expectedAsterisks = 2L * rowNumber - 1;

            // Count actual asterisk characters in the line
            long actualAsterisks = lines[i].chars().filter(c -> c == '*').count();

            assertEquals(expectedAsterisks, actualAsterisks,
                    "Row " + rowNumber + " should contain " + expectedAsterisks
                            + " asterisks, but contained " + actualAsterisks);
        }
    }

    // ========================================================================
    // Test Group 5: Edge-Case Handling
    // ========================================================================

    /**
     * Verifies that generating a pyramid with 0 rows returns an empty string.
     * Zero is a valid non-negative input that should produce no output.
     */
    @Test
    @DisplayName("Zero rows should return an empty string")
    void testZeroRowsReturnsEmptyString() {
        // Generate a pyramid with 0 rows
        String result = PyramidGenerator.generatePyramid(0);

        // The result must be an empty string — no lines, no characters
        assertEquals("", result,
                "generatePyramid(0) must return an empty string");
    }

    /**
     * Verifies that a negative input (-1) causes an {@link IllegalArgumentException}
     * to be thrown. Negative row counts are invalid and must be rejected.
     */
    @Test
    @DisplayName("Negative input (-1) should throw IllegalArgumentException")
    void testNegativeRowsThrowsException() {
        // Attempting to generate a pyramid with -1 rows must throw IllegalArgumentException
        assertThrows(IllegalArgumentException.class,
                () -> PyramidGenerator.generatePyramid(-1),
                "generatePyramid(-1) must throw IllegalArgumentException for negative input");
    }

    /**
     * Verifies that a large negative input (-100) also causes an
     * {@link IllegalArgumentException} to be thrown, confirming that the validation
     * is not limited to small negative values.
     */
    @Test
    @DisplayName("Large negative input (-100) should throw IllegalArgumentException")
    void testNegativeRowsThrowsExceptionForLargeNegative() {
        // Attempting to generate a pyramid with -100 rows must throw IllegalArgumentException
        assertThrows(IllegalArgumentException.class,
                () -> PyramidGenerator.generatePyramid(-100),
                "generatePyramid(-100) must throw IllegalArgumentException for negative input");
    }
}
