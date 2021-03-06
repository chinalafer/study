package com.lafer.leetcode.tree;

import com.lafer.leetcode.TreeNode;

/**
 *
 * 687. 最长同值路径
 * 给定一个二叉树，找到最长的路径，这个路径中的每个节点具有相同值。 这条路径可以经过也可以不经过根节点。
 *
 * 注意：两个节点之间的路径长度由它们之间的边数表示。
 *
 * 示例 1:
 *
 * 输入:
 *
 *               5
 *              / \
 *             4   5
 *            / \   \
 *           1   1   5
 * 输出:
 *
 * 2
 * 示例 2:
 *
 * 输入:
 *
 *               1
 *              / \
 *             4   5
 *            / \   \
 *           4   4   5
 * 输出:
 *
 * 2
 * 注意: 给定的二叉树不超过10000个结点。 树的高度不超过1000。
 *
 */

public class LeetCode687 {

    private int maxPath = 0;

    public int longestUnivaluePath(TreeNode root) {
        DFS(root);
        return maxPath;
    }

    public int DFS(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = DFS(root.left);
        int right = DFS(root.right);
        int maxLeftPath = 0, maxRightPath = 0;
        if (root.left != null && root.left.val == root.val) {
            maxLeftPath = left + 1;
        }
        if (root.right != null && root.right.val == root.val) {
            maxRightPath = right + 1;
        }
        maxPath = Math.max(maxLeftPath + maxRightPath, maxPath);
        return Math.max(maxLeftPath, maxRightPath);
    }

}
