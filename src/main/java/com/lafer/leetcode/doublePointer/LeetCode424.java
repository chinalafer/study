package com.lafer.leetcode.doublePointer;

public class LeetCode424 {

    public static void main(String[] args) {
        characterReplacement("BAAA", 0);
    }

    public static int characterReplacement(String s, int k) {
        if (s == null || s.length() < 1) {
            return 0;
        }
        int start = 0, end = 1, ret = 0;
        int[] flag = new int[26];
        flag[s.charAt(0) - 'A']++;
        while (end < s.length()) {
            if (check(flag, k)) {
                ret = Math.max(ret, end - start);
                flag[s.charAt(end++) - 'A']++;
            } else {
                flag[s.charAt(start++) - 'A']--;
            }
        }
        if (check(flag, k)) {
            ret = Math.max(ret, end - start);
        }
        return ret;
    }

    private static boolean check(int[] flag, int k) {
        int max = 0, sum = 0;
        for (int i : flag) {
            max = Math.max(i, max);
            sum += i;
        }
        return sum - max <= k;
    }

}
