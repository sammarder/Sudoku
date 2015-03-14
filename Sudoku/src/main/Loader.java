package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Vector;

public class Loader {
	private GameModel gameModel;
	private Vector<String> data;
	private int[] puzzleIndicies;
	private int[] originalIndicies;
	private PuzzleValidator validator;
	private String[][] puzzle;
	
	public Loader(GameModel gameModel, PuzzleValidator validator){
		this.gameModel = gameModel;
		this.data = new Vector<String>();
		this.puzzleIndicies = new int[]{1,2,3,4,5,6,7,8,9};
		this.originalIndicies = new int[]{11,12,13,14,15,16,17,18,19};
		this.validator = validator;
	}
	
	public void load(File file){
		Scanner fileScanner = null;
		try {
			fileScanner = new Scanner(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		while (fileScanner.hasNextLine()){
			String line = fileScanner.nextLine();
			data.add(line);
		}
		fileScanner.close();
		if (isValid()){
			setGameModel();
		}
		else{
			System.out.print("Invalid file\n");
		}
	}

	private boolean isValid() {
		if (data.size() != 20){
			System.out.print("Data is not the right size\n");
			return false;
		}
		String headerRegex = "\\d\\d:\\d\\d\\s[a-zA-Z]*";
		String puzzleRegex = "([0-9]|\\s),([0-9]|\\s),([0-9]|\\s),([0-9]|\\s),([0-9]|\\s)," +
				"([0-9]|\\s),([0-9]|\\s),([0-9]|\\s),([0-9]|\\s)";
		if (!data.get(0).trim().matches(headerRegex)){
			System.out.print("Header doesnt match regex\n");
			return false;
		}
		for (int i = 0; i < puzzleIndicies.length; i++){
			int index = puzzleIndicies[i];
			if (!data.get(index).matches(puzzleRegex)){
				System.out.print("Puzzle line does not match regex\n");
				return false;
			}
		}
		for (int i = 0; i < originalIndicies.length; i++){
			int index = originalIndicies[i];
			if (!data.get(index).matches(puzzleRegex)){
				System.out.print("Original line does not match regex\n");
				return false;
			}
		}
		puzzle = populatePuzzle();
		if (!validator.check(puzzle)){
			return false;
		}
		return true;
	}

	private void setGameModel() {
		String[] header = data.get(0).split(" ");
		gameModel.setTime(header[0]);
		gameModel.setId(header[1]);
		populateGamePuzzle(puzzle);
		populateGameOriginal(populateOriginal());
	}

	private void populateGameOriginal(String[][] original) {
		for (int i = 0; i < original.length; i++){
			for (int j = 0; j < original[i].length; j++){
				String arg0 = original[i][j];
				if (arg0.equals(" ")){
					gameModel.setOriginalAt("", i, j);
				}
				else {
					gameModel.setOriginalAt(arg0, i, j);
				}
			}
		}
	}

	private void populateGamePuzzle(String[][] puzzle) {
		for (int i = 0; i < puzzle.length; i++){
			for (int j = 0; j < puzzle[i].length; j++){
				String arg0 = puzzle[i][j];
				if (arg0.equals(" ")){
					gameModel.setValueAt("", i, j);
				}
				else {
					gameModel.setValueAt(arg0, i, j);
				}
			}
		}
	}

	private String[][] populateOriginal() {
		String[][] original = new String[9][9];
		for (int i = 0; i < originalIndicies.length; i++){
			int index = originalIndicies[i];
			String[] line = data.get(index).split(",");
			for (int j = 0; j < line.length; j++){
				original[j][i] = line[j];
			}
		}
		return original;
	}

	private String[][] populatePuzzle() {
		String[][] puzzle = new String[9][9];
		for (int i = 0; i < puzzleIndicies.length; i++){
			int index = puzzleIndicies[i];
			String[] line = data.get(index).split(",");
			for (int j = 0; j < line.length; j++){
				puzzle[j][i] = line[j];
			}
		}
		return puzzle;
	}
}
