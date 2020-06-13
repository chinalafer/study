package com.lafer.leetcode.tree;

import com.lafer.leetcode.TreeNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * 501. 二叉搜索树中的众数
 * 给定一个有相同值的二叉搜索树（BST），找出 BST 中的所有众数（出现频率最高的元素）。
 *
 * 假定 BST 有如下定义：
 *
 * 结点左子树中所含结点的值小于等于当前结点的值
 * 结点右子树中所含结点的值大于等于当前结点的值
 * 左子树和右子树都是二叉搜索树
 * 例如：
 * 给定 BST [1,null,2,2],
 *
 *    1
 *     \
 *      2
 *     /
 *    2
 * 返回[2].
 *
 * 提示：如果众数超过1个，不需考虑输出顺序
 *
 * 进阶：你可以不使用额外的空间吗？（假设由递归产生的隐式调用栈的开销不被计算在内）
 *
 * 思考：利用中序遍历，和遍历数组类似解法
 *
 */

public class LeetCode501 {

    private TreeNode preNode = null;
    private int curCount = 0;
    private int maxCount = 0;

    public int[] findMode(TreeNode root) {
        List<Integer> re = new ArrayList<>();
        inOrder(root, re);
        return re.stream().mapToInt(Integer::valueOf).toArray();
    }

    private void inOrder(TreeNode root, List<Integer> re) {
        if (root == null) {
            return;
        }
        inOrder(root.left, re);
        if (preNode == null) {
            curCount++;
            maxCount++;
            re.add(root.val);
        } else {
            if (root.val == preNode.val) {
                curCount++;
            } else {
                curCount = 1;
            }
            if (curCount == maxCount) {
                re.add(root.val);
            } else if (curCount > maxCount) {
                maxCount = curCount;
                re.clear();
                re.add(root.val);
            }
        }
        preNode = root;
        inOrder(root.right, re);
    }

}
