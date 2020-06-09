package com.lafer.leetcode.tree;

import com.lafer.leetcode.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 *
 * 637. 二叉树的层平均值
 * 给定一个非空二叉树, 返回一个由每层节点平均值组成的数组.
 *
 * 示例 1:
 *
 * 输入:
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * 输出: [3, 14.5, 11]
 * 解释:
 * 第0层的平均值是 3,  第1层是 14.5, 第2层是 11. 因此返回 [3, 14.5, 11].
 * 注意：
 *
 * 节点值的范围在32位有符号整数范围内。
 *
 * 思考：
 * dfs或bfs
 *
 */

public class LeetCode637 {

    public List<Double> averageOfLevels(TreeNode root) {
        List<Double> result = new ArrayList<>();
        Queue<TreeNode> tempQueue1 = new LinkedList<>();
        Queue<TreeNode> tempQueue2 = new LinkedList<>();
        Queue<TreeNode> temp = null;
        if (root == null) {
            return result;
        }
        tempQueue1.add(root);
        while (!tempQueue1.isEmpty()) {
            Double tempResult = Double.valueOf(0);
            int n = 0;
            while (!tempQueue1.isEmpty()) {
                TreeNode node = tempQueue1.poll();
                if (node.left != null) {
                    tempQueue2.add(node.left);
                }
                if (node.right != null) {
                    tempQueue2.add(node.right);
                }
                tempResult += node.val;
                n++;
            }
            result.add(tempResult / n);
            //交换tempQueue1 与 tempQueue2
            temp = tempQueue1;
            tempQueue1 = tempQueue2;
            tempQueue2 = temp;
        }
        return result;
    }

    public List<Double> averageOfLevels1(TreeNode root) {
        List<Double> result = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        if (root == null) {
            return result;
        }
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            int memoSize = size;
            double ans = 0;
            while (size-- > 0) {
                TreeNode node = queue.poll();
                ans += node.val;
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
            result.add(ans / memoSize);
        }
        return result;
    }

    public List<Double> averageOfLevels2(TreeNode root) {
        List<Double> res = new ArrayList<>();
        List<Integer> count = new ArrayList<>();
        if (root == null) {
            return res;
        }
        average(root, 0, res, count);
        for (int i = 0; i < res.size(); i++) {
            res.set(i, res.get(i) / count.get(i));
        }
        return res;
    }

    public void average(TreeNode root, int level, List<Double> res, List<Integer> count) {
        if (root == null) {
            return;
        }
        if (level < res.size()) {
            res.set(level, res.get(level) + root.val);
            count.set(level, count.get(level) + 1);
        } else {
            res.add((double) root.val);
            count.add(1);
        }
        average(root.left, level + 1, res, count);
        average(root.right, level + 1, res, count);
    }


}
