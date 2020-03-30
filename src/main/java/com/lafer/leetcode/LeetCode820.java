package com.lafer.leetcode;

import java.util.Arrays;

/**
 * 给定一个单词列表，我们将这个列表编码成一个索引字符串 S 与一个索引列表 A。
 * <p>
 * 例如，如果这个列表是 ["time", "me", "bell"]，我们就可以将其表示为 S = "time#bell#" 和 indexes = [0, 2, 5]。
 * <p>
 * 对于每一个索引，我们可以通过从字符串 S 中索引的位置开始读取字符串，直到 "#" 结束，来恢复我们之前的单词列表。
 * <p>
 * 那么成功对给定单词列表进行编码的最小字符串长度是多少呢？
 * <p>
 * 看到题目，第一想法就是字典树。
 *  题解中提到的反转后在进行排序的想法甚是美妙。
 */

public class LeetCode820 {

    static class Trie {

        TrieNode root;

        public Trie() {
            root = new TrieNode();
        }


        class TrieNode {

            char val;
            int nums;
            int level;
            TrieNode[] son = new TrieNode[26];

            public TrieNode() {
            }

            public TrieNode(char val) {
                this.val = val;
                nums = 1;
            }

        }

        public void insert(String str) {
            if (str == null || str.length() == 0) {
                return;
            }
            char[] chars = str.toCharArray();
            TrieNode node = root;
            for (int i = 0; i < chars.length; i++) {
                int index = chars[i] - 'a';
                if (node.son[index] == null) {
                    node.son[index] = new TrieNode(chars[i]);
                    node.son[index].level = node.level + 1;
                } else {
                    node.son[index].nums++;
                }
                node = node.son[index];
            }
        }

        public int getY(TrieNode node) {
            if (node == null) {
                return 0;
            }
            int result = 0;
            for (TrieNode tempNode : node.son) {
                result += getY(tempNode);
            }
            result += result == 0 ? node.level + 1 : 0;
            return result;
        }

    }

    public static int minimumLengthEncoding(String[] words) {
        Trie trie = new Trie();
        for (String str : words) {
            trie.insert(new StringBuilder(str).reverse().toString());
        }
        return trie.getY(trie.root);
    }

    public static void main(String[] args) {
        String[] strs = new String[]{"time", "me", "bell"};
        System.out.println(minimumLengthEncoding(strs));
    }

    public int minimumLengthEncoding1(String[] words) {
        int N = words.length;
        // 反转每个单词
        String[] reversed_words = new String[N];
        for (int i = 0; i < N; i++) {
            String word = words[i];
            String rword = new StringBuilder(word).reverse().toString();
            reversed_words[i] = rword;
        }
        // 字典序排序
        Arrays.sort(reversed_words);

        int res = 0;
        for (int i = 0; i < N; i++) {
            if (i+1 < N && reversed_words[i+1].startsWith(reversed_words[i])) {
                // 当前单词是下一个单词的前缀，丢弃
            } else {
                res += reversed_words[i].length() + 1; // 单词加上一个 '#' 的长度
            }
        }
        return res;
    }


}
