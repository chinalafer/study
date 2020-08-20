package com.lafer.designpatterns.staitcproxy;

public class StaticProxy {

    public static void main(String[] args) {
        People lafer = new People("lafer");
        MarryCompany marryCompany = new MarryCompany(lafer);
        marryCompany.marry();

        new MarryCompany(() -> {
            System.out.println("我结婚啦");
        }).marry();

    }

}

interface Marry {
    void marry();
}

class People implements Marry {

    String name;

    public People(String name) {
        this.name = name;
    }

    @Override
    public void marry() {
        System.out.println(this.name + "结婚啦！！！");
    }

}

class MarryCompany implements Marry {

    private Marry target;

    public MarryCompany(Marry target) {
        this.target = target;
    }

    @Override
    public void marry() {
        System.out.println("婚前准备！！！");
        this.target.marry();
        System.out.println("婚后结账");
    }

}
