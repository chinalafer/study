package com.lafer.leetcode.BFS;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

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
 * 思考：
 * BFS:问题转换成从节点n到节点0的问题，相差为平方数的节点之间可达。
 * DP:动态规划 F(n) = Math.min(F(n - 1), F(n - 4), F(n - 9), F(n - 16)......);
 *
 */

public class LeetCode279 {

    public int numSquares(int n) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(n);
        int squa = 0;
        int[] flag = new int[n + 1];
        List<Integer> squares = generateSquares(n);
        while (queue.size() > 0) {
             squa++;
             int size = queue.size();
             while (size-- > 0) {
                 int temp = queue.poll();
                 for (int i = 0; i < squares.size(); i++) {
                     int tag = temp - squares.get(i);
                     if (tag == 0) {
                         return squa;
                     }
                     if (tag < 0) {
                         break;
                     }
                     if (flag[tag] == 1) {
                         continue;
                     }
                     queue.add(tag);
                     flag[tag] = 1;

                 }
             }
        }
        return n;
    }

    /**
     * 生成小于等于n的平方数链表
     * @param n
     * @return [1,4,9,16,15...]
     */
    public static List<Integer> generateSquares(int n) {
        List<Integer> result = new ArrayList<>();
        int square = 1, diff = 3;
        while (square <= n) {
            result.add(square);
            square += diff;
            diff += 2;
        }
        return result;
    }

    public static int numSquares1(int n) {
        List<Integer> squares = generateSquares(n);
        int[] dp = new int[n + 1];
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            int min = Integer.MAX_VALUE;
            for (int j = 0; j < squares.size(); j++) {
                if (i - squares.get(j) < 0) {
                    break;
                }
                min = Math.min(min, dp[i - squares.get(j)] + 1);
            }
            dp[i] = min;
        }
        return dp[n];
    }

    public static void main(String[] args) {
        numSquares1(13);
    }

}
