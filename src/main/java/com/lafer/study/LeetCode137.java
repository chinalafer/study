package com.lafer.study;

/**
 * 给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现了三次。找出那个只出现了一次的元素。
 * <p>
 * 说明：
 * <p>
 * 你的算法应该具有线性时间复杂度。 你可以不使用额外空间来实现吗？
 * <p>
 * 想法：每一个数都是整型，即32位，现在对数组中的每一位相加（比如第一位上面的数全部加起来）然后对3取余，结果就是对应位上的值。
 */

public class LeetCode137 {

    public int singleNumber(int[] nums) {
        int result = 0;
        int yw = 1;
        int count;
        for (int i = 0; i < 32; i++) {
            count = 0;
            for (int num : nums) {
                if ((yw & num) != 0) {
                    count++;
                }
            }
            if (count % 3 == 1) {
                result = result | yw;
            }
            yw <<= 1;
        }
        return result;
    }

}
