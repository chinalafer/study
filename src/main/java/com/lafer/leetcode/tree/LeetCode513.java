package com.lafer.leetcode.tree;

import com.lafer.leetcode.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 513. 找树左下角的值
 * 给定一个二叉树，在树的最后一行找到最左边的值。
 *
 * 示例 1:
 *
 * 输入:
 *
 *     2
 *    / \
 *   1   3
 *
 * 输出:
 * 1
 *
 *
 * 示例 2:
 *
 * 输入:
 *
 *         1
 *        / \
 *       2   3
 *      /   / \
 *     4   5   6
 *        /
 *       7
 *
 * 输出:
 * 7
 *
 * 思考：
 * bfs（右子树先入队列）
 * dfs
 *
 */

public class LeetCode513 {

    public int findBottomLeftValue(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            root = queue.poll();
            if (root.right != null) {
                queue.add(root.right);
            }
            if (root.left != null) {
                queue.add(root.left);
            }
        }
        return root.val;
    }

    int re;
    int maxDeep = 0;

    public int findBottomLeftValue1(TreeNode root) {
        dfs(root, 1);
        return re;
    }

    private void dfs(TreeNode root, int deep) {
        if (root == null) {
            return;
        }
        if (deep > maxDeep) {
            maxDeep = deep;
            re = root.val;
        }
        dfs(root.left, deep + 1);
        dfs(root.right, deep + 1);
    }

}
