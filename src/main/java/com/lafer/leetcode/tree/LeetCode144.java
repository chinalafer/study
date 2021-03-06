package com.lafer.leetcode.tree;

import com.lafer.leetcode.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 *
 * 144. 二叉树的前序遍历
 * 给定一个二叉树，返回它的 前序 遍历。
 *
 *  示例:
 *
 * 输入: [1,null,2,3]
 *    1
 *     \
 *      2
 *     /
 *    3
 *
 * 输出: [1,2,3]
 * 进阶: 递归算法很简单，你可以通过迭代算法完成吗？
 *
 */

public class LeetCode144 {

    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> re = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        if (root == null) {
            return re;
        }
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode pop = stack.pop();
            re.add(pop.val);
            if (pop.right != null) {
                stack.push(pop.right);
            }
            if (pop.left != null) {
                stack.push(pop.left);
            }
        }
        return re;
    }

}
