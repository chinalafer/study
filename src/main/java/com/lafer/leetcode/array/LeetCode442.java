package com.lafer.leetcode.array;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * 442. 数组中重复的数据
 * 给定一个整数数组 a，其中1 ≤ a[i] ≤ n （n为数组长度）, 其中有些元素出现两次而其他元素出现一次。
 *
 * 找到所有出现两次的元素。
 *
 * 你可以不用到任何额外空间并在O(n)时间复杂度内解决这个问题吗？
 *
 * 示例：
 *
 * 输入:
 * [4,3,2,7,8,2,3,1]
 *
 * 输出:
 * [2,3]
 *
 * 思路：
 * 遍历数组，通过不断交换数组的中元素，使得数组中的数都在各自正确的位置上。
 * 也可以使用标记法，遍历数组，将数对应的索引的数置负，如果对应索引上的数已经为负数，那么这是重复出现的书。
 *
 */

public class LeetCode442 {

    public List<Integer> findDuplicates(int[] nums) {
        List<Integer> re = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            while (nums[i] != i + 1 && nums[i] != nums[nums[i] - 1]) {
                swap(nums, i, nums[i] - 1);
            }
        }
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != i + 1) {
                re.add(nums[i]);
            }
        }
        return re;
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

}
