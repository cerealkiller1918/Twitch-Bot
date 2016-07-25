package com.justin.thread;

import com.justin.clock.MainClock;
import com.justin.window.Clock;

/**
 * Created by Justin on 07/22/16.
 */
public class Clock_Thread implements Runnable {

    private MainClock mainClock;
    private Clock clock;

    public Clock_Thread() {
        mainClock = new MainClock();
        clock = new Clock("Clock");

    }

    public void run() {


        while (true) {
            clock.setTime(mainClock.getTime());
        }
    }
}
