package main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.Timer;

public class GameController extends WindowAdapter implements IController, ISubject, IObserver {
	private GameModel model;
	private WinModel winModel;
	private GameView view;
	private int seconds;
	private int minutes;
	private Map<String, Integer> numbers;
	private Timer gameTimer;
	private ArrayList<IObserver> observers;
	private LoadModel loadModel;
	private SaveModel saveModel;
	private Loader loader;
	
	public GameController(GameModel model, GameView view, WinModel winModel, LoadModel loadModel, 
			SaveModel saveModel, Loader loader){
		this.model = model;
		this.view = view;
		this.winModel = winModel;
		this.loadModel = loadModel;
		this.observers = new ArrayList<IObserver>();
		this.numbers = new HashMap<String, Integer>();
		this.saveModel = saveModel;
		this.loader = loader;
		for(int i = 1; i <= 9; i++){
			String key = Integer.toString(i);
			numbers.put(key, 0);
		}
		this.gameTimer = new Timer(1000, new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				incrementTimer();
				clockTimer();
				count();
			}
		});	
	}
	
	@Override
	public void actionPerformed(ActionEvent event) {
		String actionCommand = event.getActionCommand();
		switch(actionCommand){
		case GameView.GAME_CHECK:
			System.out.print("Game checking\n");
			check();
			break;
		case GameView.GAME_CLEAR:
			System.out.print("Game clearing\n");
			clear();
			break;
		case GameView.GAME_SAVE:
			System.out.print("Game saving\n");
			save();
			break;
		case LaunchView.LAUNCH_PLAY:
			System.out.print("Game heard launch playing\n");
			play();
			break;
		case CreateGameView.CREATE_PLAY:
			System.out.print("Game heard create playing\n");
			play();
			break;
		case SaveView.SAVE_CANCEL:
			System.out.print("Game heard save cancel\n");
			saveCancel();
			break;
		}
	}

	private void saveCancel() {
		toggleScreen();
		gameTimer.start();
	}

	private void play() {
		toggleScreen();	
		resetTimer();
		gameTimer.start();
	}

	private void check() {
		if (model.isValid()){
			winModel.setTime(model.getTime());
			winModel.setPuzzleId(model.getId());
			notifyObservers();
			toggleScreen();			
		}
	}

	private void resetTimer() {
		minutes = 0;
		seconds = 0;
	}

	private void clear() {
		model.resetPuzzle();
		resetTimer();
		view.update();		
	}

	private void save() {
		saveModel.save(model.toString());
		gameTimer.stop();
		toggleScreen();
	}
	
	private void incrementTimer() {
		seconds++;
		if (seconds == 60){
			seconds = 0;
			minutes++;
		}		
	}	
	
	private void clockTimer(){
		String time = formatTime(minutes,seconds);
		view.updateTimer(time);
		model.setTime(time);
	}
	
	private String formatTime(int min, int sec) {
		String time = "";
		if (min < 10){
			time = time + "0" + min; 
		}
		else {
			time = time + min;
		}
		time = time + ":";
		if (sec < 10){
			time = time + "0" + sec; 
		}
		else {
			time = time + sec;
		}
		return time;
	}

	private void count(){
		model.count();
		for (int i = 0; i < 9; i++){
			if (model.getCount(i) == 9){
				view.hide(i);
			}
			else {
				view.show(i);
			}
		}
	}
	
	public void toggleScreen() {
		view.toggleScreen();		
	}	
	
	@Override
	public void windowClosing (WindowEvent e) { 
		System.exit(0); 
	}
	
	private void loadGame(File file){
		if (!file.equals(null)){
			loader.load(file);
		}
		gameTimer.start();
	}

	@Override
	public void update() {
		System.out.print("Game controller updating\n");
		toggleScreen();
		loadGame(loadModel.getFile());
		String time = model.getTime();
		String[] stuff = time.split(":");
		this.minutes = Integer.parseInt(stuff[0]);
		this.seconds = Integer.parseInt(stuff[1]);
	}

	@Override
	public void addObserver(IObserver observer) {
		if (!observer.equals(null)){
			observers.add(observer);
		}
	}

	@Override
	public void removerObserver(IObserver observer) {
		if (observers.contains(observer)){
			observers.remove(observer);
		}
	}

	@Override
	public void notifyObservers() {
		for (IObserver observer : observers){
			observer.update();
		}
	}
}
