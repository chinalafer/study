package com.lafer.leetcode.DFS;

import java.util.*;

public class LeetCode140 {

    public List<String> wordBreak(String s, List<String> wordDict) {
        List<List<String>> wordBreaks = dfs(s, 0, new HashMap<Integer, List<List<String>>>(), new HashSet<>(wordDict));
        List<String> ans = new ArrayList<>();
        for (List<String> wordBreak : wordBreaks) {
            ans.add(String.join(" ", wordBreak));
        }
        return ans;
    }

    private List<List<String>> dfs (String s, int index, HashMap<Integer, List<List<String>>> hashMap, HashSet<String> set) {
        // 剪枝
        if (hashMap.containsKey(index)) {
            return hashMap.get(index);
        }
        List<List<String>> wordBreaks = new LinkedList<>();
        if (index == s.length()) {
            wordBreaks.add(new LinkedList<>());
        }
        for (int i = index + 1; i <= s.length(); i++) {
            String substring = s.substring(index, i);
            // 单词存在于字典中
            if (set.contains(substring)){
                List<List<String>> dfs = dfs(s, i, hashMap, set);
                for (List<String> df : dfs) {
                    LinkedList<String> strings = new LinkedList<>(df);
                    strings.addFirst(substring);
                    wordBreaks.add(strings);
                }
            }
        }
        hashMap.put(index, wordBreaks);
        return wordBreaks;
    }

}
