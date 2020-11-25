package com.lafer.algorithm;

/**
 * 快速选择算法解决
 * 数组中的第K个最大元素
 * LeetCode 215題
 */
public class QuickSelect {

    public int select(int[] nums, int k) {
        k = nums.length - k;
        int l = 0, r = nums.length - 1;
        while (l <= r) {
            int i = partition(nums, l, r);
            if (i == k) {
                return nums[i];
            } else if (i < k) {
                l = i + 1;
            } else {
                r = i - 1;
            }
        }
        return nums[k];
    }

    private void swap(int[] nums, int index1, int index2) {
        int temp = nums[index1];
        nums[index1] = nums[index2];
        nums[index2] = temp;
    }

    private int partition(int[] nums, int l, int r) {
        int i = l + 1, j = r, num = nums[l];
        while (i <= j) {
            if (num >= nums[i]) {
                i++;
            } else if (num < nums[i]) {
                swap(nums, i, j--);
            }
        }
        swap(nums, l, j);
        return j;
    }

}
