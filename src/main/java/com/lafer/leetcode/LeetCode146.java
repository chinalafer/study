package com.lafer.leetcode;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;

public class LeetCode146 {

    class LRUCache {

        class Node {
            int key;
            int value;
            Node next;
            Node pre;

            public Node() {

            }

            public Node(int key, int value) {
                this.value = value;
                this.key = key;
            }

            public Node(int key, int value, Node pre, Node next) {
                this.value = value;
                this.next = next;
                this.pre = pre;
                this.key = key;
            }
        }

        Node head;
        Node end;
        Map<Integer, Node> cache;
        int capacity;

        public LRUCache(int capacity) {
            this.cache = new HashMap<>((int) (capacity / 0.75) + 1);
            this.capacity = capacity;
        }

        public int get(int key) {
            Node node = cache.get(key);
            if (node != null) {
                // 移动对应节点（如果是头节点，则不变，如果是尾节点，更新尾节点）放到头节点
                if (node == head) {
                    return node.value;
                } else if (node == end) {
                    end = end.pre;
                    if (end != null) {
                        end.next = null;
                    }
                } else {
                    Node pre = node.pre;
                    Node next = node.next;
                    pre.next = node.next;
                    next.pre = node.pre;
                }
                node.pre = null;
                node.next = head;
                if (head != null) {
                    head.pre = node;
                }
                head = node;
                return node.value;
            }
            return -1;
        }

        public void put(int key, int value) {
            if (cache.containsKey(key)) {
                // 更新Node节点的value， 移动对应节点（如果是头节点，则不变，如果是尾节点，更新尾节点）放到头节点
                Node node = cache.get(key);
                node.value = value;
                if (node == head) {
                    return;
                } else if (node == end) {
                    end = end.pre;
                    if (end != null) {
                        end.next = null;
                    }
                } else {
                    Node pre = node.pre;
                    Node next = node.next;
                    pre.next = node.next;
                    next.pre = node.pre;
                }
                node.pre = null;
                node.next = head;
                if (head != null) {
                    head.pre = node;
                }
                head = node;
            } else {
                // 头节点插入
                Node node = new Node(key, value, null, head);
                if (head != null) {
                    head.pre = node;
                }
                head = node;
                cache.put(key, node);
                if (end == null) {
                    end = head;
                }
                // 判断cache 大小 ， 大于 capacity 移除尾节点并更新尾节点
                if (cache.size() > capacity) {
                    if (end != null) {
                        cache.remove(end.key);
                        end = end.pre;
                        if (end != null) {
                            end.next = null;
                        }
                    }
                }
            }
        }
    }

}
