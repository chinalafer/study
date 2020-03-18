package com.lafer.study;

import java.util.Arrays;

public class BestGold {
    public static void main(String[] args) {
        int w = 10;
        int[] p = {5, 5, 3, 4, 3};
        int[] g = {400, 500, 200, 300, 350};
        System.out.println(" 最优收益：" + getBestGoldMining(w, g.length, p, g));
        System.out.println(" 最优收益 " + getBestGoldMiningV2(w, p, g));
        System.out.println(" 最优收益 "  + getBestGoldMiningV3(w, p, g));
    }

    /**
     * 获得金矿最优收益
     *
     * @param w 工人数量
     * @param n 可选金矿数量
     * @param p 金矿开采所需的工人数量
     * @param g 金矿储量
     * @return
     */
    public static int getBestGoldMining(int w, int n, int[] p, int[] g) {
        if (w == 0 || n == 0) {
            return 0;
        }
        if (p[n - 1] > w) {
            return getBestGoldMining(w, n - 1, p, g);
        }
        return Integer.max(getBestGoldMining(w, n - 1, p, g), getBestGoldMining(w - p[n - 1], n - 1, p, g) + g[n - 1]);
    }


    /**
     * 获得金矿最优收益
     *
     * @param w 工人数量
     * @param p 金矿开采所需的工人数量
     * @param g 金矿储量
     * @return
     */
    public static int getBestGoldMiningV2(int w, int[] p, int[] g) {
        int [][]dp = new int[g.length + 1][w + 1];
        for (int go = 1; go < dp.length; go++) {
            for (int pe = 1; pe < dp[go].length; pe++) {
                if (pe - p[go - 1] >= 0) {
                    dp[go][pe] = Integer.max(dp[go - 1][pe], dp[go - 1][pe - p[go - 1]] + g[go - 1]);
                } else {
                    dp[go][pe] = dp[go - 1][pe];
                }
            }
        }
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[i].length; j++) {
                System.out.print(dp[i][j] + " ");
            }
            System.out.println();
        }
        return dp[g.length][w];
    }

    /**
     * 获得金矿最优收益
     *
     * @param w 工人数量
     * @param p 金矿开采所需的工人数量
     * @param g 金矿储量
     * @return
     */
    public static int getBestGoldMiningV3(int w, int[] p, int[] g) {
        int dp[] = new int[w + 1];
        System.out.println(Arrays.toString(dp));
        for (int go = 1; go <= p.length; go++) {
            for (int pe = dp.length - 1; pe >= 0; pe--) {
                if (pe - p[go - 1] >= 0) {
                    dp[pe] = Integer.max(dp[pe], dp[pe - p[go - 1]] + g[go - 1]);
                }
            }
            System.out.println(Arrays.toString(dp));
        }
        System.out.println(Arrays.toString(dp));
        return dp[w];
    }

}
