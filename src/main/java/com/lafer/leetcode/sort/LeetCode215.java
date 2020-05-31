package com.lafer.leetcode.sort;

import java.util.PriorityQueue;

/**
 * 215. 数组中的第K个最大元素
 * 在未排序的数组中找到第 k 个最大的元素。请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。
 *
 * 示例 1:
 *
 * 输入: [3,2,1,5,6,4] 和 k = 2
 * 输出: 5
 * 示例 2:
 *
 * 输入: [3,2,3,1,2,4,5,5,6] 和 k = 4
 * 输出: 4
 * 说明:
 *
 * 你可以假设 k 总是有效的，且 1 ≤ k ≤ 数组的长度。
 *
 */

public class LeetCode215 {

    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
        for (int num : nums) {
            priorityQueue.add(num);
            if (priorityQueue.size() > k) {
                priorityQueue.poll();
            }
        }
        return priorityQueue.peek();
    }

    public static void main(String[] args) {
        System.out.println(findKthLargest1(new int[]{3,2,1,5,6,4}, 2));
    }

    public static int findKthLargest1(int[] nums, int k) {
        int l = 0, h = nums.length - 1;
        int target;
        while (true) {
            target = partition(nums, l, h);
            if (target == (nums.length - k)) {
                return nums[target];
            } else if (target < (nums.length - k)) {
                l = target + 1;
            } else {
                h = target - 1;
            }
        }
    }

    public static int partition(int[] nums, int l, int h) {
        int target = nums[l];
        int less = l - 1, high = h + 1;
        int index = l;
        while (index < high) {
            if (nums[index] > target) {
                swap(nums, --high, index);
            } else if (nums[index] < target) {
                swap(nums, ++less, index++);
            } else {
                index++;
            }
        }
        return index - 1;
    }

    private static void swap(int[] a, int i, int j) {
        int t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

}
