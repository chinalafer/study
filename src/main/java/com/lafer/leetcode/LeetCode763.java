package com.lafer.leetcode;

import org.w3c.dom.ls.LSException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * 763. 划分字母区间
 * 字符串 S 由小写字母组成。我们要把这个字符串划分为尽可能多的片段，同一个字母只会出现在其中的一个片段。返回一个表示每个字符串片段的长度的列表。
 *
 * 示例 1:
 *
 * 输入: S = "ababcbacadefegdehijhklij"
 * 输出: [9,7,8]
 * 解释:
 * 划分结果为 "ababcbaca", "defegde", "hijhklij"。
 * 每个字母最多出现在一个片段中。
 * 像 "ababcbacadefegde", "hijhklij" 的划分是错误的，因为划分的片段数较少。
 * 注意:
 *
 * S的长度在[1, 500]之间。
 * S只包含小写字母'a'到'z'。
 */

public class LeetCode763 {

    public static void main(String[] args) {
        partitionLabels("ababcbacadefegdehijhklij");
    }

    public static List<Integer> partitionLabels(String S) {

        List<Integer> result = new ArrayList<>();
        int[] nums = new int[26];
        Arrays.fill(nums, -1);

        for (int i = 0; i < S.length(); i++) {
            nums[S.charAt(i) - 'a'] = i;
        }

        int firstIndex = 0;
        while (firstIndex < S.length()) {
            int lastIndex = firstIndex;
            for (int i = firstIndex; i < S.length() && i <= lastIndex; i++) {
                lastIndex = Math.max(nums[S.charAt(i) - 'a'], lastIndex);
            }
            result.add(lastIndex - firstIndex + 1);
            firstIndex = lastIndex + 1;
        }

        return result;

    }

}
