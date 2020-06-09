package com.lafer.leetcode.math;

/**
 *
 * 169. 多数元素
 * 给定一个大小为 n 的数组，找到其中的多数元素。多数元素是指在数组中出现次数大于 ⌊ n/2 ⌋ 的元素。
 *
 * 你可以假设数组是非空的，并且给定的数组总是存在多数元素。
 *
 *
 *
 * 示例 1:
 *
 * 输入: [3,2,3]
 * 输出: 3
 * 示例 2:
 *
 * 输入: [2,2,1,1,1,2,2]
 * 输出: 2
 *
 * 思考：
 * 1、排序取中位数
 * 2、摩尔投票算法
 *
 */

public class LeetCode169 {

    public int majorityElement(int[] nums) {
        int m = nums[0];
        int count = 0;
        for (int num : nums) {
            m = count == 0 ? num : m;
            count = num == m ? count + 1 : count - 1;
        }
        return m;
    }

}
