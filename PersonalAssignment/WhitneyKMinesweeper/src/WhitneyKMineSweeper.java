import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;


/**
 * Mine Sweeper game.
 *
 * @author Kassie Whitney
 * @version 6.30.25
 */
public class WhitneyKMineSweeper {

    /**
     * Constructor for the minesweeper game.
     */
    public WhitneyKMineSweeper() {
        super();
    }

    /**
     * Starts the minesweeper game.
     *
     * @param theRows     The number of rows on the minesweeper field.
     * @param theColumns  The number of columns on the minesweeper field.
     * @param theMineCord A set of lists with mine coordinate locations.
     */
    public void play(final int theRows, final int theColumns,
                     final Set<List<Integer>> theMineCord) {


        final String[][] result = fieldBuilder(theRows, theColumns, theMineCord);
        addHints(result, theRows, theColumns);

        for (final String[] strings : result) {
            for (final String string : strings) {
                System.out.print(string);
            }
            System.out.println();
        }
    }

    /**
     * Adds hints to the fields, if the location touches 1 mine, a 1 will appear, if 2 than
     * two and so-and-so forth.
     *
     * @param theField The 2D-array that is made up of mines and 0's.
     */
    private void addHints(final String[][] theField, final int theRows,
                          final int theColumns) {

        for (int row = 0; row < theRows; row++) {
            for (int col = 0; col < theColumns; col++) {

                final String topSide;

                final String rightTopDia;
                final String rightSide;
                final String rightBtmDia;

                final String bottomSide;

                final String leftBtmDia;
                final String leftSide;
                final String leftTopDia;

                final boolean isRowTopEdge = row == 0; //if row does not have a top
                final boolean isRowBtmEdge = row == theRows - 1; //if row does not have a
                // bottom
                final boolean isColLeftEdge = col == 0; //if the column does not have a left
                // side
                final boolean isColRightEdge = col == theColumns - 1; //if the column does not

                //Checks all eight side of the mine, considers all the edge cases,
                // increments the hints based on if the mine touches the hint safe square

                if (!isSafeSpot(theField[row][col])) {
                    if (theRows > 1 && theColumns > 1) {
                        if (isRowTopEdge && isColLeftEdge) { //Top left corner

                            rightSide = theField[row][col + 1];
                            rightBtmDia = theField[row + 1][col + 1];
                            bottomSide = theField[row + 1][col];

                            incrementSafeSpot(theField, row, col + 1, rightSide);
                            incrementSafeSpot(theField, row + 1, col + 1, rightBtmDia);
                            incrementSafeSpot(theField, row + 1, col, bottomSide);

                        } else if (isRowTopEdge && isColRightEdge) { //top right corner

                            leftSide = theField[row][col - 1];
                            leftBtmDia = theField[row + 1][col - 1];
                            bottomSide = theField[row + 1][col];

                            incrementSafeSpot(theField, row, col - 1, leftSide);
                            incrementSafeSpot(theField, row + 1, col - 1, leftBtmDia);
                            incrementSafeSpot(theField, row + 1, col, bottomSide);

                        } else if (isRowTopEdge) { // top boarder

                            leftSide = theField[row][col - 1];
                            leftBtmDia = theField[row + 1][col - 1];
                            bottomSide = theField[row + 1][col];
                            rightBtmDia = theField[row + 1][col + 1];
                            rightSide = theField[row][col + 1];

                            incrementSafeSpot(theField, row, col - 1, leftSide);
                            incrementSafeSpot(theField, row + 1, col - 1, leftBtmDia);
                            incrementSafeSpot(theField, row + 1, col, bottomSide);
                            incrementSafeSpot(theField, row + 1, col + 1, rightBtmDia);
                            incrementSafeSpot(theField, row, col + 1, rightSide);


                        } else if (isRowBtmEdge && isColLeftEdge) { //bottom left
                            // corner

                            topSide = theField[row - 1][col];
                            rightTopDia = theField[row - 1][col + 1];
                            rightSide = theField[row][col + 1];

                            incrementSafeSpot(theField, row - 1, col, topSide);
                            incrementSafeSpot(theField, row - 1, col + 1, rightTopDia);
                            incrementSafeSpot(theField, row, col + 1, rightSide);

                        } else if (isColLeftEdge) { // Left side wall

                            topSide = theField[row - 1][col];
                            rightTopDia = theField[row - 1][col + 1];
                            rightSide = theField[row][col + 1];
                            rightBtmDia = theField[row + 1][col + 1];
                            bottomSide = theField[row + 1][col];

                            incrementSafeSpot(theField, row - 1, col, topSide);
                            incrementSafeSpot(theField, row - 1, col + 1, rightTopDia);
                            incrementSafeSpot(theField, row, col + 1, rightSide);
                            incrementSafeSpot(theField, row + 1, col + 1, rightBtmDia);
                            incrementSafeSpot(theField, row + 1, col, bottomSide);

                        } else if (isRowBtmEdge && isColRightEdge) { // bottom right corner
                            topSide = theField[row - 1][col];
                            leftTopDia = theField[row - 1][col - 1];
                            leftSide = theField[row][col - 1];

                            incrementSafeSpot(theField, row - 1, col, topSide);
                            incrementSafeSpot(theField, row - 1, col - 1, leftTopDia);
                            incrementSafeSpot(theField, row, col - 1, leftSide);

                        } else if (isRowBtmEdge) { // bottom boarder

                            leftSide = theField[row][col - 1];
                            leftTopDia = theField[row - 1][col - 1];
                            topSide = theField[row - 1][col];
                            rightTopDia = theField[row - 1][col + 1];
                            rightSide = theField[row][col + 1];

                            incrementSafeSpot(theField, row, col - 1, leftSide);
                            incrementSafeSpot(theField, row - 1, col - 1, leftTopDia);
                            incrementSafeSpot(theField, row - 1, col, topSide);
                            incrementSafeSpot(theField, row - 1, col + 1, rightTopDia);
                            incrementSafeSpot(theField, row, col + 1, rightSide);

                        } else if (isColRightEdge) { // right side wall

                            topSide = theField[row - 1][col];
                            leftTopDia = theField[row - 1][col - 1];
                            leftSide = theField[row][col - 1];
                            leftBtmDia = theField[row + 1][col - 1];
                            bottomSide = theField[row + 1][col];


                            incrementSafeSpot(theField, row - 1, col - 1, leftTopDia);
                            incrementSafeSpot(theField, row, col - 1, leftSide);
                            incrementSafeSpot(theField, row + 1, col - 1, leftBtmDia);
                            incrementSafeSpot(theField, row + 1, col, bottomSide);
                            incrementSafeSpot(theField, row - 1, col, topSide);

                        } else { //everything in the middle
                            leftTopDia = theField[row - 1][col - 1];
                            leftSide = theField[row][col - 1];
                            topSide = theField[row - 1][col];
                            rightTopDia = theField[row - 1][col + 1];
                            rightSide = theField[row][col + 1];
                            rightBtmDia = theField[row + 1][col + 1];
                            bottomSide = theField[row + 1][col];
                            leftBtmDia = theField[row + 1][col - 1];

                            incrementSafeSpot(theField, row - 1, col, topSide);
                            incrementSafeSpot(theField, row - 1, col - 1, leftTopDia);
                            incrementSafeSpot(theField, row, col - 1, leftSide);
                            incrementSafeSpot(theField, row + 1, col - 1, leftBtmDia);
                            incrementSafeSpot(theField, row + 1, col, bottomSide);
                            incrementSafeSpot(theField, row - 1, col + 1, rightTopDia);
                            incrementSafeSpot(theField, row, col + 1, rightSide);
                            incrementSafeSpot(theField, row + 1, col + 1, rightBtmDia);

                        }
                    } else if (theColumns > 1) { // single row multiple columns
                        if (col == 0) { // left edge
                            // case single row

                            rightSide = theField[row][col + 1];
                            incrementSafeSpot(theField, row, col + 1, rightSide);

                        } else if (isColRightEdge) { // right
                            // edge case single row

                            leftSide = theField[row][col - 1];
                            incrementSafeSpot(theField, row, col - 1, leftSide);

                        } else if (col < theColumns - 1) { //middle
                            // elements single row

                            leftSide = theField[row][col - 1];
                            rightSide = theField[row][col + 1];

                            incrementSafeSpot(theField, row, col - 1, leftSide);
                            incrementSafeSpot(theField, row, col + 1, rightSide);

                        }
                    } else if (theRows > 1) { // single column, multiple rows

                        if (row == 0) {
                            bottomSide = theField[row + 1][col];
                            incrementSafeSpot(theField, row + 1, col, bottomSide);
                        } else if (row == theRows - 1) {
                            topSide = theField[row - 1][col];
                            incrementSafeSpot(theField, row - 1, col, topSide);
                        } else {
                            topSide = theField[row - 1][col];
                            bottomSide = theField[row + 1][col];
                            incrementSafeSpot(theField, row - 1, col, topSide);
                            incrementSafeSpot(theField, row + 1, col, bottomSide);
                        }
                    }
                }
            }
        }
    }

