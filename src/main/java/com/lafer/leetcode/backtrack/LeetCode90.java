package com.lafer.leetcode.backtrack;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 90. 子集 II
 * 给定一个可能包含重复元素的整数数组 nums，返回该数组所有可能的子集（幂集）。
 *
 * 说明：解集不能包含重复的子集。
 *
 * 示例:
 *
 * 输入: [1,2,2]
 * 输出:
 * [
 *   [2],
 *   [1],
 *   [1,2,2],
 *   [2,2],
 *   [1,2],
 *   []
 * ]
 *
 * 思考：DFS + 回溯 + 剪枝
 *
 */

public class LeetCode90 {

    List<List<Integer>> output = new ArrayList<>();
    int[] nums = null;

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
        this.nums = nums;
        for (int size = 0; size <= nums.length; size++) {
            backTrack(0, size, new LinkedList<>());
        }
        return output;
    }

    public void backTrack(int start, int size, LinkedList<Integer> curr) {
        if (curr.size() == size) {
            output.add(new ArrayList<>(curr));
        }
        for (int i = start; i < nums.length; i++) {
            if (i > start && nums[i] == nums[i - 1]) {
                continue;
            }
            curr.add(nums[i]);
            backTrack(i + 1, size, curr);
            curr.removeLast();
        }
    }

}
