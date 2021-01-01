package com.lafer.leetcode.greed;


public class LeetCode738 {
    public static void main(String[] args) {
        monotoneIncreasingDigits(10);
    }
    public static int monotoneIncreasingDigits(int N) {
        char[] strN = Integer.toString(N).toCharArray();
        int index = 0;
        for (int i = 1; i < strN.length; i++) {
            if (strN[i] < strN[i - 1]) {
                index = i;
                break;
            }
        }
        if (index == 0) {
            return N;
        }
        index--;
        while (index > 0 && strN[index] == strN[index - 1]) {
            index--;
        }
        strN[index] = (char) (strN[index]- 1);
        index++;
        while (index < strN.length) {
            strN[index++] = '9';
        }
        return Integer.parseInt(new String(strN));
    }
}
