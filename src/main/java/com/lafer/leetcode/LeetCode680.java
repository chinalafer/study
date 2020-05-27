package com.lafer.leetcode;

/**
 * 680. 验证回文字符串 Ⅱ
 * 给定一个非空字符串 s，最多删除一个字符。判断是否能成为回文字符串。
 *
 * 示例 1:
 *
 * 输入: "aba"
 * 输出: True
 * 示例 2:
 *
 * 输入: "abca"
 * 输出: True
 * 解释: 你可以删除c字符。
 * 注意:
 *
 * 字符串只包含从 a-z 的小写字母。字符串的最大长度是50000。
 *
 * 思考：双指针
 */

public class LeetCode680 {

    public boolean validPalindrome(String s) {
        char[] chars = s.toCharArray();
        int start = 0, end = chars.length - 1, count = 0;
        while (start <= end) {
            if (chars[start] != chars[end]) {
                return isPalindrome(s, start, end - 1) || isPalindrome(s, start + 1, end);
            } else {
                start++;
                end--;
            }
        }
        return true;
    }

    public boolean isPalindrome(String s, int a, int b) {
        while (a < b) {
            if (s.charAt(a++) != s.charAt(b--)) {
                return false;
            }
        }
        return true;
    }

}
