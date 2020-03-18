package com.lafer.studythread;

public class GetFundThread implements Runnable {

    private Funds funds;

    public GetFundThread (Funds funds) {
        this.funds = funds;
    }

    @Override
    public void run() {
        while (true) {
            synchronized(funds) {
                if (funds.size() == 0) {
                    try {
                        funds.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("消费者购买：" + funds.getfund().getName());
                funds.notify();
            }
        }
    }
}
