package com.lafer.algorithm;

public class ThreeWayQuickSort {

    public void quickSort(int[] nums) {
        quickSort(nums, 0, nums.length - 1);
    }

    public void quickSort(int[] nums, int l, int r) {
        if (l > r) {
            return;
        }
        int[] mid = partition(nums, l, r);
        quickSort(nums, l, mid[0] - 1);
        quickSort(nums, mid[1] + 1, r);
    }

    /**
     * 三向切分
     * 同时解决荷兰国旗问题
     * @param nums
     * @param l
     * @param r
     * @return
     */
    private int[] partition(int[] nums, int l, int r) {
        int a = l, i = l, j = r, num = nums[l];
        while (i <= j) {
            if (nums[i] == num) {
                i++;
            } else if (nums[i] > num) {
                swap(nums, i, j--);
            } else {
                swap(nums, a++, i++);
            }
        }
        return new int[]{a, j};
    }

    private void swap(int[] nums, int index1, int index2) {
        int temp = nums[index1];
        nums[index1] = nums[index2];
        nums[index2] = temp;
    }

}
