package com.quantumn.future.algorithm.leetcode;

import java.util.ArrayList;
import java.util.List;

public class Subject46_1 {
    public static void main(String[] args) {
        List<List<Integer>> result =new  Subject46_1().permute(new int[]{1, 2, 3});

    }

    public List<List<Integer>> permute(int[] nums) {
        //list本来就是带有顺序的啊
        List<List<Integer>> res = new ArrayList<>();
        ArrayList list = new ArrayList<Integer>();
        permute(res,list,nums,0);
        return res;
    }
    public void permute(List<List<Integer>> res,ArrayList<Integer> list,int []nums,int index) {
        if(index == nums.length){
            ArrayList perList = new ArrayList<>();
            perList.addAll(list);
            res.add(perList);
            return;
        }else{
            for(int i = 0;i < nums.length;i++){
                if(!list.contains(nums[i])){
                    /**
                     * list作为计算中的缓存，先增加，再删除，精髓部分
                     */
                    list.add(nums[i]);
                    permute(res,list,nums,index+1);
                    list.remove(list.size() - 1);
                }
            }
        }
    }
}
