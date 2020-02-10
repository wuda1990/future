package com.quantumn.future.algorithm;

import com.quantumn.future.algorithm.leetcode.common.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

public class SymmetricTree {
    Queue<Integer> dataQueue = new LinkedList<>();
    private boolean isSymmetric = true;
    public boolean isSymmetric(TreeNode root) {
        tranverse(root);
        System.out.println();
        reverse_tranverse(root);
        return isSymmetric;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(2);
        root.left.left = new TreeNode(2);
        root.left.right = null;
        root.right.left = new TreeNode(2);
        root.right.right = null;
        SymmetricTree symmetricTree = new SymmetricTree();
        System.out.println("result:"+symmetricTree.isSymmetric(root));
    }

    void tranverse(TreeNode root) {
        if (root == null) {
            dataQueue.offer(null);
            System.out.print("nil,");
            return;
        }
        System.out.print(root.val+",");
        dataQueue.offer(root.val);
        tranverse(root.left);
        tranverse(root.right);
    }

    void reverse_tranverse(TreeNode root) {
        if (isSymmetric == false) {
            return ;
        }
        if (root==null){
            System.out.print("nil,");
            if(dataQueue.poll()!=null){
                isSymmetric = false;
            }
            return;
        }
        Integer k = dataQueue.poll();
        System.out.print(root.val+",");
        if (k == null || root.val != k) {
            isSymmetric = false;
            return;
        }
        reverse_tranverse(root.right);
        reverse_tranverse(root.left);
    }
}
