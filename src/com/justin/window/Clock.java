package com.justin.window;

import javax.swing.*;

/**
 * Created by Justin Frasier on 07/22/16.
 */
public class Clock {

    private JFrame frame;
    private JPanel panel;
    private JLabel timeLabel;

    public Clock(String title) {
        frame = new JFrame();
        frame.setContentPane(panel);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setTitle(title);
        frame.pack();
        frame.setVisible(true);


    }

    public void setTime(String time) { timeLabel.setText(time); }


}
