package com.lafer.leetcode.dp;

import java.util.*;

/**
 * 322. 零钱兑换
 * 给定不同面额的硬币 coins 和一个总金额 amount。编写一个函数来计算可以凑成总金额所需的最少的硬币个数。如果没有任何一种硬币组合能组成总金额，返回 -1。
 *
 *
 *
 * 示例 1:
 *
 * 输入: coins = [1, 2, 5], amount = 11
 * 输出: 3
 * 解释: 11 = 5 + 5 + 1
 * 示例 2:
 *
 * 输入: coins = [2], amount = 3
 * 输出: -1
 *
 *
 * 说明:
 * 你可以认为每种硬币的数量是无限的。
 *
 * 思考：
 * 记忆化搜索
 * dp
 * 贪心 + dfs + 剪纸 最快
 *
 */

public class LeetCode322 {

    public int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, -1);
        dp[0] = 0;
        for (int i = 1; i <= amount; i++) {
            for (int coin : coins) {
                if (i - coin >= 0 && dp[i - coin] != -1) {
                    dp[i] = Math.min(dp[i] == -1 ? Integer.MAX_VALUE : dp[i], dp[i - coin] + 1);
                }
            }
        }
        return dp[amount];
    }



    private int ret = Integer.MAX_VALUE;

    Map<Integer, Integer> map = new HashMap<>();
    public int coinChange1(int[] coins, int amount) {
        if (amount < 0) {
            return -1;
        }
        if (amount == 0) {
            return 0;
        }
        if (map.containsKey(amount)) {
            return map.get(amount);
        }
        int ret = Integer.MAX_VALUE;
        for (int coin : coins) {
            int t = coinChange(coins, amount - coin);
            ret = Math.min(ret, t == -1 ? Integer.MAX_VALUE : t + 1);
        }
        map.put(amount, ret == Integer.MAX_VALUE ? -1 : ret);
        return ret == Integer.MAX_VALUE ? -1 : ret;
    }


    public int coinChange2(int[] coins, int amount) {
        Queue<Integer> queue = new LinkedList<>();
        Set<Integer> set = new HashSet<>();
        queue.add(amount);
        set.add(amount);
        int ret = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            ret++;
            while (size-- > 0) {
                int t = queue.poll();
                for (int coin : coins) {
                    int next = t - coin;
                    if (next == 0) {
                        return ret;
                    } else if (next > 0 && !set.contains(next)) {
                        set.add(next);
                        queue.add(next);
                    }
                }
            }
        }
        return -1;
    }

    private int ret1 = Integer.MAX_VALUE;
    public int coinChange3(int[] coins, int amount) {
        Arrays.sort(coins);
        dfs(coins, coins.length - 1, amount, 0);
        return ret1 == Integer.MAX_VALUE ? -1 : ret1;
    }

    private void dfs(int[] coins, int index, int amount, int ans) {
        if (index < 0) {
            return;
        }
        int n = amount / coins[index];
        for (int i = n; i >= 0; i--) {
            if (amount - i * coins[index] == 0) {
                ret1 = Math.min(i + ans, ret1);
                break;
            }
            if (ans + i + 1 >= ret1) {
                break;
            }
            dfs(coins, index - 1, amount - i * coins[index], ans + i);
        }
    }

}
