package com.lafer.leetcode;

/**
 * 给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。
 *
 * 设计一个算法来计算你所能获取的最大利润。你可以尽可能地完成更多的交易（多次买卖一支股票）。
 *
 * 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 *
 * 思考：假设自己买这只股票，有遇见未来的可能，当天买入可以当天卖出，当我发现今天的价格比明天要高时，果断卖出，如果今天的价格比明天要低时，不卖出。
 * 最终的收益即为本题的解。
 */

public class LeetCode122 {

    public static void main(String[] args) {
        int[] a = new int[]{7,1,5,3,6,4};
        System.out.println(maxProfit(a));
    }

    public static int maxProfit(int[] prices) {

        if (prices == null) {
            return 0;
        }

        int maxProfit = 0 - prices[0];
        int price = prices[0];
        for (int i = 0; i < prices.length; i++) {
            if (i + 1 == prices.length || prices[i] > prices[i + 1]) {
                maxProfit += prices[i];
                if (i + 1 < prices.length) {
                    maxProfit -= prices[i + 1];
                }
            }
        }
        return maxProfit;

    }

}
