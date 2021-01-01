package com.lafer.leetcode;

import java.util.Stack;

public class LeetCode84 {
    public int largestRectangleArea(int[] heights) {
        int[] next = nextMinElement(heights);
        int[] pre = preMinElement(heights);
        int ret = 0;
        for (int i = 0; i < heights.length; i++) {
            ret = Math.max(ret, (next[i] - pre[i] - 1) * heights[i]);
        }
        return ret;
    }

    public static int[] nextMinElement(int[] nums) {
        int[] retIndex = new int[nums.length];
        Stack<Integer> stack = new Stack<>();
        for (int i = nums.length - 1; i >= 0; i--) {
            while (!stack.isEmpty() && nums[stack.peek()] >= nums[i]) {
                stack.pop();
            }
            retIndex[i] = stack.isEmpty() ? nums.length : stack.peek();
            stack.push(i);
        }
        return retIndex;
    }

    public static int[] preMinElement(int[] nums) {
        int[] retIndex = new int[nums.length];
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < nums.length; i++) {
            while (!stack.isEmpty() && nums[stack.peek()] >= nums[i]) {
                stack.pop();
            }
            retIndex[i] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(i);
        }
        return retIndex;
    }

}
