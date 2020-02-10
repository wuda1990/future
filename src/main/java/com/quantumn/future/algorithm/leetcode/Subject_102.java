package com.quantumn.future.algorithm.leetcode;

import com.quantumn.future.algorithm.leetcode.common.TreeNode;
import lombok.val;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 给定一个二叉树，返回其按层次遍历的节点值。 （即逐层地，从左到右访问所有节点）。
 *
 * 例如:
 * 给定二叉树: [3,9,20,null,null,15,7],
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 *
 * 返回其层次遍历结果：
 *
 * [
 *   [3],
 *   [9,20],
 *   [15,7]
 * ]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/binary-tree-level-order-traversal
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author: huajun.wu
 * @create: 2020-01-10
 **/
public class Subject_102 {
    //迭代，广度优先遍历
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> ans = new ArrayList();
        if (root==null) return ans;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int level = 0;
        while (!queue.isEmpty()) {
            List<Integer> list = null;
            //new level,new list
            if (ans.size() == level) {
                list = new ArrayList();
                ans.add(list);
            } else {
                list = ans.get(level);
            }
            //求当前队列的长度，遍历当前层级的节点，这里是重点，要考的
            int m = queue.size();
            for(int i=0;i<m;i++){
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

    //dfs,深度优先遍历
    public List<List<Integer>> levelOrder2(TreeNode root) {
        List<List<Integer>> ans = new ArrayList();
        if (root==null) return ans;
        dfs(root, 0,ans);
        return ans;
    }

    private void dfs(TreeNode root, int level, List<List<Integer>> ans) {
        if (root==null) return;
        if (ans.size() == level) {
            ans.add(new ArrayList<Integer>());
        }
        ans.get(level).add(root.val);
        dfs(root.left, level + 1, ans);
        dfs(root.right, level + 1, ans);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);
        Subject_102 demo = new Subject_102();
        List<List<Integer>> ans = demo.levelOrder2(root);
        for (List<Integer> list : ans) {
            for (Integer val : list) {
                System.out.print(val+",");
            }
            System.out.println();
        }
    }
}
