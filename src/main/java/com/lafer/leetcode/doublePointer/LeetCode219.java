package com.lafer.leetcode.doublePointer;

import java.util.HashSet;
import java.util.Set;

/**
 *
 * 219. 存在重复元素 II
 * 给定一个整数数组和一个整数 k，判断数组中是否存在两个不同的索引 i 和 j，使得 nums [i] = nums [j]，并且 i 和 j 的差的 绝对值 至多为 k。
 *
 *
 *
 * 示例 1:
 *
 * 输入: nums = [1,2,3,1], k = 3
 * 输出: true
 * 示例 2:
 *
 * 输入: nums = [1,0,1,1], k = 1
 * 输出: true
 * 示例 3:
 *
 * 输入: nums = [1,2,3,1,2,3], k = 2
 * 输出: false
 *
 * 思考：滑动窗口思想
 *
 */

public class LeetCode219 {

    public boolean containsNearbyDuplicate(int[] nums, int k) {
        if (nums == null || nums.length < 1 || k < 1) {
            return false;
        }
        int start = 0, end = 1;
        Set<Integer> set = new HashSet<Integer>();
        set.add(nums[0]);
        while (end < nums.length) {
            if (end - start > k) {
                set.remove(nums[start++]);
            }
            if (set.contains(nums[end])) {
                return true;
            }
            set.add(nums[end++]);
        }
        return false;
    }

}
