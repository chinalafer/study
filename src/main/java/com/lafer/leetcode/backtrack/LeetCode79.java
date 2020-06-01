package com.lafer.leetcode.backtrack;

import java.util.Arrays;

/**
 *
 * 79. 单词搜索
 * 给定一个二维网格和一个单词，找出该单词是否存在于网格中。
 *
 * 单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。
 *
 *
 *
 * 示例:
 *
 * board =
 * [
 *   ['A','B','C','E'],
 *   ['S','F','C','S'],
 *   ['A','D','E','E']
 * ]
 *
 * 给定 word = "ABCCED", 返回 true
 * 给定 word = "SEE", 返回 true
 * 给定 word = "ABCB", 返回 false
 *
 *
 * 提示：
 *
 * board 和 word 中只包含大写和小写英文字母。
 * 1 <= board.length <= 200
 * 1 <= board[i].length <= 200
 * 1 <= word.length <= 10^3
 *
 */

public class LeetCode79 {

    private int[][] direction = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    private int m, n;

    public boolean exist(char[][] board, String word) {
        if (word == null || word.length() == 0) {
            return true;
        }
        if (board == null || board.length == 0 || board[0].length == 0) {
            return false;
        }
        m = board.length;
        n = board[0].length;
        char c = word.charAt(0);
        int[][] isVisit = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (c == board[i][j]) {
                    boolean b = backTrack(board, word, isVisit, i, j);
                    if (b) {
                        return b;
                    }
                }
            }
        }
        return false;
    }

    private boolean backTrack(char[][] board, String word, int[][] isVisit, int i, int j) {
        if ("".equals(word)) {
            return true;
        }
        if (i < 0 || i >= m || j < 0 || j >= n || isVisit[i][j] == 1 || board[i][j] != word.charAt(0)) {
            return false;
        }
        isVisit[i][j] = 1;
        for (int[] dir : direction) {
            boolean b = backTrack(board, word.substring(1),isVisit, i + dir[0], j + dir[1]);
            if (b) {
                return b;
            }
        }
        isVisit[i][j] = 0;
        return false;
    }

}
