package com.lafer.template;

public class Trie {

    private int SIZE = 26;
    private TrieNode root;                      //字典树的根

    Trie()                                      //初始化字典树
    {
        root = new TrieNode();
    }

    private class TrieNode                      //字典树节点
    {
        private int num;                        //有多少单词通过这个节点,即由根至该节点组成的字符串模式出现的次数
        private TrieNode[] son;                 //所有的儿子节点
        private boolean isEnd;                  //是不是最后一个节点
        private char val;                       //节点的值
        private boolean haveSon;

        TrieNode() {
            num = 1;
            son = new TrieNode[SIZE];
            isEnd = false;
            haveSon = false;
        }
    }

    private void insert(String str) {           //插入一个字符串
        if (str == null || str.length() == 0) {
            return;
        }
        char[] chars = str.toCharArray();
        TrieNode node = root;
        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];
            int index = c - 'a';
            if (node.son[index] == null) {
                node.son[index] = new TrieNode();
                node.son[index].val = c;
                node.num++;
                node.haveSon = true;
                node = node.son[index];
            } else {
                node.num++;
                node.haveSon = true;
            }
        }
        node.isEnd = true;
    }

}
