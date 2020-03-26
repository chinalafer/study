package com.lafer.leetcode;

/**
 * 给定一个链表，判断链表中是否有环。
 */
public class LeetCode141 {

    public boolean hasCycle(ListNode head) {
        ListNode fast = head, slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (slow == fast) {
                return true;
            }
        }
        return false;
    }

}
