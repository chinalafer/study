package com.lafer.leetcode.dp;

/**
 *
 * 303. 区域和检索 - 数组不可变
 * 给定一个整数数组  nums，求出数组从索引 i 到 j  (i ≤ j) 范围内元素的总和，包含 i,  j 两点。
 *
 * 示例：
 *
 * 给定 nums = [-2, 0, 3, -5, 2, -1]，求和函数为 sumRange()
 *
 * sumRange(0, 2) -> 1
 * sumRange(2, 5) -> -1
 * sumRange(0, 5) -> -3
 * 说明:
 *
 * 你可以假设数组不可变。
 * 会多次调用 sumRange 方法。
 *
 * 思考：
 *
 */

public class LeetCode303 {

    class NumArray {

        private int[] nums = null;

        private int[] sumNums = null;

        public NumArray(int[] nums) {
            this.nums = nums;
            sumNums = new int[nums.length];
            init();
        }

        public int sumRange(int i, int j) {
            return sumNums[j] - (i == 0 ? 0 : sumNums[i - 1]);
        }

        private void init() {
            int sum = 0;
            for (int i = 0; i < nums.length; i++) {
                sum += nums[i];
                sumNums[i] = sum;
            }
        }
    }

/**
 * Your NumArray object will be instantiated and called as such:
 * NumArray obj = new NumArray(nums);
 * int param_1 = obj.sumRange(i,j);
 */

}
