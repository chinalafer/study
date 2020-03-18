package com.lafer.study;


/**
 * 给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素。
 *
 * 说明：
 *
 * 你的算法应该具有线性时间复杂度。 你可以不使用额外空间来实现吗？
 *
 * 思考：异或运算， a ^ a = 0, 0 ^ b = b, 相同的两个数异或等到0 ， 0 与 任何数异或得到它本身， 所以，将数组中的每个数进行异或既可得到那个只出现一次的数字。
 *
 */

public class LeetCode136 {

    public static void main(String[] args) {

    }


    public int singleNumber(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }
        int result = 0;
        for (int i: nums) {
            result ^= i;
        }
        return result;
    }

}
