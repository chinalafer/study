package com.lafer.leetcode.doublePointer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * 424. 替换后的最长重复字符
 * 给你一个仅由大写英文字母组成的字符串，你可以将任意位置上的字符替换成另外的字符，总共可最多替换 k 次。在执行上述操作后，找到包含重复字母的最长子串的长度。
 *
 * 注意:
 * 字符串长度 和 k 不会超过 104。
 *
 * 示例 1:
 *
 * 输入:
 * s = "ABAB", k = 2
 *
 * 输出:
 * 4
 *
 * 解释:
 * 用两个'A'替换为两个'B',反之亦然。
 * 示例 2:
 *
 * 输入:
 * s = "AABABBA", k = 1
 *
 * 输出:
 * 4
 *
 * 解释:
 * 将中间的一个'A'替换为'B',字符串变为 "AABBBBA"。
 * 子串 "BBBB" 有最长重复字母, 答案为 4。
 *
 * 思考：滑动窗口。
 * 维护一个map，记录各个字符出现的次数，同时维护maxcount记录窗口内出现最多的字符的次数。
 * 滑动窗口
 * 符合条件 扩容 right++
 * 不符合条件 平移窗口 left++ right++
 *
 */

public class LeetCode424 {

    public int characterReplacement(String s, int k) {
        int left = 0, right = 0, maxCount = 0, ret = 0;
        int[] map = new int[26];
        char[] cs = s.toCharArray();
        while (right < cs.length) {
            map[cs[right] - 'A']++;
            maxCount = Math.max(maxCount,map[cs[right] - 'A']);
            if (right - left + 1 - maxCount > k) {
                map[cs[left] - 'A']--;
                left++;
            } else {
                ret = Math.max(ret, right - left + 1);
            }
            right++;
        }
        return ret;
    }

    public String[] uncommonFromSentences(String A, String B) {
        Map<String, Integer> map = new HashMap<>();
        List<String> ret = new ArrayList<>();
        for (String str : A.split(" ")) {
            map.put(str, map.getOrDefault(str, 0) + 1);
        }
        for (String str : B.split(" ")) {
            map.put(str, map.getOrDefault(str, 0) + 1);
        }
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            if (entry.getValue() == 1) {
                ret.add(entry.getKey());
            }
        }
        return ret.toArray(new String[ret.size()]);
    }

}
