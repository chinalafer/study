package com.lafer.leetcode.dp;

/**
 *
 * 72. 编辑距离
 * 给你两个单词 word1 和 word2，请你计算出将 word1 转换成 word2 所使用的最少操作数 。
 *
 * 你可以对一个单词进行如下三种操作：
 *
 * 插入一个字符
 * 删除一个字符
 * 替换一个字符
 *
 *
 * 示例 1：
 *
 * 输入：word1 = "horse", word2 = "ros"
 * 输出：3
 * 解释：
 * horse -> rorse (将 'h' 替换为 'r')
 * rorse -> rose (删除 'r')
 * rose -> ros (删除 'e')
 * 示例 2：
 *
 * 输入：word1 = "intention", word2 = "execution"
 * 输出：5
 * 解释：
 * intention -> inention (删除 't')
 * inention -> enention (将 'i' 替换为 'e')
 * enention -> exention (将 'n' 替换为 'x')
 * exention -> exection (将 'n' 替换为 'c')
 * exection -> execution (插入 'u')
 *
 * 思考：
 * 如果word1[i] == word2[j] 则可以直接比较word1[0 ... i - 1]和word2[0 ... j - 1]
 * 如果word2[i] != word2[j] 根据编辑的定义，有三种操作：
 *  1、插入，在word1[i]后面插入word2[j], 则比较word1[0 ... i]和word2[0 ... j - 1]
 *  2、删除，删除word1[i],则比较word1[0 ... i - 1]和word2[0 ... j]
 *  3、替换，将word1[i]替换成word2[j],则直接比较word1[0 ... i - 1]和word2[0 ... j - 1]
 *
 * 经过上面的思考可以将大问题划分成对应的小问题进行求解
 *
 * 考虑使用递归（自顶向下）
 *  出口条件：如果word1或者word2中的长度有一个为0，返回不为零的字符串长度
 * 递归的优化：递归求解了很多次重复的计算，导致超时，可以使用辅组空间进行记录已经计算过的值避免重复计算，或者使用动态规划（自底向上）
 *
 * 动态规划：
 * 状态的定义：dp[i][j] 表示 word1前i个字符 转换成 word2前j个字符所使用的最少操作数
 * 状态转移：
 *  word1[i] == word2[j]; dp[i][j] = dp[i - 1][j - 1];
 *  word1[i] != word2[j]; dp[i][j] = Math.min(dp[i - 1][j - 1], Math.min(dp[i - 1][j], dp[i][j - 1])) + 1;
 * 初始化：dp[0][j] = j;dp[i][0] = i;表示空字符串转换成word2前j个字符需要的最少操作数。、word1前j个字符转换成空字符串需要的最少操作数。
 * 结果：dp[word1.length()][word2.length];
 * 状态压缩：由于只是用到了dp[i - 1][j - 1], dp[i - 1][j], dp[i][j - 1]可以进行状态压缩，使用一维数组即可，注意dp[0]的初始话。
 *
 */

public class LeetCode72 {

    public int minDistance(String word1, String word2) {
        if (word1.length() == 0 || word2.length() == 0) {
            return Math.max(word1.length(), word2.length());
        }
        if (word1.charAt(word1.length() - 1) == word2.charAt(word2.length() - 1)) {
            return minDistance(word1.substring(0, word1.length() - 1),
                    word2.substring(0, word2.length() - 1));
        }
        return Math.min(Math.min(minDistance(word1, word2.substring(0, word2.length() - 1)),
                minDistance(word1.substring(0, word1.length() - 1), word2)),
                minDistance(word1.substring(0, word1.length() - 1), word2.substring(0, word2.length() - 1)))
                + 1;
    }

    public int minDistance1(String word1, String word2) {
        if (word1.length() == 0 || word2.length() == 0) {
            return Math.max(word1.length(), word2.length());
        }
        int m = word1.length(), n = word2.length();
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 0; i <= m; i++) {
            dp[i][0] = i;
        }
        for (int i = 0; i <= n; i++) {
            dp[0][i] = i;
        }
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.min(dp[i - 1][j - 1], Math.min(dp[i][j - 1], dp[i - 1][j])) + 1;
                }
            }
        }
        return dp[m][n];
    }

    public int minDistance2(String word1, String word2) {
        if (word1.length() == 0 || word2.length() == 0) {
            return Math.max(word1.length(), word2.length());
        }
        int m = word1.length(), n = word2.length();
        int[] dp = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            dp[i] = i;
        }
        for (int i = 1; i <= m; i++) {
            dp[0] = i;
            int pre = i - 1;
            for (int j = 1; j <= n; j++) {
                int temp = dp[j];
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[j] = pre;
                } else {
                    dp[j] = Math.min(pre, Math.min(dp[j - 1], dp[j])) + 1;
                }
                pre = temp;
            }
        }
        return dp[n];
    }

}
