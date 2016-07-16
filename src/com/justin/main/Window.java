package com.justin.main;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.text.DefaultCaret;

public class Window {
	
	private JFrame frame;
	private JTextArea textArea;
	private JScrollPane pane;
	private JPanel topPanel;
	private DefaultCaret caret;
	
	private int WIDTH = 400, HEIGTH = 600;
	
	public Window(){
		frame = new JFrame();
		frame.setTitle("Twitch Chat");
		frame.setSize(WIDTH, HEIGTH);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		setupTopPanel();
		frame.add(topPanel);
		frame.setVisible(true);

	}
	
	private void setupTopPanel(){
		topPanel = new JPanel();
		textArea = new JTextArea(20,32);
		textArea.setText("");
		textArea.setEditable(false);
		textArea.setLineWrap(true);
		pane = new JScrollPane(textArea);
		pane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		pane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		caret = (DefaultCaret)textArea.getCaret();
		caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
		topPanel.add(pane);
		
	}
		
	
	public void updateChatLog(String log){
		textArea.append(log);
	}
}
