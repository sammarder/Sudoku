package main;

import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;



public class LaunchController extends WindowAdapter implements IController{
	private LaunchView view;

	public LaunchController(LaunchView view){
		this.view = view;
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		String actionCommand = event.getActionCommand();
		switch(actionCommand){
		case LaunchView.LAUNCH_LOAD:
			System.out.print("Launch loading\n");
			toggleScreen();
			break;
		case LaunchView.LAUNCH_PLAY:
			System.out.print("Launch playing\n");
			toggleScreen();
			break;
		case LaunchView.LAUNCH_CREATE:
			System.out.print("Launch creating\n");
			toggleScreen();
			break;
		case LaunchView.LAUNCH_STATS:
			System.out.print("Launch stats\n");
			toggleScreen();
			break;
		case StatsView.STATS_MAIN:
			System.out.print("Launch heard stats main\n");
			toggleScreen();
			break;
		}
	}

	public void toggleScreen(){
		view.toggleScreen();
	}

	@Override
	public void windowClosing (WindowEvent e) {
		System.exit(0);
	}
}
