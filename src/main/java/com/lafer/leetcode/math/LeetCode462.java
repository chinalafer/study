package com.lafer.leetcode.math;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

/**
 *
 * 462. 最少移动次数使数组元素相等 II
 * 给定一个非空整数数组，找到使所有数组元素相等所需的最小移动数，其中每次移动可将选定的一个元素加1或减1。 您可以假设数组的长度最多为10000。
 *
 * 例如:
 *
 * 输入:
 * [1,2,3]
 *
 * 输出:
 * 2
 *
 * 说明：
 * 只有两个动作是必要的（记得每一步仅可使其中一个元素加1或减1）：
 *
 * [1,2,3]  =>  [2,2,3]  =>  [2,2,2]
 *
 *
 * 思考：
 * 要想移动的次数最少，那么寻找中位数（排序下标的中位数），将每个数与其比较，差值之和就是结果。
 * 实现方式：
 *  使用排序，时间复杂度为O(nlogn)
 *  使用快熟选择，找出排序数组中的第l/2个数
 *
 */

public class LeetCode462 {

    public int minMoves2(int[] nums) {
        Arrays.sort(nums);
        int result = 0;
        int start = 0, end = nums.length - 1;
        while (start < end) {
            result += (nums[end] - nums[start]);
            start++;
            end--;
        }
        return result;
    }

    public int minMoves22(int[] nums) {
        int sum = 0;
        int midV = select(nums, 0, nums.length - 1, nums.length / 2);
        for (int num : nums) {
            sum += Math.abs(num - midV);
        }
        return sum;
    }

    /**
     * 查找第k个数
     * @param nums
     * @param left
     * @param right
     * @param k
     * @return
     */
    private int select(int[] nums, int left, int right, int k) {
        if (left == right) {
            return nums[left];
        }
        int index = partition(nums, left, right);
        if (index == k) {
            return nums[index];
        }
        if (index < k) {
            return select(nums, index + 1, right, k);
        } else {
            return select(nums, left, index - 1, k);
        }
    }

    /**
     * 以最右边的数target有基准，将数组分成比target小的在左边，比target大的在右边，返回target的下标
     * @param num
     * @param left
     * @param right
     * @return
     */
    private int partition(int[] num, int left, int right) {
        int target = num[right];
        int i = left;
        for (int j = left; j <= right; j++) {
            if (num[j] < target) {
                swap(num, j, i);
                i++;
            }
        }
        swap(num, i, right);
        return i;
    }

    private void swap(int[] num, int i, int j) {
        int temp = num[i];
        num[i] = num[j];
        num[j] = temp;
    }

}
