package com.lafer.leetcode.dp;

/**
 *
 * 650. 只有两个键的键盘
 * 最初在一个记事本上只有一个字符 'A'。你每次可以对这个记事本进行两种操作：
 *
 * Copy All (复制全部) : 你可以复制这个记事本中的所有字符(部分的复制是不允许的)。
 * Paste (粘贴) : 你可以粘贴你上一次复制的字符。
 * 给定一个数字 n 。你需要使用最少的操作次数，在记事本中打印出恰好 n 个 'A'。输出能够打印出 n 个 'A' 的最少操作次数。
 *
 * 示例 1:
 *
 * 输入: 3
 * 输出: 3
 * 解释:
 * 最初, 我们只有一个字符 'A'。
 * 第 1 步, 我们使用 Copy All 操作。
 * 第 2 步, 我们使用 Paste 操作来获得 'AA'。
 * 第 3 步, 我们使用 Paste 操作来获得 'AAA'。
 *
 * 思考：
 *  递归(自顶向下)：
 *   举个例子：当 n = 42 时， 42 = 21 * 2 = 7 * 3 * 2
 *   即最快42个A的方式是 1个A 复制1次粘贴6次 得到7个A -> 复制1次粘贴两次得到21个A -> 复制1次粘贴1次得到42个A
 *   于是有 minSteps(n) = numSteps(n / i) + 1 + i - 1 (复制一次 粘贴n/i次) 即 minSteps(n) = numSteps(n / i) + i;
 *   考虑递归出口：当 n == 1 时 返回 0； i的取值从2开始， 如果没有数可以被n整除（n为素数）则直接返回n
 *
 *  dp(自底向上)：
 *   状态：dp[i] 表示生成i个A最少的次数
 *   状态转移：dp[i] = dp[j] + i / j | i > 1 且是 i整除的最小整数 （因为需要尽可能的进行分解）
 *   初始化：dp[i] = i, dp[1] = 0;
 *   结果：dp[n]
 *   状态压缩：由于使用到之前的所有状态，无法进行状态压缩
 *
 *  素数分解：
 *    经过上面的分析，其实该题的解为 n 的素数分解的和
 *
 */

public class LeetCode650 {

    public int minSteps(int n) {
        if (n == 1) {
            return 0;
        }
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) {
                return minSteps(n / i) + i;
            }
        }
        return n;
    }

    public int minSteps1(int n) {
        int[] dp = new int[n + 1];
        for (int i = 2; i <= n; i++) {
            dp[i] = i;
            for (int j = 2; j <= Math.sqrt(i); j++) {
                if (i % j == 0) {
                    dp[i] = dp[i / j] + j;
                    break;
                }
            }
        }
        return dp[n];
    }

    public int minSteps3(int n) {
        int ret = 0, yz = 2;
        while (n > 1) {
            while (n % yz == 0) {
                ret += yz;
                n /= yz;
            }
            yz++;
        }
        return ret;
    }

}
