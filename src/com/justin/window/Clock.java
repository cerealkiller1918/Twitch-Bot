package com.justin.window;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class Clock {
	
	private JFrame frame;
	private JLabel label;
	private Color background = new Color(0, 255, 0);	
	private int WIDTH = 400, HEIGTH = 600;
	
	public Clock() {
		frame = new JFrame();
		frame.setBackground(background);
		
		frame.setSize(WIDTH, HEIGTH);
		frame.setVisible(true);
		
	}
	
}
