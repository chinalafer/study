package com.lafer.leetcode.DFS;

import java.util.ArrayList;
import java.util.List;

/**
 * 417. 太平洋大西洋水流问题
 * 给定一个 m x n 的非负整数矩阵来表示一片大陆上各个单元格的高度。“太平洋”处于大陆的左边界和上边界，而“大西洋”处于大陆的右边界和下边界。
 *
 * 规定水流只能按照上、下、左、右四个方向流动，且只能从高到低或者在同等高度上流动。
 *
 * 请找出那些水流既可以流动到“太平洋”，又能流动到“大西洋”的陆地单元的坐标。
 *
 *
 *
 * 提示：
 *
 * 输出坐标的顺序不重要
 * m 和 n 都小于150
 *
 *
 * 示例：
 *
 *
 *
 * 给定下面的 5x5 矩阵:
 *
 *   太平洋 ~   ~   ~   ~   ~
 *        ~  1   2   2   3  (5) *
 *        ~  3   2   3  (4) (4) *
 *        ~  2   4  (5)  3   1  *
 *        ~ (6) (7)  1   4   5  *
 *        ~ (5)  1   1   2   4  *
 *           *   *   *   *   * 大西洋
 *
 * 返回:
 *
 * [[0, 4], [1, 3], [1, 4], [2, 2], [3, 0], [3, 1], [4, 0]] (上图中带括号的单元).
 *
 * 思考：从四边出发逆流深搜（DFS）
 *
 */

public class LeetCode417 {

    private int[][] direction = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    private int m, n;

    public List<List<Integer>> pacificAtlantic(int[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            return new ArrayList<>();
        }
        m = matrix.length;
        n = matrix[0].length;
        int[][] tmatrix  = new int[m][n];
        int[][] dmatrix = new int[m][n];
        for (int i = 0; i < m; i++) {
            DFS(matrix, i, 0, tmatrix, matrix[i][0]);
            DFS(matrix, i, n - 1, dmatrix, matrix[i][n - 1]);
        }
        for (int j = 0; j < n; j++) {
            DFS(matrix, 0, j, tmatrix, matrix[0][j]);
            DFS(matrix, m - 1, j, dmatrix, matrix[m - 1][j]);
        }
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (tmatrix[i][j] == 1 && dmatrix[i][j] == 1) {
                    List<Integer> point = new ArrayList<>();
                    point.add(i);
                    point.add(j);
                    result.add(point);
                }
            }
        }
        return result;
    }

    private void DFS(int[][] matrix, int i, int j, int[][] targetMatrix, int pre) {
        if (i < 0 || j < 0 || i >= m || j >= n || targetMatrix[i][j] == 1 || pre > matrix[i][j]) {
            return;
        }
        targetMatrix[i][j] = 1;
        for (int[] dir : direction) {
            DFS(matrix, i + dir[0], j + dir[1], targetMatrix, matrix[i][j]);
        }
    }


}
