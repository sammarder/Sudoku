package main;

import javax.swing.table.AbstractTableModel;

@SuppressWarnings("serial")
public class CreateGameModel extends AbstractTableModel{
	public static final String CREATE_DEFAULT = "a;lskdjf;alksdjz;lkjvc";
	private String[][] puzzle;
	private PuzzleValidator validator;

	public CreateGameModel(PuzzleValidator validator){
		this.puzzle = new String[][]
				{{"","","","","","","","",""},{"","","","","","","","",""},{"","","","","","","","",""},
				{"","","","","","","","",""},{"","","","","","","","",""},{"","","","","","","","",""},
				{"","","","","","","","",""},{"","","","","","","","",""},{"","","","","","","","",""}};
		this.validator = validator;
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
	public Object getValueAt(int arg0, int arg1) {
		return puzzle[arg1][arg0];
	}

	@Override
	public boolean isCellEditable(int arg0, int arg1) {
		return true;
	}

	@Override
	public void setValueAt(Object arg0, int arg1, int arg2) {
		String newValue = arg0.toString();
		if(isValid(newValue, arg1, arg2)){
			puzzle[arg2][arg1] = newValue;
		}
	}

	private boolean isValid(String newValue, int arg1, int arg2) {
		if (!(arg1 < 9) || !(arg2 < 9)){
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

	public String toString(){
		String myString = "00:00 " + CREATE_DEFAULT + "\n";
		myString = puzzleToString() + "\n" + puzzleToString();
		myString = myString.replaceAll(",\n", "\n");
		return myString.trim();
	}

	private String puzzleToString() {
		String returnString = "";
		for (int i = 0; i < puzzle.length; i++){
			for (int j = 0; j < puzzle[i].length; j++){
				if (puzzle[j][i].equals("")){
					returnString += " ,";
				}
				else{
					returnString += puzzle[j][i] + ",";
				}
			}
			returnString += "\n";
		}
		return returnString;
	}

	public void clear() {
		for (int i = 0; i < puzzle.length; i++){
			for (int j = 0; j < puzzle[i].length; j++){
				puzzle[i][j] = "";
			}
		}
	}

	public void save(){
		if(isValid()){
			System.out.print("Saving your new puzzle\n");
		}
	}

	public boolean isValid() {
		return validator.check(puzzle);
	}
}
