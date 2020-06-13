package com.lafer.leetcode.tree;

import com.lafer.leetcode.TreeNode;

import java.util.Stack;

/**
 *
 * 230. 二叉搜索树中第K小的元素
 * 给定一个二叉搜索树，编写一个函数 kthSmallest 来查找其中第 k 个最小的元素。
 *
 * 说明：
 * 你可以假设 k 总是有效的，1 ≤ k ≤ 二叉搜索树元素个数。
 *
 * 示例 1:
 *
 * 输入: root = [3,1,4,null,2], k = 1
 *    3
 *   / \
 *  1   4
 *   \
 *    2
 * 输出: 1
 * 示例 2:
 *
 * 输入: root = [5,3,6,2,4,null,null,1], k = 3
 *        5
 *       / \
 *      3   6
 *     / \
 *    2   4
 *   /
 *  1
 * 输出: 3
 * 进阶：
 * 如果二叉搜索树经常被修改（插入/删除操作）并且你需要频繁地查找第 k 小的值，你将如何优化 kthSmallest 函数？
 *
 * 思考：
 *  中序遍历解法
 *
 *
 */

public class LeetCode230 {

    int k;
    int n;

    public int kthSmallest(TreeNode root, int k) {
        this.k = k;
        dfs(root);
        return n;
    }

    private void dfs(TreeNode root) {
        if (root == null) {
            return;
        }
        dfs(root.left);
        if (k-- == 1) {
            n = root.val;
            return;
        }
        dfs(root.right);
    }

    public int kthSmallest1(TreeNode root, int k) {
        Stack<TreeNode> stack = new Stack<>();
        int ret = 0;
        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                stack.add(root);
                root = root.left;
            }
            root = stack.pop();
            if (k-- == 1) {
                ret = root.val;
                break;
            }
            root = root.right;
        }
        return ret;
    }

    public int kthSmallest2(TreeNode root, int k) {
        int left = getNodeNums(root);
        if (k == left + 1) {
            return root.val;
        } else if (k < left + 1) {
            return kthSmallest2(root.left, k);
        } else {
            return kthSmallest2(root.right, k - (left + 1));
        }
    }

    private int getNodeNums(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = getNodeNums(root.left);
        int right = getNodeNums(root.right);
        return 1 + left + right;
    }

}
