package com.lafer.leetcode.bitoperation;

/**
 *
 * 318. 最大单词长度乘积
 * 给定一个字符串数组 words，找到 length(word[i]) * length(word[j]) 的最大值，并且这两个单词不含有公共字母。你可以认为每个单词只包含小写字母。如果不存在这样的两个单词，返回 0。
 *
 * 示例 1:
 *
 * 输入: ["abcw","baz","foo","bar","xtfn","abcdef"]
 * 输出: 16
 * 解释: 这两个单词为 "abcw", "xtfn"。
 * 示例 2:
 *
 * 输入: ["a","ab","abc","d","cd","bcd","abcd"]
 * 输出: 4
 * 解释: 这两个单词为 "ab", "cd"。
 * 示例 3:
 *
 * 输入: ["a","aa","aaa","aaaa"]
 * 输出: 0
 * 解释: 不存在这样的两个单词。
 *
 * 思考：
 * 使用整数的位中对应的1来表示对应字母是否出现在该字符串中。
 * 比较两个字符串是否包含相同的字母，只需要将两个对应的整数进行&运算即可，等于0说明没有重复，不等于0说明有重复。
 *
 */

public class LeetCode318 {

    public int maxProduct(String[] words) {
        int[] val = new int[words.length];
        for (int i = 0; i < words.length; i++) {
            for (int j = 0; j < words[i].length(); j++) {
                val[i] |= (1 << (words[i].charAt(j) - 'a'));
            }
        }
        int ret = 0;
        for (int i = 0; i < words.length; i++) {
            for (int j = 0; j < words.length; j++) {
                if ((val[i] & val[j]) == 0) {
                    ret = Integer.max(words[i].length() * words[j].length(), ret);
                }
            }
        }
        return ret;
    }

}
