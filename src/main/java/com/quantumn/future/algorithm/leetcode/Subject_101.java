package com.quantumn.future.algorithm.leetcode;

import com.quantumn.future.algorithm.leetcode.common.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 给定一个二叉树，检查它是否是镜像对称的。
 *
 * 例如，二叉树 [1,2,2,3,4,4,3] 是对称的。
 *
 *     1
 *    / \
 *   2   2
 *  / \ / \
 * 3  4 4  3
 *
 * 但是下面这个 [1,2,2,null,3,null,3] 则不是镜像对称的:
 *
 *     1
 *    / \
 *   2   2
 *    \   \
 *    3    3
 *
 * 说明:
 *
 * 如果你可以运用递归和迭代两种方法解决这个问题，会很加分。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/symmetric-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author: huajun.wu
 * @create: 2020-01-10
 **/
public class Subject_101 {
    public boolean isSymmetric(TreeNode root) {
        if(root==null) return true;
        return cmp(root.left,root.right);
    }

    private boolean cmp(TreeNode left, TreeNode right) {
        if (left == null || right == null) {
            return left==right;
        }
        if ( left.val != right.val) {
            return false;
        }
        if (!cmp(left.left, right.right)){
            return false;
        }
        if (!cmp(left.right, right.left)) {
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(2);
        root.left.left = new TreeNode(3);
//        root.left.right = new TreeNode(4);
//        root.right.left = new TreeNode(4);
        root.right.right = new TreeNode(5);
        Subject_101 demo = new Subject_101();
        System.out.println(demo.isSymmetric2(root));
    }

    //迭代的方法，用队列
    public boolean isSymmetric2(TreeNode root) {
        if(root==null) return true;
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.offer(root.left);
        queue.offer(root.right);
        while (!queue.isEmpty()) {
            TreeNode left = queue.poll();
            TreeNode right = queue.poll();
            if ((left == null || right == null)) {
                if (left != right) {
                    return false;
                } else {
                    continue;
                }
            }
            if ( left.val != right.val) {
                return false;
            }
            //add the children to the queue
            queue.offer(left.left);
            queue.offer(right.right);
            queue.offer(left.right);
            queue.offer(right.left);
        }
        return true;
    }
}

