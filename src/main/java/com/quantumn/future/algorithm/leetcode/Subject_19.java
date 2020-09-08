package com.quantumn.future.algorithm.leetcode;

import com.quantumn.future.algorithm.leetcode.common.ListNode;

import java.util.HashMap;
import java.util.Map;

/**
 * 删除链表的倒数第n个节点
 * 给定一个链表，删除链表的倒数第 n 个节点，并且返回链表的头结点。
 * 示例：
 * 给定一个链表: 1->2->3->4->5, 和 n = 2.
 * 当删除了倒数第二个节点后，链表变为 1->2->3->5.
 * 说明：
 * 给定的 n 保证是有效的。
 * 进阶：
 * 你能尝试使用一趟扫描实现吗？
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/remove-nth-node-from-end-of-list
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Subject_19 {
    //扫描两次
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode p = head;
        int i=0;//i为链表大小
        while (p != null) {
            i++;
            p = p.next;
        }
        int j=0;
        ListNode dummy = new ListNode(-1);
        dummy.next = head;

        //如果删除的是头结点
//        if (i == n) {
//            ListNode new_head = head.next;
//            head.next = null;
//            return new_head;
//        }
        p = dummy.next;
        ListNode pre = dummy;
        while (p != null) {
            if (j++ == i - n) {
                //remove the node
                pre.next = p.next;
                p.next = null;
                return dummy.next;
            }
            pre = p;
            p = p.next;
        }
        return dummy.next;
    }

    public static void main(String[] args) {
        Subject_19 demo = new Subject_19();
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(5);
        node1.append(node2).append(node3).append(node4).append(node5);
        demo.removeNthFromEnd2(node1, 5).print();
    }

    /**
     * 循环，用map记住位置和node的对应关系
     * @param head
     * @param n
     * @return
     */
    public ListNode removeNthFromEnd2(ListNode head, int n) {
        Map<Integer, ListNode> map = new HashMap<>();
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode p = dummy;
        int i=0;
        while (p != null) {
            map.put(i++, p);
            p = p.next;
        }
        ListNode last_n = map.get(i - n);
        map.get(i - n - 1).next = last_n.next;
        last_n.next = null;
        return dummy.next;
    }

    /**
     * 双指针题解（双指针的用处是真多啊！）
     * 这题思路是真巧妙，通过start,end维护一个n大小的范围,当end为最后的一个数,start则为倒数第n个数，厉害！
     * @param head
     * @param n
     * @return
     */
    public ListNode removeNthFromEnd3(ListNode head, int n) {
        //哑指针处理删除头结点或链表只有一个结点的情况
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode start = dummy;
        ListNode end = dummy;
        for (int i = 0; i < n; i++) {
            end = end.next;
        }
        while (end.next != null) {
            start = start.next;
            end = end.next;
        }
        //将前结点start的指针指向要删除结点的下一个结点
        //将被删除结点的指针指向null
        ListNode p = start.next;
        start.next = p.next;
        p.next = null;
        return dummy.next;
    }

}
