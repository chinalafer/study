package com.lafer.leetcode.dp;

/**
 *
 * 718. 最长重复子数组
 * 给两个整数数组 A 和 B ，返回两个数组中公共的、长度最长的子数组的长度。
 *
 * 示例 1:
 *
 * 输入:
 * A: [1,2,3,2,1]
 * B: [3,2,1,4,7]
 * 输出: 3
 * 解释:
 * 长度最长的公共子数组是 [3, 2, 1]。
 * 说明:
 *
 * 1 <= len(A), len(B) <= 1000
 * 0 <= A[i], B[i] < 100
 *
 */

public class LeetCode718 {

    public int findLength(int[] A, int[] B) {
        if (A == null || B == null || A.length == 0 || B.length == 0) {
            return 0;
        }
        int[][] dp = new int[A.length + 1][B.length + 1];
        int ret = 0;
        for (int i = 1; i <= A.length; i++) {
           for (int j = 1; j <= B.length; j++) {
               if (A[i - 1] == B[j - 1]) {
                   dp[i][j] = dp[i - 1][j - 1] + 1;
                   ret = Math.max(dp[i][j], ret);
               }
           }
        }
        return ret;
    }

}
