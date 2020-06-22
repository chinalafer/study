package com.lafer.leetcode.graph;

/**
 *
 * 684. 冗余连接
 * 在本问题中, 树指的是一个连通且无环的无向图。
 *
 * 输入一个图，该图由一个有着N个节点 (节点值不重复1, 2, ..., N) 的树及一条附加的边构成。附加的边的两个顶点包含在1到N中间，这条附加的边不属于树中已存在的边。
 *
 * 结果图是一个以边组成的二维数组。每一个边的元素是一对[u, v] ，满足 u < v，表示连接顶点u 和v的无向图的边。
 *
 * 返回一条可以删去的边，使得结果图是一个有着N个节点的树。如果有多个答案，则返回二维数组中最后出现的边。答案边 [u, v] 应满足相同的格式 u < v。
 *
 * 示例 1：
 *
 * 输入: [[1,2], [1,3], [2,3]]
 * 输出: [2,3]
 * 解释: 给定的无向图为:
 *   1
 *  / \
 * 2 - 3
 * 示例 2：
 *
 * 输入: [[1,2], [2,3], [3,4], [1,4], [1,5]]
 * 输出: [1,4]
 * 解释: 给定的无向图为:
 * 5 - 1 - 2
 *     |   |
 *     4 - 3
 * 注意:
 *
 * 输入的二维数组大小在 3 到 1000。
 * 二维数组中的整数在1到N之间，其中N是输入数组的大小。
 *
 * 思考：
 * 并查集，输入边的时候，如果边的两个节点已经属于一个集合了，如果这条边就是多月的。
 *
 */

public class LeetCode685 {

    public int[] findRedundantConnection(int[][] edges) {
        UF uf = new UF(edges.length);
        int[] re = new int[]{-1, -1};
        for (int[] edg : edges) {
            if (uf.connect(edg[0], edg[1])) {
                re = edg;
            } else {
                uf.union(edg[0], edg[1]);
            }
        }
        return re;
    }

    class UF {

        private int[] id;

        public UF(int N) {
            id = new int[N + 1];
            for (int i = 0; i < id.length; i++) {
                id[i] = i;
            }
        }

        void union(int u, int v) {
            int fu = find(u);
            int fv = find(v);
            if (fu != fv) {
                id[fv] = fu;
            }
        }

        /**
         * 路径压缩
         * @param p
         * @return
         */
        int find(int p) {
            int re = p;
            while (id[re] != re) {
                re = id[re];
            }
            while (id[p] != re) {
                int fa = id[p];
                id[p] = re;
                p = fa;
            }
            return re;
        }

        boolean connect(int u, int v) {
            return find(u) == find(v);
        }

    }

}
