package com.lafer.leetcode.doublePointer;

public class LeetCode1004 {

    public int longestOnes(int[] A, int K) {
        int ret = 0, right = 0, left = 0, count = 0;
        while (right < A.length) {
            count += A[right];
            int length = right - left + 1;
            if (length - count - K > 0) {
                count -= A[left++];
            } else {
                ret = Math.max(length, ret);
            }
            right++;
        }
        return ret;
    }

}
