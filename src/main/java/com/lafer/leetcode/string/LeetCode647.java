package com.lafer.leetcode.string;

/**
 *
 * 647. 回文子串
 * 给定一个字符串，你的任务是计算这个字符串中有多少个回文子串。
 *
 * 具有不同开始位置或结束位置的子串，即使是由相同的字符组成，也会被计为是不同的子串。
 *
 * 示例 1:
 *
 * 输入: "abc"
 * 输出: 3
 * 解释: 三个回文子串: "a", "b", "c".
 * 示例 2:
 *
 * 输入: "aaa"
 * 输出: 6
 * 说明: 6个回文子串: "a", "a", "a", "aa", "aa", "aaa".
 * 注意:
 *
 * 输入的字符串长度不会超过1000。
 *
 * 思考：从中间向两侧拓展
 *
 */

public class LeetCode647 {

    int res = 0;

    public int countSubstrings(String s) {
        for (int i = 0; i < s.length(); i++) {
            countHW(s, i, i);
            countHW(s, i, i + 1);
        }
        return res;
    }

    private void countHW(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            res++;
            left--;
            right++;
        }
    }

}
