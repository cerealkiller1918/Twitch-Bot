package com.justin.window;

import javax.swing.*;

/**
 * Created by Justin on 07/22/16.
 */
public class Clock {

    private boolean visible = false;
    private JFrame frame;
    private JPanel panel;
    private JLabel timeLable;

    public Clock(String title){
        frame = new JFrame();
        frame.setContentPane(panel);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setTitle(title);
        frame.pack();
        frame.setVisible(true);


    }

    public void setTime (String time){
        timeLable.setText(time);
    }
    public void setVisible(boolean visible){ this.visible = visible;}


}
