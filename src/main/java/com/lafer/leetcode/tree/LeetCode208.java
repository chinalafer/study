package com.lafer.leetcode.tree;

/**
 *
 * 实现一个 Trie (前缀树)，包含 insert, search, 和 startsWith 这三个操作。
 *
 * 说明:
 *
 * 你可以假设所有的输入都是由小写字母 a-z 构成的。
 * 保证所有输入均为非空字符串。
 *
 */

public class LeetCode208 {

}


class Trie {

    TrieNode root = null;

    class TrieNode {
        char val;
        TrieNode[] son = new TrieNode[26];
        boolean isEnd;
        int nums;

        public TrieNode() {

        }

        public TrieNode(char c) {
            val = c;
            isEnd = false;
            nums = 1;
        }

    }

    /** Initialize your data structure here. */
    public Trie() {
        root = new TrieNode();
    }

    /** Inserts a word into the trie. */
    public void insert(String word) {
        char[] chars = word.toCharArray();
        TrieNode node = root;
        for (int i = 0; i < chars.length; i++) {
            int index = chars[i] - 'a';
            if (node.son[index] == null) {
                node.son[index] = new TrieNode(chars[i]);
            } else {
                node.son[index].nums++;
            }
            node = node.son[index];
        }
        node.isEnd = true;
    }

    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        char[] chars = word.toCharArray();
        TrieNode node = root;
        for (int i = 0; i < chars.length; i++) {
            int index = chars[i] - 'a';
            if (node.son[index] == null) {
                return false;
            }
            node = node.son[index];
        }
        return node.isEnd;
    }

    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        char[] chars = prefix.toCharArray();
        TrieNode node = root;
        for (int i = 0; i < chars.length; i++) {
            int index = chars[i] - 'a';
            if (node.son[index] == null) {
                return false;
            }
            node = node.son[index];
        }
        return node.nums > 0;
    }
}

/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */