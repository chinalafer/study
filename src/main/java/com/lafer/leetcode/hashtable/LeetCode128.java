package com.lafer.leetcode.hashtable;

import java.util.Set;
import java.util.TreeSet;

/**
 *
 * 128. 最长连续序列
 * 给定一个未排序的整数数组，找出最长连续序列的长度。
 *
 * 要求算法的时间复杂度为 O(n)。
 *
 * 示例:
 *
 * 输入: [100, 4, 200, 1, 3, 2]
 * 输出: 4
 * 解释: 最长连续序列是 [1, 2, 3, 4]。它的长度为 4。
 *
 * 思考：
 * 如果是有序的数组可以使用dp
 * 无序使用哈希表，保存nums数组中的值，然后根据不断判断 num + p 是否在数组内，不断更新结果。
 *
 */

public class LeetCode128 {

    public int longestConsecutive(int[] nums) {
        Set<Integer> set = new TreeSet<>();
        for (int num : nums) {
            set.add(num);
        }
        int re = 0;
        for (Integer num : set) {
            if (!set.contains(num - 1)) {
                int p = 1;
                while (set.contains(num + p)) {
                    p++;
                }
                re = Math.max(p, re);
            }
        }
        return re;
    }

}
