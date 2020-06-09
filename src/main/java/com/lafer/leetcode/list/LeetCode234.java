package com.lafer.leetcode.list;

import com.lafer.leetcode.ListNode;

/**
 *
 * 234. 回文链表
 * 请判断一个链表是否为回文链表。
 *
 * 示例 1:
 *
 * 输入: 1->2
 * 输出: false
 * 示例 2:
 *
 * 输入: 1->2->2->1
 * 输出: true
 * 进阶：
 * 你能否用 O(n) 时间复杂度和 O(1) 空间复杂度解决此题？
 *
 *
 * 思考：切成两半，把后半段反转，然后比较两半是否相等。
 *
 */

public class LeetCode234 {

    public boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null) {
            return true;
        }
        ListNode temp = head;
        int n = 0;
        while (temp != null) {
            n++;
            temp = temp.next;
        }
        temp = head;
        int h = n / 2;
        while (h-- > 0) {
            temp = temp.next;
        }
        ListNode temp1 = temp;
        ListNode temp2 = temp.next;
        while (temp1 != null && temp2 != null) {
            ListNode next = temp2.next;
            temp2.next = temp1;
            temp1 = temp2;
            temp2 = next;
        }
        n = n / 2;
        while (n-- > 0) {
            if (head.val != temp1.val) {
                return false;
            }
        }
        return true;
    }


}
