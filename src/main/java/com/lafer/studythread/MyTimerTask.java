package com.lafer.studythread;

import java.util.Timer;
import java.util.TimerTask;

public class MyTimerTask extends TimerTask {

    private Timer timer;

    public MyTimerTask(Timer timer) {
        this.timer = timer;
    }

    @Override
    public void run() {


        System.out.println("hello world");


        timer.cancel();
    }
}
