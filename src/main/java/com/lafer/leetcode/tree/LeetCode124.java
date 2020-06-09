package com.lafer.leetcode.tree;

import com.lafer.leetcode.TreeNode;

/**
 *
 * 124. 二叉树中的最大路径和
 * 给定一个非空二叉树，返回其最大路径和。
 *
 * 本题中，路径被定义为一条从树中任意节点出发，达到任意节点的序列。该路径至少包含一个节点，且不一定经过根节点。
 *
 * 示例 1:
 *
 * 输入: [1,2,3]
 *
 *        1
 *       / \
 *      2   3
 *
 * 输出: 6
 * 示例 2:
 *
 * 输入: [-10,9,20,null,null,15,7]
 *
 *    -10
 *    / \
 *   9  20
 *     /  \
 *    15   7
 *
 * 输出: 42
 *
 */

public class LeetCode124 {

    private int maxPath = Integer.MIN_VALUE;

    public int maxPathSum(TreeNode root) {
        DFS(root);
        return maxPath;
    }

    private int DFS(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftMaxPath = DFS(root.left);
        int rightMaxPath = DFS(root.right);
        maxPath = Math.max(leftMaxPath + rightMaxPath + root.val, maxPath);
        int ret = Math.max(leftMaxPath, rightMaxPath) + root.val;
        return ret < 0 ? 0 : ret;
    }

}
