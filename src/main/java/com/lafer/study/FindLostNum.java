package com.lafer.study;

/**
 * 一个整型数组中，只有两个整数出现奇数次，其他整数均出现偶数次，找出这两个整数
 */
public class FindLostNum {

    public static int[] findLostNum(int[] array) {
        if (array == null || array.length < 2) {
            return null;
        }
        //用来保存两个出现奇数次的整数
        int[] result = new int[2];
        int temp = 0;
        //第一次异或得到两个出现奇数次的整数的异或的值
        for (int i = 0; i < array.length; i++) {
            temp ^= array[i];
        }
        //没有目标结果
        if (temp == 0) {
            return null;
        }
        int t = 1;
        //取得两个出现奇数次的整数第一次不同的位数
        while ((temp & t) == 0) {
            t <<= 1;
        }
        //第二次分组异或得出结果
        for (int i = 0; i < array.length; i++) {
            if ((t & array[i]) == 0) {
                result[0] ^= array[i];
            } else {
                result[1] ^= array[i];
            }
        }
        return result;
    }


    public static void main(String[] args) {
        int[] array = {4, 1, 2, 2, 5, 1, 4, 3};
        int[] result = findLostNum(array);
        System.out.println(result[0] + "," + result[1]);
    }

}
