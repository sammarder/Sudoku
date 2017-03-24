package main;

import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.JFileChooser;



public class LoadController extends WindowAdapter implements IController, ISubject {
	private LoadView view;
	private ArrayList<IObserver> observers;
	private LoadModel model;

	public LoadController(LoadView view, LoadModel model){
		this.view = view;
		this.model = model;
		this.observers = new ArrayList<IObserver>();
	}

	public void toggleScreen() {
		view.toggleScreen();
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		String actionCommand = event.getActionCommand();
		switch(actionCommand){
		case LaunchView.LAUNCH_LOAD:
			System.out.print("Load heard launch load\n");
			view.toggleScreen();
			break;
		case JFileChooser.APPROVE_SELECTION:
			System.out.print("Load opening file\n");
			openFile();
			break;
		case JFileChooser.CANCEL_SELECTION:
			System.out.print("Load canceling\n");
			System.exit(0);
		}
	}

	private void openFile() {
		model.setFile((view.getFile()));
		toggleScreen();
		notifyObservers();
	}

	@Override
	public void windowClosing (WindowEvent e) {
		System.exit(0);
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
		for (IObserver observer: observers){
			observer.update();
		}
	}
}
