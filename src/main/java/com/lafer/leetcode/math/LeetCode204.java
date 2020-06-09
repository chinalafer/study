package com.lafer.leetcode.math;

import java.util.Arrays;

/**
 *
 * 204. 计数质数
 * 统计所有小于非负整数 n 的质数的数量。
 *
 * 示例:
 *
 * 输入: 10
 * 输出: 4
 * 解释: 小于 10 的质数一共有 4 个, 它们是 2, 3, 5, 7 。
 *
 *
 * 思考：
 * 1、暴力法
 * 2、埃拉托斯特尼筛法在每次找到一个素数时，将能被素数整除的数排除掉。
 *
 */

public class LeetCode204 {

    public int countPrimes(int n) {
        int count = 0;
        boolean[] isPrimes = new boolean[n];
        Arrays.fill(isPrimes, true);
        for (int i = 2; i < n; i++) {
            if (isPrimes[i]) {
                count++;
                // 从 i * i 开始， 因为如果 j < i, i * j 在之前就已经被标记为非素数了
                for (long k = (long)i * i; k < n; k += i) {
                    isPrimes[(int) k] = false;
                }
            }

        }
        return count;
    }

}
