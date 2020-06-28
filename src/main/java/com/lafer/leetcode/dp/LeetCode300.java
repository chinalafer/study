package com.lafer.leetcode.dp;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 *
 * 300. 最长上升子序列
 * 给定一个无序的整数数组，找到其中最长上升子序列的长度。
 *
 * 示例:
 *
 * 输入: [10,9,2,5,3,7,101,18]
 * 输出: 4
 * 解释: 最长的上升子序列是 [2,3,7,101]，它的长度是 4。
 * 说明:
 *
 * 可能会有多种最长上升子序列的组合，你只需要输出对应的长度即可。
 * 你算法的时间复杂度应该为 O(n2) 。
 * 进阶: 你能将算法的时间复杂度降低到 O(n log n) 吗?
 *
 * 思考：
 * dp[i] 表示到第i个数最长的上升子序列的长度，则 dp[i] = max{1, dp[i - j] + j | nums[i] > nums[i - j] && 0 < j < i}
 * 然后遍历得到最大上升子序列的长度。
 *
 *
 */

public class LeetCode300 {

    public int lengthOfLIS(int[] nums) {
        int[] dp = new int[nums.length];
        int ret = 0;
        Arrays.fill(dp, 1);
        for (int i = 1; i < nums.length; i++) {
            int maxLen = 1;
            for (int j = 1; j <= i; j++) {
                if (nums[i] > nums[i - j]) {
                    maxLen = Math.max(maxLen, dp[i - j] + 1);
                }
            }
            dp[i] = maxLen;
        }
        for (int i = 0; i < dp.length; i++) {
            ret = Math.max(ret, dp[i]);
        }
        return ret;
    }

}
