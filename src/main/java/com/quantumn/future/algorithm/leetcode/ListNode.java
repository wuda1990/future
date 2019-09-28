package com.quantumn.future.algorithm.leetcode;
/**
 * Definition for singly-linked list.
 */
public class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }

    //append the list node ,return the next node
    public ListNode append(ListNode p) {
        this.next = p;
        return p;
    }
    public void print() {
        System.out.print(val);
        ListNode p = next;
        while (p != null) {
            System.out.print("->"+p.val);
            p = p.next;
        }
        System.out.println();
    }
}
