package com.lafer.leetcode.backtrack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * 40. 组合总和 II
 * 给定一个数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
 *
 * candidates 中的每个数字在每个组合中只能使用一次。
 *
 * 说明：
 *
 * 所有数字（包括目标数）都是正整数。
 *
 * 。
 * 示例 1:
 *
 * 输入: candidates = [10,1,2,7,6,1,5], target = 8,
 * 所求解集为:
 * [
 *   [1, 7],
 *   [1, 2, 5],
 *   [2, 6],
 *   [1, 1, 6]
 * ]
 * 示例 2:
 *
 * 输入: candidates = [2,5,2,1,2], target = 5,
 * 所求解集为:
 * [
 *   [1,2,2],
 *   [5]
 * ]
 *
 * 思考：dfs+回溯+剪枝
 *      先排序，然后根据当前数与前一个数比较判断是否需要剪枝去掉。
 *
 */

public class LeetCode40 {

    List<List<Integer>> output = new ArrayList<>();
    int[] candidates = null;

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        this.candidates = candidates;
        backTrack(0, target, new LinkedList<>());
        return output;
    }

    public void backTrack(int start, int target, LinkedList<Integer> curr) {
        if (target < 0) {
            return;
        }
        if (target == 0) {
            output.add(new LinkedList<>(curr));
            return;
        }
        for (int i = start; i < candidates.length; i++) {
            if (i > start && candidates[i - 1] == candidates[i]) {
                continue;
            }
            curr.add(candidates[i]);
            backTrack(i + 1, target - candidates[i], curr);
            curr.removeLast();
        }
    }

}
