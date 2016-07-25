package com.justin.actionListener;

import com.justin.window.Window;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Justin Frasier on 07/22/16.
 */
public class UpTimeClickActionListener implements ActionListener {

    private Window window;

    public UpTimeClickActionListener(Window window) {
        this.window = window;
    }


    public void actionPerformed(ActionEvent e) {
        if (!window.getThreadStatus()) {

            window.startUptime();
        } else {
            System.out.println("Thread Status : " + window.getThreadStatus());
            window.stopUptime();
        }
    }
}
