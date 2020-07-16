package com.lafer.leetcode.dp;

/**
 *
 * 509. 斐波那契数
 * 斐波那契数，通常用 F(n) 表示，形成的序列称为斐波那契数列。该数列由 0 和 1 开始，后面的每一项数字都是前面两项数字的和。也就是：
 *
 * F(0) = 0,   F(1) = 1
 * F(N) = F(N - 1) + F(N - 2), 其中 N > 1.
 * 给定 N，计算 F(N)。
 *
 */

public class LeetCode509 {

    public int fib(int N) {
        if (N < 2) {
            return N;
        }
        int pre1 = 1, pre2 = 0;
        for (int i = 2; i <= N; i++) {
            int temp = pre1;
            pre1 += pre2;
            pre2 = temp;
        }
        return pre1;
    }

}
