package com.justin.window;

import com.justin.button.UpTimeClick;
import com.justin.thread.Clock_Thread;
import com.justin.thread.Timeup_Thread;

import javax.swing.*;
import javax.swing.text.DefaultCaret;

public class Window {

	private JFrame frame;
	private JTextArea textArea;
	private JScrollPane pane;
	private JPanel topPanel;
	private DefaultCaret caret;
	private JButton startClockBtn;
	private Thread uptimeThread;
	private Timeup_Thread timeup_thread;
	private boolean timeUpRunning;

	private int WIDTH = 400, HEIGTH = 600;

	public Window() {
		timeUpRunning = false;
		timeup_thread = new Timeup_Thread();
		uptimeThread = new Thread(timeup_thread);
		startClockBtn = new JButton();
		startClockBtn.addActionListener(new UpTimeClick(this));
		startClockBtn.setText("Click Me");
		frame = new JFrame();
		frame.setTitle("Twitch Chat");
		frame.setSize(WIDTH, HEIGTH);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		setupTopPanel();
		frame.add(topPanel);
		frame.add(startClockBtn);
		frame.setVisible(true);

	}

	private void setupTopPanel() {
		topPanel = new JPanel();
		textArea = new JTextArea(20, 32);
		textArea.setText("");
		textArea.setEditable(false);
		textArea.setLineWrap(true);
		pane = new JScrollPane(textArea);
		pane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		pane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		caret = (DefaultCaret) textArea.getCaret();
		caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
		topPanel.add(pane);

	}

	public void updateChatLog(String log) {
		textArea.append(log);
	}

	public void ChatLogSetText(String log) {
		textArea.setText(log);
	}


	public void startUptime(){
		timeUpRunning = true;
		timeup_thread.setClockRunning(timeUpRunning);
		try {
			uptimeThread.start();
		}catch (Exception e) {
			uptimeThread = new Thread(timeup_thread);
			uptimeThread.start();
		}
	}
	public void stopUptime(){
		timeUpRunning = false;
		timeup_thread.setClockRunning(timeUpRunning);
	}

	public boolean isTimeUpRunning(){ return  timeUpRunning; }
	public boolean getThreadStatus(){ return uptimeThread.isAlive();}
}
