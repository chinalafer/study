package com.lafer.leetcode.math;

/**
 * 233. 数字 1 的个数
 * 给定一个整数 n，计算所有小于等于 n 的非负整数中数字 1 出现的个数。
 *
 * 示例:
 *
 * 输入: 13
 * 输出: 6
 * 解释: 数字 1 出现在以下数字中: 1, 10, 11, 12, 13 。
 *
 * 思考：数学找规律
 *
 */

public class LeetCode233 {

    public int countDigitOne(int n) {
        int ans = 0;
        for (long i = 1; i <= n; i *= 10) {
            long di = i * 10;
            ans += (n / di) * i + Math.min(Math.max(n % di - i + 1, 0), i);
        }
        return ans;
    }

}
