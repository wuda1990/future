package com.quantumn.future.algorithm.leetcode;

import com.quantumn.future.algorithm.leetcode.common.ListNode;

/**
 * 合并k个排序链表
 * 合并 k 个排序链表，返回合并后的排序链表。请分析和描述算法的复杂度。
 *
 * 示例:
 *
 * 输入:
 * [
 *   1->4->5,
 *   1->3->4,
 *   2->6
 * ]
 * 输出: 1->1->2->3->4->4->5->6
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/merge-k-sorted-lists
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Subject_23 {
    /**
     * 每次比较头节点的值，将最小值作为下一次递归的头节点
     * 每一次递归消耗O（K)时间，链表个数为K,最长链表的长度为N,所以总耗时O(K*N)
     * 空间复杂度O(1)
     * @param lists
     * @return
     */
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists.length == 0) return null;
        ListNode min = new ListNode(Integer.MAX_VALUE);
        int index = -1;
        for (int i = 0; i < lists.length; i++) {
            if (lists[i] != null && lists[i].val <= min.val) {
                min = lists[i];
                index = i;
            }
        }
        if (index == -1) return null;
        lists[index] = min.next;
        min.next = mergeKLists(lists);
        return min;
    }

    /**
     * 采用分治算法，类似与快速排序
     * 将问题分解成规模更小的问题，然后把结果集merge
     * 分解的过程类似回溯，这个merge的过程感觉是将回溯算法倒过来了
     *
     * 这里有两个变量，一个是链表的个数K，一个是所有链表的总结点数N
     * 性能分析：
     * 两个链表聚合确实发生了K-1次。但是注意，题解中把 K个链表两两聚合，生成K/2个链表的过程叫一次Merging。然后这样的Merging总共发生log(K)次。
     * 每一次Merging需要比较的次数是N。 所以总的时间复杂度是O(N*log(K))。 这才是两两聚合和逐一聚合的本质差别。
     * 逐一聚合的情况下，两个聚合的链表长度会发生偏斜，其中一个链表长度越来越长。考虑最坏情况K个链表每个仅包含一个元素（N为总元素数，这里N=K)，
     * 那么逐一聚合的总复杂度就是O(1+2+3+...N-1) = O(K*N). 而两两聚合的情况下，仍然考虑刚才的例子，
     *
     * 第一轮K个链表，聚合完成后剩K/2个，发生的比较次数是 1 + 1 + 1 + ...+ 1 =1*K = N.
     *
     * 第二轮K/2个链表，聚合完成后剩K/4个，发生的比较次数是(最坏情况) 2+2+2+ ... + 2 = 2 * K/2 = N .
     *
     * 第三轮K/4个链表，聚合完成后剩K/8个，发生的比较次数 4 + 4 + 4 + .... + 4 = 4 * K/4 = N .
     *
     * .....
     *
     * 最后一轮剩2个链表，比较次数 K/2 + K/2 = 2* K/2 = N .
     *
     * 总共有log(K)轮，总比较次数 N*log(K)
     * @param lists
     * @return
     */
    public ListNode mergeKLists2(ListNode[] lists) {
        if (lists.length == 0) return null;
        return split(lists, 0, lists.length-1);
    }

    /**
     * First tranverse left,right child node, then combine two results and return to the caller
     *
     * @param lists
     * @param left
     * @param right
     * @return
     */
    private ListNode split(ListNode[] lists, int left, int right) {
        if (right == left) {
            return lists[left];
        }
        int mid = (left + right) / 2;
        ListNode l1 = split(lists, left, mid);
        ListNode l2 = split(lists,mid+1,right);
        return merge(l1, l2);
    }

    private ListNode merge(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }
        if (l1.val < l2.val) {
            l1.next = merge(l1.next, l2);
            return l1;
        } else {
            l2.next = merge(l1, l2.next);
            return l2;
        }
    }

    public static void main(String[] args) {
        ListNode list1 = new ListNode(1);
        list1.append(new ListNode(4)).append(new ListNode(5));
        ListNode list2 = new ListNode(1);
        list2.append(new ListNode(3)).append(new ListNode(4));
        ListNode list3 = new ListNode(2);
        list3.append(new ListNode(6));
        Subject_23 demo = new Subject_23();
        demo.mergeKLists2(new ListNode[]{list1, list2,list3}).print();
    }
}
