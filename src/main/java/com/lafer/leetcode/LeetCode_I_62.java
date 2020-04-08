package com.lafer.leetcode;

import java.util.ArrayList;

/**
 * 0,1,,n-1这n个数字排成一个圆圈，从数字0开始，每次从这个圆圈里删除第m个数字。求出这个圆圈里剩下的最后一个数字。
 *
 * 例如，0、1、2、3、4这5个数字组成一个圆圈，从数字0开始每次删除第3个数字，则删除的前4个数字依次是2、0、4、1，因此最后剩下的数字是3。
 *
 * 思考：模拟法（使用ArrayList）
 * 题解中的数学方法：约瑟夫环问题
 *
 */

public class LeetCode_I_62 {

    public static void main(String[] args) {
        System.out.println(lastRemaining(5, 3));
    }

    public static int lastRemaining(int n, int m) {
        ArrayList<Integer> arrayList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            arrayList.add(i);
        }
        int index = 0;
        while (arrayList.size() > 1) {
            index = (index + m - 1) % arrayList.size();
            arrayList.remove(index);
        }
        return arrayList.get(0);
    }

    public static int lastRemaining2(int n, int m) {
        int res = 0;
        for (int i = 2; i <= n; i++) {
            res = (res + m) % i;
        }
        return res;
    }

}
