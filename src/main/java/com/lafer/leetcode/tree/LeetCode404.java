package com.lafer.leetcode.tree;

import com.lafer.leetcode.TreeNode;

/**
 *
 * 404. 左叶子之和
 * 计算给定二叉树的所有左叶子之和。
 *
 * 示例：
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 *
 * 在这个二叉树中，有两个左叶子，分别是 9 和 15，所以返回 24
 *
 */

public class LeetCode404 {

    public int sumOfLeftLeaves(TreeNode root) {
        if (root == null || (root.left == null && root.right == null)) {
            return 0;
        }
        return sumOfLeftLeaves(root , 0);
    }

    private int sumOfLeftLeaves(TreeNode root, int dir) {
        if (root == null) {
            return 0;
        }
        int re = 0;
        if (dir == 0 && root.left == null && root.right == null) {
            re += root.val;
        }
        re += (sumOfLeftLeaves(root.left, 0) + sumOfLeftLeaves(root.right, 1));
        return re;
    }

}
