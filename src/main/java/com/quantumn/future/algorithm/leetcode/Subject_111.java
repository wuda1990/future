package com.quantumn.future.algorithm.leetcode;

import com.quantumn.future.algorithm.leetcode.common.TreeNode;

/**
 * 给定一个二叉树，找出其最小深度。
 *
 * 最小深度是从根节点到最近叶子节点的最短路径上的节点数量。
 *
 * 说明: 叶子节点是指没有子节点的节点。
 *
 * 示例:
 *
 * 给定二叉树 [3,9,20,null,null,15,7],
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 *
 * 返回它的最小深度  2.
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/minimum-depth-of-binary-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author: huajun.wu
 * @create: 2020-01-10
 **/
public class Subject_111  {
    public int minDepth(TreeNode root) {
        if (root==null) return 0;
        int left_depth = minDepth(root.left);
        int right_depth = minDepth(root.right);
        if (left_depth != right_depth) {
            if(left_depth==0) return right_depth+1;
            if(right_depth==0) return left_depth+1;
        }
        return Math.min(left_depth,right_depth)+1;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);
        Subject_111 demo = new Subject_111();
        System.out.println(demo.minDepth(root));
    }
}
