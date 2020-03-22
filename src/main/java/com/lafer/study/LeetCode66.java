package com.lafer.study;

import java.util.Arrays;

/**
 * 给定一个由整数组成的非空数组所表示的非负整数，在该数的基础上加一。
 *
 * 最高位数字存放在数组的首位， 数组中每个元素只存储单个数字。
 *
 * 你可以假设除了整数 0 之外，这个整数不会以零开头。
 *
 * 想法： 数学中 加1的挨个考虑进位即可。最后一个算法中，如果最后会进位， 那肯定就是1开头后面全是0的数。
 */

public class LeetCode66 {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(plusOne2(new int[]{9, 9, 3})));
    }

    public static int[] plusOne(int[] digits) {
        int result[] = new int[digits.length + 1];
        int jw = 1;
        for (int i$ = digits.length - 1; i$ >= 0; i$--) {
            result[i$ + 1] = (digits[i$] + jw) % 10;
            jw = (digits[i$] + jw) / 10;
        }
        result[0] = jw;
        return Arrays.copyOfRange(result, result[0] ^ 1, result.length);
    }

    public static int[] plusOne2(int[] digits) {
        int jw = 1;
        int temp = 0;
        for (int i = digits.length - 1; i >= 0; i--) {
            temp = digits[i];
            digits[i] = (temp + jw) % 10;
            jw = (temp + jw) / 10;
            if (jw == 0) {
                break;
            }
        }
        if (jw == 1) {
            int[] a = new int[]{jw};
            int[] result = new int[digits.length + 1];
            System.arraycopy(a, 0, result, 0, a.length);
            System.arraycopy(digits, 0, result, a.length, digits.length);
            return result;
        } else {
            return digits;
        }
    }

    public static int[] plusOne3(int[] digits) {
        int jw = 1;
        int temp = 0;
        for (int i = digits.length - 1; i >= 0; i--) {
            temp = digits[i];
            digits[i] = (temp + jw) % 10;
            jw = (temp + jw) / 10;
            if (jw == 0) {
                return digits;
            }
        }
        digits = new int[digits.length + 1];
        digits[0] = 1;
        return digits;
    }


}
