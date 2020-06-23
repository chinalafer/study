package com.lafer.leetcode.bitoperation;

/**
 *
 * 461. 汉明距离
 * 两个整数之间的汉明距离指的是这两个数字对应二进制位不同的位置的数目。
 *
 * 给出两个整数 x 和 y，计算它们之间的汉明距离。
 *
 * 注意：
 * 0 ≤ x, y < 231.
 *
 * 示例:
 *
 * 输入: x = 1, y = 4
 *
 * 输出: 2
 *
 * 解释:
 * 1   (0 0 0 1)
 * 4   (0 1 0 0)
 *        ↑   ↑
 *
 * 上面的箭头指出了对应二进制位不同的位置。
 *
 */

public class LeetCode461 {

    public int hammingDistance(int x, int y) {
        int yh = x ^ y;
        int re = 0;
        while (yh != 0) {
            if ((yh & 1) == 1) {
                re++;
            }
            yh >>=1;
        }
        Integer.bitCount(yh);
        return re;
    }

}
