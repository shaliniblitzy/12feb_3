# Asterisk Pyramid Generator

A standalone Java console application that prints a centered pyramid pattern of asterisks (`*`) to the console. By default, the program generates a perfectly centered pyramid with **15 rows**, where each row's asterisk count follows the formula `2n − 1` (1, 3, 5, …, 29). Users can optionally enter a custom row count at runtime for dynamic pyramid generation.

---

## Table of Contents

- [Prerequisites](#prerequisites)
- [Build Instructions](#build-instructions)
- [Run Instructions](#run-instructions)
- [Example Output](#example-output)
- [Project Structure](#project-structure)
- [Optional Enhancements](#optional-enhancements)
- [License](#license)

---

## Prerequisites

Before building and running this project, ensure the following tools are installed on your system:

| Tool | Minimum Version | Verification Command |
|---|---|---|
| **Java Development Kit (JDK)** | OpenJDK 21.0.10+ | `java --version` |
| **Apache Maven** | 3.8.7+ | `mvn --version` |

> **Note:** The project is configured for Java 21. Ensure your `JAVA_HOME` environment variable points to a JDK 21 installation.

---

## Build Instructions

All build commands should be run from the project root directory (where `pom.xml` is located).

### Compile the Project

```bash
mvn clean compile
```

This compiles all Java source files under `src/main/java/` and places the compiled classes in the `target/classes/` directory.

### Run Unit Tests

```bash
mvn test
```

Executes all JUnit 5 test classes located under `src/test/java/`. Test results are printed to the console and saved in `target/surefire-reports/`.

### Build Executable JAR

```bash
mvn package
```

Compiles the source code, runs all tests, and packages the application into an executable JAR file at `target/pyramid-generator-1.0.0.jar`. The JAR manifest includes the `Main-Class` entry pointing to `com.pyramid.Main`, so it can be run directly.

---

## Run Instructions

### Default Mode (15-Row Pyramid)

After building the project, run the application with:

```bash
java -jar target/pyramid-generator-1.0.0.jar
```

By default, the program prints a centered pyramid with **15 rows** (1 asterisk on the first row, 29 asterisks on the last row) and then exits.

### Dynamic Input Mode

When the application starts, it prompts you to either use the default 15-row pyramid or enter a custom number of rows. Simply follow the on-screen instructions:

1. Press **Enter** or type the default value to generate a 15-row pyramid.
2. Type a **positive integer** (e.g., `5`, `10`, `20`) to generate a pyramid with that many rows.

If an invalid value is entered (non-integer or non-positive number), the application displays a clear error message and exits with a non-zero status code.

---

## Example Output

### 5-Row Pyramid

```
    *
   ***
  *****
 *******
*********
```

### 15-Row Pyramid (Default)

```
              *
             ***
            *****
           *******
          *********
         ***********
        *************
       ***************
      *****************
     *******************
    *********************
   ***********************
  *************************
 ***************************
*****************************
```

Each row is centered by prepending the appropriate number of leading spaces. The first row always contains exactly **1** asterisk, and the last row contains exactly **2n − 1** asterisks, where `n` is the total number of rows.

---

## Project Structure

```
pyramid-generator/
├── pom.xml                                          # Maven project descriptor (Java 21, JUnit 5)
├── .gitignore                                       # Git exclusion rules for build artifacts and IDE files
├── README.md                                        # Project documentation (this file)
└── src/
    ├── main/
    │   └── java/
    │       └── com/
    │           └── pyramid/
    │               ├── Main.java                    # Application entry point (default 15 rows, optional user input)
    │               └── PyramidGenerator.java        # Core pyramid generation logic (printPyramid, generatePyramid)
    └── test/
        └── java/
            └── com/
                └── pyramid/
                    └── PyramidGeneratorTest.java     # JUnit 5 unit tests for pyramid generation
```

### Key Components

- **`Main.java`** — Entry point with `main(String[] args)`. Handles default 15-row mode and optional dynamic user input via `java.util.Scanner`. Validates that input is a positive integer before delegating to the generator.
- **`PyramidGenerator.java`** — Contains the core pyramid-building logic:
  - `printPyramid(int rows)` — Prints the centered pyramid directly to `System.out` using nested `for` loops.
  - `generatePyramid(int rows)` — Returns the pyramid as a `String` (via `StringBuilder`) for unit testing and programmatic use.
- **`PyramidGeneratorTest.java`** — JUnit 5 tests covering default output dimensions, custom row counts (1, 5, 10, 15), centering alignment verification, asterisk count formula validation, and edge cases (0 rows, negative input).

---

## Optional Enhancements

The application supports the following optional enhancement built into the runtime:

- **Custom Row Count**: Instead of the default 15 rows, users can enter any positive integer at the prompt to generate a pyramid of the desired height. Input validation ensures only valid positive integers are accepted; all other input is rejected with a descriptive error message.

**Potential future enhancements** (not included in the current version):

- Reverse (inverted) pyramid patterns
- Number-based pyramid patterns
- Hollow pyramid patterns
- Diamond patterns combining upward and downward pyramids

---

## License

This project is provided as-is for educational and demonstration purposes.
