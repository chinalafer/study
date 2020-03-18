package com.lafer.studythread;

public class SetFundThread implements Runnable {

    private Funds funds;

    public SetFundThread(Funds funds) {
        this.funds = funds;
    }

    private int index;

    @Override
    public void run() {
        while (true) {
            synchronized (funds) {
                if (funds.size() >= 5) {
                    try {
                        funds.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                if ((index & 1) == 0) {
                    Fund fund = new Fund("苹果" + index +  "号");
                    System.out.println("生产者生产：苹果" + index +  "号");
                    funds.addFund(fund);
                } else {
                    Fund fund = new Fund("橘子" + index + "号");
                    System.out.println("生产者生产：橘子" + index + "号");
                    funds.addFund(fund);
                }
                index++;
                funds.notify();
            }
        }
    }
}
