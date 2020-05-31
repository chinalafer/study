package com.lafer.leetcode.doublePointer;

import java.util.List;

/**
 * 524. 通过删除字母匹配到字典里最长单词
 * 给定一个字符串和一个字符串字典，找到字典里面最长的字符串，该字符串可以通过删除给定字符串的某些字符来得到。
 * 如果答案不止一个，返回长度最长且字典顺序最小的字符串。如果答案不存在，则返回空字符串。
 *
 * 示例 1:
 *
 * 输入:
 * s = "abpcplea", d = ["ale","apple","monkey","plea"]
 *
 * 输出:
 * "apple"
 * 示例 2:
 *
 * 输入:
 * s = "abpcplea", d = ["a","b","c"]
 *
 * 输出:
 * "a"
 * 说明:
 *
 * 所有输入的字符串只包含小写字母。
 * 字典的大小不会超过 1000。
 * 所有输入的字符串长度不会超过 1000。
 *
 * 思考： 双指针， 扫描字段中的字符串和指定字符串，判断是否是它的一个子串。
 *
 */

public class LeetCode524 {

    public String findLongestWord(String s, List<String> d) {
        String result = "";
        for (String str : d) {
            if (isHas(s, str)) {
                result = (str.length() == result.length() && str.compareTo(result) < 0) || str.length() > result.length() ? str : result;
            }
        }
        return result;
    }

    public boolean isHas(String ms, String zs) {
        int i = 0, j = 0;
        while (i < ms.length() && j < zs.length()) {
            if (ms.charAt(i) == zs.charAt(j)) {
                i++;
            }
            i++;
        }
        return j == zs.length();
    }

}
