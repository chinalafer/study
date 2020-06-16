package com.lafer.leetcode.hashtable;

import java.util.HashMap;
import java.util.Map;

/**
 *
 *1. 两数之和
 * 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
 *
 * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素不能使用两遍。
 *
 *
 *
 * 示例:
 *
 * 给定 nums = [2, 7, 11, 15], target = 9
 *
 * 因为 nums[0] + nums[1] = 2 + 7 = 9
 * 所以返回 [0, 1]
 *
 * 思考：
 * 1、先排序，然后使用双指针尽心查找，但是排序会打乱原有的顺序。（不可行）
 * 2、使用一个map用来保存<target - num[i], i>如此只要遍历一遍，时间复杂度空间复杂度均为O(N)
 *
 */

public class LeetCode1 {

    public static void main(String[] args) {
        twoSum(new int[]{2, 7, 11, 15}, 9);
    }

    public static int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> m = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (m.containsKey(nums[i])) {
                return new int[]{m.get(nums[i]), i};
            } else {
                m.put(target - nums[i], i);
            }
        }
        return null;
    }

}
