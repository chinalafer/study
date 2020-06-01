package com.lafer.leetcode.backtrack;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * 257. 二叉树的所有路径
 * 给定一个二叉树，返回所有从根节点到叶子节点的路径。
 *
 * 说明: 叶子节点是指没有子节点的节点。
 *
 * 示例:
 *
 * 输入:
 *
 *    1
 *  /   \
 * 2     3
 *  \
 *   5
 *
 * 输出: ["1->2->5", "1->3"]
 *
 * 解释: 所有根节点到叶子节点的路径为: 1->2->5, 1->3
 *
 */

public class LeetCode257 {

    class TreeNode {

        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }

    }

    public List<String> binaryTreePaths(TreeNode root) {
        List<String> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        backTrack(root, result, "");
        return result;
    }

    private void backTrack(TreeNode root, List<String> result, String re) {
        if (root != null && root.left == null && root.right == null) {
            result.add((re + "->" + root.val).substring(2));
            return;
        }
        if (root.left != null) {
            backTrack(root.left, result, re + "->" + root.val);
        }
        if (root.right != null) {
            backTrack(root.right, result, re + "->" + root.val);
        }
    }

}
