package com.justin.button;

import com.justin.window.Window;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by justin on 07/22/16.
 */
public class UpTimeClick implements ActionListener {

    private Window window;

    public UpTimeClick(Window window){
        this.window = window;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!window.isTimeUpRunning() && !window.getThreadStatus()){

            window.startUptime();
        }else{
            System.out.println("Thread Status : " + window.getThreadStatus());
            window.stopUptime();
        }
    }
}
