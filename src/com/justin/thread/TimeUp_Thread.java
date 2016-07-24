package com.justin.thread;

import com.justin.clock.MainClock;
import com.justin.window.Clock;

/**
 * Created by justin on 07/22/16.
 */
public class TimeUp_Thread implements Runnable {

    private MainClock mainClock;
    private Clock timeUp;
    private boolean clockRunning;

    public TimeUp_Thread() {
        clockRunning = true;
        mainClock = new MainClock();
        timeUp = new Clock("Timeup");

    }

    @Override
    public void run() {

        mainClock.startStreamTime();

        while (clockRunning) {
            timeUp.setTime(mainClock.getStreamTime());
        }
    }

    public void setClockRunning(boolean clockRunning) {
        this.clockRunning = clockRunning;
    }
}
