package com.lafer.leetcode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 你现在手里有一份大小为 N x N 的「地图」（网格） grid，上面的每个「区域」（单元格）都用 0 和 1 标记好了。其中 0 代表海洋，1 代表陆地，请你找出一个海洋区域，这个海洋区域到离它最近的陆地区域的距离是最大的。
 * <p>
 * 我们这里说的距离是「曼哈顿距离」（ Manhattan Distance）：(x0, y0) 和 (x1, y1) 这两个区域之间的距离是 |x0 - x1| + |y0 - y1| 。
 * <p>
 * 如果我们的地图上只有陆地或者海洋，请返回 -1。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * <p>
 * 输入：[[1,0,1],[0,0,0],[1,0,1]]
 * 输出：2
 * 解释：
 * 海洋区域 (1, 1) 和所有陆地区域之间的距离都达到最大，最大距离为 2。
 * 示例 2：
 * <p>
 * <p>
 * <p>
 * 输入：[[1,0,0],[0,0,0],[0,0,0]]
 * 输出：4
 * 解释：
 * 海洋区域 (2, 2) 和所有陆地区域之间的距离都达到最大，最大距离为 4。
 * <p>
 * 提示：
 * <p>
 * 1 <= grid.length == grid[0].length <= 100
 * grid[i][j] 不是 0 就是 1
 * <p>
 * 思考：bfs
 */

public class LeetCode1162 {

    public int maxDistance(int[][] grid) {

        if (grid == null || grid[0].length == 0) {
            return 0;
        }
        int maxLength = 0;
        Queue<int[]> queue = new LinkedList<>();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid.length; j++) {
                if (grid[i][j] == 1) {
                    queue.offer(new int[]{i, j});
                }
            }
        }
        //全是海洋或者全是陆地
        if (queue.size() == 0 || queue.size() == grid.length * grid[0].length) {
            return 0;
        }

        while (queue.size() > 0) {
            int[] point = queue.poll();
            int[] dirtx = new int[]{1, 0, -1, 0};
            int[] dirty = new int[]{0, 1, 0, -1};
            for (int i = 0; i < dirtx.length; i++) {
                int newx = point[0] + dirtx[i];
                int newy = point[1] + dirty[i];
                if (newx >= 0 && newx < grid.length && newy > 0 && newy < grid[0].length && grid[newx][newy] == 0) {
                    grid[newx][newy] = grid[point[0]][point[1]] + 1;
                    maxLength = maxLength < grid[newx][newy] ? grid[newx][newy] : maxLength;
                    queue.offer(new int[]{newx, newy});
                }
            }
        }
        return maxLength - 1;

    }

}
