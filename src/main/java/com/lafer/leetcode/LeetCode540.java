package com.lafer.leetcode;

/**
 * 540. 有序数组中的单一元素
 * 给定一个只包含整数的有序数组，每个元素都会出现两次，唯有一个数只会出现一次，找出这个数。
 *
 * 示例 1:
 *
 * 输入: [1,1,2,3,3,4,4,8,8]
 * 输出: 2
 * 示例 2:
 *
 * 输入: [3,3,7,7,10,11,11]
 * 输出: 10
 * 注意: 您的方案应该在 O(log n)时间复杂度和 O(1)空间复杂度中运行。
 *
 * 思考：如果是无序的数组，那么使用异或运算在时间O(n)的时间复杂度上完成，由于是有序的，可以使用二分查找。
 *  判断依据：如果在index位置上的数只出现了一次，那么index后面的数与下边的对应关系将发生改变，
 *  如果m为偶数且 m > index , m + 1 < nums.length, 那么nums[m] != nums[m + 1]
 *  如果m为偶数且 0 < m < index , m + 1 < index, 那么nums[m] == nums[m + 1]
 *  这便是二分法判断的依据。
 */

public class LeetCode540 {

    public int singleNonDuplicate(int[] nums) {
        int l = 0, h = nums.length - 1;
        while (l < h) {
            int m = l + (l - h) / 2;
            if ((m & 1) == 1) {
                m--;
            }
            if (nums[m] != nums[m + 1]) {
                h = m;
            } else {
                l = m + 2;
            }
        }
        return nums[l];
    }

}
