package com.lafer.leetcode.backtrack;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 78. 子集
 * 给定一组不含重复元素的整数数组 nums，返回该数组所有可能的子集（幂集）。
 *
 * 说明：解集不能包含重复的子集。
 *
 * 示例:
 *
 * 输入: nums = [1,2,3]
 * 输出:
 * [
 *   [3],
 *   [1],
 *   [2],
 *   [1,2,3],
 *   [1,3],
 *   [2,3],
 *   [1,2],
 *   []
 * ]
 *
 * 思考：DFS + 回溯
 *
 */

public class LeetCode78 {

    List<List<Integer>> output = new ArrayList<>();
    int[] nums = null;

    public List<List<Integer>> subsets(int[] nums) {
        this.nums = nums;
        for (int size = 0; size <= nums.length; size++) {
            backTrack(0, size, new LinkedList<>());
        }
        return output;
    }

    private void backTrack(int start, int size, LinkedList<Integer> curr) {
        if (curr.size() == size) {
            output.add(new ArrayList<>(curr));
            return;
        }
        for (int i = start; i < nums.length; i++) {
            curr.add(nums[i]);
            backTrack(i + 1, size, curr);
            curr.removeLast();
        }
    }

}
