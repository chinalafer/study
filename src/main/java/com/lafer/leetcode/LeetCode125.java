package com.lafer.leetcode;

/**
 * 125. 验证回文串
 * 给定一个字符串，验证它是否是回文串，只考虑字母和数字字符，可以忽略字母的大小写。
 *
 * 说明：本题中，我们将空字符串定义为有效的回文串。
 *
 * 思考：前后双指针向中间夹紧判断（跳过非字母和数字字符）
 *
 */

public class LeetCode125 {

    public static void main(String[] args) {
        System.out.println(".,");
    }

    public boolean isPalindrome(String s) {
        int head = 0, end = s.length() - 1;
        while (head < end) {
            if (check(s, head) && check(s, end)) {
                int char1 = s.charAt(head), char2 = s.charAt(end);
                if (char1 >= 'a' && char1 <= 'z') {
                    char1 -= 32;
                }
                if (char2 >= 'a' && char2 <= 'z') {
                    char2 -= 32;
                }
                if (char1 != char2) {
                    return false;
                }
                head ++;
                end --;
            }
            while (head < s.length() && !check(s, head)) {
                head ++;
            }
            while (end >= 0 && !check(s, end)) {
                end --;
            }
        }
        return true;
    }

    private boolean check(String s, int index) {
        return (s.charAt(index) >= '0' && s.charAt(index) <= '9') || (s.charAt(index) >= 'a' && s.charAt(index) <= 'z')
                || (s.charAt(index) >= 'A' && s.charAt(index) <= 'Z');
    }

}
