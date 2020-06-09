package com.lafer.leetcode.math;

/**
 *
 * 415. 字符串相加
 * 给定两个字符串形式的非负整数 num1 和num2 ，计算它们的和。
 *
 * 注意：
 *
 * num1 和num2 的长度都小于 5100.
 * num1 和num2 都只包含数字 0-9.
 * num1 和num2 都不包含任何前导零。
 * 你不能使用任何內建 BigInteger 库， 也不能直接将输入的字符串转换为整数形式。
 *
 */

public class LeetCode415 {

    public String addStrings(String num1, String num2) {

        if (num1 == null || num2 == null) {
            return num1 == null ? num2 : num1;
        }
        int index1 = num1.length() - 1;
        int index2 = num2.length() - 1;
        StringBuilder rs = new StringBuilder();
        int j = 0;
        while (index1 >= 0 || index2 >= 0 || j != 0) {
            int a1 = index1 >= 0 ? num1.charAt(index1) - '0' : 0;
            int b1 = index2 >= 0 ? num2.charAt(index2) - '0' : 0;
            int r = a1 + b1 + j;
            j = r / 10;
            rs.append(r % 10);
            index1--;
            index2--;
        }
        return rs.reverse().toString();

    }

}
