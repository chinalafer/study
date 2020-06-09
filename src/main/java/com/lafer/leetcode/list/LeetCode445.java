package com.lafer.leetcode.list;

import com.lafer.leetcode.ListNode;

import java.util.Stack;

/**
 *
 * 445. 两数相加 II
 * 给你两个 非空 链表来代表两个非负整数。数字最高位位于链表开始位置。它们的每个节点只存储一位数字。将这两数相加会返回一个新的链表。
 *
 * 你可以假设除了数字 0 之外，这两个数字都不会以零开头。
 *
 *
 *
 * 进阶：
 *
 * 如果输入链表不能修改该如何处理？换句话说，你不能对列表中的节点进行翻转。
 *
 *
 *
 * 示例：
 *
 * 输入：(7 -> 2 -> 4 -> 3) + (5 -> 6 -> 4)
 * 输出：7 -> 8 -> 0 -> 7
 *
 * 思考：
 * 使用stact存储两个数
 * 建立返回链表，每次跟新head为新建的节点，新建阶段的下一个阶段为上一个节点。
 *
 */

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


