package com.lafer.leetcode.graph;

import java.util.*;

/**
 *
 * 207. 课程表
 * 你这个学期必须选修 numCourse 门课程，记为 0 到 numCourse-1 。
 *
 * 在选修某些课程之前需要一些先修课程。 例如，想要学习课程 0 ，你需要先完成课程 1 ，我们用一个匹配来表示他们：[0,1]
 *
 * 给定课程总量以及它们的先决条件，请你判断是否可能完成所有课程的学习？
 *
 *
 *
 * 示例 1:
 *
 * 输入: 2, [[1,0]]
 * 输出: true
 * 解释: 总共有 2 门课程。学习课程 1 之前，你需要完成课程 0。所以这是可能的。
 * 示例 2:
 *
 * 输入: 2, [[1,0],[0,1]]
 * 输出: false
 * 解释: 总共有 2 门课程。学习课程 1 之前，你需要先完成​课程 0；并且学习课程 0 之前，你还应先完成课程 1。这是不可能的。
 *
 *
 * 提示：
 *
 * 输入的先决条件是由 边缘列表 表示的图形，而不是 邻接矩阵 。详情请参见图的表示法。
 * 你可以假定输入的先决条件中没有重复的边。
 * 1 <= numCourses <= 10^5
 *
 * 思考：
 * 判断有向图是否有环，使用dfs判断是否存在环，可以使用一个全局数组剪枝（之前节点访问过的就不用再去递归判断）
 * bfs拓扑排序，判断是否所有节点都进入的队列即可。
 *
 */

public class LeetCode207 {

    public static void main(String[] args) {
        canFinish(4, new int[][]{{1, 3}, {0, 1},{3, 1},{3, 2}});
    }

    public static boolean canFinish(int numCourses, int[][] prerequisites) {
        List<Integer>[] graph = new List[numCourses];
        //图邻接表初始化
        for (int i = 0; i < numCourses; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int[] pre : prerequisites) {
            graph[pre[1]].add(pre[0]);
        }
        int[] curVisit = new int[numCourses];
        int[] globalVisit = new int[numCourses];
        for (int i = 0; i < numCourses; i++) {
            if (graph[i].isEmpty()) {
                continue;
            }
            if (globalVisit[i] == 0 && dfs(i, graph, curVisit, globalVisit)) {
                return false;
            }
        }
        return true;
    }

    private static boolean dfs(int i, List<Integer>[] graph, int[] curVisit, int[] globalVisit) {
        if (curVisit[i] == 1) {
            return true;
        }
        if (globalVisit[i] == 1) {
            return false;
        }
        globalVisit[i] = 1;
        curVisit[i] = 1;
        for (int index : graph[i]) {
            if (dfs(index, graph, curVisit, globalVisit)) {
                return true;
            }
        }
        curVisit[i] = 0;
        return false;
    }

    public boolean canFinish1(int numCourses, int[][] prerequisites) {
        List<Integer>[] graph = new List[numCourses];
        //入度表
        int[] indegrees = new int[numCourses];
        //图邻接表初始化
        for (int i = 0; i < numCourses; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int[] pre : prerequisites) {
            graph[pre[1]].add(pre[0]);
            indegrees[pre[0]]++;
        }
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < indegrees.length; i++) {
            if (indegrees[i] == 0) {
                queue.add(i);
            }
        }
        int num = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                int index = queue.poll();
                num++;
                for (int next : graph[index]) {
                    if (--indegrees[next] == 0) {
                        queue.add(next);
                    }
                }
            }
        }
        return num == numCourses;

    }

}
