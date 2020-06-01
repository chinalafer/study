package com.lafer.leetcode.backtrack;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * 93. 复原IP地址
 * 给定一个只包含数字的字符串，复原它并返回所有可能的 IP 地址格式。
 *
 * 有效的 IP 地址正好由四个整数（每个整数位于 0 到 255 之间组成），整数之间用 '.' 分隔。
 *
 *
 *
 * 示例:
 *
 * 输入: "25525511135"
 * 输出: ["255.255.11.135", "255.255.111.35"]
 *
 */

public class LeetCode93 {

    public List<String> restoreIpAddresses(String s) {
        List<String> result = new ArrayList<>();
        DFS(s, 1, new StringBuilder(), result);
        return result;
    }

    private void DFS(String s, int index, StringBuilder re, List<String> result) {
        if (index == 5 || s.length() == 0) {
            if (index == 5 && s.length() == 0) {
                result.add(re.toString());
            }
            return;
        }
        for (int i = 1; i <= 3 && i <= s.length(); i++) {
            if (i != 1 && s.startsWith("0")) {
                break;
            }
            String part = s.substring(0, i);
            if (Integer.parseInt(part) <= 255) {
                if (index != 1) {
                    part = "." + part;
                }
                re.append(part);
                DFS(s.substring(i), index + 1, re, result);
                re.delete(re.length() - part.length(), re.length());
            }
        }
    }

}
