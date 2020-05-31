package com.lafer.leetcode.doublePointer;

/**
 * 633. 平方数之和
 * 给定一个非负整数 c ，你要判断是否存在两个整数 a 和 b，使得 a2 + b2 = c。
 *
 * 示例1:
 *
 * 输入: 5
 * 输出: True
 * 解释: 1 * 1 + 2 * 2 = 5
 *
 *
 * 示例2:
 *
 * 输入: 3
 * 输出: False
 *
 * 思考：双指针
 *
 */

public class LeetCode633 {

    public boolean judgeSquareSum(int c) {
        int index1 = 1, index2 = (int)Math.sqrt(c);
        while (index1 <= index2) {
            int p = index1 * index1 + index2 * index2;
            if (p == c) {
                return true;
            } else if (p < c) {
                index1++;
            } else {
                index2--;
            }
        }
        return false;
    }

}
