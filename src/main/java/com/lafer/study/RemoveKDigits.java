package com.lafer.study;

import java.util.Stack;

public class RemoveKDigits {

    /**
     * 基本思路：对数字而言，高位对其值的影响较大，所以只要能降低高位的数字的大小，便会得到最小的值
     * @param num
     * @param k
     * @return
     */
    public static String removeKDigits (String num, int k) {
        if (num == null) {
            return num;
        }

        for (int i = 0; i < k; i++) {

            if (num.length() <= 1) {
                return "0";
            }

            int index = 0;
            for (int strIndex = 0; strIndex < num.length() - 1; strIndex++) {
                if (num.charAt(strIndex) > num.charAt(strIndex + 1)) {
                    index = strIndex;
                    break;
                }
            }

            num = num.substring(0, index) + num.substring(index + 1);

            while (num.startsWith("0") && num.length() > 1) {
                num = num.substring(1);
            }
        }
        return num;
    }

    public static String superRemoveDigits (String num, int k) {
        String result = "";
        Stack<Character> nums = new Stack<Character>();
        for (int i = 0; i < num.length(); i++) {
            if (k <= 0 || nums.empty() || nums.peek() <= num.charAt(i)) {
                nums.push(num.charAt(i));
            } else {
                while (!nums.empty() && nums.peek() > num.charAt(i) && k > 0) {
                    nums.pop();
                    k--;
                }
                nums.push(num.charAt(i));
            }
        }
        int offset = 0;
        while (offset < nums.size() && nums.get(offset) == '0') {
            offset++;
        }
        for (int i = offset; i < nums.size(); i++) {
            result = result + nums.get(i);
        }
        return offset == nums.size() ? "0" : result;
    }

    public static void main(String[] args) {

        System.out.println(removeKDigits("1593212", 3));
        System.out.println(removeKDigits("302000", 1));
        System.out.println(removeKDigits("10",2));

        System.out.println(superRemoveDigits("1593212", 3));
        System.out.println(superRemoveDigits("302000", 1));
        System.out.println(superRemoveDigits("10",2));

    }
}
