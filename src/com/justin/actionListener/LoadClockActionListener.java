package com.justin.actionListener;

import com.justin.thread.Clock_Thread;
import com.justin.thread.TimeUp_Thread;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Justin Frasier on 07/25/16.
 */
public class LoadClockActionListener implements ActionListener{

    private Thread clockThread = null;
    private Thread timeUpThread = null;

    public void actionPerformed(ActionEvent e) {
        if(clockThread == null) clockThread = new Thread(new Clock_Thread());
        if(timeUpThread == null) timeUpThread = new Thread(new TimeUp_Thread());
        if(!clockThread.isAlive()) clockThread.start();
        else clockThread.stop();
        if (!timeUpThread.isAlive()) timeUpThread.start();
        else timeUpThread.stop();


    }
}
