package com.lafer.leetcode.dp;

/**
 *
 * 376. 摆动序列
 * 如果连续数字之间的差严格地在正数和负数之间交替，则数字序列称为摆动序列。第一个差（如果存在的话）可能是正数或负数。
 * 少于两个元素的序列也是摆动序列。
 *
 * 例如， [1,7,4,9,2,5] 是一个摆动序列，因为差值 (6,-3,5,-7,3) 是正负交替出现的。相反,
 * [1,4,7,2,5] 和 [1,7,4,5,5] 不是摆动序列，第一个序列是因为它的前两个差值都是正数，
 * 第二个序列是因为它的最后一个差值为零。
 *
 * 给定一个整数序列，返回作为摆动序列的最长子序列的长度。 通过从原始序列中删除一些（也可以不删除）元素来获得子序列，
 * 剩下的元素保持其原始顺序。
 *
 * 示例 1:
 *
 * 输入: [1,7,4,9,2,5]
 * 输出: 6
 * 解释: 整个序列均为摆动序列。
 * 示例 2:
 *
 * 输入: [1,17,5,10,13,15,10,5,16,8]
 * 输出: 7
 * 解释: 这个序列包含几个长度为 7 摆动序列，其中一个可为[1,17,10,13,10,16,8]。
 * 示例 3:
 *
 * 输入: [1,2,3,4,5,6,7,8,9]
 * 输出: 2
 * 进阶:
 * 你能否用 O(n) 时间复杂度完成此题?
 *
 * 思考：
 * dp：
 * 状态定义：dp[i] 表示 从nums[0] - nums[i] 的摆动序列的最长子序列的长度，如果dp[i] < 0 表示最后一次是向下的反之向上
 * 状态转移：dp[i] = max{dp[j] | 0 <= j < i} 其中nums[i] - nums[j] < 0 那么 dp[j] > 0; 反之亦然。注意dp[i]的正负号
 * 初始化：dp[0] = 1
 * 结果：dp数组中的最大绝对值。
 * 状态压缩：由于要记录之前的状态无法进行压缩。
 * 时间复杂度：O(n * n)
 * 空间复杂度：O(n)
 *
 * dp优化：
 * 状态定义：定义up数组和down数组，up[i] 表示从nums[0] - nums[i] 的摆动序列最后两个数是上升的最长子序列的长度；
 *          down[i] 表示从nums[0] - nums[i] 的摆动序列最后两个数是下降的最长子序列的长度
 * 状态转移：如果nums[i] > nums[i - 1], up[i] = down[i - 1] + 1， down[i] = down[i - 1];
 *          如果nums[i] < nums[i - 1], down[i] = up[i - 1] + 1， up[i] = up[i - 1];
 * 初始化：down[0] = 1;up[0] = 1;
 * 结果：Math.max(up[n - 1], down[n - 1]);
 * 状态压缩：由于每次只用到了之前的一个状态，可以直接使用两个常数up、down表示即可。
 * 时间复杂度：O(n)
 * 空间复杂度：O(1)
 */

public class LeetCode376 {

    public int wiggleMaxLength(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int up = 1, down = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > nums[i - 1]) {
                up = down + 1;
            } else if (nums[i] < nums[i - 1]) {
                down = up + 1;
            }
        }
        return Math.max(up, down);
    }

}
