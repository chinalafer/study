package com.lafer.leetcode.string;

import java.util.Arrays;

/**
 *
 * 205. 同构字符串
 * 给定两个字符串 s 和 t，判断它们是否是同构的。
 *
 * 如果 s 中的字符可以被替换得到 t ，那么这两个字符串是同构的。
 *
 * 所有出现的字符都必须用另一个字符替换，同时保留字符的顺序。两个字符不能映射到同一个字符上，但字符可以映射自己本身。
 *
 * 示例 1:
 *
 * 输入: s = "egg", t = "add"
 * 输出: true
 * 示例 2:
 *
 * 输入: s = "foo", t = "bar"
 * 输出: false
 * 示例 3:
 *
 * 输入: s = "paper", t = "title"
 * 输出: true
 *
 * 说明:
 * 你可以假设 s 和 t 具有相同的长度。
 *
 * 思考：
 * 1、记录字符出现的次数，然后排序比较（错误）无法比较 "aba" "bba" 这种情况
 * 2、记录字符上次出现的位置，如果字符上次出现的位置都相同，则是同构。
 */

public class LeetCode205 {

    public boolean isIsomorphic(String s, String t) {
        int[] sPreIndex = new int[256];
        int[] tPreIndex = new int[256];
        for (int i = 0; i < s.length(); i++) {
            char cs = s.charAt(i), ts = t.charAt(i);
            if (sPreIndex[cs] != tPreIndex[ts]) {
                return false;
            }
            sPreIndex[cs] = i + 1;
            sPreIndex[ts] = i + 1;
        }
        return true;
    }

}
