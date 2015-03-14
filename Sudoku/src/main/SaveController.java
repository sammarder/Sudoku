package main;

import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class SaveController extends WindowAdapter implements IController {

	private SaveView view;
	private SaveModel model;
	private boolean createSave;

	public SaveController(SaveView view, SaveModel model){
		this.view = view;
		this.model = model;
	}
	
	@Override
	public void windowClosing (WindowEvent e) { 
		System.exit(0); 
	}

	public void toggleScreen() {
		view.toggleScreen();
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		String actionCommand = event.getActionCommand();
		switch(actionCommand){
		case CreateGameView.CREATE_SAVE:
			System.out.print("Save heard create save\n");
			createSave();
			break;
		case GameView.GAME_SAVE:
			System.out.print("Save heard game save\n");
			gameSave();
			break;
		case SaveView.SAVE_CANCEL:
			System.out.print("Save canceling\n");
			cancel();
			break;
		case SaveView.SAVE_SAVE:
			System.out.print("Save saving\n");
			save();
			break;
		}
	}

	private void save() {
		String fileName = view.getFileName();
		String data = "";
		if (isCreated()){
			data = model.getData().replace(CreateGameModel.CREATE_DEFAULT, fileName);
		}
		else if (!isCreated()){
			data = model.getData();
		}
		fileName = fileName + ".txt";
		try {
			PrintWriter writer = new PrintWriter(fileName);
			writer.write(data);
			writer.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
	}

	private boolean isCreated() {
		return createSave;
	}

	private void cancel() {
		toggleScreen();
	}

	private void gameSave() {
		toggleScreen();
	}

	private void createSave() {
		createSave = true;
		toggleScreen();
	}
}
