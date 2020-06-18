package com.lafer.leetcode.array;

/**
 *
 * 287. 寻找重复数
 * 给定一个包含 n + 1 个整数的数组 nums，其数字都在 1 到 n 之间（包括 1 和 n），
 * 可知至少存在一个重复的整数。假设只有一个重复的整数，找出这个重复的数。
 *
 * 示例 1:
 *
 * 输入: [1,3,4,2,2]
 * 输出: 2
 * 示例 2:
 *
 * 输入: [3,1,3,4,2]
 * 输出: 3
 * 说明：
 *
 * 不能更改原数组（假设数组是只读的）。
 * 只能使用额外的 O(1) 的空间。
 * 时间复杂度小于 O(n2) 。
 * 数组中只有一个重复的数字，但它可能不止重复出现一次。
 *
 * 思考：
 * 二分查找：初始左边界1，右边界n（数组的大小-1），每次遍历数组统计<=mid的数的个数count
 * 如果count<=mid说明重复的数在右边 left = mid + 1
 * 否则说明重复的数在左边也可能等于mid right = mid；
 * 时间复杂度O(NlogN)
 *
 * 双指针解法:类似于有环链表中找出环的入口
 * 时间复杂度O(N*N )
 *
 */

public class LeetCode287 {

    public int findDuplicate(int[] nums) {
        int left = 1, right = nums.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            int count = 0;
            for (int i = 0; i < nums.length; i++) {
                if (nums[i] <= mid) {
                    count++;
                }
            }
            if (count <= mid) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }

    public int findDuplicate1(int[] nums) {
        int slow = 0, fast = 0;
        while (slow == 0 || slow != fast) {
            slow = nums[slow];
            fast = nums[nums[fast]];
        }
        slow = 0;
        while (slow != fast) {
            slow = nums[slow];
            fast = nums[fast];
        }
        return slow;
    }

}
