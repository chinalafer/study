package com.lafer.study;

/**
 * 一个有名的按摩师会收到源源不断的预约请求，每个预约都可以选择接或不接。在每次预约服务之间要有休息时间，因此她不能接受相邻的预约。
 * 给定一个预约请求序列，替按摩师找到最优的预约集合（总预约时间最长），返回总的分钟数。
 * <p>
 * 注意：本题相对原题稍作改动
 * <p>
 * 思考：（递归）考虑递归的出口即可。
 * （动态规划）动态规划，关键时转移方程：dp[i] = max( dp[i - 2] + dp[i - 1],
 *
 */

public class LeeCode17_16 {

    public static void main(String[] args) {
        massage1(new int[]{2, 1, 1, 2});
    }

    public int massage(int[] nums) {
        return dg(nums, 0);
    }

    public int dg(int[] nums, int index) {
        if (index >= nums.length) {
            return 0;
        }
        return Math.max(dg(nums, index + 2) + nums[index], dg(nums, index + 1));
    }

    public static int massage1(int[] nums) {
        int[] dp = new int[nums.length];
        if (nums == null || nums.length < 1) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0];
        }
        if (nums.length == 2) {
            return Math.max(nums[1], nums[0]);
        }
        dp[0] = nums[0];
        dp[1] = Math.max(nums[1], nums[0]);
        for (int i = 2; i < nums.length; i++) {
            dp[i] = Math.max(dp[i - 2] + nums[i], dp[i - 1]);
        }
        return dp[dp.length - 1];
    }

    public static int massage2(int[] nums) {
        int pre1 = 0;
        int pre2 = 0;
        int temp = 0;
        for (int i = 0; i < nums.length; i++) {
            temp = Math.max(nums[i] + pre1, pre2);
            pre1 = pre2;
            pre2 = temp;
        }
        return pre2;
    }
}
