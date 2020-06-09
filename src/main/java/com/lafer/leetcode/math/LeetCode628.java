package com.lafer.leetcode.math;

/**
 *
 *628. 三个数的最大乘积
 * 给定一个整型数组，在数组中找出由三个数组成的最大乘积，并输出这个乘积。
 *
 * 示例 1:
 *
 * 输入: [1,2,3]
 * 输出: 6
 * 示例 2:
 *
 * 输入: [1,2,3,4]
 * 输出: 24
 * 注意:
 *
 * 给定的整型数组长度范围是[3,104]，数组中所有的元素范围是[-1000, 1000]。
 * 输入的数组中任意三个数的乘积不会超出32位有符号整数的范围。
 *
 * 思考：
 *  可能存在负数， 所以遍历数组找出最大的三个数(a1 > a2 > a3)和最小的两个数(b1 < b2)，比较a1 * a2 * a3 与 a1 * b1 * b2的大小即可。
 *
 */

public class LeetCode628 {

    public int maximumProduct(int[] nums) {
        int a1 = Integer.MIN_VALUE, a2 = Integer.MIN_VALUE, a3 = Integer.MIN_VALUE;
        int b1 = Integer.MAX_VALUE, b2 = Integer.MAX_VALUE;
        for (int num : nums) {
            if (num > a1) {
                a3 = a2;
                a2 = a1;
                a1 = num;
            } else if (num > a2) {
                a3 = a2;
                a2 = num;
            } else if (num > a3) {
                a3 = num;
            }
            if (num < b1) {
                b2 = b1;
                b1 = num;
            } else if (num < b2) {
                b2 = num;
            }
        }
        return Math.max(a1 * a2 * a3, a1 * b1 * b2);
    }

}
