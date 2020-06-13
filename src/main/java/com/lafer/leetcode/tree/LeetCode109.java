package com.lafer.leetcode.tree;

import com.lafer.leetcode.ListNode;
import com.lafer.leetcode.TreeNode;

/**
 *
 * 109. 有序链表转换二叉搜索树
 * 给定一个单链表，其中的元素按升序排序，将其转换为高度平衡的二叉搜索树。
 *
 * 本题中，一个高度平衡二叉树是指一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1。
 *
 * 示例:
 *
 * 给定的有序链表： [-10, -3, 0, 5, 9],
 *
 * 一个可能的答案是：[0, -3, 9, -10, null, 5], 它可以表示下面这个高度平衡二叉搜索树：
 *
 *       0
 *      / \
 *    -3   9
 *    /   /
 *  -10  5
 *
 */

public class LeetCode109 {

    public TreeNode sortedListToBST(ListNode head) {
        if (head == null) {
            return null;
        }
        return sortedListToBST(head, null);
    }

    private TreeNode sortedListToBST(ListNode start, ListNode end) {
        if (start == end) {
            return null;
        }
        ListNode mid = start;
        ListNode tempL = start;
        while (tempL != end && tempL.next != end) {
            mid = mid.next;
            tempL = tempL.next.next;
        }
        TreeNode node = new TreeNode(mid.val);
        node.left = sortedListToBST(start, mid);
        node.right = sortedListToBST(mid.next, end);
        return node;
    }

}
