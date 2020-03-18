package com.lafer.studythread;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.logging.SimpleFormatter;

public class MyTimerMain {

    public static void main(String[] args) throws ParseException {
        Timer timer = new Timer();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date parse = simpleDateFormat.parse("2020-01-09 23:31:00");
        timer.schedule(new MyTimerTask(timer), parse);
    }

}
