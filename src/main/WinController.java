package main;

import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

public class WinController extends WindowAdapter implements IController, IObserver, ISubject{
	private WinModel model;
	private WinView view;
	private StatsModel statsModel;
	private ArrayList<IObserver> observers;

	public WinController(WinModel model, WinView view, StatsModel statsModel){
		this.model = model;
		this.view = view;
		this.statsModel = statsModel;
		this.observers = new ArrayList<IObserver>();
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		String actionCommand = event.getActionCommand();
		switch(actionCommand){
		case WinView.WIN_SAVE:
			System.out.print("Win saving\n");
			saveScore();
			break;
		case WinView.WIN_CANCEL:
			System.out.print("Win canceling\n");
			System.exit(0);
			break;
		}
	}

	private void saveScore() {
		String[] entry = new String[3];
		entry[0] = view.getName();
		entry[1] = model.getId();
		entry[2] = model.getTime();
		statsModel.addEntry(entry);
		view.toggleScreen();
		notifyObservers();
		model.setName("");
	}

	public void toggleScreen() {
		view.toggleScreen();
	}

	@Override
	public void windowClosing (WindowEvent e) {
		System.exit(0);
	}

	@Override
	public void update() {
		System.out.print("Win updating\n");
		toggleScreen();
	}

	@Override
	public void addObserver(IObserver observer) {
		if (!observer.equals(null)){
			this.observers.add(observer);
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
