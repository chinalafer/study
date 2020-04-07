package com.lafer.leetcode;

/**
 *
 * 编写一个函数来查找字符串数组中的最长公共前缀。
 *
 * 如果不存在公共前缀，返回空字符串 ""。
 *
 * 思考：垂直扫描即可
 *
 */

public class LeetCode14 {

    public String longestCommonPrefix(String[] strs) {

        if (strs == null || strs.length == 0) {
            return "";
        }

        int index = 0;
        for (; index < strs[0].length(); index++) {
            for (int i = 0; i < strs.length; i++) {
                if (strs[i].length() - 1 < index || strs[i].charAt(index) != strs[0].charAt(index)) {
                    return strs[0].substring(0, index);
                }
            }
        }
        return strs[0].substring(0, index);
    }

}
