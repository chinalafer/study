package com.lafer.leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 面试题13. 机器人的运动范围
 *
 * 地上有一个m行n列的方格，从坐标 [0,0] 到坐标 [m-1,n-1] 。一个机器人从坐标 [0, 0] 的格子开始移动，
 * 它每次可以向左、右、上、下移动一格（不能移动到方格外），也不能进入行坐标和列坐标的数位之和大于k的格子。
 * 例如，当k为18时，机器人能够进入方格 [35, 37] ，因为3+5+3+7=18。但它不能进入方格 [35, 38]，因为3+5+3+8=19。请问该机器人能够到达多少个格子？
 *
 * 思考：这是一道典型的DFS/BFS问题
 *
 */

public class LeetCode_I_13 {

    public int movingCount(int m, int n, int k) {
        //无效处理
        if (k < 0 || m < 1 || n < 1) {
            return 0;
        }
        //建立数组，java默认赋值0
        int[][] check = new int[m][n];
//        return BFS(check, k);
        return DFS(check, k, 0, 0);
    }

    // 广搜
    private int BFS(int[][] check, int k) {
        //定义方向
        int[] dirx = new int[]{0, 0, 1, -1};
        int[] diry = new int[]{1, -1, 0, 0};
        List<point> pointList = new ArrayList<>();
        int result = 1;
        check[0][0] = 1;
        pointList.add(new point(0, 0));
        while (pointList.size() > 0) {
            point cur = pointList.remove(0);
            for (int i = 0; i < dirx.length; i++) {
                int x = cur.getX() + dirx[i];
                int y = cur.getY() + diry[i];
                if (x >= 0 && x < check.length && y >= 0 && y < check[0].length && check[x][y] == 0 && checkKD(x, y, k)) {
                    check[x][y] = 1;
                    result++;
                    pointList.add(new point(x, y));
                }
            }
        }
        return result;
    }

    private boolean checkKD(int x, int y, int k){
        int result = 0;
        while (x != 0) {
            result += x % 10;
            x = x / 10;
        }
        while (y != 0) {
            result += y % 10;
            y = y / 10;
        }
        return result <= k;
    }

    class point {
        private int x;
        private int y;
        public point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public void setX(int x) {
            this.x = x;
        }

        public int getY() {
            return y;
        }

        public void setY(int y) {
            this.y = y;
        }
    }

    // 深搜
    private int DFS(int[][] check, int k, int x, int y) {
        if (!(x >= 0 && x < check.length && y >= 0 && y < check[0].length && check[x][y] == 0 && checkKD(x, y, k))) {
            return 0;
        }
        check[x][y] = 1;
        return DFS(check, k, x + 1, y) + DFS(check, k, x - 1, y) + DFS(check, k, x, y + 1) + DFS(check, k, x, y - 1) + 1;
    }

}
