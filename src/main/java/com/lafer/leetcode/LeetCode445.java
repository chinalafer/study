package com.lafer.leetcode;

import java.util.Stack;

public class LeetCode445 {

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        Stack<Integer> s1 = new Stack<>();
        Stack<Integer> s2 = new Stack<>();
        while (l1 != null) {
            s1.push(l1.val);
            l1 = l1.next;
        }
        while (l2 != null) {
            s2.push(l2.val);
            l2 = l2.next;
        }
        ListNode head = null;
        int j = 0;
        while (s1.size() > 0 || s2.size() >0 || j > 0) {
            int temp1 = 0, temp2 = 0;
            if (s1.size() > 0) {
                temp1 =s1.pop();
            }
            if (s2.size() > 0) {
                temp2 = s2.pop();
            }
            int val = (temp1 + temp2 + j) % 10;
            j = (temp1 + temp2 + j) / 10;
            ListNode node = new ListNode(val);
            node.next = head;
            head = node;
        }
        return head;
    }

}


