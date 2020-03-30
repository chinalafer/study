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

        TrieNode() {
            num = 1;
            son = new TrieNode[SIZE];
            isEnd = false;
        }
    }

    // 建立字典树
    private void insert(String str) {           //插入一个字符串
        if (str == null || str.length() == 0) {
            return;
        }
        char[] chars = str.toCharArray();
        TrieNode node = root;
        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];
            int index = c - 'a';
            if (node.son[index] == null) {          //如果当前节点没有存在这样的儿子节点，则构建一个儿子节点并赋值。
                node.son[index] = new TrieNode();
                node.son[index].val = c;
            } else {                                //如果存在，则由根节点到该而儿子节点的字符串模式出现次数+1
                node.son[index].num++;
            }
            node = node.son[index];
        }
        node.isEnd = true;
    }

    // 在字典数中查找这个字符串
    public boolean has(String str) {
        if (str == null || str.length() == 0) {
            return false;
        }
        TrieNode node = root;
        char[] chars = str.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            int index = chars[i] - 'a';
            if (node.son[index] == null) {
                return false;
            }
            node = node.son[index];
        }
        return node.isEnd;
    }

    // 前序遍历字典树
    public void preTraverse(TrieNode node) {
        if (node != null) {
            System.out.print(node.val + "-");
            for (TrieNode trieNode : node.son) {
                preTraverse(trieNode);
            }
        }
    }

    // 计算单词前缀的数量
    public int countPrefix(String prefix) {
        if (prefix == null || prefix.length() == 0) {
            return -1;
        }
        TrieNode node = root;
        char[] chars = prefix.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            int index = chars[i] - 'a';
            if (node.son[index] == null) {
                return 0;
            } else {
                node = node.son[index];
            }
        }
        return node.num;
    }

    public TrieNode getRoot() {
        return this.root;
    }

    public static void main(String[] args) {
        Trie tree = new Trie();
        String[] strs = {"banana", "band", "bee", "absolute", "acm",};
        String[] prefix = {"ba", "b", "band", "abc",};
        for (String str : strs) {
            tree.insert(str);
        }
        System.out.println(tree.has("abc"));
        tree.preTraverse(tree.getRoot());
        System.out.println();
        //tree.printAllWords();
        for (String pre : prefix) {
            int num = tree.countPrefix(pre);
            System.out.println(pre + "数量:" + num);
        }
    }

}
