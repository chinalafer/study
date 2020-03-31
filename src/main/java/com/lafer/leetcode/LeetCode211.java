package com.lafer.leetcode;

/**
 * 设计一个支持以下两种操作的数据结构：
 *
 * void addWord(word)
 * bool search(word)
 * search(word) 可以搜索文字或正则表达式字符串，字符串只包含字母 . 或 a-z 。 . 可以表示任何一个字母。
 *
 * 思考：前缀树加递归
 */

public class LeetCode211 {
}

class WordDictionary {

    TrieNode root = null;

    class TrieNode {
        char val;
        TrieNode[] son = new TrieNode[26];
        boolean isEnd;
        boolean hasSon;

        public TrieNode() {

        }

        public TrieNode(char c) {
            val = c;
            isEnd = false;
            hasSon = false;
        }

    }

    /** Initialize your data structure here. */
    public WordDictionary() {
        root = new TrieNode();
    }

    /** Adds a word into the data structure. */
    public void addWord(String word) {

        char[] chars = word.toCharArray();
        TrieNode node = root;
        for (int i = 0; i < chars.length; i++) {
            int index = chars[i] - 'a';
            if (node.son[index] == null) {
                node.son[index] = new TrieNode(chars[i]);
            }
            node.hasSon = true;
            node = node.son[index];
        }
        node.isEnd = true;

    }

    /** Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter. */
    public boolean search(String word) {
        return dg(root, word);
    }

    public boolean dg(TrieNode trieNode, String word) {
        char[] chars = word.toCharArray();
        TrieNode node = trieNode;
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == '.') {
                if (i == chars.length - 1) {
                    boolean result = false;
                    for (TrieNode trieNode1 : node.son) {
                        if (trieNode1 != null) {
                            result = result || trieNode1.isEnd;
                        }
                    }
                    return result;
                }
                if (!node.hasSon) {
                    return false;
                }
                boolean result = false;
                for (TrieNode trieNode1 : node.son) {
                    if (trieNode1 != null) {
                        result = result || dg(trieNode1, word.substring(i + 1));
                    }
                }
                return result;
            } else {
                int index = chars[i] - 'a';
                if (node.son[index] == null) {
                    return false;
                }
                node = node.son[index];
            }
        }
        return node.isEnd;
    }
}

/**
 * Your WordDictionary object will be instantiated and called as such:
 * WordDictionary obj = new WordDictionary();
 * obj.addWord(word);
 * boolean param_2 = obj.search(word);
 */
