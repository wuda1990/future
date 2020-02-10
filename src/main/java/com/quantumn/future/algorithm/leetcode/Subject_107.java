package com.quantumn.future.algorithm.leetcode;

import com.quantumn.future.algorithm.leetcode.common.TreeNode;

import java.util.*;

/**
 * 给定一个二叉树，返回其节点值自底向上的层次遍历。 （即按从叶子节点所在层到根节点所在的层，逐层从左向右遍历）
 *
 * 例如：
 * 给定二叉树 [3,9,20,null,null,15,7],
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 *
 * 返回其自底向上的层次遍历为：
 *
 * [
 *   [15,7],
 *   [9,20],
 *   [3]
 * ]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/binary-tree-level-order-traversal-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author: huajun.wu
 * @create: 2020-01-13
 **/
public class Subject_107 {
    //dfs,参考Subject_102的例子做一些微调
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> ans = new ArrayList();
        if (root==null) return ans;
        dfs(root,0,ans);
        Collections.reverse(ans);
        return ans;
    }


    private void dfs(TreeNode root, int level, List<List<Integer>> ans) {
        if(root==null) return;
        if (ans.size() == level) {
            ans.add(0,new ArrayList<Integer>());
        }
        //广度优先和深度优先顺序是不一样的，所以这里不能总取索引为0的数组
        ans.get(ans.size()-1-level).add(root.val);
        dfs(root.left,level+1, ans);
        dfs(root.right,level+1, ans);
    }

    //迭代，广度优先遍历
    public List<List<Integer>> levelOrderBottom2(TreeNode root) {
        List<List<Integer>> ans = new ArrayList();
        if (root==null) return ans;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int level = 0;
        while (!queue.isEmpty()) {
            if (ans.size() == level) {
                ans.add(0,new ArrayList<>());
            }
            List<Integer> list = ans.get(0);
            int m = queue.size();
            for (int i = 0; i < m; i++) {
                TreeNode node = queue.poll();
                list.add(node.val);
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            level++;
        }
        return ans;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        Subject_107 demo = new Subject_107();
        List<List<Integer>> ans = demo.levelOrderBottom(root);
        for (List<Integer> list : ans) {
            for (Integer val : list) {
                System.out.print(val+",");
            }
            System.out.println();
        }
    }
}
