package main;

import java.awt.BorderLayout;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class LoadView {
	
	private JFileChooser fileChooser;
	private JFrame mainFrame;

	public LoadView(){
		//This is set for demo purposes to my public folder
		this.fileChooser = new JFileChooser("/home/ssm77/Public/");
		JPanel northPanel = createNorthPanel();
		this.mainFrame = createMainFrame(northPanel);
		
	}

	private JFrame createMainFrame(JPanel northPanel) {
		JFrame frame = new JFrame("Loader");
		frame.add(northPanel, BorderLayout.NORTH);
		frame.pack();
		return frame;
	}

	private JPanel createNorthPanel() {
		JPanel panel = new JPanel();
		panel.add(fileChooser);
		return panel;
	}

	public void toggleScreen() {
		mainFrame.setVisible(!mainFrame.isVisible());
	}

	public void addControl(IController control) {
		this.mainFrame.addWindowListener(control);
		this.fileChooser.addActionListener(control);
	}

	public File getFile(){
		return fileChooser.getSelectedFile();
	}
}
