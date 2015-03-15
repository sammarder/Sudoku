package main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowListener;

public interface IController extends ActionListener, WindowListener{
	public void actionPerformed(ActionEvent event);
}
