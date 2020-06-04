package com.lafer.leetcode.backtrack;

/**
 *
 * 37. 解数独
 * 编写一个程序，通过已填充的空格来解决数独问题。
 *
 * 一个数独的解法需遵循如下规则：
 *
 * 数字 1-9 在每一行只能出现一次。
 * 数字 1-9 在每一列只能出现一次。
 * 数字 1-9 在每一个以粗实线分隔的 3x3 宫内只能出现一次。
 * 空白格用 '.' 表示。
 *
 * 给定的数独序列只包含数字 1-9 和字符 '.' 。
 * 你可以假设给定的数独只有唯一解。
 * 给定数独永远是 9x9 形式的。
 *
 * 思考：
 * 1、暴力法
 * 2、回溯法
 *
 */

public class LeetCode37 {

    char[][] board = null;
    int[][] rowsUsed = new int[9][10];
    int[][] closUsed = new int[9][10];
    int[][] boxsUsed = new int[9][10];

    public void solveSudoku(char[][] board) {
        this.board = board;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] != '.') {
                    int num = board[i][j] - '0';
                    rowsUsed[i][num] = 1;
                    closUsed[j][num] = 1;
                    boxsUsed[boxnum(i, j)][num] = 1;
                }
            }
        }
        backTrack(0, 0);
    }


    private boolean backTrack(int i, int j) {
        while (i < 9 && j < 9 && board[i][j] != '.') {
            //以从左往右、从上往下的顺序下一个数的位置。
            i = j == 8 ? i + 1 : i;
            j = j == 8 ? 0 : j + 1;
        }
        if (i == 9) {
            return true;
        }
        for (int num = 1; num < 10; num++) {
            if (getIsLegal(i, j, num)) {
                setNumIsUse(i, j, num);
                if (backTrack(i, j)) {
                    return true;
                }
                setNumIsNotUse(i, j, num);
            }
        }
        return false;
    }

    /**
     * 判断i行j列的数属于哪一个box
     * @param i
     * @param j
     * @return
     */
    private int boxnum(int i, int j) {
        int r = i / 3;
        int c = j / 3;
        return r * 3 + c;
    }


    /**
     * 判断在第i行j列插入num是否合法
     * @param i
     * @param j
     * @param num
     * @return
     */
    private boolean getIsLegal(int i, int j, int num) {
        return rowsUsed[i][num] + closUsed[j][num] + boxsUsed[boxnum(i, j)][num] == 0;
    }

    /**
     * 设置第i行j列为num
     * @param i
     * @param j
     * @param num
     */
    private void setNumIsUse(int i, int j, int num) {
        rowsUsed[i][num] = closUsed[j][num] = boxsUsed[boxnum(i, j)][num] = 1;
        this.board[i][j] = (char)('0' + num);
    }

    /**
     * 设置第i行j列为'.'
     * @param i
     * @param j
     * @param num
     */
    private void setNumIsNotUse(int i, int j, int num) {
        rowsUsed[i][num] = closUsed[j][num] = boxsUsed[boxnum(i, j)][num] = 0;
        this.board[i][j] = '.';
    }

}
