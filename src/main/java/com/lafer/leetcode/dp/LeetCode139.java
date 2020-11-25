package com.lafer.leetcode.dp;

import java.util.List;

public class LeetCode139 {

    public boolean wordBreak(String s, List<String> wordDict) {
        boolean[] dp = new boolean[s.length()];
        for (int i = 0; i < dp.length; i++) {
            String sub = s.substring(0, i + 1);
            for (String str : wordDict) {
                if (sub.endsWith(str)) {
                    if (sub.length() == str.length()) {
                        dp[i] = true;
                        break;
                    } else {
                        dp[i] = dp[i] | dp[i - str.length()];
                        if (dp[i]) {
                            break;
                        }
                    }
                }
            }
        }
        return dp[dp.length - 1];
    }

}
