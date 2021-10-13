package main;

import java.util.Vector;

public class PuzzleValidator {
    private static final String Digit = "\\d";
    private String[][] puzzle;
    private int[][] indicies;

    public boolean check(String[][] puzzle){
        this.puzzle = puzzle;
        this.indicies = new int[][]{{0,1,2},{3,4,5},{6,7,8}};
        return rowCheck() && boxCheck() && columnCheck();
    }

    //This could have used a Set instead of a vector
    private boolean columnCheck() {
        for (int column = 0; column < puzzle.length; column++){
            Vector<String> possibleValues = new Vector<String>();
            for (String[] strings : puzzle) {
                if (strings[column].matches(Digit)) {
                    possibleValues.add(strings[column]);
                }
            }
            if (noDuplicate(possibleValues)){
                return false;
            }
        }
        return true;
    }

    private boolean noDuplicate(Vector<String> possibleValues) {
        Vector<String> values = new Vector<String>();
        for (String newValue : possibleValues) {
            if (!values.contains(newValue)) {
                values.add(newValue);
            } else {
                return true;
            }
        }
        return false;
    }

    private boolean boxCheck() {
        for (int[] xCords : indicies) {
            for (int[] yCords : indicies) {
                Vector<String> possibleValues = populateVector(xCords, yCords);
                if (noDuplicate(possibleValues)) {
                    return false;
                }
            }
        }
        return true;
    }

    private Vector<String> populateVector(int[] xCords, int[] yCords) {
        Vector<String> returnVector = new Vector<String>();
        for (int xCord : xCords) {
            for (int yCord : yCords) {
                String value = puzzle[xCord][yCord];
                if (value.matches(Digit)) {
                    returnVector.add(value);
                }
            }
        }
        return returnVector;
    }

    private boolean rowCheck() {
        for (String[] strings : this.puzzle) {
            Vector<String> possibleValues = new Vector<String>();
            for (String value : strings) {
                if (value.matches(Digit)) {
                    possibleValues.add(value);
                }
            }
            if (noDuplicate(possibleValues)) {
                return false;
            }
        }
        return true;
    }
}
