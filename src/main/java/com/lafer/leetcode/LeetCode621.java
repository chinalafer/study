package com.lafer.leetcode;

import java.util.PriorityQueue;
import java.util.Queue;

public class LeetCode621 {


    public static void main(String[] args) {
    }

    public int leastInterval(char[] tasks, int n) {
        int[] hm = new int[26];
        Queue<Character> queue = new PriorityQueue<>((c1, c2) -> hm[c2 - 'A'] - hm[c1 - 'A']);
        for (char task : tasks) {
            hm[task - 'A']++;
        }
        for (int i = 0; i < hm.length; i++) {
            if (hm[i] != 0) {
                queue.add((char) ('A' + i));
            }
        }
        int ret = 0;
        while (queue.size() > 0) {
            Character[] temp = new Character[n + 1];
            boolean flag = false;
            int count = 0;
            for (int i = 0; i <= n; i++) {
                temp[i] = queue.poll();
                if (temp[i] != null) {
                    if (--hm[temp[i] - 'A'] > 0) {
                        flag = true;
                    }
                    count++;
                }
            }
            for (Character character : temp) {
                if (character != null) {
                    if (hm[character - 'A'] > 0) {
                        queue.add(character);
                    }
                }
            }
            if (flag) {
                ret += (n + 1);
            } else {
                ret += count;
            }
        }
        return ret;
    }

}
