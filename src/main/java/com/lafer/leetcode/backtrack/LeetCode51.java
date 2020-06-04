package com.lafer.leetcode.backtrack;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * 51. N皇后
 * n 皇后问题研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。
 *
 * 给定一个整数 n，返回所有不同的 n 皇后问题的解决方案。
 *
 * 每一种解法包含一个明确的 n 皇后问题的棋子放置方案，该方案中 'Q' 和 '.' 分别代表了皇后和空位。
 *
 * 示例:
 *
 * 输入: 4
 * 输出: [
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
 * 解释: 4 皇后问题存在两个不同的解法。
 *
 */

public class LeetCode51 {

    public static void main(String[] args) {
        LeetCode51 leetCode51 = new LeetCode51();
        leetCode51.solveNQueens(4);
        System.out.println(leetCode51.output);
    }

    List<List<String>> output = new ArrayList<>();
    boolean[] colsUsed = null;
    boolean[] xzUsed = null;
    boolean[] xyUsed = null;
    StringBuilder sb = new StringBuilder("");
    int n;

    public List<List<String>> solveNQueens(int n) {
        this.colsUsed = new boolean[n];
        this.xzUsed = new boolean[3 * n];
        this.xyUsed = new boolean[3 * n];
        this.n = n;
        for (int i = 0; i < n; i++) {
            this.sb.append(".");
        }
        backTrack(1, new LinkedList<>());
        return this.output;
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
