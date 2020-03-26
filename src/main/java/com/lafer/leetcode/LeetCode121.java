package com.lafer.leetcode;

/**
 *
 */

public class LeetCode121 {

    public int maxProfit(int[] prices) {

        int minPrice = Integer.MAX_VALUE;
        int maxProfit = 0;
        for (int i = 0; i < prices.length; i++) {
            if (prices[i] - minPrice > maxProfit) {
                maxProfit = prices[i] - minPrice;
            }
            if (prices[i] < minPrice) {
                minPrice = prices[i];
            }
        }
        return maxProfit;

    }

}
