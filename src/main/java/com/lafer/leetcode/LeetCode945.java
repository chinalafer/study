package com.lafer.leetcode;

import java.util.Arrays;

/**
 * 给定整数数组 A，每次 move 操作将会选择任意 A[i]，并将其递增 1。
 *
 * 返回使 A 中的每个值都是唯一的最少操作次数。
 *
 * 提示：
 *
 * 0 <= A.length <= 40000
 * 0 <= A[i] < 40000
 *
 * 思路：
 * 1、 先进行排序， 排序玩之后，一次与前一个数进行比较，小于等于前一个数，相减后加一。(排序法)  时间复杂度为O(nlgn), 额外空间复杂度为O(k)
 *
 * 2、每个数的大小都限制了大小， 可以考虑打表计数法的方式解决问题。先设置要给大小为80000的数据，
 *   进行打表， 记录每个数出现的次数。然后对重复出现的数字由此往后占位。但是这样做的算法时间复杂度最糟糕的情况下时O(n*n)
 *   对数组[1,1,1,3,3,5,6] 结果计算为 2 - 1 + 4 - 1 + 7 - 3 = ( 2 + 4 + 7 ) - ( 1 + 1 + 3 ) = 8
 *   对就近的出现次数为0的数累加然后减去重复的数的和便是结果，数量相等。
 */

public class LeetCode945 {

    public static void main(String[] args) {
        minIncrementForUnique2(new int[]{1, 2, 2});
    }

    public int minIncrementForUnique(int[] A) {
        if (A == null || A.length < 2) {
            return 0;
        }
        Arrays.sort(A);
        int ans = 0;
        for (int i = 1; i < A.length; i++ ) {
            if (A[i - 1] >= A[i]) {
                ans += A[i - 1] - A[i] + 1;
                A[i] = A[i - 1] + 1;
            }
        }
        return ans;
    }


    public static int minIncrementForUnique2(int[] A) {
        int ans = 0, num = 0;
        int js[] = new int[80001];
        for (int i: A) {
            js[i]++;
        }
        for (int i = 0; i < js.length; i++) {
            if (js[i] > 1) {
                ans -= (js[i] - 1) * i;
                num += (js[i] - 1);
            }
            if (js[i] == 0 && num > 0) {
                ans += i;
                num--;
            }
        }
        return ans;
    }

}
