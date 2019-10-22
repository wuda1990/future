package com.quantumn.future.algorithm.leetcode;

/**
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
    private ListNode tail;

    /**
     * 递归的方法定义了一个tail全局变量存储每个递归层次最后的结点值
     * @param head
     * @return
     */
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

    //迭代的方法

    /**
     *  最先想到的是栈，虽然简洁明了，但是占了一个数组空间
     *  这里定义了一个prev取保存前一个值，定义了tmp保存下一个值
     * @param head
     * @return
     */
    public ListNode reverseList2(ListNode head) {
        ListNode prev = null;
        ListNode p = head;
        while (p != null) {
            ListNode tmp = p.next;
            p.next = prev;
            prev = p;
            p = tmp;
        }
        return prev;
    }


    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.append(new ListNode(2)).append(new ListNode(3)).append(new ListNode(4)).append(new ListNode(5));
        Subject_206 demo = new Subject_206();
        demo.reverseList2(head).print();
    }
}
