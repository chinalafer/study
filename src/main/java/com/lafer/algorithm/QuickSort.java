package com.lafer.algorithm;

import org.junit.Test;

public class QuickSort {


    public void quickSort(int[] nums) {
        quickSort(nums, 0, nums.length - 1);
    }

    public void quickSort(int[] nums, int l, int r) {
        if (l > r) {
            return;
        }
        int mid = partition(nums, l, r);
        quickSort(nums, l, mid - 1);
        quickSort(nums, mid + 1, r);
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

    private void swap(int[] nums, int index1, int index2) {
        int temp = nums[index1];
        nums[index1] = nums[index2];
        nums[index2] = temp;
    }

}
