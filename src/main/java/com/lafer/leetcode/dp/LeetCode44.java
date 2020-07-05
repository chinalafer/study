package com.lafer.leetcode.dp;

/**
 *
 * 44. 通配符匹配
 * 给定一个字符串 (s) 和一个字符模式 (p) ，实现一个支持 '?' 和 '*' 的通配符匹配。
 *
 * '?' 可以匹配任何单个字符。
 * '*' 可以匹配任意字符串（包括空字符串）。
 * 两个字符串完全匹配才算匹配成功。
 *
 * 说明:
 *
 * s 可能为空，且只包含从 a-z 的小写字母。
 * p 可能为空，且只包含从 a-z 的小写字母，以及字符 ? 和 *。
 * 示例 1:
 *
 * 输入:
 * s = "aa"
 * p = "a"
 * 输出: false
 * 解释: "a" 无法匹配 "aa" 整个字符串。
 * 示例 2:
 *
 * 输入:
 * s = "aa"
 * p = "*"
 * 输出: true
 * 解释: '*' 可以匹配任意字符串。
 * 示例 3:
 *
 * 输入:
 * s = "cb"
 * p = "?a"
 * 输出: false
 * 解释: '?' 可以匹配 'c', 但第二个 'a' 无法匹配 'b'。
 * 示例 4:
 *
 * 输入:
 * s = "adceb"
 * p = "*a*b"
 * 输出: true
 * 解释: 第一个 '*' 可以匹配空字符串, 第二个 '*' 可以匹配字符串 "dce".
 * 示例 5:
 *
 * 输入:
 * s = "acdcb"
 * p = "a*c?b"
 * 输出: false
 *
 * 思考：
 * 动态规划：
 * 状态：定义二维dp数组，dp[i][j]表示p[0] - p[i - 1] 是否匹配s[0] - s[j - 1],这里使用1表示可以匹配，0表示不可以匹配。
 * 状态转移：分三种情况 1 ： p[i] = *; p[i] = ?; p[i] 是其他字符
 *  1、p[i] == '*';则只要dp[i - 1][j - 1], dp[i - 1][j], dp[i][j - 1]中有一个为1，dp[i][j] 就等于1，其实代表*号替换0个字符一个字符和多个字符的对应关系
 *  2、dp[i] == '?';则dp[i][j] = dp[i - 1][j - 1];
 *  3、其他情况，只有当p[i] == s[j] && dp[i - 1][j - 1] == 1时， dp[i][j] = 1
 * 初始化：初始化第一行和第一列，表示""匹配，注意p中的*
 * 结果：dp[m][n]
 * 状态压缩：由于只用到了dp[i - 1][j - 1], dp[i - 1][j], dp[i][j - 1]，可以进行状态压缩，降低空间复杂度为O(n)
 *
 */

public class LeetCode44 {

    public static void main(String[] args) {
        LeetCode44 l = new LeetCode44();
        l.isMatch("aab", "c*a*b");
    }

    public boolean isMatch(String s, String p) {
        if (s == null && p == null || s.length() == 0 && p.length() == 0) {
            return true;
        }
        int[][] dp = new int[p.length() + 1][s.length() + 1];
        dp[0][0] = 1;
        for (int i = 1; i < dp.length; i++) {
            for (int j = 0; j < dp[i].length; j++) {
                if (j == 0) {
                    if (p.charAt(i - 1) == '*' && dp[i - 1][j] == 1) {
                        dp[i][j] = 1;
                    }
                } else {
                    if (p.charAt(i - 1) == '*') {
                        if (dp[i - 1][j] == 1 || dp[i][j - 1] == 1) {
                            dp[i][j] = 1;
                        }
                    } else if (p.charAt(i- 1) == '?' || p.charAt(i - 1) == s.charAt(j - 1)) {
                        dp[i][j] = dp[i - 1][j - 1];
                    }
                }
            }
        }
        return dp[p.length()][s.length()] == 1;
    }

}
