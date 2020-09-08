package com.quantumn.future.algorithm.leetcode;

import java.util.LinkedList;
import java.util.Stack;

public class SortedStack {
    private Stack<Integer> stack;
    private Stack<Integer> helpStack;
    public SortedStack() {
        stack = new Stack<>();
        helpStack = new Stack<>();
    }

    public void push(int val) {
        if (stack.isEmpty()) {
            stack.push(val);
            return;
        }
        int curVal = stack.peek();
        if (val < curVal) {
            stack.push(val);
        } else {
            while (!stack.isEmpty() && stack.peek() < val) {
                helpStack.push(stack.pop());
            }
            stack.push(val);
            while (!helpStack.isEmpty()) {
                stack.push(helpStack.pop());
            }
        }
    }

    public void pop() {
        if (!stack.isEmpty()) {
            stack.pop();
        }
    }

    public int peek() {
        if (!stack.isEmpty()) {
            return stack.peek();
        }
        return -1;
    }

    public boolean isEmpty() {
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        SortedStack sortedStack = new SortedStack();
        sortedStack.push(1);
        sortedStack.push(2);
        System.out.println(sortedStack.peek());
        sortedStack.pop();
        System.out.println(sortedStack.peek());
    }
}
