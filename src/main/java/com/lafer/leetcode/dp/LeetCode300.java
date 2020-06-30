package com.lafer.leetcode.dp;

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
 * 动态规划：
 * dp[i] 表示到第i个数最长的上升子序列的长度，则 dp[i] = max{1, dp[i - j] + j | nums[i] > nums[i - j] && 0 < j < i}
 * 然后遍历得到最大上升子序列的长度。时间复杂度为O(n*n)
 *
 * 动态规划优化：
 * 状态：定义一个tails数组，tails[i] 表示 长度为 i + 1 最长上升子序列的最后一个数的最小值。
 * 状态转移：
 *  遍历nums数组，不断更新tails数组中的值。
 *  1：如果nums[i] 大于tails最后一个数，直接加入到tails中，tails的长度 + 1
 *  2：其他情况，找到有序数组tails中第一个大于等于nums[i]的数并替换它（使用二分查找）
 * 初始化：不需要，或者直接tails[0] = nums[0],len = 1;
 * 结果：tails的长度len
 * 状态压缩：因为要记录各个长度情况下的最后一个最小的数，没法进行状态压缩。
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

    public int lengthOfLIS1(int[] nums) {
        int n = nums.length;
        int[] tails = new int[n];
        int len = 0;
        for (int i = 0; i < n; i++) {
            int index = binarySearch(tails, len, nums[i]);
            tails[index] = nums[i];
            if (index == len) {
                len++;
            }
        }
        return len;
    }

    private int binarySearch(int[] tails, int len, int key) {
        int left = 0, right = len;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (tails[mid] >= key) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }


}
