package com.lafer.leetcode.backtrack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 46. 全排列
 * 给定一个 没有重复 数字的序列，返回其所有可能的全排列。
 * <p>
 * 示例:
 * <p>
 * 输入: [1,2,3]
 * 输出:
 * [
 * [1,2,3],
 * [1,3,2],
 * [2,1,3],
 * [2,3,1],
 * [3,1,2],
 * [3,2,1]
 * ]
 *
 * 思考：dfs+回溯
 *
 */

public class LeetCode46 {

    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> reslut = new ArrayList<>();
        backTrack(nums, 0, reslut);
        return reslut;
    }

    private void backTrack(int[] nums, int index, List<List<Integer>> result) {
        if (index == nums.length) {
            List<Integer> re = Arrays.stream(nums).boxed().collect(Collectors.toList());
            result.add(re);
        }
        for (int i = index; i < nums.length; i++) {
            swap(nums, index, i);
            backTrack(nums, index + 1, result);
            swap(nums, index, i);
        }
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

}
