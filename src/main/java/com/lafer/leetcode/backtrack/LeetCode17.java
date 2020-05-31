package com.lafer.leetcode.backtrack;

import java.util.ArrayList;
import java.util.List;

/**
 * 17. 电话号码的字母组合
 * 给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。
 *
 * 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
 *
 *
 *
 * 示例:
 *
 * 输入："23"
 * 输出：["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
 * 说明:
 * 尽管上面的答案是按字典序排列的，但是你可以任意选择答案输出的顺序。
 *
 * 思考：dfs回溯
 *
 */

public class LeetCode17 {

    private static final String[] KEYS = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};

    public List<String> letterCombinations(String digits) {

        if (digits == null || digits.length() == 0) {
            return new ArrayList<>();
        }

        List<String> result = new ArrayList<>();

        DFS(digits, 0, result, "");

        return result;
    }

    public void DFS(String digits, int index, List<String> result, String str) {
        if (index == digits.length()) {
            result.add(str);
            return;
        }
        String key = KEYS[digits.charAt(index) - '0'];
        for (int i = 0; i < key.length(); i++) {
            String str1 = str + key.charAt(i);
            DFS(digits, index + 1, result, str1);
        }
    }

}
