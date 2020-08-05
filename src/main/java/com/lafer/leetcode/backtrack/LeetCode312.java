package com.lafer.leetcode.backtrack;

import java.util.Arrays;

/**
 *
 * 312. 戳气球
 * 有 n 个气球，编号为0 到 n-1，每个气球上都标有一个数字，这些数字存在数组 nums 中。
 *
 * 现在要求你戳破所有的气球。如果你戳破气球 i ，就可以获得 nums[left] * nums[i] * nums[right] 个硬币。 这里的 left 和 right 代表和 i 相邻的两个气球的序号。注意当你戳破了气球 i 后，气球 left 和气球 right 就变成了相邻的气球。
 *
 * 求所能获得硬币的最大数量。
 *
 * 说明:
 *
 * 你可以假设 nums[-1] = nums[n] = 1，但注意它们不是真实存在的所以并不能被戳破。
 * 0 ≤ n ≤ 500, 0 ≤ nums[i] ≤ 100
 * 示例:
 *
 * 输入: [3,1,5,8]
 * 输出: 167
 * 解释: nums = [3,1,5,8] --> [3,5,8] -->   [3,8]   -->  [8]  --> []
 *      coins =  3*1*5      +  3*5*8    +  1*3*8      + 1*8*1   = 167
 *
 * 思考：
 * 回溯算法：如果模拟戳气球的操作，很容易写出maxCoins1的算法，即回溯算法。但是这样会存在很多的重复计算，结果 超时。
 * 分治策略：反向思考，根据题意假设刚开始的时候只有两个气球，即边界0， length处两个数字为1的气球，想i位置上加入一个气球，
 * 则问题分解为f(i, j) = max( nums[i] * nums[j] * nums[index] + f(i, index) + f(index, j) ) | index 的取值为i + 1 ~ j - 1; （开区间） 使用二维数组进行记忆。
 * 动态规划：
 *  状态：dp[i][j] 表示 f(i, j)
 *  状态转移：dp[i][j] = max( nums[i] * nums[j] * nums[index] + dp[i][index] + dp[index][j] ) index 的取值为i + 1 ~ j - 1;
 *  初始化：无序初始化，默认就是0
 *  结果：dp[0][n]  第一行最后一个元素
 */

public class LeetCode312 {

    private int ret = 0;

    public int maxCoins1(int[] nums) {
        backTrack(nums, 0);
        return ret;
    }

    private void backTrack(int[] nums, int ret) {
        //结束条件
        if (nums.length == 0) {
            //跟新结果 ret即为路径
            if (ret > this.ret) {
                this.ret = ret;
            }
            return;
        }
        //选择
        for (int i = 0; i < nums.length; i++) {
            int[] nextNums = new int[nums.length - 1];
            System.arraycopy(nums, 0, nextNums, 0, i);
            System.arraycopy(nums, i + 1, nextNums, i, nums.length - (i + 1));
            int left = i == 0 ? 1 : nums[i - 1];
            int right = i == nums.length - 1 ? 1 : nums[i + 1];
            int newRet = left * right * nums[i] + ret;
            backTrack(nextNums, newRet);
        }
    }

    private int[][] dp;

    public int maxCoins2(int[] nums) {
        //加上边界，初始化新数组和记忆数组
        int[] newNums = new int[2 + nums.length];
        System.arraycopy(nums, 0, newNums, 1, nums.length);
        newNums[0] = newNums[newNums.length - 1] = 1;
        dp = new int[nums.length + 2][nums.length + 2];
        for (int i = 0; i < dp.length; i++) {
            Arrays.fill(dp[i], -1);
        }
        return f(newNums, 0, newNums.length - 1);
    }

    private int f(int[] nums, int i, int j) {
        if (i >= j - 1) {
            return 0;
        }
        if (dp[i][j] != -1) {
            return dp[i][j];
        }
        for (int index = i + 1; index < j; index++) {
            int sum = nums[i] * nums[j] * nums[index];
            dp[i][j] = Math.max(dp[i][j], sum + f(nums, i, index) + f(nums, index, j));
        }
        return dp[i][j];
    }

    public static void main(String[] args) {
        LeetCode312 leetCode312 = new LeetCode312();
        leetCode312.maxCoins3(new int[]{3,1,5,8});
    }

    public int maxCoins3(int[] nums) {
        int[] newNums = new int[2 + nums.length];
        System.arraycopy(nums, 0, newNums, 1, nums.length);
        int[][] dp = new int[nums.length + 2][nums.length + 2];
        for (int i = dp.length - 3; i >= 0; i--) {
            for (int j = i + 2; j < dp[i].length; j++) {
                for (int index = i + 1; index < j; index++) {
                    int sum = newNums[i] * newNums[j] * newNums[index];
                    dp[i][j] = Math.max(dp[i][j], sum + dp[i][index] + dp[index][j]);
                }
            }
        }
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[i].length; j++) {
                System.out.printf(dp[i][j] + " ");
            }
            System.out.println();
        }
        return dp[0][dp[0].length - 1];
    }


}
