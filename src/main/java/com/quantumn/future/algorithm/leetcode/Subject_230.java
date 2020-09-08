package com.quantumn.future.algorithm.leetcode;

import com.quantumn.future.algorithm.leetcode.common.TreeNode;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * 给定一个二叉搜索树，编写一个函数 kthSmallest 来查找其中第 k 个最小的元素。
 *
 * 说明：
 * 你可以假设 k 总是有效的，1 ≤ k ≤ 二叉搜索树元素个数。
 *
 * 示例 1:
 *
 * 输入: root = [3,1,4,null,2], k = 1
 *    3
 *   / \
 *  1   4
 *   \
 *    2
 * 输出: 1
 *
 * 示例 2:
 *
 * 输入: root = [5,3,6,2,4,null,null,1], k = 3
 *        5
 *       / \
 *      3   6
 *     / \
 *    2   4
 *   /
 *  1
 * 输出: 3
 *
 * 进阶：
 * 如果二叉搜索树经常被修改（插入/删除操作）并且你需要频繁地查找第 k 小的值，你将如何优化 kthSmallest 函数？
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/kth-smallest-element-in-a-bst
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author: huajun.wu
 * @create: 2020-01-14
 **/
public class Subject_230 {
    int order = 0;
    int ans = 0;
    public int kthSmallest(TreeNode root, int k) {
        if (root==null) return 0;
        dfs(root, k);
        return ans;
    }

    private void dfs(TreeNode root, int k) {
        if (root==null) return;
        if (ans!=0) return;//we already have an answer
        if (root.left != null) {
            dfs(root.left, k);
        }
        order++;
        if (order == k) {
            ans = root.val;
            return;
        }
        if (root.right != null) {
            dfs(root.right,k);
        }
    }

    //迭代的方法，用栈模拟递归
    public int kthSmallest2(TreeNode root, int k) {
        if (root==null) return 0;
        Stack<TreeNode> stack = new Stack<>();
        //和链表一样，这里顶一个当前指针cur是多么重要
        TreeNode cur = root;
        while (cur !=null || !stack.isEmpty()) {
            while (cur != null) {
                stack.push(cur);
                cur = cur.left;
            }
            TreeNode treeNode = stack.pop();
            if (--k == 0) {
                return treeNode.val;
            }
            if (treeNode.right != null) {
                cur = treeNode.right;
            }
        }
        return 0;
    }

    //分治法
    public int kthSmallest3(TreeNode root, int k) {
        int n = nodeCount(root.left);
        if(n + 1 == k) {
            return root.val;
        } else if (n + 1 < k) {
            return kthSmallest3(root.right, k - n - 1);
        } else {
            return kthSmallest3(root.left, k);
        }
    }

    private int nodeCount(TreeNode root) {
        if(root == null) {
            return 0;
        }
        return 1 + nodeCount(root.left) + nodeCount(root.right);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(3);
        root.right = new TreeNode(7);
        root.left.left = new TreeNode(2);
        root.left.right = new TreeNode(4);
        root.left.left.left = new TreeNode(1);
        Subject_230 demo = new Subject_230();
        System.out.println(demo.kthSmallest2(root,6));

    }
}
