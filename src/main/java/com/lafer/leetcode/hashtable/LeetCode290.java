package com.lafer.leetcode.hashtable;

import java.util.HashMap;

public class LeetCode290 {

    public static void main(String[] args) {
        wordPattern("abba",
                "dog cat cat dog");
    }

    public static boolean wordPattern(String pattern, String s) {
        String[] s1 = s.split(" ");
        if (s1.length != pattern.length()) {
            return false;
        }
        HashMap<String, Integer> hash1 = new HashMap<>();
        HashMap<String, Integer> hash2 = new HashMap<>();
        for (int i = 0; i < s1.length; i++) {
            if ((hash1.get(s1[i]) == null && hash2.get(String.valueOf(pattern.charAt(i))) == null) || (hash1.get(s1[i]) != null && hash2.get(String.valueOf(pattern.charAt(i))) != null && hash1.get(s1[i]).equals(hash2.get(String.valueOf(pattern.charAt(i)))))) {
                hash1.put(s1[i], i + 1);
                hash2.put(String.valueOf(pattern.charAt(i)), i + 1);
            } else {
                return false;
            }
        }
        return true;
    }
}
