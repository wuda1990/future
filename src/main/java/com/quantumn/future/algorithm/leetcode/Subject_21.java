package com.quantumn.future.algorithm.leetcode;

import org.mvel2.util.Make;

/**
 * 合并两个有序链表
 * 将两个有序链表合并为一个新的有序链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。
 *
 * 示例：
 *
 * 输入：1->2->4, 1->3->4
 * 输出：1->1->2->3->4->4
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/merge-two-sorted-lists
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Subject_21 {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode head = new ListNode(0);
        ListNode p = head;
        ListNode p1 = l1;
        ListNode p2 = l2;
        while (p1 != null && p2 != null) {
            if (p1.val < p2.val) {
                p.next = p1;
                p = p.next;
                p1 = p1.next;
            } else {
                p.next = p2;
                p = p.next;
                p2 = p2.next;
            }
        }
        if (p1 != null) {
            p.next = p1;
        } else {
            p.next = p2;
        }
        return head.next;
    }

    public static void main(String[] args) {
        Subject_21 demo = new Subject_21();
        ListNode l1 = new ListNode(1);
        l1.append(new ListNode(2)).append(new ListNode(4));
        ListNode l2 = new ListNode(1);
        l2.append(new ListNode(3)).append(new ListNode(4));
        l1.print();
        l2.print();
        demo.mergeTwoLists2(l1, l2).print();
    }

    /**
     * 链表的大神递归解法
     * 这种递归分支就一个，是线性的！
     * @param l1
     * @param l2
     * @return
     */
    public ListNode mergeTwoLists2(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }
        if (l1.val < l2.val) {
            l1.next = mergeTwoLists2(l1.next, l2);
            return l1;
        } else {
            l2.next = mergeTwoLists2(l1, l2.next);
            return l2;
        }
    }

}
