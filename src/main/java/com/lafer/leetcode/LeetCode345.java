package com.lafer.leetcode;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * 345. 反转字符串中的元音字母
 * 编写一个函数，以字符串作为输入，反转该字符串中的元音字母。
 *
 * 示例 1:
 *
 * 输入: "hello"
 * 输出: "holle"
 * 示例 2:
 *
 * 输入: "leetcode"
 * 输出: "leotcede"
 * 说明:
 * 元音字母不包含字母"y"。
 *
 * 思考：双指针， 元音字母有：a、e、i、o、u
 *
 */

public class LeetCode345 {

    public static void main(String[] args) {
        reverseVowels("hello");
    }

    public static String reverseVowels(String s) {

        char[] cs = s.toCharArray();
        int index1 = 0, index2 = cs.length - 1;
        while (index1 <= index2) {
            while (index1 <= index2 && Character.toLowerCase(cs[index1]) != 'a' && Character.toLowerCase(cs[index1]) != 'e' && Character.toLowerCase(cs[index1]) != 'i' && Character.toLowerCase(cs[index1]) != 'o' && Character.toLowerCase(cs[index1]) != 'u') {
                index1++;
            }
            while (index1 <= index2 && Character.toLowerCase(cs[index2]) != 'a' && Character.toLowerCase(cs[index2]) != 'e' && Character.toLowerCase(cs[index2]) != 'i' && Character.toLowerCase(cs[index2]) != 'o' && Character.toLowerCase(cs[index2]) != 'u') {
                index2--;
            }
            if (index1 <= index2 && !(Character.toLowerCase(cs[index1]) != 'a' && Character.toLowerCase(cs[index1]) != 'e' && Character.toLowerCase(cs[index1]) != 'i' && Character.toLowerCase(cs[index1]) != 'o' && Character.toLowerCase(cs[index1]) != 'u') && !(Character.toLowerCase(cs[index2]) != 'a' && Character.toLowerCase(cs[index2]) != 'e' && Character.toLowerCase(cs[index2]) != 'i' && Character.toLowerCase(cs[index2]) != 'o' && Character.toLowerCase(cs[index2]) != 'u')) {
                char temp = cs[index1];
                cs[index1] = cs[index2];
                cs[index2] = temp;
                index1++;
                index2--;
            }
        }
        return new String(cs);
    }

    public String reverseVowels1(String s) {

        char[] cs = s.toCharArray();
        int index1 = 0, index2 = cs.length - 1;
        while (index1 <= index2) {
            if (isVowel(cs[index1])) {
                if (isVowel(cs[index2])) {
                    swap(cs, index1, index2);
                    index1++;
                    index2--;
                }else {
                    index2--;
                }
            } else {
                index1++;
            }
        }
        return new String(cs);
    }

    private void swap(char[] arr, int a, int b) {
        char tmp = arr[a];
        arr[a] = arr[b];
        arr[b] = tmp;
    }

    private boolean isVowel(char ch) {
        return ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u'
                ||ch=='A'|| ch == 'E' || ch == 'I' || ch == 'O' || ch == 'U';
    }


}
