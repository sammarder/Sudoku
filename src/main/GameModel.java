package main;

import javax.swing.table.AbstractTableModel;

@SuppressWarnings("serial")
public class GameModel extends AbstractTableModel{
	private String gameTimer;
	private int[] count;
	private String[][] original;
	private String[][] puzzle;
	private String id;
	private SudokuValidator validator;

	public GameModel(SudokuValidator validator){
		this.validator = validator;
		this.count = new int[]{0,0,0,0,0,0,0,0,0};
		this.puzzle = new String[][]
				{{"5","3","","","","","","7","2"},
				{"7","","6","4","","9","8","","1"},
				{"","1","","","3","","","4",""},
				{"","6","","9","","1","","2",""},
				{"","","3","","","","6","",""},
				{"","8","","3","","7","","9",""},
				{"","4","","","7","","","5",""},
				{"9","","1","8","","4","7","","3"},
				{"3","7","","","","","","1","8"}};
		this.original = new String[][]
				{{"5","3","","","","","","7","2"},
				{"7","","6","4","","9","8","","1"},
				{"","1","","","3","","","4",""},
				{"","6","","9","","1","","2",""},
				{"","","3","","","","6","",""},
				{"","8","","3","","7","","9",""},
				{"","4","","","7","","","5",""},
				{"9","","1","8","","4","7","","3"},
				{"3","7","","","","","","1","8"}};
		this.id = "default";
	}

	private String[][] hardCopy(String[][] toBeCopied) {
		String[][] matrix = new String[9][9];
		for (int i = 0; i < 9; i++){
			for (int j = 0; j < 9; j++){
				matrix[i][j] = toBeCopied[i][j];
			}
		}
		return matrix;
	}

	@Override
	public int getColumnCount() {
		return puzzle.length;
	}

	@Override
	public int getRowCount() {
		return puzzle.length;
	}

	@Override
	public String getValueAt(int arg0, int arg1) {
		return puzzle[arg0][arg1];
	}

	@Override
	public boolean isCellEditable(int arg0, int arg1) {
		if(original[arg0][arg1].matches("\\d")){
			return false;
		}
		return true;
	}

	@Override
	public void setValueAt(Object arg0, int arg1, int arg2) {
		String newEntry = arg0.toString();
		if (isValid(newEntry, arg1, arg2) || newEntry.equals("")){
			puzzle[arg1][arg2] = newEntry;
		}
	}
	
	private boolean isValid(String newValue, int arg1, int arg2) {
		if (!(arg1 < 9)){
			return false;
		}
		if (!(arg2 < 9)){
			return false;
		}
		if (!(newValue.length() == 1)){
			return false;
		}
		if (!(newValue.matches("\\d"))){
			return false;
		}
		if (newValue.equalsIgnoreCase("0")){
			return false;
		}
		return true;
	}
	
	public String getTime(){
		return gameTimer;
	}
	
	public void setTime(String gameTimer) {
		this.gameTimer = gameTimer;
	}

	public boolean isValid() {
		return validator.check(puzzle);
	}
	
	public void setPuzzle(String[][] puzzle){
		this.puzzle = puzzle;
		this.original = puzzle;
	}

	public void resetPuzzle() {
		puzzle = hardCopy(original);
	}
	
	public String toString(){
		String myString = this.gameTimer + " " + this.id + "\n";
		myString = myString + puzzleToString() + "\n" + originalToString();
		myString = myString.replaceAll(",\n", "\n");
		return myString.trim();
	}

	private String originalToString() {
		String returnString = "";
		for (int i = 0; i < original.length; i++){			
			for (int j = 0; j < original[i].length; j++){
				if (original[j][i].equals("")){
					returnString  = returnString + " ,";
				}
				else{
					returnString = returnString + original[j][i] + ",";
				}
			}
			returnString = returnString + "\n";
		}
		return returnString;
	}

	private String puzzleToString() {
		String returnString = "";
		for (int i = 0; i < puzzle.length; i++){			
			for (int j = 0; j < puzzle[i].length; j++){
				if (puzzle[j][i].equals("")){
					returnString = returnString + " ,";
				}
				else{
					returnString = returnString + puzzle[j][i] + ",";
				}
			}
			returnString = returnString + "\n";
		}
		return returnString;
	}

	public String getId() {
		return id;
	}
	
	public void count(){
		count = new int[]{0,0,0,0,0,0,0,0,0};
		for (int i = 0; i < puzzle.length; i++){
			for (int j = 0; j < puzzle.length; j++){
				if (!puzzle[i][j].equals("")){
					int index = Integer.parseInt(puzzle[i][j]) - 1;
					if (index < count.length){
						count[index]++;
					}
				}
			}
		}
	}
	
	public int getCount(int i){
		return count[i];
	}
	
	public void setId(String id){
		this.id = id;
	}
	
	public void setOriginalAt(String value, int arg1, int arg2){
		original[arg1][arg2] = value;
	}
}