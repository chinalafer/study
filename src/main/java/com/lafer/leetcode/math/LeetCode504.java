package com.lafer.leetcode.math;

/**
 *
 * 504. 七进制数
 * 给定一个整数，将其转化为7进制，并以字符串形式输出。
 *
 * 示例 1:
 *
 * 输入: 100
 * 输出: "202"
 * 示例 2:
 *
 * 输入: -7
 * 输出: "-10"
 *
 */

public class LeetCode504 {

    public String convertToBase7(int num) {
        if (num == 0) {
            return "0";
        }
        StringBuilder stringBuilder = new StringBuilder();
        String f = "";
        if (num < 0) {
            f = "-";
            num = -num;
        }
        while (num != 0) {
            stringBuilder.append((char)('0' + num % 7));
            num /= 7;
        }
        return f + stringBuilder.reverse().toString();
    }

}
