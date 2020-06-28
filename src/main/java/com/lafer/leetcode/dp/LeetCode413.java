package com.lafer.leetcode.dp;

/**
 *
 * 413. 等差数列划分
 * 如果一个数列至少有三个元素，并且任意两个相邻元素之差相同，则称该数列为等差数列。
 *
 * 例如，以下数列为等差数列:
 *
 * 1, 3, 5, 7, 9
 * 7, 7, 7, 7
 * 3, -1, -5, -9
 * 以下数列不是等差数列。
 *
 * 1, 1, 2, 5, 7
 *
 *
 * 数组 A 包含 N 个数，且索引从0开始。数组 A 的一个子数组划分为数组 (P, Q)，P 与 Q 是整数且满足 0<=P<Q<N 。
 *
 * 如果满足以下条件，则称子数组(P, Q)为等差数组：
 *
 * 元素 A[P], A[p + 1], ..., A[Q - 1], A[Q] 是等差的。并且 P + 1 < Q 。
 *
 * 函数要返回数组 A 中所有为等差数组的子数组个数。
 *
 *
 *
 * 示例:
 *
 * A = [1, 2, 3, 4]
 *
 * 返回: 3, A 中有三个子等差数组: [1, 2, 3], [2, 3, 4] 以及自身 [1, 2, 3, 4]。
 *
 * 思考：
 * 1、找出等差数组长度 与 它的 子等差数组个数的关系，然后遍历数组即可。
 * 2、动态规划 如果以A[i - 1]结尾的子等差数组个数位n个，那么在符合条件的情况下以A[i]结尾
 *
 */

public class LeetCode413 {

    public int numberOfArithmeticSlices(int[] A) {
        if (A == null || A.length < 3) {
            return 0;
        }
        int[] counts = new int[A.length + 1];
        for (int i = 3; i < counts.length; i++) {
            counts[i] = counts[i - 1] + i - 2;
        }
        int preC = A[1] - A[0];
        int count = 2, ret = 0;
        for (int i = 2; i < A.length; i++) {
            int x = A[i] - A[i - 1];
            if (x == preC) {
                count++;
            } else {
                ret += counts[count];
                preC = x;
                count = 2;
            }
        }
        return ret + counts[count];
    }

    public int numberOfArithmeticSlices1(int[] A) {
        if (A == null || A.length < 3) {
            return 0;
        }
        int ret = 0, pre = 0;
        for (int i = 2; i < A.length; i++) {
            if (A[i] - A[i - 1] == A[i - 1] - A[i - 2]) {
                pre++;
                ret += pre;
            } else {
                pre = 0;
            }
        }
        return ret;
    }

}
