package com.lafer.leetcode.dp;

/**
 *
 * 583. 两个字符串的删除操作
 * 给定两个单词 word1 和 word2，找到使得 word1 和 word2 相同所需的最小步数，每步可以删除任意一个字符串中的一个字符。
 *
 *
 *
 * 示例：
 *
 * 输入: "sea", "eat"
 * 输出: 2
 * 解释: 第一步将"sea"变为"ea"，第二步将"eat"变为"ea"
 *
 *
 * 提示：
 *
 * 给定单词的长度不超过500。
 * 给定单词中的字符只含有小写字母。
 *
 */

public class LeetCode583 {

    public int minDistance(String word1, String word2) {

        if (word1 == null || word1.length() == 0) {
            return word2.length();
        }
        if (word2 == null || word2.length() == 0) {
            return word1.length();
        }
        int n = word1.length(), m = word2.length();
        int[] dp = new int[m + 1];
        int temp = 0;
        for (int i = 1; i <= n; i++) {
            char c1 = word1.charAt(i - 1);
            int last = 0;
            for (int j = 1; j <= m; j++) {
                temp = dp[j];
                if (c1 == word2.charAt(j - 1)) {
                    dp[j] = last + 1;
                } else {
                    dp[j] = Math.max(dp[j], dp[j - 1]);
                }
                last = temp;
            }
        }
        return word1.length() + word2.length() + dp[m];
    }

}