    private void incrementSafeSpot(final String[][] thePlayfield, final int theRow,
                                   final int theCol, final String theElement) {

        final int safeSpot;
        if (isSafeSpot(theElement)) {
            safeSpot = Integer.parseInt(theElement) + 1;
            thePlayfield[theRow][theCol] = String.valueOf(safeSpot);
        }

    }

    private boolean isSafeSpot(final String theElement) {
        return !"*".equals(theElement);
    }


    /**
     * Builds the minefield with the corresponding mine location.
     *
     * @param theRows     The number of rows that makes up the minefield.
     * @param theColumns  The number of columns that makes up the minefield.
     * @param theMineCord A set of lists that consists of the mines grid locations
     * @return A 2d-array of the completed field with mines and zeros.
     */
    private String[][] fieldBuilder(final int theRows, final int theColumns,
                                    final Set<List<Integer>> theMineCord) {

        final String[][] field = new String[theRows][theColumns];

        for (int row = 0; row < theRows; row++) {
            for (int col = 0; col < theColumns; col++) {
                field[row][col] = "0";
            }
        }

        //inputs the mines into the field
        for (List<Integer> mineLoc : theMineCord) {

            final int row = mineLoc.getFirst();
            final int col = mineLoc.getLast();

            field[row][col] = "*";
        }

        return field;
    }

