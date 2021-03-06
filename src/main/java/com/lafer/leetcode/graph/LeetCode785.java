package com.lafer.leetcode.graph;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * 785. 判断二分图
 * 给定一个无向图graph，当这个图为二分图时返回true。
 *
 * 如果我们能将一个图的节点集合分割成两个独立的子集A和B，并使图中的每一条边的两个节点一个来自A集合，一个来自B集合，我们就将这个图称为二分图。
 *
 * graph将会以邻接表方式给出，graph[i]表示图中与节点i相连的所有节点。每个节点都是一个在0到graph.length-1之间的整数。这图中没有自环和平行边：
 * graph[i] 中不存在i，并且graph[i]中没有重复的值。
 *
 *
 * 示例 1:
 * 输入: [[1,3], [0,2], [1,3], [0,2]]
 * 输出: true
 * 解释:
 * 无向图如下:
 * 0----1
 * |    |
 * |    |
 * 3----2
 * 我们可以将节点分成两组: {0, 2} 和 {1, 3}。
 *
 * 示例 2:
 * 输入: [[1,2,3], [0,2], [0,1,3], [0,2]]
 * 输出: false
 * 解释:
 * 无向图如下:
 * 0----1
 * | \  |
 * |  \ |
 * 3----2
 * 我们不能将节点分割成两个独立的子集。
 * 注意:
 *
 * graph 的长度范围为 [1, 100]。
 * graph[i] 中的元素的范围为 [0, graph.length - 1]。
 * graph[i] 不会包含 i 或者有重复的值。
 * 图是无向的: 如果j 在 graph[i]里边, 那么 i 也会在 graph[j]里边。
 *
 * 思考：DFS BFS 标记染色
 *
 */

public class LeetCode785 {

    public boolean isBipartite(int[][] graph) {
        int[] colors = new int[graph.length];
        Arrays.fill(colors, -1);
        for (int i = 0; i < graph.length; i++) {
            if (colors[i] == -1 && !isBipartite(i, 0, colors, graph)) {
                return false;
            }
        }
        return true;
    }

    private boolean isBipartite(int index, int curColor ,int[] colors ,int[][] graph) {
        if (colors[index] != -1) {
            return curColor == colors[index];
        }
        colors[index] = curColor;
        for (int i = 0; i < graph[index].length; i++) {
            if (!isBipartite(graph[index][i], 1 - curColor, colors, graph)) {
                return false;
            }
        }
        return true;
    }


    public boolean isBipartite1(int[][] graph) {
        color = new int[graph.length];
        for (int i = 0; i < graph.length; i++) {
            if (color[i] == 0 && !bfs(graph, i)) {
                return false;
            }
        }
        return true;
    }

    private int[] color = null;
    public boolean bfs(int[][] graph, int index) {
        int expectColor = 1;
        Queue<Integer> queue = new LinkedList<>();
        queue.add(index);
        color[index] = expectColor;
        while (!queue.isEmpty()) {
            expectColor = -expectColor;
            int size = queue.size();
            while (size-- > 0) {
                int po = queue.poll();
                for (int next : graph[po]) {
                    if (color[next] != expectColor) {
                        if (color[next] != 0) {
                            return false;
                        } else {
                            queue.add(next);
                            color[next] = expectColor;
                        }
                    }
                }
            }
        }
        return true;
    }

}
