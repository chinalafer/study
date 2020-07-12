package com.lafer.leetcode.string;

public class LeetCode1392 {

    public String longestPrefix(String s) {
        long base = 27, prefix = 0, suffix = 0, suffixBase = 1;
        int ret = -1, mod = 100000009;
        for (int i = 0; i < s.length() - 1; i++) {
            prefix = (prefix * base + s.charAt(i) - 'a') % mod;
            suffix = (suffixBase * (s.charAt(s.length() - i - 1) - 'a') + suffix) % mod;
            if (prefix == suffix) {
                ret = i;
            }
            suffixBase = (base * suffixBase) % mod;
        }
        return s.substring(0, ret + 1);
    }

}
