package com.lafer.leetcode.tree;

import com.lafer.leetcode.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 *
 * 94. 二叉树的中序遍历
 * 给定一个二叉树，返回它的中序 遍历。
 *
 * 示例:
 *
 * 输入: [1,null,2,3]
 *    1
 *     \
 *      2
 *     /
 *    3
 *
 * 输出: [1,3,2]
 * 进阶: 递归算法很简单，你可以通过迭代算法完成吗？
 *
 */

public class LeetCode94 {

    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> re = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        if (root == null) {
            return re;
        }
        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                stack.add(root);
                root = root.left;
            }
            root = stack.pop();
            re.add(root.val);
            root = root.right;
        }
        return re;
    }

}
