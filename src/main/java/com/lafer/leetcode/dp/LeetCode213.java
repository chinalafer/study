package com.lafer.leetcode.dp;

/**
 *
 * 213. 打家劫舍 II
 * 你是一个专业的小偷，计划偷窃沿街的房屋，每间房内都藏有一定的现金。这个地方所有的房屋都围成一圈，这意味着第一个房屋和最后一个房屋是紧挨着的。同时，相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。
 *
 * 给定一个代表每个房屋存放金额的非负整数数组，计算你在不触动警报装置的情况下，能够偷窃到的最高金额。
 *
 * 示例 1:
 *
 * 输入: [2,3,2]
 * 输出: 3
 * 解释: 你不能先偷窃 1 号房屋（金额 = 2），然后偷窃 3 号房屋（金额 = 2）, 因为他们是相邻的。
 * 示例 2:
 *
 * 输入: [1,2,3,1]
 * 输出: 4
 * 解释: 你可以先偷窃 1 号房屋（金额 = 1），然后偷窃 3 号房屋（金额 = 3）。
 *      偷窃到的最高金额 = 1 + 3 = 4 。
 *
 * 思考：
 * 由于是环形的，所以根据第一个房子偷还是不偷进行分开讨论。
 * 即：1 到 n - 1   和   2 到 n。解法回归到第198题。
 * 使用dp数组来保存从第1个房子到第i个房子可以偷取的最大金额，即：dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i]);
 *
 */

public class LeetCode213 {

    public static void main(String[] args) {
        rob(new int[] {2, 3, 2});
    }

    public static int rob(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }
        int pre1 = 0, pre2 = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            int cur = Math.max(pre1, pre2 + nums[i]);
            pre2 = pre1;
            pre1 = cur;
        }
        int pre11 = 0, pre22 = 0;
        for (int i = 1; i < nums.length; i++) {
            int cur = Math.max(pre11, pre22 + nums[i]);
            pre22 = pre11;
            pre11 = cur;
        }
        return Math.max(pre1, pre11);
    }

}
