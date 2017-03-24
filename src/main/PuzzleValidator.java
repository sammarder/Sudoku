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
		//puzzle[row][column]
		for (int column = 0; column < puzzle.length; column++){
			Vector<String> possibleValues = new Vector<String>();
			for (int row = 0; row < puzzle.length; row++){
				if (puzzle[row][column].matches(Digit)){
					possibleValues.add(puzzle[row][column]);
				}
			}
			if (!noDuplicate(possibleValues)){
				return false;
			}
		}
		return true;
	}

	private boolean noDuplicate(Vector<String> possibleValues) {
		Vector<String> values = new Vector<String>();
		for (int i = 0; i < possibleValues.size(); i++){
			String newValue = possibleValues.get(i);
			if (!values.contains(newValue)){
				values.add(newValue);
			}
			else return false;
		}
		return true;
	}

	private boolean boxCheck() {
		//3 is the length of each element of the indicies matrix
		for (int i = 0; i < 3; i++){
			int[] xCoords = indicies[i];
			for (int j = 0; j < 3; j++){
				int[] yCoords = indicies[j];
				Vector<String> possibleValues = populateVector(xCoords, yCoords);
				if (!noDuplicate(possibleValues)){
					return false;
				}
			}
		}
		return true;
	}

	private Vector<String> populateVector(int[] xCoords, int[] yCoords) {
		Vector<String> returnVector = new Vector<String>();
		for (int x = 0; x < xCoords.length; x++){
			for (int y = 0; y < yCoords.length; y++){
				String value = puzzle[xCoords[x]][yCoords[y]];
				if (value.matches(Digit)){
					returnVector.add(value);
				}
			}
		}
		return returnVector;
	}

	private boolean rowCheck() {
		for (int row = 0; row < this.puzzle.length; row++){
			Vector<String> possibleValues = new Vector<String>();
			for (int column = 0; column < puzzle[row].length; column++){
				String value = puzzle[row][column];
				if (value.matches(Digit)){
					possibleValues.add(value);
				}
			}
			if (!noDuplicate(possibleValues)){
				return false;
			}
		}
		return true;
	}
}
