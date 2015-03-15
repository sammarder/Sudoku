package main;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class SaveView {	
	public static final String SAVE_SAVE = "save save";
	public static final String SAVE_CANCEL = "save cancel";
	private JFrame mainFrame;
	private JButton save;
	private JButton cancel;
	private JTextField fileName;
	
	public SaveView(SaveModel model){
		JPanel northPanel = createNorthPanel();
		JPanel southPanel = createSouthPanel();
		mainFrame = createMainFrame(northPanel, southPanel);
	}
	
	private JPanel createSouthPanel() {
		JPanel panel = new JPanel();
		this.save = new JButton("Save");
		save.setActionCommand(SAVE_SAVE);
		this.cancel = new JButton("Cancel");
		cancel.setActionCommand(SAVE_CANCEL);
		panel.add(save);
		panel.add(cancel);
		return panel;
	}

	private JPanel createNorthPanel() {
		JPanel panel = new JPanel();
		fileName = new JTextField();
		JLabel prompt = new JLabel("File name:");
		panel.setLayout(new GridLayout(0,2));
		panel.add(prompt, BorderLayout.WEST);
		panel.add(fileName, BorderLayout.EAST);
		return panel;
	}

	private JFrame createMainFrame(JPanel northPanel, JPanel southPanel) {
		JFrame frame = new JFrame("Save");
		frame.add(northPanel, BorderLayout.NORTH);
		frame.add(southPanel, BorderLayout.SOUTH);
		frame.pack();
		return frame;
	}

	public void toggleScreen(){
		this.mainFrame.setVisible(!mainFrame.isVisible());
	}

	public void addControl(IController controller) {
		save.addActionListener(controller);
		cancel.addActionListener(controller);
		mainFrame.addWindowListener(controller);
	}

	public String getFileName() {
		return this.fileName.getText();
	}
}
