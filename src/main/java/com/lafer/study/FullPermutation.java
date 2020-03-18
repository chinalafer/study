package com.lafer.study;

import com.sun.deploy.util.StringUtils;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 全排列算法
 */

public class FullPermutation {
    /**
     * 获得全排列下一个数
     *
     * @param numbers
     * @return
     */
    public static int[] findNearestNumber(int[] numbers) {
        //空或者长度小于二，本身便是最大
        if (numbers == null || numbers.length < 2) {
            return numbers;
        }
        int index = findTransferPoint(numbers);
        //已是最大的组合
        if (index == -1) {
            return numbers;
        }
        numbers = exchangeHead(numbers, index);
        numbers = reverse(numbers, index);
        return numbers;
    }

    /**
     * 从后向前查看逆序区域，找到逆序区域的前一位，也就是数字置换的边界
     *
     * @param numbers
     * @return
     */
    public static int findTransferPoint(int[] numbers) {
        int index = numbers.length - 2;
        while (index >= 0 && numbers[index] > numbers[index + 1]) {
            index--;
        }
        return index;
    }

    /**
     * 把逆序区域的前一位和逆序区域中刚刚大于它的数字交换位置
     *
     * @param numbers
     * @param index
     * @return
     */
    public static int[] exchangeHead(int[] numbers, int index) {
        Integer targetIndex = null;
        for (int i = index + 1; i < numbers.length; i++) {
            if (numbers[i] > numbers[index] && (targetIndex == null || numbers[i] < numbers[targetIndex])) {
                targetIndex = i;
            }
        }
        //交换numbers[index]与numbers[targetIndex]
        numbers[index] = numbers[index] ^ numbers[targetIndex];
        numbers[targetIndex] = numbers[index] ^ numbers[targetIndex];
        numbers[index] = numbers[index] ^ numbers[targetIndex];
        return numbers;
    }

    /**
     * 逆序区域改成正序
     *
     * @param numbers
     * @param index
     * @return
     */
    public static int[] reverse(int[] numbers, int index) {
        int start = index + 1;
        int end = numbers.length - 1;
        while (start < end) {
            numbers[start] = numbers[start] ^ numbers[end];
            numbers[end] = numbers[start] ^ numbers[end];
            numbers[start] = numbers[start] ^ numbers[end];
            start++;
            end--;
        }
        return numbers;
    }


    // 比较两个数组是否相等
    public static boolean equalse(int nums1[], int nums2[]) {
        if (nums1 == null && nums2 == null) {
            return true;
        }
        if (nums1 == null || nums2 == null || nums1.length != nums2.length) {
            return false;
        }

        for (int index = 0; index < nums1.length; index++) {
            if (nums1[index] != nums2[index]) {
                return false;
            }
        }

        return true;
    }


    public static void main(String[] args) {
        System.out.println("1-5的全排列：");
        int[] nums = new int[]{1, 2, 3, 4, 5};
        System.out.println(Arrays.toString(nums));
        int[] nextNums = findNearestNumber(nums.clone());
        while (!equalse(nums, nextNums)) {
            System.out.println(Arrays.toString(nextNums));
            nums = nextNums;
            nextNums = findNearestNumber(nums.clone());
        }
        System.out.println("1-4的全排列：");
        Permutations(new int[]{1, 2, 3, 4}, 0);

    }


    // 全排类递归算法(dfs)
    // 有序地枚举数组每个位置的数字,做到全面不重复即可

    /**
     * 全排类递归算法(dfs)
     * 有序地枚举数组每个位置的数字,做到全面不重复即可
     *
     * @param nums
     * @param start
     */
    public static void Permutations(int[] nums, int start) {
        // 出口条件
        if (start == nums.length) {
            System.out.println(Arrays.toString(nums));
        }
        for (int i = start; i < nums.length; i++) {
            //置换
            int temp = nums[i];
            nums[i] = nums[start];
            nums[start] = temp;

            Permutations(nums, start + 1);
            //置换
            temp = nums[i];
            nums[i] = nums[start];
            nums[start] = temp;
        }
    }


}