    /**
     * Checks if the parsed string is an integer.
     * Used for checking if the scanned line is the dimension or the field.
     *
     * @param parsedStr The string parsed from executeComLineArgs();
     * @return True if the parsed string is an integer. False otherwise.
     */
    private boolean isInteger(final String parsedStr) {

        final boolean result;

        try {
            if (parsedStr.isEmpty()) {
                result = false;
            } else {
                Integer.parseInt(parsedStr);
                result = true;
            }

            return result;

        } catch (NumberFormatException NFE) {
            return true;
        }
    }

    /**
     * Parses through user entry separating the data to be used to build the field.
     */
    private void executeComLineArgs() {
        final Scanner sc = new Scanner(System.in);
        final List<MineField> allFields = new ArrayList<>();

        //Parses through the user entry line by line
        while (sc.hasNextLine()) {

            final String[] line = sc.nextLine().trim().split("\\s+");
            final Set<List<Integer>> mineGrid = new HashSet<>();
            final int rows;
            final int cols;
            int currentRow = 0;

            if (line.length == 1) {
                mineGrid.add(List.of(0, 0));
                allFields.add(new MineField(0, 0, mineGrid));
                continue;
            }

            rows = Integer.parseInt(line[0]);
            cols = Integer.parseInt(line[1]);


            if (rows == 0 && cols == 0) {
                break;
            }

            while (currentRow < rows && sc.hasNextLine()) {
                 final String gridLine = sc.nextLine().trim();

                for (int c = 0; c < cols; c++) {
                    if (gridLine.charAt(c) == '*') {
                        mineGrid.add(List.of(currentRow, c));
                    }
                }
                currentRow++;
            }

            allFields.add(new MineField(rows, cols, mineGrid));
        }

        //Prints the final minefields based on users entry
        int counter = 0;
        for (MineField field : allFields) {
            System.out.printf("Field #%d:\n", ++counter);
            if (field.myRows == 0 || field.myCols == 0) {
                System.out.println("0");
                System.out.println();
            } else {
                play(field.myRows, field.myCols, field.myMineCord);
                System.out.println();
            }
        }

    }

    /**
     * The main method for the minesweeper class.
     *
     * @param theArgs Command line arguments, the redirected file from the command prompt
     */
    public static void main(final String[] theArgs) {
        WhitneyKMineSweeper mineSweeper = new WhitneyKMineSweeper();

        mineSweeper.executeComLineArgs();

    }

    /**
     * Stores the parsed data from the user entry and stores the data in their own field.
     * Inner class is treated like a record.
     *
     * @author Kassie Whitney
     * @version 7.3.2025
     */
    public static class MineField {
        /**
         * The number of rows in the minefield.
         */
        private final int myRows;

        /**
         * The number of columns in the minefield.
         */
        private final int myCols;

        /**
         * The list of grid Coordinates of the mines.
         */
        private final Set<List<Integer>> myMineCord;

        MineField(final int theRows, final int theCol, final Set<List<Integer>> theMineGrid) {
            super();

            myRows = theRows;
            myCols = theCol;
            myMineCord = theMineGrid;
        }
    }
}

