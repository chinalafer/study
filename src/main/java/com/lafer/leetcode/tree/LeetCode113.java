package com.lafer.leetcode.tree;

import com.lafer.leetcode.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * 113. 路径总和 II
 * 给定一个二叉树和一个目标和，找到所有从根节点到叶子节点路径总和等于给定目标和的路径。
 *
 * 说明: 叶子节点是指没有子节点的节点。
 *
 * 示例:
 * 给定如下二叉树，以及目标和 sum = 22，
 *
 *               5
 *              / \
 *             4   8
 *            /   / \
 *           11  13  4
 *          /  \    / \
 *         7    2  5   1
 * 返回:
 *
 * [
 *    [5,4,11,2],
 *    [5,8,4,5]
 * ]
 *
 */

public class LeetCode113 {

    private List<List<Integer>> ret = new ArrayList<>();

    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        DFS(root, sum, new LinkedList<>());
        return ret;
    }

    private void DFS(TreeNode root, int sum, LinkedList<Integer> tem) {
        if (root == null) {
            return;
        }
        tem.add(root.val);
        if (root != null && root.left == null && root.right == null && sum == root.val) {
            ArrayList<Integer> re = new ArrayList<>();
            re.addAll(tem);
            ret.add(re);
        }
        DFS(root.left, sum - root.val, tem);
        DFS(root.right, sum - root.val, tem);
        tem.removeLast();
    }

}
