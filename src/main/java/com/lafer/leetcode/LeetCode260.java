package com.lafer.leetcode;

/**
 * 260. 只出现一次的数字 III
 * 给定一个整数数组 nums，其中恰好有两个元素只出现一次，其余所有元素均出现两次。 找出只出现一次的那两个元素。
 *
 * 示例 :
 *
 * 输入: [1,2,1,3,2,5]
 * 输出: [3,5]
 * 注意：
 *
 * 结果输出的顺序并不重要，对于上面的例子， [5, 3] 也是正确答案。
 * 你的算法应该具有线性时间复杂度。你能否仅使用常数空间复杂度来实现？
 *
 * 思考：位运算全部疑惑，使用最右边不为1的位进行区分两个数
 *  x & (-x) 是保留位中最右边 1 ，且将其余的 1 设位 0 的方法。
 *
 */

public class LeetCode260 {

    public int[] singleNumber(int[] nums) {
        int ans = 0, flag = 1;
        int[] result = new int[2];
        for (int num : nums) {
            ans ^= num;
        }
        flag = ans & (-ans);
        for (int num : nums) {
            if ((flag & num) == 0) {
                result[0] ^= num;
            } else {
                result[1] ^= num;
            }
        }
        return result;
    }

}
