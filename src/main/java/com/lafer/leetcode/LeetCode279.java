package com.lafer.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 279. 完全平方数
 * 给定正整数 n，找到若干个完全平方数（比如 1, 4, 9, 16, ...）使得它们的和等于 n。你需要让组成和的完全平方数的个数最少。
 *
 * 示例 1:
 *
 * 输入: n = 12
 * 输出: 3
 * 解释: 12 = 4 + 4 + 4.
 * 示例 2:
 *
 * 输入: n = 13
 * 输出: 2
 * 解释: 13 = 4 + 9.
 *
 *
 * 思考：问题转换成从节点n到节点0的问题，相差为平方数的节点之间可达。
 *
 */

public class LeetCode279 {

    public int numSquares(int n) {

    }

    /**
     * 生成小于等于n的平方数链表
     * @param n
     * @return [1,4,9,16,15...]
     */
    public List<Integer> generateSquares(int n) {
        List<Integer> result = new ArrayList<>();
        int square = 1, diff = 3;
        while (square <= n) {
            result.add(square);
            square += diff;
            diff += 2;
        }
    }

}
