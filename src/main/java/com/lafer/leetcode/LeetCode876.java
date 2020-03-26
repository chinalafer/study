package com.lafer.leetcode;

/**
 * 给定一个带有头结点 head 的非空单链表，返回链表的中间结点。
 *
 * 如果有两个中间结点，则返回第二个中间结点。
 *
 * 思考：快慢指针
 */

public class LeetCode876 {

    public ListNode middleNode(ListNode head) {
        ListNode fast = head, slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        if (fast == null) {
            slow = slow.next;
        }
        return slow;
    }

}

