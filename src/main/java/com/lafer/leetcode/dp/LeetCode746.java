package com.lafer.leetcode.dp;

public class LeetCode746 {

    public static void main(String[] args) {
        System.out.println(minCostClimbingStairs(new int[]{0, 0, 0, 1}));
    }

    public static int minCostClimbingStairs(int[] cost) {
        for (int i = 2; i < cost.length; i++) {
            cost[i] += Math.max(cost[i - 1], cost[i - 2]);
        }
        return Math.min(cost[cost.length - 1], cost[cost.length - 2]);
    }

}
