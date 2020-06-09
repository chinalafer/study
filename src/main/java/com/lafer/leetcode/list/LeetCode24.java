package com.lafer.leetcode.list;

import com.lafer.leetcode.ListNode;

/**
 * 24. 两两交换链表中的节点
 * 给定一个链表，两两交换其中相邻的节点，并返回交换后的链表。
 *
 * 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
 *
 *
 *
 * 示例:
 *
 * 给定 1->2->3->4, 你应该返回 2->1->4->3.
 *
 * 思考：
 *  递归
 *  迭代
 */

public class LeetCode24 {

    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode n = head.next.next;
        ListNode t1 = head;
        ListNode t2 = head.next;
        t2.next = t1;
        t1.next = swapPairs(n);
        return t2;
    }

}
