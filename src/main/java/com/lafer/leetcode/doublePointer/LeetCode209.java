package com.lafer.leetcode.doublePointer;

public class LeetCode209 {

    public int minSubArrayLen(int s, int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int end = 0, start = 0;
        int ant = Integer.MAX_VALUE;
        int sum = nums[0];
        while (start <= end && end < nums.length) {
            if (sum < s) {
                ++end;
                if (end < nums.length) {
                    sum += nums[end];
                }
            } else {
                ant = Math.min(end - start + 1, ant);
                sum -= nums[start++];
            }
        }
        return ant == Integer.MAX_VALUE ? 0 : ant;
    }

}
