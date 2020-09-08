package com.quantumn.future.algorithm.leetcode;

import com.quantumn.future.algorithm.leetcode.common.ListNode;

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
     * 当遇到head也参与到循环里时，需
     * 要要用到dummy节点
     * 循环的判断里，要减少判断次数
     * @param head
     * @param m
     * @param n
     * @return
     */
    public ListNode reverseBetween(ListNode head, int m, int n) {
        ListNode dummy  = new ListNode(-1);
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

        start.next.next = p; //设置reverseList的右边界,以m=2,n=4举例因为start.next仍是指向节点1，所以要把节点1指向p，即节点5，然后再把start.next指向pre，即4
        start.next = prev;// 设置左边界

        return dummy.next;
    }

    /**
     * 泡菜大神的把反转化成插入的手法，很巧妙
     * 用三个指针,进行插入操作
     *
     * 例如:
     *
     * 1->2->3->4->5->NULL, m = 2, n = 4
     *
     * 将节点3插入节点1和节点2之间
     *
     * 变成: 1->3->2->4->5->NULL
     *
     * 再将节点4插入节点1和节点3之间
     *
     * 变成:1->4->3->2->5->NULL
     *
     * 实现翻转的效果!
     *
     * 作者：powcai
     * 链接：https://leetcode-cn.com/problems/reverse-linked-list-ii/solution/liang-chong-fang-fa-by-powcai/
     * 来源：力扣（LeetCode）
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
        demo.reverseBetween(head,2,4).print();
//        demo.reverseBetween2(head,2,4).print();
    }


    //借鉴反转链表的例子，注意这里的tail很有意思，他表示当前反转列表尾部指针，而且其值一直等于pre，需要持续更新其next指向,这样就不用考虑反转链表的右边界问题了
    public ListNode reverseBetween_review(ListNode head, int m, int n) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode start = dummy;
        for (int i = 1; i < m && start.next!=null; i++) {
            start = start.next;
        }
        ListNode pre = start.next;
        ListNode tail = start.next;
        if (pre != null) {
            ListNode p = pre.next;
            int i=0;// loop count
            while (p != null && i<n-m) {
                tail.next = p.next;
                p.next = pre;
                start.next = p;
                pre = p;
                p = tail.next;
                i++;
            }
        }
        return dummy.next;
    }


}
