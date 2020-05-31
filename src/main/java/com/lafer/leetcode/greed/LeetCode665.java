package com.lafer.leetcode.greed;

/**
 * 665. 非递减数列
 * 给你一个长度为 n 的整数数组，请你判断在 最多 改变 1 个元素的情况下，该数组能否变成一个非递减数列。
 *
 * 我们是这样定义一个非递减数列的： 对于数组中所有的 i (1 <= i < n)，总满足 array[i] <= array[i + 1]。
 *
 *
 *
 * 示例 1:
 *
 * 输入: nums = [4,2,3]
 * 输出: true
 * 解释: 你可以通过把第一个4变成1来使得它成为一个非递减数列。
 * 示例 2:
 *
 * 输入: nums = [4,2,1]
 * 输出: false
 * 解释: 你不能在只改变一个元素的情况下将其变为非递减数列。
 *
 *
 * 说明：
 *
 * 1 <= n <= 10 ^ 4
 * - 10 ^ 5 <= nums[i] <= 10 ^ 5
 *
 * 思考：遍历一次数组，遇到逆序，修改一次字符，分两种情况进行分析，修改较大的数还是较小的数。两种情况中只要符合一次即可。
 */

public class LeetCode665 {

    public boolean checkPossibility(int[] nums) {
        if (nums == null || nums.length == 0) {
            return true;
        }
        int count = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] > nums[i + 1]) {
                if (i - 1 < 0 || nums[i - 1] <= nums[i + 1]) {
                    nums[i] = nums[i + 1];
                    count++;
                    continue;
                } else if (i + 2 >= nums.length || nums[i] <= nums[i + 2]) {
                    nums[i + 1] = nums[i];
                    count++;
                    continue;
                } else {
                    return false;
                }
            }
        }
        return count <= 1;
    }

}
