package com.lafer.leetcode.dp;

/**
 * 10. 正则表达式匹配
 * 给你一个字符串 s 和一个字符规律 p，请你来实现一个支持 '.' 和 '*' 的正则表达式匹配。
 * <p>
 * '.' 匹配任意单个字符
 * '*' 匹配零个或多个前面的那一个元素
 * 所谓匹配，是要涵盖 整个 字符串 s的，而不是部分字符串。
 * <p>
 * 说明:
 * <p>
 * s 可能为空，且只包含从 a-z 的小写字母。
 * p 可能为空，且只包含从 a-z 的小写字母，以及字符 . 和 *。
 * 示例 1:
 * <p>
 * 输入:
 * s = "aa"
 * p = "a"
 * 输出: false
 * 解释: "a" 无法匹配 "aa" 整个字符串。
 * 示例 2:
 * <p>
 * 输入:
 * s = "aa"
 * p = "a*"
 * 输出: true
 * 解释: 因为 '*' 代表可以匹配零个或多个前面的那一个元素, 在这里前面的元素就是 'a'。因此，字符串 "aa" 可被视为 'a' 重复了一次。
 * 示例 3:
 * <p>
 * 输入:
 * s = "ab"
 * p = ".*"
 * 输出: true
 * 解释: ".*" 表示可匹配零个或多个（'*'）任意字符（'.'）。
 * 示例 4:
 * <p>
 * 输入:
 * s = "aab"
 * p = "c*a*b"
 * 输出: true
 * 解释: 因为 '*' 表示零个或多个，这里 'c' 为 0 个, 'a' 被重复一次。因此可以匹配字符串 "aab"。
 * 示例 5:
 * <p>
 * 输入:
 * s = "mississippi"
 * p = "mis*is*p*."
 * 输出: false
 * <p>
 * 思考：dp
 *
 */

public class LeetCode10 {

    public static void main(String[] args) {
        LeetCode10 l = new LeetCode10();
        l.isMatch(
                "aa",
                "a*");

    }

    public boolean isMatch(String s, String p) {
        if (s == null && p == null || s.length() == 0 && p.length() == 0) {
            return true;
        }
        int[][] dp = new int[p.length() + 1][s.length() + 1];
        //初始化
        dp[0][0] = 1;
        for (int i = 1; i < dp.length; i++) {
            if (p.charAt(i - 1) == '*') {
                dp[i][0] = 1;
            } else {
                break;
            }
        }
        for (int i = 1; i < dp.length; i++) {
            if (p.charAt(i - 1) == '*') {
                if (dp[i - 1][0] == 1 || (i- 2 >= 0 && dp[i - 2][0] == 1)) {
                    dp[i][0] = 1;
                }
            }
        }

        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[i].length; j++) {
                if (p.charAt(i - 1) == '*') {
                    if (dp[i - 1][j] == 1 || (i - 2 >= 0 && dp[i - 2][j] == 1)) {
                        dp[i][j] = 1;
                    } else {
                        int index = i - 1;
                        while (p.charAt(index) == '*') {
                            index--;
                        }
                        if (dp[i][j - 1] == 1 && (p.charAt(index) == '.' || p.charAt(index) == s.charAt(j - 1))) {
                            dp[i][j] = 1;
                        }
                    }
                } else if (p.charAt(i - 1) == '.') {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    if (dp[i - 1][j - 1] == 1 && p.charAt(i - 1) == s.charAt(j - 1)) {
                        dp[i][j] = 1;
                    }
                }
            }
        }
//        for (int i = 0; i < dp.length; i++) {
//            for (int j = 0; j < dp[i].length; j++) {
//                System.out.print(dp[i][j] + " ");
//            }
//            System.out.println();
//        }
        return dp[p.length()][s.length()] == 1;
    }

}
