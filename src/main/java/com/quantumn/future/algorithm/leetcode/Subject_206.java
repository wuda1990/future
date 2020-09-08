package com.quantumn.future.algorithm.leetcode;

import com.quantumn.future.algorithm.leetcode.common.ListNode;

/**
 * 反转链表
 * 反转一个单链表。
 * <p>
 * 示例:
 * <p>
 * 输入: 1->2->3->4->5->NULL
 * 输出: 5->4->3->2->1->NULL
 * <p>
 * 进阶:
 * 你可以迭代或递归地反转链表。你能否用两种方法解决这道题？
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/reverse-linked-list
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Subject_206 {
    /**
     * 递归的方法定义了一个tail全局变量存储每个递归层次最后的结点值
     * @param head
     * @return
     */
    private ListNode tail;
    public ListNode reverseList(ListNode head) {
        if(head == null) return null;
        if (head.next == null) {
            tail = head;
            return head;
        }
        ListNode new_head = reverseList(head.next);
        tail.next = head;
        head.next = null;
        tail = head;
        return new_head;
    }

    /**
     * 递归的方法定义了一个start全局变量表示当前链表的尾部，即反转链表的头部
     * @param head
     * @return
     */
    private ListNode start;
    public ListNode reverseList2(ListNode head) {
        ListNode ans = null;
        reverse2(head);
        return start;
    }
    public ListNode reverse2(ListNode head) {
        if(head == null) return null;
        if (head.next == null) {
            start = head;
            return head;
        }
        ListNode tail = reverse2(head.next);
        tail.next = head;
        head.next = null;
        return head;
    }

    /**
     * 迭代的方法,这是最简洁的方法
     * 链表的方法之所以可行，是因为我可以有多个引用变量可以指向同一个对象！
     *  最先想到的是栈，虽然简洁明了，但是占了一个数组空间
     *  这里定义了一个prev取保存前一个值，定义了tmp保存下一个值
     * @param head
     * @return
     */
    public ListNode reverseList3(ListNode head) {
        ListNode prev = null;
        ListNode p = head;
        while (p != null) {
            ListNode tmp = p.next;//注意p.next需要先保存起来
            p.next = prev;
            prev = p;
            p = tmp;
        }
        return prev;
    }

    //链表反转的核心是双指针,一个指向前一个指针，一个指向当前指针，p.next下一个指针也需要暂存
    public ListNode reverse_review(ListNode head) {
        ListNode pre = head;
        ListNode tail = pre;
        if (pre != null) {
            ListNode p = pre.next;
            while (p != null) {
                tail.next = p.next;
                p.next = pre;
                pre = p;
                p = tail.next;
            }
        }
        return pre;
    }


    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.append(new ListNode(2)).append(new ListNode(3)).append(new ListNode(4)).append(new ListNode(5));
        Subject_206 demo = new Subject_206();
        demo.reverseList3(head).print();
    }
}
