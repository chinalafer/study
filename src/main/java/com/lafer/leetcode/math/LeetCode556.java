package com.lafer.leetcode.math;

/**
 *
 * 556. 下一个更大元素 III
 * 给定一个32位正整数 n，你需要找到最小的32位整数，其与 n 中存在的位数完全相同，并且其值大于n。如果不存在这样的32位整数，则返回-1。
 *
 * 示例 1:
 *
 * 输入: 12
 * 输出: 21
 * 示例 2:
 *
 * 输入: 21
 * 输出: -1
 *
 *
 *
 */

public class LeetCode556 {

    public int nextGreaterElement(int n) {
        char[] c = String.valueOf(n).toCharArray();
        int transIndex = findTransIndex(c);
        if (transIndex == -1) {
            return -1;
        }
        int exchangeIndex = exchangeIndex(c, transIndex);
        swap(c, transIndex, exchangeIndex);
        revice(c, transIndex + 1, c.length - 1);
        long re = charArraysToNum(c);
        if (re > Integer.MAX_VALUE) {
            return -1;
        }
        return ((int) re) == n ? -1 : (int) re;
    }

    private int findTransIndex(char[] c) {
        int index = c.length - 2;
        while (index >= 0 && c[index] > c[index + 1]) {
            index--;
        }
        return index;
    }

    private int exchangeIndex(char[] c, int index) {
//        int targetIndex = index + 1;
//        int target = c[index];
//        while (index < c.length) {
//            if (c[index] > target && c[index] < c[targetIndex]) {
//                targetIndex = index;
//            }
//            index++;
//        }
        int target = c[index];
        int taIndex = c.length - 1;
        while (taIndex > index) {
            if (c[taIndex] > c[index]) {
                return taIndex;
            }
            taIndex--;
        }
        return taIndex;
    }

    private void swap(char[] c, int i, int j) {
        char temp = c[i];
        c[i] = c[j];
        c[j] = temp;
    }

    private void revice(char[] c, int i, int j) {
        while (i < j) {
            swap(c, i, j);
            j--;
            i++;
        }
    }

    private long charArraysToNum(char[] c) {
        long re = 0;
        int index = 0;
        while (index < c.length) {
            re = re * 10 + c[index++] - '0';
        }
        return re;
    }

}
