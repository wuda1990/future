package com.quantumn.future.algorithm.leetcode;

/**
 * 反转链表II
 * 反转从位置 m 到 n 的链表。请使用一趟扫描完成反转。
 *
 * 说明:
 * 1 ≤ m ≤ n ≤ 链表长度。
 *
 * 示例:
 *
 * 输入: 1->2->3->4->5->NULL, m = 2, n = 4
 * 输出: 1->4->3->2->5->NULL
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/reverse-linked-list-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Subject_92 {
    /**
     * 当遇到head也参与到循环里时，需要要用到dummy节点
     * 循环的判断里，要减少判断次数
     * @param head
     * @param m
     * @param n
     * @return
     */
    public ListNode reverseBetween(ListNode head, int m, int n) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        int i = 1;// count the node
        ListNode start = dummy;
        ListNode prev = null;
        ListNode p = head;
        while (p != null && i<=n) {
            if (i < m) {
                start = p;
                p = p.next;
            } else{
                //start to reverse the listNode
                ListNode tmp = p.next;
                p.next = prev;
                prev = p;
                p = tmp;
            }
            i++;
        }

        start.next.next = p; //设置reverseList的右边界
        start.next = prev;// 设置左边界

        return dummy.next;
    }

    /**
     * 泡菜大神的把反转化成插入的手法，很巧妙
     * @param head
     * @param m
     * @param n
     * @return
     */
    public ListNode reverseBetween2(ListNode head, int m, int n) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode pre = dummy;
        for (int i = 0; i < m-1; i++) {
            pre = pre.next;
        }
        ListNode start = pre.next;
        ListNode tail = start.next;
        for (int i = m; i < n; i++) {
            start.next = tail.next;
            tail.next = pre.next;
            pre.next = tail;
            tail = start.next;
        }
        return dummy.next;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.append(new ListNode(2)).append(new ListNode(3)).append(new ListNode(4)).append(new ListNode(5));
        Subject_92 demo = new Subject_92();
        demo.reverseBetween2(head,2,4).print();
    }
}
