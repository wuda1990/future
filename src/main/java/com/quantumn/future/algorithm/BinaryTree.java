package com.quantumn.future.algorithm;

import javafx.util.Pair;

import java.util.*;

public class BinaryTree {
    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftDepth = maxDepth(root.left);
        int rightDepth = maxDepth(root.right);
        return Math.max(leftDepth, rightDepth) + 1;
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
            queue.offer(new TreeLevelNode(root, 0));
        }
        while (!queue.isEmpty()) {
            TreeLevelNode pair = queue.poll();
            TreeNode node = pair.getNode();
            int level = pair.getLevel();
            List<Integer> subList = null;
            if (result.size() <= level) {
                subList = new ArrayList<>();
                result.add(subList);
            } else {
                subList = result.get(level);
            }
            subList.add(node.val);

            if (node.left == null && node.right == null) {
                continue;
            }
            ;
            if (node.left != null) {
                queue.offer(new TreeLevelNode(node.left, level + 1));
            }
            if (node.right != null) {
                queue.offer(new TreeLevelNode(node.right, level + 1));
            }
        }
        return result;
    }

    ArrayList<List<Integer>> resultList = new ArrayList<>();
    SortedMap<Integer, List<Integer>> resultMap = new TreeMap<>();
    int maxLevel = 0;

    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        dfs(root, 0);
        resultList = new ArrayList<>(resultMap.values());
        return resultList;

    }

    void dfs(TreeNode root, int level) {
        if (root == null) {
            return;
        }
        dfs(root.left, level + 1);
        dfs(root.right, level + 1);

        if (level > maxLevel) {
            //update the map index
            SortedMap<Integer, List<Integer>> tempResultMap = new TreeMap<>();
            for (Map.Entry<Integer, List<Integer>> entry : resultMap.entrySet()) {
                tempResultMap.put(entry.getKey() + level - maxLevel, entry.getValue());
            }
            resultMap = tempResultMap;
            List<Integer> subList = new ArrayList<>();
            subList.add(root.val);
            resultMap.put(0,subList);
            maxLevel = level;
        } else {
            int index = maxLevel - level;
            if (resultMap.get(index) == null) {
                List<Integer> subList = new ArrayList<>();
                subList.add(root.val);
                resultMap.put(index, subList);
            } else {
                resultMap.get(index).add(root.val);
            }
        }
    }

    public List<List<Integer>> levelOrderBottom2(TreeNode root) {
        List<List<Integer>> resultList = new ArrayList<>();
        helper(root, 0, resultList);
        Collections.reverse(resultList);
        return resultList;
    }

    private void helper(TreeNode root, int level, List<List<Integer>> resultList) {
        if (root == null) {
            return;
        }
        if (resultList.size() == level) {
            resultList.add(new ArrayList<>());
        }
        resultList.get(level).add(root.val);
        if (root.left != null) {
            helper(root.left, level + 1, resultList);
        }
        if (root.right != null) {
            helper(root.right, level + 1, resultList);
        }
    }

    public List<List<Integer>> levelOrderByQueue(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        if (root != null) {
            queue.offer(root);
        }
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> subList = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                subList.add(node.val);
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            res.add(subList);
        }
        Collections.reverse(res);
        return res;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(7);
        root.left = new TreeNode(-7);
        root.right = new TreeNode(8);
        root.left.left = null;
        root.left.right = null;
        root.right.left = new TreeNode(-3);
        root.right.right = new TreeNode(6);
        root.right.left.right = new TreeNode(9);
        root.right.left.right.right = new TreeNode(-5);
        BinaryTree binaryTree = new BinaryTree();
//        System.out.println("result:"+binaryTree.maxDepth(root));
        List<List<Integer>> result = binaryTree.levelOrderByQueue(root);
        System.out.println("ok");
    }
}
