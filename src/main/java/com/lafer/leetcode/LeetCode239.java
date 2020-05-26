package com.lafer.leetcode;

import java.util.LinkedList;

/**
 * 239. 滑动窗口最大值
 * 给定一个数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。你只可以看到在滑动窗口内的 k 个数字。滑动窗口每次只向右移动一位。
 * <p>
 * 返回滑动窗口中的最大值。
 * <p>
 * <p>
 * <p>
 * 进阶：
 * <p>
 * 你能在线性时间复杂度内解决此题吗？
 * <p>
 * <p>
 * <p>
 * 示例:
 * <p>
 * 输入: nums = [1,3,-1,-3,5,3,6,7], 和 k = 3
 * 输出: [3,3,5,5,6,7]
 * 解释:
 * <p>
 * 滑动窗口的位置                最大值
 * ---------------               -----
 * [1  3  -1] -3  5  3  6  7       3
 * 1 [3  -1  -3] 5  3  6  7       3
 * 1  3 [-1  -3  5] 3  6  7       5
 * 1  3  -1 [-3  5  3] 6  7       5
 * 1  3  -1  -3 [5  3  6] 7       6
 * 1  3  -1  -3  5 [3  6  7]      7
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= nums.length <= 10^5
 * -10^4 <= nums[i] <= 10^4
 * 1 <= k <= nums.length
 * <p>
 * 思考：双端队列
 */

public class LeetCode239 {

    public static void main(String[] args) {
        maxSlidingWindow(new int[]{1,3,1,2,0,5}, 3);
    }

    public static int[] maxSlidingWindow(int[] nums, int k) {
        int length = nums.length - k + 1;
        if (length <= 0) return null;
        int[] result = new int[length];
        int index = 0;
        LinkedList<Integer> queue = new LinkedList<>();
        for (int i = 0 ;i < nums.length; i++) {
            //不在范围的去掉
            while (!queue.isEmpty() && queue.peek() <= i - k) {
                queue.pop();
            }
            //不可能成为最大值的去掉
            while (!queue.isEmpty() && nums[queue.peekLast()] < nums[i]) {
                queue.removeLast();
            }
            queue.addLast(i);
            if (i >= k - 1) {
                result[index++] = nums[queue.peek()];
            }
        }
        return result;
    }

}
