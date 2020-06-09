package com.lafer.leetcode.math;

/**
 *
 * 67. 二进制求和
 * 给你两个二进制字符串，返回它们的和（用二进制表示）。
 *
 * 输入为 非空 字符串且只包含数字 1 和 0。
 *
 *
 *
 * 示例 1:
 *
 * 输入: a = "11", b = "1"
 * 输出: "100"
 * 示例 2:
 *
 * 输入: a = "1010", b = "1011"
 * 输出: "10101"
 *
 *
 * 提示：
 *
 * 每个字符串仅由字符 '0' 或 '1' 组成。
 * 1 <= a.length, b.length <= 10^4
 * 字符串如果不是 "0" ，就都不含前导零。
 *
 */

public class LeetCode67 {

    public String addBinary(String a, String b) {
        if (a == null || b == null) {
            return a == null ? b : a;
        }
        int index1 = a.length() - 1;
        int index2 = b.length() - 1;
        StringBuilder rs = new StringBuilder();
        int j = 0;
        while (index1 >= 0 || index2 >= 0 || j != 0) {
            int a1 = index1 >= 0 ? a.charAt(index1) - '0' : 0;
            int b1 = index2 >= 0 ? b.charAt(index2) - '0' : 0;
            int r = a1 + b1 + j;
            j = r / 2;
            rs.append(r % 2);
            index1--;
            index2--;
        }
        return rs.reverse().toString();
    }

}
