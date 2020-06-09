package com.lafer.leetcode.math;

/**
 *
 * 367. 有效的完全平方数
 * 给定一个正整数 num，编写一个函数，如果 num 是一个完全平方数，则返回 True，否则返回 False。
 *
 * 说明：不要使用任何内置的库函数，如  sqrt。
 *
 * 示例 1：
 *
 * 输入：16
 * 输出：True
 * 示例 2：
 *
 * 输入：14
 * 输出：False
 *
 */

public class LeetCode367 {

    public boolean isPerfectSquare(int num) {
        long n = 0, seq = 1;
        while (n < num) {
            n = n + seq;
            seq = seq + 2;
        }
        return n == num;
    }

}
