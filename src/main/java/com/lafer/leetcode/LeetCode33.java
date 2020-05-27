package com.lafer.leetcode;

/**
 * 33.搜索旋转排序数组
 * <p>
 * 假设按照升序排序的数组在预先未知的某个点上进行了旋转。
 * <p>
 * ( 例如，数组 [0,1,2,4,5,6,7] 可能变为 [4,5,6,7,0,1,2] )。
 * <p>
 * 搜索一个给定的目标值，如果数组中存在这个目标值，则返回它的索引，否则返回 -1 。
 * <p>
 * 你可以假设数组中不存在重复的元素。
 * <p>
 * 你的算法时间复杂度必须是 O(log n) 级别。
 * <p>
 * 示例 1:
 * <p>
 * 输入: nums = [4,5,6,7,0,1,2], target = 0
 * 输出: 4
 * 示例 2:
 * <p>
 * 输入: nums = [4,5,6,7,0,1,2], target = 3
 * 输出: -1
 * <p>
 * <p>
 * 思考：二分法先找目标数字可能存在的区间，在对应区间里面使用二分法查找目标数。
 */

public class LeetCode33 {

    public static void main(String[] args) {
        System.out.println(search(new int[]{6, 7, 1, 2, 3, 4, 5}, 6));
    }

    public static int search(int[] nums, int target) {
        int start1 = 0, end1 = nums.length - 1;
        int min = -1;
        while (start1 < end1) {
            int temp = (start1 + end1) >> 1;
            if (temp == nums.length - 1) {
                min = nums.length;
                break;
            }
            if (nums[temp] > nums[temp + 1]) {
                min = temp + 1;
                break;
            } else {
                if (nums[temp] > nums[start1]) {
                    start1 = temp;
                } else {
                    end1 = temp;
                }
            }
        }
        int start = 0, end = nums.length - 1;
        if (min != -1 && min != nums.length) {
            if (nums[min - 1] >= target && target >= nums[0]) {
                end = min - 1;
            }
            if (nums[min] <= target && target <= nums[nums.length - 1]) {
                start = min;
            }
        }
        while (start <= end) {
            int mind = (start + end) >> 1;
            if (nums[mind] == target) {
                return mind;
            }
            if (nums[mind] > target) {
                end = mind - 1;
            } else {
                start = mind + 1;
            }
        }
        return -1;
    }


    public static int search1(int[] nums, int target) {
        int less = 0, more = nums.length - 1;
        while (less <= more) {
            //防止溢出
            int mid = less + (more - less) / 2;
            if (nums[mid] == target) {
                return mid;
            }
            // 新根据 nums[mid] 和 nums[less] 判断mid在左段还是右端
            if (nums[mid] >= nums[less]) {
                if (target >= nums[less] && target < nums[mid]) {
                    more = mid - 1;
                } else {
                    less = mid + 1;
                }
            } else {
                if (target <= nums[more] && target > nums[mid]) {
                    less = mid + 1;
                } else {
                    more = mid - 1;
                }
            }
        }
        return -1;
    }

}
