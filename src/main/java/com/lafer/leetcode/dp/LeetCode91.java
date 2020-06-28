package com.lafer.leetcode.dp;

/**
 * 91. 解码方法
 * 一条包含字母 A-Z 的消息通过以下方式进行了编码：
 * <p>
 * 'A' -> 1
 * 'B' -> 2
 * ...
 * 'Z' -> 26
 * 给定一个只包含数字的非空字符串，请计算解码方法的总数。
 * <p>
 * 示例 1:
 * <p>
 * 输入: "12"
 * 输出: 2
 * 解释: 它可以解码为 "AB"（1 2）或者 "L"（12）。
 * 示例 2:
 * <p>
 * 输入: "226"
 * 输出: 3
 * 解释: 它可以解码为 "BZ" (2 26), "VF" (22 6), 或者 "BBF" (2 2 6) 。
 *
 * 思考：
 * dp：
 * 在 s[n - 1] != 0 且 s[n] != 0 且 s[n - 1] * 10 + s[n] <= 26 时： dp[n] = dp[n - 1] + dp[n - 2]
 * 在 s[n - 1] != 0 且 s[n] != 0 且 s[n - 1] * 10 + s[n] > 26 时: dp[n] = dp[n - 1]
 * 当 s[n] == 0时： 讨论 s[n - 1]是否等于1 或者 2， 等于 1 或者 2 ： dp[n] = d[n - 2]; 否则 结果为0
 * 当 s[n - 1] == 0 时： dp[n] = dp[n - 1]
 */

public class LeetCode91 {

    /**
     *  dp：
     *  在 s[n - 1] != 0 且 s[n] != 0 且 s[n - 1] * 10 + s[n] <= 26 时： dp[n] = dp[n - 1] + dp[n - 2]
     *  在 s[n - 1] != 0 且 s[n] != 0 且 s[n - 1] * 10 + s[n] > 26 时: dp[n] = dp[n - 1]
     *  当 s[n] == 0 时： 讨论 s[n - 1]是否等于1 或者 2， 等于 1 或者 2 ： dp[n] = d[n - 2]; 否则 结果为0
     *  当 s[n - 1] == 0 时： dp[n] = dp[n - 1]
     *
     */

    public static void main(String[] args) {
        numDecodings("2262612");
    }

    public static int numDecodings(String s) {
        if (s.charAt(0) == '0') {
            return 0;
        }
        int pre1 = 1, pre2 = 1;
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == '0') {
                if (s.charAt(i - 1) > '2' || s.charAt(i - 1) <= '0') {
                    return 0;
                } else {
                    pre1 = pre2;
                }
            } else if (s.charAt(i - 1) == '0' || (s.charAt(i - 1) - '0') * 10 + s.charAt(i) - '0' > 26) {
                pre2 = pre1;
            } else {
                int temp = pre1 + pre2;
                pre2 = pre1;
                pre1 = temp;
            }
        }
        return pre1;
    }

}
