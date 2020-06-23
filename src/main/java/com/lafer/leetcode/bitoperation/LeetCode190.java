package com.lafer.leetcode.bitoperation;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * 190. 颠倒二进制位
 * 颠倒给定的 32 位无符号整数的二进制位。
 *
 *
 *
 * 示例 1：
 *
 * 输入: 00000010100101000001111010011100
 * 输出: 00111001011110000010100101000000
 * 解释: 输入的二进制串 00000010100101000001111010011100 表示无符号整数 43261596，
 *      因此返回 964176192，其二进制表示形式为 00111001011110000010100101000000。
 * 示例 2：
 *
 * 输入：11111111111111111111111111111101
 * 输出：10111111111111111111111111111111
 * 解释：输入的二进制串 11111111111111111111111111111101 表示无符号整数 4294967293，
 *      因此返回 3221225471 其二进制表示形式为 10111111111111111111111111111111 。
 *
 *
 * 提示：
 *
 * 请注意，在某些语言（如 Java）中，没有无符号整数类型。在这种情况下，输入和输出都将被指定为有符号整数类型，并且不应影响您的实现，因为无论整数是有符号的还是无符号的，其内部的二进制表示形式都是相同的。
 * 在 Java 中，编译器使用二进制补码记法来表示有符号整数。因此，在上面的 示例 2 中，输入表示有符号整数 -3，输出表示有符号整数 -1073741825。
 *
 *
 * 进阶:
 * 如果多次调用这个函数，你将如何优化你的算法？
 *
 * 如果需多次调用这个函数，则按字节进行反转，反转过的字节放到cache中。
 *
 */

public class LeetCode190 {

    // you need treat n as an unsigned value
    public int reverseBits(int n) {
        int ret = 0;
        int cnt = 0;
        while (cnt++ < 32) {
            int t = n & 1;
            ret <<= 1;
            ret |= t;
            n >>>= 1;
        }
        return ret;
    }

    private static Map<Byte, Integer> cache = new HashMap<>();

    public static int reverseBits1(int n) {
        int ret = 0, cnt = 0;
        while (cnt++ < 4) {
            int re = reverseByte((byte) (n & 0b11111111));
            ret <<= 8;
            ret |= re;
            n >>= 8;
        }
        return ret;
    }

    private static int reverseByte(byte b) {
        if (cache.containsKey(b)) {
            return cache.get(b);
        }
        int ret = 0, cnt = 0;
        byte temp = b;
        while (cnt++ < 8) {
            int t = b & 1;
            ret <<= 1;
            ret |= t;
            b >>>= 1;
        }
        cache.put(temp, ret);
        return ret;
    }

    public static void main(String[] args) {
        int i = reverseBits1(43261596);
        System.out.println(i);
    }

}
