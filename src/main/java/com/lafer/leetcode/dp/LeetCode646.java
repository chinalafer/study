package com.lafer.leetcode.dp;

import java.util.Arrays;

/**
 *
 * 646. 最长数对链
 * 给出 n 个数对。 在每一个数对中，第一个数字总是比第二个数字小。
 *
 * 现在，我们定义一种跟随关系，当且仅当 b < c 时，数对(c, d) 才可以跟在 (a, b) 后面。我们用这种形式来构造一个数对链。
 *
 * 给定一个对数集合，找出能够形成的最长数对链的长度。你不需要用到所有的数对，你可以以任何顺序选择其中的一些数对来构造。
 *
 * 示例 :
 *
 * 输入: [[1,2], [2,3], [3,4]]
 * 输出: 2
 * 解释: 最长的数对链是 [1,2] -> [3,4]
 * 注意：
 *
 * 给出数对的个数在 [1, 1000] 范围内。
 *
 * 思考：由于数组是无序的，组成的最长数对链也是无序的，考虑直接将数据进行排序（根据第二个元素进行升序排序）
 * 然后使用动态规划的思想进行求解。
 * dp：
 * 状态：dp[i] 表示 pairs[0] - pairs[i] 组成的最长数对链
 * 状态转移：dp[i] = max{1 | dp[j] + 1, 0 <= j < i && pairs[i][0] > pairs[j][1]}
 * 初始化: dp[i] = 1
 * 结果：dp[i] 中的最大值
 * 状态压缩：由于要使用到之前的所有状态，无法进行状态压缩
 *
 */

public class LeetCode646 {

    public int findLongestChain(int[][] pairs) {
        if (pairs == null || pairs.length == 0) {
            return 0;
        }
        Arrays.sort(pairs, (a, b)-> a[1] - b[1]);
        int[] dp = new int[pairs.length];
        int ret = 1;
        dp[0] = 1;
        for (int i = 1; i < dp.length; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (pairs[j][1] < pairs[i][0]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            ret = Math.max(ret, dp[i]);
        }
        return ret;
    }

}
