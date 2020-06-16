package com.lafer.leetcode.string;

/**
 * 给定一个包含大写字母和小写字母的字符串，找到通过这些字母构造成的最长的回文串。
 *
 * 在构造过程中，请注意区分大小写。比如 "Aa" 不能当做一个回文字符串。
 *
 * 注意:
 * 假设字符串的长度不会超过 1010。
 *
 * 思考：因为是英文字母，根据ASCII码编码，使用打表的方式给定一个大小为128的数据对各个字母出现的次数进行统计。
 * 最后遍历数组，对每个数的偶数部分进行累加，如果出现单个奇数加一次处理即可。
 */

public class LeetCode409 {

    public int longestPalindrome(String s) {
        int []count = new int[128];
        int result = 0;
        for (char c : s.toCharArray()) {
            count[c]++;
        }
        for (int i : count) {
            result += (i / 2) * 2;
        }
        return result == s.length() ? result : ++result;
    }

}
