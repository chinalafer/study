package com.lafer.leetcode.dp;

/**
 *
 * 97. 交错字符串
 * 给定三个字符串 s1, s2, s3, 验证 s3 是否是由 s1 和 s2 交错组成的。
 *
 * 示例 1:
 *
 * 输入: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbcbcac"
 * 输出: true
 * 示例 2:
 *
 * 输入: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbbaccc"
 * 输出: false
 *
 * 思考：
 * 暴力递归  超时
 * 暴力递归记忆化， 不太好做记忆化
 * table dp :  ( -> 表示 至 )
 * 状态: dp[i][j] 表示 s1(0 -> i) s2(0 -> j) 是否可以匹配 s3(0 -> (i + j))
 * 状态转移：如果s1(i) == s3(i + j) && dp[i - 1][j] == true 则 dp[i][j] = true, 如果s2[j] == s3(i + j) 那么 dp[i][j - 1] == true, dp[i][j] = true;
 * 初始化： dp[0][0] = true;
 * 结果： dp[m][n]
 * 状态压缩： 由于只用到了上面和左边的两个状态， 可以进行状态压缩，定义一个一维数组即可。
 *
 */

public class LeetCode97 {


    public boolean isInterleave(String s1, String s2, String s3) {
        if ("".equals(s1) && "".equals(s2) && "".equals(s3)) {
            return true;
        }
        if (s1.length() > 0 && s3.length() > 0 && s1.charAt(0) == s3.charAt(0)) {
            boolean ret1 = isInterleave(s1.substring(1), s2, s3.substring(1));
            if (ret1 == true) {
                return true;
            }
        }
        if (s2.length() > 0 && s3.length() > 0 && s2.charAt(0) == s3.charAt(0)) {
            boolean ret2 = isInterleave(s1, s2.substring(1), s3.substring(1));
            if (ret2 == true) {
                return true;
            }
        }
        return false;
    }

    public boolean isInterleave1(String s1, String s2, String s3) {
        int m = s1.length(), n = s2.length();
        if (m + n != s3.length()) {
            return false;
        }
        boolean[][] dp = new boolean[m + 1][n + 1];
        dp[0][0] = true;
        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                int index = i + j - 1;
                if (i > 0 && s1.charAt(i - 1) == s3.charAt(index) && dp[i - 1][j] == true) {
                    dp[i][j] = true;
                }
                if (j > 0 && s2.charAt(j - 1) == s3.charAt(index) && dp[i][j - 1] == true) {
                    dp[i][j] = true;
                }
            }
        }
        return dp[m][n];
    }

    public boolean isInterleave2(String s1, String s2, String s3) {
        int m = s1.length(), n = s2.length();
        if (m + n != s3.length()) {
            return false;
        }
        boolean[] dp = new boolean[n + 1];
        dp[0] = true;
        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                int index = i + j - 1;
                if (i > 0 && s1.charAt(i - 1) == s3.charAt(index) && dp[j] == true) {
                    dp[j] = true;
                }
                if (j > 0 && s2.charAt(j - 1) == s3.charAt(index) && dp[j - 1] == true) {
                    dp[j] = true;
                }
            }
        }
        return dp[n];
    }

}
