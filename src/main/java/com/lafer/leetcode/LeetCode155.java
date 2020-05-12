package com.lafer.leetcode;

import java.util.Stack;

/**
 * 155. 最小栈
 * 设计一个支持 push ，pop ，top 操作，并能在常数时间内检索到最小元素的栈。
 * <p>
 * push(x) —— 将元素 x 推入栈中。
 * pop() —— 删除栈顶的元素。
 * top() —— 获取栈顶元素。
 * getMin() —— 检索栈中的最小元素。
 */

public class LeetCode155 {

    /**
     * initialize your data structure here.
     */

    private Stack<Integer> minStack = null;
    private Stack<Integer> numStack = null;

    public LeetCode155() {
        this.minStack = new Stack<>();
        this.numStack = new Stack<>();
    }

    public void push(int x) {
        numStack.push(x);
        if (minStack.empty() || minStack.peek() >= x) {
            minStack.push(x);
        }
    }

    public void pop() {
        int num = numStack.pop();
        if (num == minStack.peek()) {
            minStack.pop();
        }
    }

    public int top() {
        return numStack.peek();
    }

    public int getMin() {
        return minStack.peek();
    }

    /**
     * Your MinStack object will be instantiated and called as such:
     * MinStack obj = new MinStack();
     * obj.push(x);
     * obj.pop();
     * int param_3 = obj.top();
     * int param_4 = obj.getMin();
     */

}
