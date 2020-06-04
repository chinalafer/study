package com.lafer.leetcode.backtrack;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * 52. N皇后 II
 * n 皇后问题研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。
 *
 *
 *
 * 上图为 8 皇后问题的一种解法。
 *
 * 给定一个整数 n，返回 n 皇后不同的解决方案的数量。
 *
 * 示例:
 *
 * 输入: 4
 * 输出: 2
 * 解释: 4 皇后问题存在如下两个不同的解法。
 * [
 *  [".Q..",  // 解法 1
 *   "...Q",
 *   "Q...",
 *   "..Q."],
 *
 *  ["..Q.",  // 解法 2
 *   "Q...",
 *   "...Q",
 *   ".Q.."]
 * ]
 *
 */

public class LeetCode52 {

    List<List<String>> output = new ArrayList<>();
    boolean[] colsUsed = null;
    boolean[] xzUsed = null;
    boolean[] xyUsed = null;
    StringBuilder sb = new StringBuilder("");
    int n;

    public int totalNQueens(int n) {
        this.colsUsed = new boolean[n];
        this.xzUsed = new boolean[3 * n];
        this.xyUsed = new boolean[3 * n];
        this.n = n;
        for (int i = 0; i < n; i++) {
            this.sb.append(".");
        }
        backTrack(1, new LinkedList<>());
        return this.output.size();
    }

    private void backTrack(int cols, LinkedList<String> curr) {
        if (cols > n) {
            output.add(new ArrayList<>(curr));
            return;
        }
        for (int i = 0; i < this.n; i++) {
            int t1 = i - (cols - 1) + n;
            int t2 = i + cols - 1;
            if (colsUsed[i] || xzUsed[t1] || xyUsed[t2]) {
                continue;
            }
            StringBuilder temp = new StringBuilder(sb);
            temp.replace(i, i + 1, "Q");
            curr.add(temp.toString());
            colsUsed[i] = true;
            xzUsed[t1] = true;
            xyUsed[t2] = true;
            backTrack(cols + 1, curr);
            curr.removeLast();
            colsUsed[i] = false;
            xzUsed[t1] = false;
            xyUsed[t2] = false;
        }
    }
}
