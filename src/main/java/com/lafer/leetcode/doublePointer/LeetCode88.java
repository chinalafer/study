package com.lafer.leetcode.doublePointer;

import java.util.Arrays;

/**
 *
 * 给你两个有序整数数组 nums1 和 nums2，请你将 nums2 合并到 nums1 中，使 num1 成为一个有序数组。
 * 说明:
 * 初始化 nums1 和 nums2 的元素数量分别为 m 和 n 。
 * 你可以假设 nums1 有足够的空间（空间大小大于或等于 m + n）来保存 nums2 中的元素。
 *
 * 思考：
 * 双指针法(从前往后)： 对nums1中前面的数移到数组的后面， 然后使用双指针法进行遍历两个数据。时间复杂度:O(n + m) 额外空间复杂度O(1)
 *
 * 排序法： 合并后排序。时间复杂度：O((n + m)log(n + m)), 空间复杂度：O(1)。
 *
 * 双指针法(从后往前)*
 *
 */
public class LeetCode88 {

    public static void main(String[] args) {
        merge(new int[]{1,2,3,0,0,0}, 3, new int[] {2,5,6}, 3);
    }

    public static void merge(int[] nums1, int m, int[] nums2, int n) {
        System.arraycopy(nums1, 0, nums1,nums1.length - m,  m);
        int index1 = nums1.length - m, index2 = 0, index = 0;
        while(index1 < nums1.length && index2 < nums2.length) {
            nums1[index++] = nums1[index1] > nums2[index2] ? nums2[index2++] : nums1[index1++];
        }
        if (index1 < nums1.length) {
            System.arraycopy(nums1, index1, nums1, index, nums1.length - index1);
        }
        if (index2 < nums2.length) {
            System.arraycopy(nums2, index2, nums1, index, nums2.length - index2);
        }
    }

}
