package main;

import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class CreateGameController extends WindowAdapter implements IController {
	private CreateGameView view;
	private CreateGameModel model;
	private GameModel gameModel;
	private SaveModel saveModel;
	
	public CreateGameController(CreateGameView view, CreateGameModel model, 
			GameModel gameModel, SaveModel saveModel){
		this.view = view;
		this.model = model;
		this.gameModel = gameModel;
		this.saveModel = saveModel;
	}
	
	public void toggleScreen() {
		view.toggleScreen();
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		String actionCommand = event.getActionCommand();
		switch (actionCommand){
		case LaunchView.LAUNCH_CREATE:
			System.out.print("Create heard launch create\n");
			toggleScreen();
			break;
		case CreateGameView.CREATE_SAVE:
			System.out.print("Create saving\n");
			save();
			break;
		case CreateGameView.CREATE_CLEAR:
			System.out.print("Create clearing\n");
			clear();
			break;
		case CreateGameView.CREATE_CLOSE:
			System.out.print("Create closing\n");
			System.exit(0);
			break;
		case CreateGameView.CREATE_PLAY:
			System.out.print("Create playing\n");
			play();
			break;
		}
	}

	private void clear() {
		model.clear();
		view.update();
	}

	private void save() {
		if(model.isValid()){
			saveModel.save(model.toString());
			toggleScreen();
		}		
	}

	private void play() {
		for (int i = 0; i < model.getColumnCount(); i++){
			for (int j = 0; j < model.getRowCount(); j++){
				Object arg0 = model.getValueAt(i, j);
				gameModel.setValueAt(arg0, i, j);
			}
		}
		gameModel.setId("user made");
		toggleScreen();
	}

	@Override
	public void windowClosing (WindowEvent e) { 
		System.exit(0); 
	}
}
