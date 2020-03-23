package com.lafer.study;

/**
 * 给定一个包含大写字母和小写字母的字符串，找到通过这些字母构造成的最长的回文串。
 *
 * 在构造过程中，请注意区分大小写。比如 "Aa" 不能当做一个回文字符串。
 *
 * 注意:
 * 假设字符串的长度不会超过 1010。
 *
 * 思考：
 */

public class LeetCode409 {

    public int longestPalindrome(String s) {
        int []count = new int[128];
        int result = 0;
        for (char c : s.toCharArray()) {
            count[c]++;
        }
        for (int i : count) {
            result += (i / 2) * 2;
            if ((result & 1) == 0 && (i & 1) == 1) {
                result++;
            }
        }
        return result;
    }

}
