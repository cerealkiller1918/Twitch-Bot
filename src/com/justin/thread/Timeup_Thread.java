package com.justin.thread;

import com.justin.clock.MainClock;
import com.justin.main.Main;
import com.justin.window.Clock;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * Created by justin on 07/22/16.
 */
public class Timeup_Thread implements Runnable{

    private MainClock mainClock;
    private Clock timeUp;
    private boolean clockRunning;

    public Timeup_Thread(){
        clockRunning = true;
        mainClock = new MainClock();
        timeUp = new Clock("Timeup");

    }

    @Override
    public void run(){
        while (clockRunning){
            timeUp.setTime(mainClock.getStreamTime());
        }
    }

    public void setClockRunning(boolean clockRunning){
        this.clockRunning = clockRunning;
    }
}
