package com.lafer.leetcode.backtrack;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * 131. 分割回文串
 * 给定一个字符串 s，将 s 分割成一些子串，使每个子串都是回文串。
 *
 * 返回 s 所有可能的分割方案。
 *
 * 示例:
 *
 * 输入: "aab"
 * 输出:
 * [
 *   ["aa","b"],
 *   ["a","a","b"]
 * ]
 *
 */

public class LeetCode131 {

    List<List<String>> output = new ArrayList<>();

    public List<List<String>> partition(String s) {
        backTrack(s, new LinkedList<>());
        return output;
    }

    private void backTrack(String s, LinkedList<String> curr) {
        if ("".equals(s)) {
            output.add(new ArrayList<>(curr));
            return;
        }
        for (int i = 1; i <= s.length(); i++) {
            String str = s.substring(0, i);
            if (!isHW(str)) {
                continue;
            }
            curr.add(str);
            backTrack(s.substring(i), curr);
            curr.removeLast();
        }
    }

    private boolean isHW(String s) {
        if (s == null || s.length() == 0) {
            return true;
        }
        int start = 0, end = s.length() - 1;
        while (start <= end) {
            if (s.charAt(start++) != s.charAt(end--)) {
                return false;
            }
        }
        return true;
    }

}
