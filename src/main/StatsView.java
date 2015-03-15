package main;

import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;


public class StatsView {
	public static final String STATS_CLOSE = "stats close";
	public static final String STATS_CLEAR = "stats clear";
	public static final String STATS_MAIN = "stats main";
	private JFrame mainFrame;
	private JTable scores;
	private JButton clear;
	private JButton main;
	private JButton close;

	public StatsView(StatsModel model){
		this.scores = new JTable(model);				
		JPanel northPanel = createNorthPanel();
		JPanel southPanel = createSouthPanel();
		mainFrame = createMainFrame(northPanel, southPanel); 		
	}

	private JFrame createMainFrame(JPanel northPanel, JPanel southPanel) {
		JFrame frame = new JFrame("High Scores");
		frame.add(northPanel, BorderLayout.NORTH);
		frame.add(southPanel, BorderLayout.SOUTH);
		frame.pack();
		return frame;
	}

	private JPanel createSouthPanel() {
		JPanel panel = new JPanel();
		clear = new JButton("Clear");
		close = new JButton("Close");
		main = new JButton("Main");
		clear.setActionCommand(STATS_CLEAR);
		close.setActionCommand(STATS_CLOSE);
		main.setActionCommand(STATS_MAIN);
		panel.add(clear, BorderLayout.CENTER);
		panel.add(main, BorderLayout.CENTER);
		panel.add(close, BorderLayout.CENTER);
		return panel;
	}

	private JPanel createNorthPanel() {
		JPanel panel = new JPanel();
		panel.add(scores);
		return panel;
	}

	
	public void toggleScreen() {
		mainFrame.setVisible(!mainFrame.isVisible());
		update();
	}

	
	public void addControl(IController control, IController launchController) {
		clear.addActionListener(control);
		close.addActionListener(control);
		main.addActionListener(control);
		main.addActionListener(launchController);
		mainFrame.addWindowListener(control);
	}
	
	public void update(){
		scores.repaint();
	}
}
