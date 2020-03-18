package com.lafer.studythread;

public class FundsDemo {

    public static void main(String[] args) {

        Funds funds = new Funds();
        GetFundThread getFundThread = new GetFundThread(funds);
        SetFundThread setFundThread = new SetFundThread(funds);

        Thread thread1 = new Thread(getFundThread);
        Thread thread2 = new Thread(setFundThread);

        thread1.start();
        thread2.start();

    }

}
