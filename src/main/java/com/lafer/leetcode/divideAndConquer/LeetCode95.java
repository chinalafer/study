package com.lafer.leetcode.divideAndConquer;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;

/**
 *
 * 给定一个整数 n，生成所有由 1 ... n 为节点所组成的二叉搜索树。
 *
 * 示例:
 *
 * 输入: 3
 * 输出:
 * [
 *   [1,null,3,2],
 *   [3,2,null,1],
 *   [3,1,null,null,2],
 *   [2,1,3],
 *   [1,null,2,null,3]
 * ]
 * 解释:
 * 以上的输出对应以下 5 种不同结构的二叉搜索树：
 *
 *    1         3     3      2      1
 *     \       /     /      / \      \
 *      3     2     1      1   3      2
 *     /     /       \                 \
 *    2     1         2                 3
 *
 *
 */

public class LeetCode95 {

    public List<TreeNode> generateTrees(int n) {
        if (n < 1) {
            return new ArrayList<>();
        }
        return generateSubTrees(1, n);
    }

    public List<TreeNode> generateSubTrees(int start, int end) {
        List<TreeNode> list = new ArrayList<>();
        if (end < start) {
            list.add(null);
            return list;
        }
        for (int i = start; i <= end; i++) {
            List<TreeNode> leftList = generateSubTrees(start, i - 1);
            List<TreeNode> rightList = generateSubTrees(i + 1, end);
            for (TreeNode leftTreeNode : leftList) {
                for (TreeNode rightTreeNode : rightList) {
                    TreeNode treeNode = new TreeNode(i);
                    treeNode.left = leftTreeNode;
                    treeNode.right = rightTreeNode;
                    list.add(treeNode);
                }
            }
        }
        return list;
    }

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
}
