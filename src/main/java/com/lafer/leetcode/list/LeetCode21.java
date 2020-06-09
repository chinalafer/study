package com.lafer.leetcode.list;

import com.lafer.leetcode.ListNode;

/**
 *
 * 21. 合并两个有序链表
 * 将两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。
 *
 *
 *
 * 示例：
 *
 * 输入：1->2->4, 1->3->4
 * 输出：1->1->2->3->4->4
 *
 * 思考：
 *  递归
 *  迭代（官方给的题解更简单）
 *
 */

public class LeetCode21 {

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null || l2 == null) {
            return l1 == null ? l2 : l1;
        }
        ListNode head1 = l1, head2 = l2;
        if (head1.val > head2.val) {
            ListNode temp = head1;
            head1 = head2;
            head2 = temp;
        }
        ListNode re = head1;
        while (head1.next != null || head2 != null) {
            if (head2 == null) {
                break;
            }
            if (head1.next == null) {
                head1.next = head2;
                break;
            }
            if (head2.val < head1.next.val) {
                ListNode temp = head2.next;
                head2.next = head1.next;
                head1.next = head2;
                head2 = temp;
            }
            head1 = head1.next;
        }
        return re;
    }

    public ListNode mergeTwoLists1(ListNode l1, ListNode l2) {
        if (l1 == null || l2 == null) {
            return l1 == null ? l2 : l1;
        }
        if (l1.val < l2.val) {
            l1.next = mergeTwoLists1(l1.next, l2);
            return l1;
        } else {
            l2.next = mergeTwoLists1(l1, l2.next);
            return l2;
        }
    }

}
