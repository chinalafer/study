package com.lafer.leetcode;

/**
 * 给定一副牌，每张牌上都写着一个整数。
 *
 * 此时，你需要选定一个数字 X，使我们可以将整副牌按下述规则分成 1 组或更多组：
 *
 * 每组都有 X 张牌。
 * 组内所有的牌上都写着相同的整数。
 * 仅当你可选的 X >= 2 时返回 true。
 *
 * 提示：
 *
 * 1 <= deck.length <= 10000
 * 0 <= deck[i] < 10000
 *
 * 思考：看到提示，优先考虑使用打表计数法。
 * 误区：刚开始的以为取得最少出现的数的次数，然后判断使用其他数出现的次数能否整除这个最小出现的次数
 *      但是如果是这样的数据就会存在问题{1,1,1,2,2,2,3,3}。
 * 正解：对各个数出现的次数，求他们的最大公约数，最大公约数如果大于等于2返回true,否则返回false.
 */


public class LeetCode914 {

    public static void main(String[] args) {
        hasGroupsSizeX(new int[] {1,1,1,2,2,2,3,3});
    }

    public static boolean hasGroupsSizeX(int[] deck) {
        int[] counts = new int[10001];
        int g = 0;
        for (int value : deck) {
            counts[value]++;
            g = Math.max(counts[value], g);
        }
        for (int count : counts) {
            if (count > 0) {
                g = gc(count, g);
            }
        }
        return g > 1;
    }

    public static int gc(int a, int b) {
//        while (b != 0) {
//            int temp = a % b;
//            a = b;
//            b= temp;
//        }
//        return a;
        return a % b == 0 ? b : gc(b, a % b);
    }

}
