package com.justin.window;

import com.justin.button.UpTimeClick;
import com.justin.thread.TimeUp_Thread;

import javax.swing.*;
import javax.swing.text.DefaultCaret;
import java.awt.*;

public class Window {

    private JFrame frame;
    private JTextArea textArea;
    private JScrollPane pane;
    private JPanel topPanel;
    private JPanel bottomPanel;
    private DefaultCaret caret;
    private JButton startClockBtn;
    private Thread uptimeThread;
    private TimeUp_Thread timeUp_thread;
    private boolean timeUpRunning;

    private int WIDTH = 400, HEIGTH = 600;

    public Window() {
        timeUpRunning = false;
        timeUp_thread = new TimeUp_Thread();
        uptimeThread = new Thread(timeUp_thread);
        frame = new JFrame();
        frame.setTitle("Twitch Chat");
        frame.setSize(WIDTH, HEIGTH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new FlowLayout());
        setupTopPanel();
        setupBottonPanel();
        frame.add(topPanel);
        frame.add(bottomPanel);
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

    public void setupBottonPanel() {
        bottomPanel = new JPanel();
        startClockBtn = new JButton();
        startClockBtn.addActionListener(new UpTimeClick(this));
        startClockBtn.setText("Start Up Time");
        bottomPanel.add(startClockBtn);
    }

    public void updateChatLog(String log) {
        textArea.append(log);
    }

    public void ChatLogSetText(String log) {
        textArea.setText(log);
    }


    public void startUptime() {
        timeUpRunning = true;
        timeUp_thread.setClockRunning(timeUpRunning);
        try {
            uptimeThread.start();
        } catch (Exception e) {
            uptimeThread = new Thread(timeUp_thread);
            uptimeThread.start();
        }
    }

    public void stopUptime() {
        timeUpRunning = false;
        timeUp_thread.setClockRunning(timeUpRunning);
    }

    public boolean isTimeUpRunning() {
        return timeUpRunning;
    }

    public boolean getThreadStatus() {
        return uptimeThread.isAlive();
    }
}
