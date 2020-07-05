package com.lafer.leetcode;

import java.util.Stack;

/**
 *
 * 32. 最长有效括号
 * 给定一个只包含 '(' 和 ')' 的字符串，找出最长的包含有效括号的子串的长度。
 *
 * 示例 1:
 *
 * 输入: "(()"
 * 输出: 2
 * 解释: 最长有效括号子串为 "()"
 * 示例 2:
 *
 * 输入: ")()())"
 * 输出: 4
 * 解释: 最长有效括号子串为 "()()"
 *
 */

public class LeetCode32 {

    public int longestValidParentheses(String s) {
        Stack<Character> stack = new Stack<>();
        int[] flag = new int[s.length()];
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (stack.size() == 0 || c == '(' || c == stack.peek()) {
                stack.push(c);
            } else {
                stack.pop();
                flag[i] = 1;
                int k = i - 1;
                while (flag[k] == 1) {
                    k--;
                }
                flag[k] = 1;
            }
        }
        int ret = 0, ant = 0;
        for (int f : flag) {
            if (f == 0) {
                ret = Math.max(ret, ant);
                ant = 0;
            } else {
                ant++;
            }
        }
        return Math.max(ret, ant);
    }

}
