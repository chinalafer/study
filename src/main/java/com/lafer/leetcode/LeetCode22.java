package com.lafer.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 22. 括号生成
 *
 * 数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。
 *
 * 示例：
 *
 * 输入：n = 3
 * 输出：[
 *        "((()))",
 *        "(()())",
 *        "(())()",
 *        "()(())",
 *        "()()()"
 *      ]
 *
 * 思考：DFS深度优先遍历
 *
 */

public class LeetCode22 {

    List<String> result = new ArrayList<>();

    public List<String> generateParenthesis(int n) {
        DFS("", n, n);
        return result;
    }

    private void DFS(String sb, int z, int y) {
        if (z < 0 || y < 0 || z > y) {
            return;
        }
        if (z == 0 && y == 0) {
            result.add(sb.toString());
        }
        DFS(sb + "(", z - 1, y);
        DFS(sb + ")", z, y - 1);
    }

}
