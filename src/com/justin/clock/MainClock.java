package com.justin.clock;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Justin on 07/22/16.
 */
public class MainClock {

    private DateFormat dateFormat;
    private long startTime;
    private long detla = 21600000;

    public MainClock() {

        dateFormat = new SimpleDateFormat("HH:mm:ss");
        startTime = new Date().getTime();
    }


    public String getTime() {
        return dateFormat.format(new Date());
    }

    public String getStreamTime() {
        long now = new Date().getTime();
        long timeUp = (now - startTime) + detla;
        Date date = new Date();
        date.setTime(timeUp);
        return dateFormat.format(date);
    }

    public void startStreamTime() {
        startTime = new Date().getTime();
    }
}
