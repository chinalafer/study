package com.lafer.leetcode;


import java.util.LinkedList;
import java.util.Queue;

/**
 * 542. 01 矩阵
 * 给定一个由 0 和 1 组成的矩阵，找出每个元素到最近的 0 的距离。
 * <p>
 * 两个相邻元素间的距离为 1 。
 * <p>
 * 示例 1:
 * 输入:
 * <p>
 * 0 0 0
 * 0 1 0
 * 0 0 0
 * 输出:
 * <p>
 * 0 0 0
 * 0 1 0
 * 0 0 0
 * 示例 2:
 * 输入:
 * <p>
 * 0 0 0
 * 0 1 0
 * 1 1 1
 * 输出:
 * <p>
 * 0 0 0
 * 0 1 0
 * 1 2 1
 * 注意:
 * <p>
 * 给定矩阵的元素个数不超过 10000。
 * 给定矩阵中至少有一个元素是 0。
 * 矩阵中的元素只在四个方向上相邻: 上、下、左、右。
 * <p>
 * 思考：bfs
 */

public class LeetCode542 {

    public int[][] updateMatrix(int[][] matrix) {
        //为了方便记录步数，初始话matrix，1变成-1，0保持不变。
        if (matrix == null || matrix.length == 0) {
            return matrix;
        }
        Queue<int[]> queue = new LinkedList<>();
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] == 0) {
                    queue.offer(new int[]{i, j});
                } else {
                    matrix[i][j] = -1;
                }
            }
        }
        while (queue.size() > 0) {
            int[] dirtx = new int[]{1, 0, -1, 0};
            int[] dirty = new int[]{0, 1, 0, -1};
            int[] point = queue.poll();
            for (int i = 0; i < dirtx.length; i++) {
                int bs = matrix[point[0]][point[1]];
                int x = point[0] + dirtx[i];
                int y = point[1] + dirty[i];
                if (x >= 0 && y >= 0 && x < matrix.length && y < matrix[x].length && (matrix[x][y]  > bs + 1 || matrix[x][y] == -1)) {
                    matrix[x][y] = bs + 1;
                    queue.offer(new int[]{x, y});
                }
            }
        }
        return matrix;

    }

}
