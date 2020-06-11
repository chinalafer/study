package com.lafer.leetcode.tree;

import com.lafer.leetcode.TreeNode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

/**
 *
 * 145. 二叉树的后序遍历
 * 给定一个二叉树，返回它的 后序 遍历。
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
 * 输出: [3,2,1]
 * 进阶: 递归算法很简单，你可以通过迭代算法完成吗？
 *
 * 思考：
 *  前序遍历是root -> left -> right
 *  后续遍历是left -> right -> root
 *  在前序遍历的基础上改成root -> right -> left 然后翻转
 *
 */

public class LeetCode145 {

    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> re = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<TreeNode>();
        if (root == null) {
            return re;
        }
        stack.add(root);
        while (!stack.isEmpty()) {
            root = stack.pop();
            re.add(root.val);
            if (root.left  != null) {
                stack.add(root.left);
            }
            if (root.right != null) {
                stack.add(root.right);
            }
        }
        Collections.reverse(re);
        return re;
    }

}
