package main;

import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class LaunchView{
	public static final String LAUNCH_HELP = "launch help";
	public static final String LAUNCH_STATS = "launch stats";
	public static final String LAUNCH_PLAY = "launch play";
	public static final String LAUNCH_CREATE = "launch create";
	public static final String LAUNCH_LOAD = "launch load";
	private JFrame mainFrame;
	private JButton load;
	private JButton create;
	private JButton play;
	private JButton stats;

	public LaunchView(){
		load = new JButton("Load");
		create = new JButton("Create");
		play = new JButton("Play");
		stats = new JButton("Stats");
		load.setActionCommand(LAUNCH_LOAD);
		create.setActionCommand(LAUNCH_CREATE);
		play.setActionCommand(LAUNCH_PLAY);
		stats.setActionCommand(LAUNCH_STATS);
		JPanel labelPanel = createLabelPanel();
		JPanel buttonPanel = createButtonPanel();
		mainFrame = createMainFrame(labelPanel, buttonPanel);
	}

	private JPanel createLabelPanel() {
		JLabel label = new JLabel("Welcome to Sudoku the Game!");
		JPanel panel = new JPanel();
		panel.add(label);
		return panel;
	}

	private JPanel createButtonPanel() {
		JPanel panel = new JPanel();
		panel.add(create);
		panel.add(load);
		panel.add(play);
		panel.add(stats);
		return panel;
	}

	private JFrame createMainFrame(JPanel labelPanel, JPanel buttonPanel) {
		JFrame frame = new JFrame ("Sudoku Game");
		frame.add(labelPanel, BorderLayout.NORTH);
		frame.add(buttonPanel, BorderLayout.SOUTH);
		frame.pack();
		return frame;
	}

	public void addControllers(IController launchController, IController gameController,
			IController statsController, IController createGameController, IController loadController){
		create.addActionListener(launchController);
		create.addActionListener(createGameController);
		load.addActionListener(launchController);
		load.addActionListener(loadController);
		play.addActionListener(launchController);
		play.addActionListener(gameController);
		stats.addActionListener(launchController);
		stats.addActionListener(statsController);
		mainFrame.addWindowListener(launchController);
	}

	public void toggleScreen() {
		this.mainFrame.setVisible(!mainFrame.isVisible());
	}
}
