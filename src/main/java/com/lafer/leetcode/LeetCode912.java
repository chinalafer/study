package com.lafer.leetcode;

import java.util.Arrays;

/**
 *
 * 给你一个整数数组 nums，请你将该数组升序排列。
 *
 * 提示：
 *
 * 1 <= nums.length <= 50000
 * 2 -50000 <= nums[i] <= 50000
 *
 * 思考：给定的数字范围不大，可以考虑计数排序
 *
 */

public class LeetCode912 {

    public int[] sortArray(int[] nums) {
        Arrays.sort(nums);
        return nums;
    }

    public int[] sortArray2(int[] nums) {
        int[] js = new int[50000 * 2 + 1];
        int min = 50001, max = -50001;
        for (int num : nums) {
            js[num + 50000]++;
            min = num + 50000 < min ? num + 50000 : min;
            max = num + 50000 > max ? num + 50000 : max;
        }
        int index = 0;
        for (int i = min; i <= max; i++) {
            while (js[i]-- > 0) {
                nums[index++] = i - 50000;
            }
        }
        return nums;
    }

}
