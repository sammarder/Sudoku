package main;

import java.awt.BorderLayout;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;


public class GameView{
	public static final String GAME_SAVE = "game save";
	public static final String GAME_CLEAR = "game clear";
	public static final String GAME_CHECK = "game check";
	private JTable game;
	private JButton clear;
	private JButton check;
	private JButton save;
	private JLabel timer;
	private Vector<JLabel> numberTracker;
	private JFrame mainFrame;

	public GameView(GameModel gameModel){
		timer = new JLabel("00:00");
		game = new JTable(gameModel);
		int edge = game.getRowHeight();
		edge = 2 * edge;
		game.setRowHeight(edge);
		for (int i = 0; i < 9; i++){
			game.getColumnModel().getColumn(i).setPreferredWidth(edge);
		}

		JPanel northPanel = createNorthPanel();
		JPanel centerPanel = createCenterPanel();
		JPanel southPanel = createSouthPanel();
		mainFrame = createMainFrame(northPanel, centerPanel, southPanel);
	}

	private JFrame createMainFrame(JPanel northPanel, JPanel centerPanel, JPanel southPanel) {
		JFrame frame = new JFrame ("Sudoku Game");
		frame.add(northPanel, BorderLayout.NORTH);
		frame.add(centerPanel, BorderLayout.CENTER);
		frame.add(southPanel, BorderLayout.SOUTH);
		frame.pack();
		return frame;
	}

	private JPanel createSouthPanel() {
		JPanel panel = new JPanel();
		check = new JButton("Check");
		check.setActionCommand(GAME_CHECK);
		clear = new JButton("Clear");
		clear.setActionCommand(GAME_CLEAR);
		save = new JButton("Save");
		save.setActionCommand(GAME_SAVE);
		panel.add(save);
		panel.add(clear);
		panel.add(check);
		return panel;
	}

	private JPanel createCenterPanel() {
		JPanel panel = new JPanel();
		JLabel label = new JLabel("Numbers left:");
		numberTracker = new Vector<JLabel>();
		for (int i = 1; i < 10; i++){
			numberTracker.add(new JLabel(Integer.toString(i)));
		}
		panel.add(label);
		for (int i = 0; i < numberTracker.size(); i++){
			panel.add(numberTracker.get(i));
		}
		return panel;
	}

	private JPanel createNorthPanel() {
		JPanel panel = new JPanel();
		panel.add(game, BorderLayout.CENTER);
		panel.add(timer, BorderLayout.EAST);
		return panel;
	}

	public void addControl(IController controller, IController saveController){
		clear.addActionListener(controller);
		check.addActionListener(controller);
		save.addActionListener(controller);
		save.addActionListener(saveController);
		mainFrame.addWindowListener(controller);
	}

	public void update(){
		this.game.repaint();
	}

	public void updateTimer(String time) {
		this.timer.setText(time);
	}

	public void hide(int i){
		numberTracker.get(i).setVisible(false);
	}

	public void toggleScreen(){
		this.mainFrame.setVisible(!mainFrame.isVisible());
	}

	public void show(int i) {
		if (!numberTracker.get(i).isVisible()){
			numberTracker.get(i).setVisible(true);
		}
	}
}
