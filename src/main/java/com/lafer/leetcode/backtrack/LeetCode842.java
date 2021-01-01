package com.lafer.leetcode.backtrack;

import sun.applet.Main;
import sun.security.jca.GetInstance;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 给定一个数字字符串 S，比如 S = "123456579"，我们可以将它分成斐波那契式的序列 [123, 456, 579]。
 * <p>
 * 形式上，斐波那契式序列是一个非负整数列表 F，且满足：
 * <p>
 * 0 <= F[i] <= 2^31 - 1，（也就是说，每个整数都符合 32 位有符号整数类型）；
 * F.length >= 3；
 * 对于所有的0 <= i < F.length - 2，都有 F[i] + F[i+1] = F[i+2] 成立。
 * 另外，请注意，将字符串拆分成小块时，每个块的数字一定不要以零开头，除非这个块是数字 0 本身。
 * <p>
 * 返回从 S 拆分出来的任意一组斐波那契式的序列块，如果不能拆分则返回 []。
 */

public class LeetCode842 {

    public static void main(String[] args) {
        splitIntoFibonacci("123446");
    }


    static LinkedList<Integer> res = new LinkedList<>();

    public static List<Integer> splitIntoFibonacci(String S) {
        dfs(S, 0);
        return res;
    }

    private static boolean dfs(String S, int index) {
        if (index == S.length()) {
            return res.size() > 2;
        }

        for (int i = index; i < S.length(); i++) {
            String substring = S.substring(index, i + 1);
            // 剪枝 超过范围 或者 以零开头，却不是0本身
            if (substring.length() > 11 || Long.valueOf(substring) > Integer.MAX_VALUE || (S.charAt(index) == '0' && i != index)) {
                break;
            }
            int pre1 = res.size() > 1 ? res.get(res.size() - 2) : -1;
            int pre2 = res.size() > 0 ? res.get(res.size() - 1) : -1;
            int cur = Integer.valueOf(substring);
            if (pre1 == -1 || pre2 == -1 || (cur== pre2 + pre1)) {
                res.add(cur);
                boolean dfs = dfs(S, i + 1);
                if (dfs) {
                    return true;
                }
                res.removeLast();
            } else if (pre1 != -1 && pre2 != -1 && (pre1 + pre2 < cur)) {
                break;
            }
        }
        return false;
    }


    private static volatile LeetCode842 leetCode842;

    private LeetCode842() {

    }

    public static LeetCode842 getInstance() {
        if (leetCode842 == null) {
            synchronized (LeetCode842.class) {
                if (leetCode842 == null) {
                    leetCode842 = new LeetCode842();
                }
            }
        }
        return leetCode842;
    }

    enum EnumSingle {
        Instance;
        private LeetCode842 leetCode842;
        EnumSingle(){
            leetCode842 = new LeetCode842();
        }
        public LeetCode842 getInstance() {
            return leetCode842;
        }
    }

    public static LeetCode842 getInstance2() {
        return EnumSingle.Instance.getInstance();
    }

}
