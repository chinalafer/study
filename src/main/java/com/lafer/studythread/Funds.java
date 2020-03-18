package com.lafer.studythread;

import java.util.*;

public class Funds {

    private List<Fund> fundLists;

    public Funds() {
        fundLists = new LinkedList();
    }

    public void addFund(Fund fund) {
        fundLists.add(fund);
    }

    public Fund getfund() {
        if (fundLists.size() > 0) {
            return fundLists.remove(0);
        } else {
            return null;
        }
    }

    public int size() {
        return fundLists.size();
    }

}

class Fund {

    public Fund(String name) {
        this.name = name;
    }

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
