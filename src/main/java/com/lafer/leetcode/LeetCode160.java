package com.lafer.leetcode;

/**
 * 编写一个程序，找到两个单链表相交的起始节点。
 * 思考：
 * 方法1：使用hash表保存一个单链表，然后遍历第二个单链表，找到第一个在hash表中存在的节点即可。 时间复杂度：O(n + m)
 * 空间复杂度：O(n)
 * 方法2： 统计出两个链表的长度，比如，长度相差d，则长的那一个单链表的头指针先走d步，然后两个指针同步走，相等的时候便是相交节点。
 * 方法3： 双指针法：两个指针分别从两个链表的头部开始，到达尾部的时候，从另一个单链表的都不开始， 相交的时候就是起始节点。
 */

public class LeetCode160 {

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }
        ListNode indexA = headA, indexB = headB;
        while (indexA != indexB) {
            indexA = indexA == null ? headB : indexA.next;
            indexB = indexB == null ? headA : indexB.next;
        }
        return indexA;
    }

}
