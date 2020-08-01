package com.lafer.leetcode.bfs;

import java.util.*;

/**
 *
 * 752. 打开转盘锁
 * 你有一个带有四个圆形拨轮的转盘锁。每个拨轮都有10个数字： '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' 。每个拨轮可以自由旋转：
 * 例如把 '9' 变为  '0'，'0' 变为 '9' 。每次旋转都只能旋转一个拨轮的一位数字。
 *
 * 锁的初始数字为 '0000' ，一个代表四个拨轮的数字的字符串。
 *
 * 列表 deadends 包含了一组死亡数字，一旦拨轮的数字和列表里的任何一个元素相同，这个锁将会被永久锁定，无法再被旋转。
 *
 * 字符串 target 代表可以解锁的数字，你需要给出最小的旋转次数，如果无论如何不能解锁，返回 -1。
 *
 *
 *
 * 示例 1:
 *
 * 输入：deadends = ["0201","0101","0102","1212","2002"], target = "0202"
 * 输出：6
 * 解释：
 * 可能的移动序列为 "0000" -> "1000" -> "1100" -> "1200" -> "1201" -> "1202" -> "0202"。
 * 注意 "0000" -> "0001" -> "0002" -> "0102" -> "0202" 这样的序列是不能解锁的，
 * 因为当拨动到 "0102" 时这个锁就会被锁定。
 * 示例 2:
 *
 * 输入: deadends = ["8888"], target = "0009"
 * 输出：1
 * 解释：
 * 把最后一位反向旋转一次即可 "0000" -> "0009"。
 * 示例 3:
 *
 * 输入: deadends = ["8887","8889","8878","8898","8788","8988","7888","9888"], target = "8888"
 * 输出：-1
 * 解释：
 * 无法旋转到目标数字且不被锁定。
 * 示例 4:
 *
 * 输入: deadends = ["0000"], target = "8888"
 * 输出：-1
 *
 *
 * 提示：
 *
 * 死亡列表 deadends 的长度范围为 [1, 500]。
 * 目标数字 target 不会在 deadends 之中。
 * 每个 deadends 和 target 中的字符串的数字会在 10,000 个可能的情况 '0000' 到 '9999' 中产生。
 *
 * 思考：BFS, 由于初始位置已知，目标位置也已知，可以使用双向BFS
 *
 */

public class LeetCode752 {

    private Set<String> deadEndSet = new HashSet<>();

    private Set<String> isVisit = new HashSet<>();

    public int openLock(String[] deadends, String target) {
        for (String str : deadends) {
            deadEndSet.add(str);
        }
        int step = 0;
        Queue<String> queue = new LinkedList<>();
        if (!deadEndSet.contains("0000")) {
            queue.add("0000");
            isVisit.add("0000");
        }
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                String str = queue.poll();
                List<String> nextSelect = nextSelect(str);
                for(String next : nextSelect) {
                    if (next.equals(target)) {
                        return step + 1;
                    }
                    queue.add(next);
                    isVisit.add(next);
                }
            }
            step++;
        }
        return -1;
    }

    private List<String> nextSelect(String cur) {
        List<String> ret = new ArrayList<>();
        StringBuilder curB = new StringBuilder(cur);
        for (int i = 0; i < cur.length(); i++) {
            char curC = cur.charAt(i);
            char nextAC = (char) (((curC - '0' + 1) % 10) + '0');
            char nextEC = (char) (((curC - '0' - 1 + 10) % 10) + '0');
            String nextACStr = curB.substring(0, i) + nextAC + cur.substring(i + 1);
            String nextECStr = cur.substring(0, i) + nextEC + cur.substring(i + 1);
            if (!deadEndSet.contains(nextACStr) && !isVisit.contains(nextACStr)) {
                ret.add(nextACStr);
            }
            if (!deadEndSet.contains(nextECStr) && !isVisit.contains(nextECStr)) {
                ret.add(nextECStr);
            }
        }
        return ret;
    }

    public int openLock1(String[] deadends, String target) {
        for (String str : deadends) {
            deadEndSet.add(str);
        }
        int step = 0;
        Set<String> setStart = new HashSet<>();
        Set<String> setEnd = new HashSet<>();
        if (!deadEndSet.contains("0000")) {
            setStart.add("0000");
            isVisit.add("0000");
        }
        setEnd.add(target);
        isVisit.add(target);
        while (!setStart.isEmpty() && !setEnd.isEmpty()) {
            if (setStart.size() > setEnd.size()) {
                Set<String> temp = setStart;
                setStart = setEnd;
                setEnd = temp;
            }
            Set<String> cur = new HashSet<>(setStart);
            setStart.clear();
            for (String str : cur) {
                List<String> nextSelect = nextSelect1(str);
                for (String nextStr : nextSelect) {
                    if (setEnd.contains(nextStr)) {
                        return step + 1;
                    }
                    setStart.add(nextStr);
                }
            }
            step++;
        }
        return -1;
    }

    private List<String> nextSelect1(String cur) {
        List<String> ret = new ArrayList<>();
        StringBuilder curB = new StringBuilder(cur);
        for (int i = 0; i < cur.length(); i++) {
            char curC = cur.charAt(i);
            char nextAC = (char) (((curC - '0' + 1) % 10) + '0');
            char nextEC = (char) (((curC - '0' - 1 + 10) % 10) + '0');
            String nextACStr = curB.substring(0, i) + nextAC + cur.substring(i + 1);
            String nextECStr = cur.substring(0, i) + nextEC + cur.substring(i + 1);
            if (!deadEndSet.contains(nextACStr)) {
                ret.add(nextACStr);
            }
            if (!deadEndSet.contains(nextECStr)) {
                ret.add(nextECStr);
            }
        }
        return ret;
    }

}
