package com.lafer.leetcode.tree;

import com.lafer.leetcode.TreeNode;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * 677. 键值映射
 * 实现一个 MapSum 类里的两个方法，insert 和 sum。
 *
 * 对于方法 insert，你将得到一对（字符串，整数）的键值对。字符串表示键，整数表示值。如果键已经存在，那么原来的键值对将被替代成新的键值对。
 *
 * 对于方法 sum，你将得到一个表示前缀的字符串，你需要返回所有以该前缀开头的键的值的总和。
 *
 * 示例 1:
 *
 * 输入: insert("apple", 3), 输出: Null
 * 输入: sum("ap"), 输出: 3
 * 输入: insert("app", 2), 输出: Null
 * 输入: sum("ap"), 输出: 5
 *
 * 思考：
 * 1、使用map，统计key前缀。时间复杂度：inset：O(N), sum:O(N*K) N为插入元素个数，K为统计元素的长度， 空间复杂度为Map的开销
 * 2、使用字典树，时间复杂度：inset：O(K * N),sum:(K), 空间复杂度为字典树的开销，使用空间和输入的大小是线性的。
 *
 */

public class LeetCode677 {

    class MapSum {

        Map<String, Integer> map = null;

        public MapSum() {
            map = new HashMap<>();
        }

        public void insert(String key, int val) {
            map.put(key, val);
        }

        public int sum(String prefix) {
            int sum = 0;
            for (Map.Entry<String, Integer> entry : map.entrySet()) {
                if (entry.getKey().startsWith(prefix)) {
                    sum +=  entry.getValue();
                }
            }
            return sum;
        }

    }

    class MapSum1 {

        private Node root;

        public MapSum1() {
            root = new Node();
        }

        private class Node {
            Node[] child = new Node[26];
            int value;
        }

        private int indexForChar(char c) {
            return c - 'a';
        }

        public void insert(String key, int val) {
            insert(root, key, val);
        }

        public void insert(Node root, String key, int val) {
            if (root == null) {
                return;
            }
            if (key.length() == 0) {
                root.value = val;
                return;
            }
            int index = indexForChar(key.charAt(0));
            if (root.child[index] == null) {
                root.child[index] = new Node();
            }
            insert(root.child[index], key.substring(1), val);
        }

        public int sum(String prefix) {
            return sum(root, prefix);
        }

        public int sum(Node root, String prefix) {
            if (root == null) {
                return 0;
            }
            if (prefix.length() != 0) {
                int index = indexForChar(prefix.charAt(0));
                return sum(root.child[index], prefix.substring(1));
            }
            int sum = root.value;
            for (Node node : root.child) {
                sum += sum(node, prefix);
            }
            return sum;
        }

    }


}