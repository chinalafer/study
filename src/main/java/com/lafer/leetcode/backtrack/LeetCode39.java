package com.lafer.leetcode.backtrack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * 39. 组合总和
 * 给定一个无重复元素的数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
 *
 * candidates 中的数字可以无限制重复被选取。
 *
 * 说明：
 *
 * 所有数字（包括 target）都是正整数。
 * 解集不能包含重复的组合。
 * 示例 1:
 *
 * 输入: candidates = [2,3,6,7], target = 7,
 * 所求解集为:
 * [
 *   [7],
 *   [2,2,3]
 * ]
 * 示例 2:
 *
 * 输入: candidates = [2,3,5], target = 8,
 * 所求解集为:
 * [
 *   [2,2,2,2],
 *   [2,3,3],
 *   [3,5]
 * ]
 *
 */

public class LeetCode39 {

    List<List<Integer>> output = new ArrayList<>();
    int[] candidates = null;


    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        this.candidates = candidates;
        backtrack(new LinkedList<>(), 0, target);
        return output;
    }

    private void backtrack(LinkedList<Integer> curr, int start, int tartet) {
        if (tartet < 0) {
            return;
        }
        if (tartet == 0) {
            output.add(new LinkedList<>(curr));
            return;
        }
        for (int i = start; i < candidates.length; i++) {
            curr.add(candidates[i]);
            backtrack(curr, i, tartet - candidates[i]);
            curr.removeLast();
        }
    }


}