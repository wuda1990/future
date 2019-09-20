package com.quantumn.future.algorithm.leetcode;

import java.util.*;

public class Subject46 {
    public static List<List<Integer>> permute(int[] nums) {
        return  myfunc(nums, null,0);
    }

    public static void main(String[] args) {
        permute(new int[]{1,2,3});
    }

    public static List<List<Integer>> myfunc(int[] nums, ArrayList<Integer> takedList,int index) {
        List<List<Integer>> ans = new ArrayList<List<Integer>>();
        if (takedList == null) {
            takedList = new ArrayList<Integer>();
        }

        if (index == nums.length) {
            System.out.println();
            takedList.forEach(m->{
                System.out.print(" "+m);
            });
            ans.add(takedList);

        }
//        for (int i=0;i<nums.length-takedSets.size();i++) {
            for (Integer num : nums) {
                if (!takedList.contains(num)) {
                    /**
                     * takedList是模拟组合中的拿黑盒子里的球
                     */
                    ArrayList<Integer> ownTakedList = new ArrayList<Integer>(takedList);
                    ownTakedList.add(num);
                    ans.addAll(myfunc(nums, ownTakedList,index+1));
                }
            }
//        }
        return ans;

    }


}
