package com.lafer.leetcode;

import java.util.HashMap;
import java.util.Stack;

public class LeetCode496 {

    public int[] nextGreaterElements(int[] nums) {
        int n = nums.length;
        int[] ret = new int[nums.length];
        Stack<Integer> stack = new Stack<>();
        for (int i = n * 2 - 1; i >= 0; i--) {
            while (!stack.isEmpty() && nums[stack.peek() % n] <= nums[i % n]) {
                stack.pop();
            }
            ret[i % n] = stack.isEmpty() ? -1 : nums[stack.peek() % n];
            stack.push(i % n);
        }
        return ret;
    }

    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        int[] nums3 = nextMaxElement(nums2);
        HashMap<Integer, Integer> hash = new HashMap<>();
        for (int i = 0; i < nums2.length; i++) {
            hash.put(nums2[i], nums3[i]);
        }
        int[] ret = new int[nums1.length];
        for (int i = 0; i < nums1.length; i++) {
            ret[i] = hash.get(nums1[i]);
        }
        return ret;
    }

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
    }


    public static void main(String[] args) {
        nextMaxElement(new int[]{2, 1, 2, 4, 3});
        nextMinElement(new int[]{2, 1, 2, 4, 3});
    }

}
