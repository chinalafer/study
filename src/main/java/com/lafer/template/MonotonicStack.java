package com.lafer.template;

import java.util.Stack;

public class MonotonicStack {

    // 求下一个比它大的数
    public static int[] nextMaxElement(int[] nums) {
        int[] ret = new int[nums.length];
        int[] retIndex = new int[nums.length];
        Stack<Integer> stack = new Stack<>();
        for (int i = nums.length - 1; i >= 0; i--) {
            while (!stack.isEmpty() && nums[stack.peek()] <= nums[i]) {
                stack.pop();
            }
            ret[i] = stack.isEmpty() ? -1 : nums[stack.peek()];
            retIndex[i] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(i);
        }
        return ret;
        // return retIndex;
    }

    public static int[] nextMinElement(int[] nums) {
        int[] ret = new int[nums.length];
        int[] retIndex = new int[nums.length];
        Stack<Integer> stack = new Stack<>();
        for (int i = nums.length - 1; i >= 0; i--) {
            while (!stack.isEmpty() && nums[stack.peek()] >= nums[i]) {
                stack.pop();
            }
            ret[i] = stack.isEmpty() ? -1 : nums[stack.peek()];
            retIndex[i] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(i);
        }
        return ret;
        // return retIndex;
    }

}
