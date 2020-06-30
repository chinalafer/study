package com.lafer.leetcode.dp;

/**
 *
 * 1143. 最长公共子序列
 * 给定两个字符串 text1 和 text2，返回这两个字符串的最长公共子序列的长度。
 *
 * 一个字符串的 子序列 是指这样一个新的字符串：它是由原字符串在不改变字符的相对顺序的情况下删除某些字符（也可以不删除任何字符）后组成的新字符串。
 * 例如，"ace" 是 "abcde" 的子序列，但 "aec" 不是 "abcde" 的子序列。两个字符串的「公共子序列」是这两个字符串所共同拥有的子序列。
 *
 * 若这两个字符串没有公共子序列，则返回 0。
 *
 *
 *
 * 示例 1:
 *
 * 输入：text1 = "abcde", text2 = "ace"
 * 输出：3
 * 解释：最长公共子序列是 "ace"，它的长度为 3。
 * 示例 2:
 *
 * 输入：text1 = "abc", text2 = "abc"
 * 输出：3
 * 解释：最长公共子序列是 "abc"，它的长度为 3。
 * 示例 3:
 *
 * 输入：text1 = "abc", text2 = "def"
 * 输出：0
 * 解释：两个字符串没有公共子序列，返回 0。
 *
 *
 * 提示:
 *
 * 1 <= text1.length <= 1000
 * 1 <= text2.length <= 1000
 * 输入的字符串只含有小写英文字符。
 *
 * 思考：
 * 动态规划：
 * 状态：定义一个二维数组dp[n + 1][m + 1],dp[i][j]表示text1前i个数与text2前j个数最长子序列的长度
 * 状态转移：如果text[i] = text[j] 则 dp[i][j] = dp[i - 1][j - 1] + 1; 否则dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
 * 初始化：dp数组初始化为0
 * 结果：dp[n][m]即为结果
 * 状态压缩：由于只用到了dp[i - 1][j - 1]、dp[i - 1][j]、dp[i][j - 1]，可以使用一个一位数组进行保存状态即可。
 *
 */

public class LeetCode1143 {
    public int longestCommonSubsequence(String text1, String text2) {
        if (text1 == null || text1.length() == 0 || text2 == null || text2.length() == 0) {
            return 0;
        }
        int n = text1.length(), m = text2.length();
        int[][] dp = new int[n + 1][m + 1];
        for (int i = 1; i <= n; i++) {
            char c1 = text1.charAt(i - 1);
            for (int j = 1; j <= m; j++) {
                if (c1 == text2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[n][m];
    }


    public int longestCommonSubsequence1(String text1, String text2) {
        if (text1 == null || text1.length() == 0 || text2 == null || text2.length() == 0) {
            return 0;
        }
        int n = text1.length(), m = text2.length();
        int[] dp = new int[m + 1];
        int temp = 0;
        for (int i = 1; i <= n; i++) {
            char c1 = text1.charAt(i - 1);
            int last = 0;
            for (int j = 1; j <= m; j++) {
                temp = dp[j];
                if (c1 == text2.charAt(j - 1)) {
                    dp[j] = last + 1;
                } else {
                    dp[j] = Math.max(dp[j], dp[j - 1]);
                }
                last = temp;
            }
        }
        return dp[m];
    }

}
