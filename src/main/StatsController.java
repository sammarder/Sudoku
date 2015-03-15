package main;

import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class StatsController extends WindowAdapter implements IController, IObserver{
	private StatsView view;
	private StatsModel model;

	public StatsController(StatsView view, StatsModel model) {
		this.view = view;
		this.model = model;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		String actionCommand = arg0.getActionCommand();
		switch(actionCommand){
		case LaunchView.LAUNCH_STATS:
			System.out.print("Stats heard launch stats\n");
			toggleScreen();
			break;
		case StatsView.STATS_CLEAR:
			System.out.print("Stats clearing\n");
			clear();			
			break;
		case StatsView.STATS_CLOSE:
			System.out.print("Stats closinging\n");
			System.exit(0);
			break;
		case StatsView.STATS_MAIN:
			System.out.print("Stats return to launch\n");
			toggleScreen();
			break;
		}
	}

	private void clear() {
		model.clear();
		view.update();
	}
	
	public void toggleScreen(){
		view.toggleScreen();
	}
	
	@Override
	public void windowClosing (WindowEvent e) { 
		System.exit(0); 
	}

	@Override
	public void update() {
		System.out.print("Stats updating\n");
		toggleScreen();
	}
}
