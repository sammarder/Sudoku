package main;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class WinView {
	public static final String WIN_CANCEL = "win cancel";
	public static final String WIN_SAVE = "win save";
	private JFrame mainFrame;
	private JTextField nameText;
	private JButton save;
	private JButton cancel;

	public WinView(WinModel model){
		JPanel northPanel = createNorthPanel();
		JPanel centerPanel = createCenterPanel();
		JPanel southPanel = createSouthPanel();
		mainFrame = createMainFrame(northPanel, centerPanel, southPanel);
	}

	private JPanel createNorthPanel() {
		JLabel text = new JLabel("Congrats!");
		JPanel panel = new JPanel();
		panel.add(text);
		return panel;
	}

	private JPanel createSouthPanel() {
		save = new JButton("Ok");
		save.setActionCommand(WIN_SAVE);
		cancel = new JButton("Cancel");
		cancel.setActionCommand(WIN_CANCEL);
		JPanel panel = new JPanel();
		//panel.setLayout(new GridLayout(0, 2));
		panel.add(save);
		panel.add(cancel);
		return panel;
	}

	private JPanel createCenterPanel() {
		nameText = new JTextField();
		nameText.setActionCommand("win name text");
		JLabel prompt = new JLabel("Name:");
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(0,2));
		panel.add(prompt);
		panel.add(nameText);
		return panel;
	}

	private JFrame createMainFrame(JPanel northPanel, JPanel centerPanel,
			JPanel southPanel) {
		JFrame frame = new JFrame("WIN");
		frame.add(northPanel, BorderLayout.NORTH);
		frame.add(centerPanel, BorderLayout.CENTER);
		frame.add(southPanel, BorderLayout.SOUTH);
		frame.pack();
		return frame;
	}

	public void toggleScreen() {
		mainFrame.setVisible(!mainFrame.isVisible());
	}
	
	public String getName(){
		return nameText.getText();
	}

	public void addControl(IController control) {
		save.addActionListener(control);
		cancel.addActionListener(control);
		nameText.addActionListener(control);
		mainFrame.addWindowListener(control);
	}
}
