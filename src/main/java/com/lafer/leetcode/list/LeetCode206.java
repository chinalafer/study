package com.lafer.leetcode.list;

import com.lafer.leetcode.ListNode;

/**
 *
 * 反转一个单链表。
 *
 * 进阶:
 * 你可以迭代或递归地反转链表。你能否用两种方法解决这道题？
 *
 * 思考：循环变换两个相邻指针即可，注意考虑首尾节点。
 * 递归方法：和循环类似，递归注意返回值以及递归的出口即可。
 *
 */
public class LeetCode206 {

    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode index1 = head, index2 = head.next;
        while (index2 != null) {
            ListNode temp = index2.next;
            index2.next = index1;
            if (index1.next == index2) {
                index1.next = null;
            }
            index1 = index2;
            index2 = temp;
        }
        return index1;
    }

    public ListNode reverseList2(ListNode head) {
        if (head == null) {
            return null;
        }

        return dg(head, head.next);

    }

    public ListNode dg(ListNode curNode, ListNode nextNode) {
        if (nextNode == null) {
            return curNode;
        }
        ListNode temp = nextNode.next;
        nextNode.next = curNode;
        if (curNode.next == nextNode) {
            curNode.next = null;
        }
        return dg(nextNode, temp);
    }

}
