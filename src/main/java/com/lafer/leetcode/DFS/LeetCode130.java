package com.lafer.leetcode.DFS;

/**
 * 130. 被围绕的区域
 * 给定一个二维的矩阵，包含 'X' 和 'O'（字母 O）。
 *
 * 找到所有被 'X' 围绕的区域，并将这些区域里所有的 'O' 用 'X' 填充。
 *
 * 示例:
 *
 * X X X X
 * X O O X
 * X X O X
 * X O X X
 * 运行你的函数后，矩阵变为：
 *
 * X X X X
 * X X X X
 * X X X X
 * X O X X
 * 解释:
 *
 * 被围绕的区间不会存在于边界上，换句话说，任何边界上的 'O' 都不会被填充为 'X'。 任何不在边界上，
 * 或不与边界上的 'O' 相连的 'O' 最终都会被填充为 'X'。如果两个元素在水平或垂直方向相邻，则称它们是“相连”的。
 *
 * 思考：从边界的'O'出发，与边界的'O'连通的'O'都不会变成'X'。其余的都会变成'X'
 *
 */

public class LeetCode130 {

    private int[][] direction = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    private int m, n;

    public void solve(char[][] board) {
        if (board == null || board.length == 0) {
            return;
        }
        m = board.length;
        n = board[0].length;
        for (int i = 0; i < board.length; i++) {
            DFS(board, i, 0);
            DFS(board, i, n - 1);
        }
        for (int j = 0; j < board[0].length; j++) {
            DFS(board, 0, j);
            DFS(board, m - 1, j);
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = board[i][j] == 'A' ? 'O' : 'X';
            }
        }

    }

    private void DFS(char[][] board, int i, int j) {
        if (i < 0 || i >= m || j < 0 || j >= n || board[i][j] == 'X' || board[i][j] == 'A') {
            return;
        }
        board[i][j] = 'A';
        for (int[] dir : direction) {
            DFS(board, i + dir[0], j + dir[1]);
        }
    }

}
