package com.lafer.leetcode.array;

/**
 *
 * 645. 错误的集合
 * 集合 S 包含从1到 n 的整数。不幸的是，因为数据错误，导致集合里面某一个元素复制了成了集合里面的另外一个元素的值，导致集合丢失了一个整数并且有一个元素重复。
 *
 * 给定一个数组 nums 代表了集合 S 发生错误后的结果。你的任务是首先寻找到重复出现的整数，再找到丢失的整数，将它们以数组的形式返回。
 *
 * 示例 1:
 *
 * 输入: nums = [1,2,2,4]
 * 输出: [2,3]
 * 注意:
 *
 * 给定数组的长度范围是 [2, 10000]。
 * 给定的数组是无序的。
 *
 * 思考：主要思想是通过交换数组元素，使得数组上的元素在正确的位置上。
 *
 */

public class LeetCode645 {

    public static void main(String[] args) {
        findErrorNums(new int[] {2, 3, 5, 4, 6, 3});
    }

    public static int[] findErrorNums(int[] nums) {
        for (int i = 1; i <= nums.length; i++) {
            while (i != nums[i - 1] && nums[nums[i - 1] - 1] != nums[i - 1]) {
                swap(nums, i - 1, nums[i - 1] - 1);
            }
        }
        for (int i = 1; i <= nums.length; i++) {
            if (nums[i - 1] != i) {
                return new int[]{nums[i - 1], i};
            }
        }
        return null;
    }

    private static void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

}
