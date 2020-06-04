package com.lafer.leetcode.backtrack;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * 216. 组合总和 III
 * 找出所有相加之和为 n 的 k 个数的组合。组合中只允许含有 1 - 9 的正整数，并且每种组合中不存在重复的数字。
 *
 * 说明：
 *
 * 所有数字都是正整数。
 * 解集不能包含重复的组合。
 * 示例 1:
 *
 * 输入: k = 3, n = 7
 * 输出: [[1,2,4]]
 * 示例 2:
 *
 * 输入: k = 3, n = 9
 * 输出: [[1,2,6], [1,3,5], [2,3,4]]
 *
 * 思考：dfs + 回溯 + 剪枝
 *
 */

public class LeetCode216 {

    List<List<Integer>> output = new ArrayList<>();

    public List<List<Integer>> combinationSum3(int k, int n) {
        backTrack(1, k, n, new LinkedList<>());
        return output;
    }

    private void backTrack(int start, int k, int n, LinkedList<Integer> curr) {
        if (n == 0 && k == 0) {
            output.add(new ArrayList<>(curr));
            return;
        }
        if (n <= 0 || k <= 0) {
            return;
        }
        if (10 - start < k) {
            return;
        }
        for (int i = start; i < 10; i++) {
            curr.add(i);
            backTrack(i + 1, k - 1, n - i, curr);
            curr.removeLast();
        }
    }

}
