package com.lafer.leetcode.dp;

import java.util.HashMap;

/**
 *
 * 96. 不同的二叉搜索树
 * 给定一个整数 n，求以 1 ... n 为节点组成的二叉搜索树有多少种？
 *
 * 示例:
 *
 * 输入: 3
 * 输出: 5
 * 解释:
 * 给定 n = 3, 一共有 5 种不同结构的二叉搜索树:
 *
 *    1         3     3      2      1
 *     \       /     /      / \      \
 *      3     2     1      1   3      2
 *     /     /       \                 \
 *    2     1         2                 3
 *
 * 思考：
 * 递归求解（使用map记忆优化）
 * 递归求解
 *
 */

public class LeetCode96 {

    static HashMap<Integer, Integer> map = new HashMap<>();

    public static void main(String[] args) {
        System.out.println(numTrees(3));
    }

    public static int numTrees(int n) {
        if (map.containsKey(n)) {
            return map.get(n);
        }
        if (n == 0) {
            map.put(n, 1);
            return 1;
        }
        if (n == 1 || n == 2) {
            map.put(n, n);
            return n;
        }
        int mid = n >> 1, ret = 0;
        for (int i = 1; i <= mid; i++) {
            ret += (numTrees(n - i) * numTrees(i - 1));
        }
        ret *= 2;
        if ((mid << 1) != n) {
            ret += numTrees(n - (mid + 1)) * numTrees(n - (mid + 1));
        }
        map.put(n, ret);
        return ret;
    }

    public int numTrees1(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            for (int j = 1; j <= i; j++) {
                dp[i] = dp[i] + (dp[i - j] * dp[j - 1]);
            }
        }
        return dp[n];
    }

}
