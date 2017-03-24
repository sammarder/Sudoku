package main;

import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;

public class CreateGameView {
	public static final String CREATE_HELP = "create help";
	public static final String CREATE_CLOSE = "create close";
	public static final String CREATE_SAVE = "create save";
	public static final String CREATE_CLEAR = "create clear";
	public static final String CREATE_PLAY = "create play";
	private JFrame mainFrame;
	private JTable puzzle;
	private JButton save;
	private JButton clear;
	private JButton close;
	private JButton help;
	private JButton play;

	public CreateGameView(CreateGameModel createGameModel){
		puzzle = new JTable(createGameModel);
		int edge = puzzle.getRowHeight();
		edge = 2 * edge;
		puzzle.setRowHeight(edge);
		for (int i = 0; i < 9; i++){
			puzzle.getColumnModel().getColumn(i).setPreferredWidth(edge);
		}
		JPanel northPanel = createNorthPanel();
		JPanel southPanel = createSouthPanel();
		mainFrame = createMainFrame(northPanel, southPanel);
	}

	private JPanel createSouthPanel() {
		save = new JButton("Save");
		save.setActionCommand(CREATE_SAVE);
		clear = new JButton("Clear");
		clear.setActionCommand(CREATE_CLEAR);
		close = new JButton("Close");
		close.setActionCommand(CREATE_CLOSE);
		help = new JButton("Help");
		help.setActionCommand(CREATE_HELP);
		play = new JButton("Play");
		play.setActionCommand(CREATE_PLAY);
		JPanel panel = new JPanel();
		panel.add(save);
		panel.add(clear);
		panel.add(play);
		panel.add(close);
		return panel;
	}

	private JPanel createNorthPanel() {
		JPanel panel = new JPanel();
		panel.add(puzzle);
		return panel;
	}

	private JFrame createMainFrame(JPanel northPanel, JPanel southPanel) {
		JFrame frame = new JFrame("Create New Game");
		frame.add(northPanel, BorderLayout.NORTH);
		frame.add(southPanel, BorderLayout.SOUTH);
		frame.pack();
		return frame;
	}

	public void toggleScreen() {
		mainFrame.setVisible(!mainFrame.isVisible());
	}

	public void addControl(IController control, IController gameController, IController saveController) {
		mainFrame.addWindowListener(control);
		save.addActionListener(control);
		save.addActionListener(saveController);
		close.addActionListener(control);
		clear.addActionListener(control);
		play.addActionListener(control);
		play.addActionListener(gameController);
	}

	public void update() {
		puzzle.repaint();
	}
}
