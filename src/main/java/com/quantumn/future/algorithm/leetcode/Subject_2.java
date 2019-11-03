package com.quantumn.future.algorithm.leetcode;

import org.apache.poi.ss.formula.functions.Sumxmy2;

/**
 * 给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。
 *
 * 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
 *
 * 您可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 *
 * 示例：
 *
 * 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
 * 输出：7 -> 0 -> 8
 * 原因：342 + 465 = 807
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/add-two-numbers
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Subject_2 {

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(-1);
        ListNode cur = dummy;
        int jw = 0;
        while (l1 != null || l2 != null || jw!=0) {
            int value1 = 0;
            int value2 = 0;
            if (l1 != null) {
                value1 = l1.val;
                l1 = l1.next;
            }
            if (l2 != null) {
                value2 = l2.val;
                l2 = l2.next;
            }
            int tmp = value1+ value2 + jw;
            if (tmp > 9) {
                tmp = tmp - 10;
                jw = 1;
            } else {
                jw = 0;
            }
            ListNode sumNode = new ListNode(tmp);
            cur.next = sumNode;
            cur = sumNode;// or cur = cur.next;

        }
        return dummy.next;

    }

    public static void main(String[] args) {
        ListNode l1 = new ListNode(2);
        l1.append(new ListNode(4)).append(new ListNode(3));
        ListNode l2 = new ListNode(5);
        l2.append(new ListNode(6)).append(new ListNode(4));
        Subject_2 demo = new Subject_2();
        demo.addTwoNumbers(l1, l2).print();
    }
}
