package com.lafer.leetcode.sort;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 347. 前 K 个高频元素
 * 给定一个非空的整数数组，返回其中出现频率前 k 高的元素。
 *
 *
 *
 * 示例 1:
 *
 * 输入: nums = [1,1,1,2,2,3], k = 2
 * 输出: [1,2]
 * 示例 2:
 *
 * 输入: nums = [1], k = 1
 * 输出: [1]
 *
 *
 * 提示：
 *
 * 你可以假设给定的 k 总是合理的，且 1 ≤ k ≤ 数组中不相同的元素的个数。
 * 你的算法的时间复杂度必须优于 O(n log n) , n 是数组的大小。
 * 题目数据保证答案唯一，换句话说，数组中前 k 个高频元素的集合是唯一的。
 * 你可以按任意顺序返回答案。
 *
 *
 * 思考：
 *  桶排序
 *      使用桶记录各个数字出现的次数，
 *      使用桶记录次数与各个数之间的关系，
 *      从后往前输出即可。
 *
 */

public class LeetCode347 {

    public int[] topKFrequent(int[] nums, int k) {

        Map<Integer, Integer> frequencyForNum = new HashMap<>();
        for (int num : nums) {
            frequencyForNum.put(num, frequencyForNum.getOrDefault(num, 0) + 1);
        }

        List<Integer>[] buckets = new ArrayList[nums.length + 1];
        for (Map.Entry<Integer, Integer> entry : frequencyForNum.entrySet()) {
            if (buckets[entry.getValue()] == null) {
                buckets[entry.getValue()] = new ArrayList<>();
            }
            buckets[entry.getValue()].add(entry.getKey());
        }

        List<Integer> result = new ArrayList<>();
        for (int i = buckets.length - 1; i >= 0 & result.size() < k; i--) {
            if (buckets[i] == null) {
                continue;
            }
            if (k - result.size() >= buckets[i].size()) {
                result.addAll(buckets[i]);
            } else {
                result.addAll(buckets[i].subList(0, k - result.size()));
            }
        }

        return result.stream().mapToInt(Integer::valueOf).toArray();
    }

}
