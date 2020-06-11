package com.lafer.leetcode.tree;

import com.lafer.leetcode.TreeNode;

/**
 *
 * 671. 二叉树中第二小的节点
 * 给定一个非空特殊的二叉树，每个节点都是正数，并且每个节点的子节点数量只能为 2 或 0。如果一个节点有两个子节点的话，那么这个节点的值不大于它的子节点的值。
 *
 * 给出这样的一个二叉树，你需要输出所有节点中的第二小的值。如果第二小的值不存在的话，输出 -1 。
 *
 * 示例 1:
 *
 * 输入:
 *     2
 *    / \
 *   2   5
 *      / \
 *     5   7
 *
 * 输出: 5
 * 说明: 最小的值是 2 ，第二小的值是 5 。
 * 示例 2:
 *
 * 输入:
 *     2
 *    / \
 *   2   2
 *
 * 输出: -1
 * 说明: 最小的值是 2, 但是不存在第二小的值。
 *
 */

public class LeetCode671 {

    private int ret = -1;

    public int findSecondMinimumValue(TreeNode root) {
        if (root == null) {
            return -1;
        }
        dfs(root, root.val);
        return ret;
    }

    private void dfs(TreeNode root, int val) {
        if (root == null) {
            return;
        }
        if (val != root.val && (ret == -1 || (ret > root.val && root.val > val))) {
            ret = root.val;
        }
        dfs(root.left, val);
        dfs(root.right, val);
    }

}