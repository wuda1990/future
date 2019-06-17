package com.quantumn.future.algorithm;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BinaryTree {
    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftDepth = maxDepth(root.left);
        int rightDepth = maxDepth(root.right);
        return Math.max(leftDepth,rightDepth) + 1;
    }

    class TreeLevelNode {
        TreeNode node;
        Integer level;

        public TreeLevelNode(TreeNode node, Integer level) {
            this.node = node;
            this.level = level;
        }

        public TreeNode getNode() {
            return node;
        }

        public void setNode(TreeNode node) {
            this.node = node;
        }

        public Integer getLevel() {
            return level;
        }

        public void setLevel(Integer level) {
            this.level = level;
        }
    }
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        Queue<TreeLevelNode> queue = new LinkedList<>();

        if (root != null) {
            queue.offer(new TreeLevelNode(root,0));
        }
        while (!queue.isEmpty()) {
            TreeLevelNode pair = queue.poll();
            TreeNode node = pair.getNode();
            int level = pair.getLevel();
            List<Integer> subList = null;
            if (result.size()<=level) {
                subList = new ArrayList<>();
                result.add(subList);
            }else {
                subList = result.get(level);
            }
            subList.add(node.val);

            if (node.left == null && node.right == null) {
                continue;
            };
            if (node.left != null) {
                queue.offer(new TreeLevelNode(node.left,level+1));
            }
            if (node.right != null) {
                queue.offer(new TreeLevelNode(node.right,level+1));
            }
        }
        return result;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.left.left = new TreeNode(4);
        root.left.right = null;
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);
        BinaryTree binaryTree = new BinaryTree();
//        System.out.println("result:"+binaryTree.maxDepth(root));
        List<List<Integer>> result = binaryTree.levelOrder(root);
        System.out.println("ok");
    }
}
