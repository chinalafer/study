package com.lafer.leetcode;

/**
 *
 * 根据 百度百科 ，生命游戏，简称为生命，是英国数学家约翰·何顿·康威在 1970 年发明的细胞自动机。
 *
 * 给定一个包含 m × n 个格子的面板，每一个格子都可以看成是一个细胞。每个细胞都具有一个初始状态：1 即为活细胞（live），或 0 即为死细胞（dead）。每个细胞与其八个相邻位置（水平，垂直，对角线）的细胞都遵循以下四条生存定律：
 *
 * 如果活细胞周围八个位置的活细胞数少于两个，则该位置活细胞死亡；
 * 如果活细胞周围八个位置有两个或三个活细胞，则该位置活细胞仍然存活；
 * 如果活细胞周围八个位置有超过三个活细胞，则该位置活细胞死亡；
 * 如果死细胞周围正好有三个活细胞，则该位置死细胞复活；
 * 根据当前状态，写一个函数来计算面板上所有细胞的下一个（一次更新后的）状态。下一个状态是通过将上述规则同时应用于当前状态下的每个细胞所形成的，其中细胞的出生和死亡是同时发生的。
 *
 *  
 *
 * 示例：
 *
 * 输入：
 * [
 *   [0,1,0],
 *   [0,0,1],
 *   [1,1,1],
 *   [0,0,0]
 * ]
 * 输出：
 * [
 *   [0,0,0],
 *   [1,0,1],
 *   [0,1,1],
 *   [0,1,0]
 * ]
 *  
 *
 * 进阶：
 *
 * 你可以使用原地算法解决本题吗？请注意，面板上所有格子需要同时被更新：你不能先更新某些格子，然后使用它们的更新后的值再更新其他格子。
 * 本题中，我们使用二维数组来表示面板。原则上，面板是无限的，但当活细胞侵占了面板边界时会造成问题。你将如何解决这些问题？
 *
 *
 * 思考：
 * 1、模拟变换法
 *   复制一个数组，保持复制的数组不变，每次都在复制好的数组上面进行判断周围活细胞的数量，然后改变目标数组的值
 * 2、使用高位保存变化后的状态（题解）
 *   由于只用到了两个状态 0 1， 使用了一位二进制，那么高位都是为0， 使用第二位二进制来表达转换后的状态
 *   00 表示 死细胞 -> 死细胞
 *   01 表示 活细胞 -> 死细胞
 *   10 表示 死细胞 -> 活细胞
 *   11 表示 活细胞 -> 活细胞
 *
 *   变换完成之后， 最后右移即可。
 *
 */

public class LeetCode289 {

    public static void main(String[] args) {
        int[][] board = new int[][]{
                {0, 1, 0},
                {0, 0, 1},
                {1, 1, 1},
                {0, 0, 0}
        };
        gameOfLife(board);
    }

    public static void gameOfLife(int[][] board) {
        if (board == null || board.length == 0) {
            return;
        }
        int[][] temp = new int[board.length][board[0].length];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                temp[i][j] = board[i][j];
            }
        }
        for (int i = 0; i < temp.length; i++) {
            for (int j = 0; j < temp[0].length; j++) {
                int count = getLCellCount(temp, i, j);
                if (temp[i][j] == 1 && (count < 2 || count > 3)) {
                    board[i][j] = 0;
                }
                if (temp[i][j] == 0 && count == 3) {
                    board[i][j] = 1;
                }
            }
        }
    }

    public static int getLCellCount(int[][] temp, int x, int y) {
        int dx[] = {-1, 0, 1, -1, 1, -1, 0, 1};
        int dy[] = {-1, -1, -1, 0, 0, 1, 1, 1};
        int count = 0;
        for (int i = 0; i < 8; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (nx >= 0 && ny >= 0 && nx < temp.length && ny < temp[0].length) {
                count += (temp[nx][ny] & 1);
            }
        }
        return count;
    }

    public static void gameOfLife1(int[][] board) {
        if (board == null || board.length == 0) {
            return;
        }

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                int count = getLCellCount(board, i, j);
//                if (board[i][j] == 1) {
//                    if (count == 2 || count == 3) {
//                        board[i][j] |= 2;
//                    }
//                } else {
//                    if (count == 3) {
//                        board[i][j] |= 2;
//                    }
//                }
                if ((board[i][j] == 1 && (count == 2 || count == 3)) || (board[i][j] == 0 && count == 3)) {
                    board[i][j] |= 2;
                }
            }
        }
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                board[i][j] >>= 1;
            }
        }
    }

}
