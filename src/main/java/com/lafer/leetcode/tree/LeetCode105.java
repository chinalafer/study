package com.lafer.leetcode.tree;

import com.lafer.leetcode.TreeNode;

/**
 *
 * 105. 从前序与中序遍历序列构造二叉树
 * 根据一棵树的前序遍历与中序遍历构造二叉树。
 *
 * 注意:
 * 你可以假设树中没有重复的元素。
 *
 * 例如，给出
 *
 * 前序遍历 preorder = [3,9,20,15,7]
 * 中序遍历 inorder = [9,3,15,20,7]
 * 返回如下的二叉树：
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 *
 * 思考：前序遍历构造二叉树
 *
 */

public class LeetCode105 {

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        return buildTree(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1);
    }

    public TreeNode buildTree(int[] preorder, int prestart, int preend, int[] inorder, int instart, int inend) {
        if (preend < prestart) {
            return null;
        }
        TreeNode root = new TreeNode(preorder[prestart]);
        int inindex = findTargetFirstIndex(inorder, instart, inend, preorder[prestart]);
        if (inindex == -1) {
            return root;
        }
        root.left = buildTree(preorder, prestart + 1, prestart + (inindex - instart), inorder, instart, inindex - 1);
        root.right = buildTree(preorder, prestart + 1 + (inindex - instart), preend, inorder, inindex + 1, inend);
        return root;
    }

    private int findTargetFirstIndex(int[] arr, int left, int right, int target) {
        for (int i = left; i <= right; i++) {
            if (target == arr[i]) {
                return i;
            }
        }
        return -1;
    }

}
