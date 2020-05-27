package com.lafer.leetcode;

import java.util.*;

/**
 * 102. 二叉树的层序遍历
 * 给你一个二叉树，请你返回其按 层序遍历 得到的节点值。 （即逐层地，从左到右访问所有节点）。
 *
 *
 *
 * 示例：
 * 二叉树：[3,9,20,null,null,15,7],
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * 返回其层次遍历结果：
 *
 * [
 *   [3],
 *   [9,20],
 *   [15,7]
 * ]
 *
 * 思考：BFS
 */

public class LeetCode102 {

    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        Queue<TreeNode> tempQueue1 = new LinkedList<>();
        Queue<TreeNode> tempQueue2 = new LinkedList<>();
        Queue<TreeNode> temp = null;
        if (root == null) {
            return result;
        }
        tempQueue1.add(root);
        while (!tempQueue1.isEmpty()) {
            List<Integer> tempResult = new ArrayList<>();
            while (!tempQueue1.isEmpty()) {
                TreeNode node = tempQueue1.poll();
                if (node.left != null) {
                    tempQueue2.add(node.left);
                }
                if (node.right != null) {
                    tempQueue2.add(node.right);
                }
                tempResult.add(node.val);
            }
            result.add(tempResult);
            //交换tempQueue1 与 tempQueue2
            temp = tempQueue1;
            tempQueue1 = tempQueue2;
            tempQueue2 = temp;
        }
        //Collections.reverse(result);
        return result;
    }

}
