package com.lafer.leetcode.bitoperation;

/**
 * 476. 数字的补数
 * 给定一个正整数，输出它的补数。补数是对该数的二进制表示取反。
 *
 *
 *
 * 示例 1:
 *
 * 输入: 5
 * 输出: 2
 * 解释: 5 的二进制表示为 101（没有前导零位），其补数为 010。所以你需要输出 2 。
 * 示例 2:
 *
 * 输入: 1
 * 输出: 0
 * 解释: 1 的二进制表示为 1（没有前导零位），其补数为 0。所以你需要输出 0 。
 *
 *
 * 注意:
 *
 * 给定的整数保证在 32 位带符号整数的范围内。
 * 你可以假定二进制数不包含前导零位。
 * 思考：·101 ^ 111 = 010,本题转化为求掩码，掩码等于最高位的1左移一位减一。
 */

public class LeetCode476 {

    public int findComplement(int num) {
        int t = num, mask = 1;
        while (t != 0) {
            mask <<= 1;
            t >>>= 1;
        }
        return num ^ ((mask >> 1) - 1);
    }


    /**
     *
     * 对于 10000000 这样的数要扩展成 11111111，可以利用以下方法：
     * mask |= mask >> 1 11000000
     * mask |= mask >> 2 11110000
     * mask |= mask >> 4 11111111
     *
     */
    public int findComplement1(int num) {
        int mask = num;
        mask |= mask >> 1;
        mask |= mask >> 2;
        mask |= mask >> 4;
        mask |= mask >> 8;
        mask |= mask >> 16;
        return num ^ mask;
    }
}
