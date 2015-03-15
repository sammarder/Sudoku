package main;

public class SudokuValidator {
	private String[] digits;
	private int[][] indicies;
	private String[][] puzzle;
	
	public SudokuValidator(){
		this.digits = new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9"};
		this.indicies = new int[][]{{0,1,2},{3,4,5},{6,7,8}};
	}

	public boolean check(String[][] puzzle) {
		//System.out.print("Checking puzzle");
		this.puzzle = puzzle;
		if (!checkRow()){
			return false;
		}
		if (!checkColumn()){
			return false;
		}
		if (!checkBoxes()){
			return false;
		}
		return true;
	}

	private boolean checkBoxes() {
		int[] xCoords;
		int[] yCoords;
		for (int i = 0; i < 3; i++){
			xCoords = indicies[i];
			for (int j = 0; j < 3; j++){
				yCoords = indicies[j];
				String[] box = populateArray(xCoords, yCoords);				
				if(!isUnique(box)){
					return false;
				}
			}
		}
		return true;		
	}

	private String[] populateArray(int[] xCoords, int[] yCoords) {
		String[] array = new String[9];
		int index = 0;
		for (int x = 0; x < xCoords.length; x++){
			for (int y = 0; y < yCoords.length; y++){
				array[index] = this.puzzle[yCoords[y]][xCoords[x]];
				index++;
			}
		}		
		return array;
	}

	private boolean checkColumn() {		
		for (int x = 0; x < 9; x++){
			String[] column = new String[9];
			for (int y = 0; y < 9; y++){
				column[y] = this.puzzle[y][x];
			}
			if (!isUnique(column)){
				return false;
			}
		}
		return true;
		
	}

	

	private boolean checkRow() {
		for (int i = 0; i < puzzle.length; i++){
			if (!isUnique(puzzle[i])){
				return false;
			}
		}
		return true;		
	}
	
	private boolean isUnique(String[] column) {
		String myColumn = createString(column);
		for (int i = 0; i < this.digits.length; i++){
			if (!myColumn.contains(this.digits[i])){				
				return false;
			}
		}
		return true;
	}

	private String createString(String[] column) {
		String returnString = "";
		for (int i = 0; i < column.length; i++){
			returnString = returnString + column[i];
		}
		return returnString;
	}

}
