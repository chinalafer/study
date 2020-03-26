package com.lafer.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个非负整数 numRows，生成杨辉三角的前 numRows 行。
 * 在杨辉三角中，每个数是它左上方和右上方的数的和。
 *
 * 思考：挨个计算即可
 */

public class LeetCode118 {

    public List<List<Integer>> generate(int numRows) {

        List<List<Integer>> result = new ArrayList<>();

        List<Integer> pre = null;

        for (int i = 1; i <= numRows; i++) {
            List<Integer> now = new ArrayList<>();
            for (int j = 0; j < i; j++) {
                if (j == 0 || j == i - 1) {
                    now.add(1);
                } else {
                    now.add(pre.get(j) + pre.get(j - 1));
                }
            }
            result.add(now);
            pre = now;
        }

        return result;
    }

}
