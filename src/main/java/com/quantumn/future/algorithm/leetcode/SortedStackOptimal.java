package com.quantumn.future.algorithm.leetcode;

import java.util.Stack;

public class SortedStackOptimal {
    public static String initOption = "2";
    private Stack<Integer> stack;
    private Stack<Integer> helpStack;

    public SortedStackOptimal() {
        stack = new Stack<>();
        helpStack = new Stack<>();
    }

    public void push(int val) {
        if (stack.isEmpty()) {
            stack.push(val);
            return;
        }
        //保持了一种结构，O(n)的算法
        //当curHelpVal<val<CurVal,存在stack，栈不需要移动元素
        int curVal = stack.isEmpty()?Integer.MAX_VALUE:stack.peek();
        int curHelpVal= helpStack.isEmpty()?Integer.MIN_VALUE:helpStack.peek();
        if (val > curVal) {
            //只需要移动主栈元素
            while (!stack.isEmpty() && stack.peek() < val) {
                helpStack.push(stack.pop());
            }
            stack.push(val);
        } else if (val >= curHelpVal) {
            stack.push(val);
        } else if (val < curHelpVal) {
            //只需要移动helpStack元素
            while (!helpStack.isEmpty() && helpStack.peek() > val) {
                stack.push(helpStack.pop());
            }
            helpStack.push(val);
        }

    }

    public void pop() {
        //懒加载
        while (!helpStack.isEmpty()) {
            stack.push(helpStack.pop());
        }
        if (!stack.isEmpty()) {
            stack.pop();
        }
    }

    public int peek() {
        while (!helpStack.isEmpty()) {
            stack.push(helpStack.pop());
        }
        if (!stack.isEmpty()) {
            return stack.peek();
        }
        return -1;
    }

    public boolean isEmpty() {
        return stack.isEmpty() && helpStack.isEmpty();
    }

    public static void main(String[] args) {
        SortedStackOptimal sortedStack = new SortedStackOptimal();
        sortedStack.push(1);
        sortedStack.push(2);
        System.out.println(sortedStack.peek());
        sortedStack.pop();
        System.out.println(sortedStack.peek());
    }
}
