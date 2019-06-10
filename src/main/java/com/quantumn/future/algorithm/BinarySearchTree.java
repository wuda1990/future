package com.quantumn.future.algorithm;


import java.util.ArrayList;
import java.util.List;

// Definition for a binary tree node.
class TreeNode {
     int val;
     TreeNode left;
     TreeNode right;
     TreeNode(int x) { val = x; }
 }

public class BinarySearchTree {
    public int kthSmallest(TreeNode root, int k) {

        List<Integer> result = new ArrayList<Integer>();
        middleScan(root,result);
        return result.get(k);
    }

    public void middleScan(TreeNode root, List<Integer> result) {
        if (root.left != null) {
            middleScan(root.left, result);
        }
        result.add(root.val);
        if (root.right != null) {
            middleScan(root.right,result);
        }
    }
}
