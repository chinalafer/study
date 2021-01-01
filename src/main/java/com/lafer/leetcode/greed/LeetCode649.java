package com.lafer.leetcode.greed;

import java.util.LinkedList;
import java.util.Queue;

public class LeetCode649 {

    public String predictPartyVictory(String senate) {
        Queue<Integer> radiantQueue = new LinkedList<>();
        Queue<Integer> direQueue = new LinkedList<>();
        int n = senate.length();
        for (int i = 0; i < senate.length(); i++) {
            if ('R' == senate.charAt(i)) {
                radiantQueue.add(i);
            } else {
                direQueue.add(i);
            }
        }
        while (!radiantQueue.isEmpty() && !direQueue.isEmpty()) {
            int radiant = radiantQueue.poll();
            int dire = direQueue.poll();
            if (dire < radiant) {
                radiantQueue.add(radiant + n);
            } else {
                direQueue.add(dire + n);
            }
        }
        return direQueue.isEmpty() ? "Radiant" : "Dire";
    }

}
