package com.lafer.leetcode.backtrack;

import java.util.LinkedList;
import java.util.List;

/**
 *
 * 77. 组合
 * 给定两个整数 n 和 k，返回 1 ... n 中所有可能的 k 个数的组合。
 *
 * 示例:
 *
 * 输入: n = 4, k = 2
 * 输出:
 * [
 *   [2,4],
 *   [3,4],
 *   [2,3],
 *   [1,2],
 *   [1,3],
 *   [1,4],
 * ]
 *
 * 思考：dfs+回溯
 *
 */

public class LeetCode77 {

    List<List<Integer>> output = new LinkedList();
    int n;
    int k;

    public List<List<Integer>> combine(int n, int k) {
        this.n = n;
        this.k = k;
        backtrack(1, new LinkedList<>());
        return output;
    }

    private void backtrack(int first, LinkedList<Integer> curr) {
        if (curr.size() == k) {
            output.add(new LinkedList<>(curr));
            return;
        }
        for (int i = first; i <= n; i++) {
            curr.add(i);
            backtrack(i + 1, curr);
            curr.removeLast();
        }
    }

}
