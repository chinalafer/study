package com.lafer.study;

import java.util.Arrays;
import java.util.List;

/**
 * 给定一个非负索引 k，其中 k ≤ 33，返回杨辉三角的第 k 行。
 * 在杨辉三角中，每个数是它左上方和右上方的数的和。
 * 你可以优化你的算法到 O(k) 空间复杂度吗？
 *
 */

public class LeetCode119 {

    public List<Integer> getRow(int rowIndex) {
        Integer[] result = new Integer[rowIndex + 1];
        for (int i = 1; i <= rowIndex + 1; i++) {
            for (int j = i - 1; j >= 0; j--) {
                if (j == 0 || j == i - 1) {
                    result[j] = 1;
                } else {
                    result[j] = result[j] + result[j - 1];
                }
            }
        }
        return Arrays.asList(result);
    }

}