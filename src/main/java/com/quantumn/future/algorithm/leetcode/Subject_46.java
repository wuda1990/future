package com.quantumn.future.algorithm.leetcode;

import java.util.*;

/**
 * 全排列
 * 给定一个没有重复数字的序列，返回其所有可能的全排列。
 *
 * 示例:
 *
 * 输入: [1,2,3]
 * 输出:
 * [
 *   [1,2,3],
 *   [1,3,2],
 *   [2,1,3],
 *   [2,3,1],
 *   [3,1,2],
 *   [3,2,1]
 * ]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/permutations
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Subject_46 {
    public  List<List<Integer>> permute(int[] nums) {
        return  myfunc(nums, null,0);
    }

    public  List<List<Integer>> myfunc(int[] nums, ArrayList<Integer> takedList,int index) {
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

        for (Integer num : nums) {
            if (!takedList.contains(num)) {
                /**
                 * takedList是模拟组合中的拿黑盒子里的球
                 * 每次定义新的list到下一次递归
                 */
                ArrayList<Integer> ownTakedList = new ArrayList<Integer>(takedList);
                ownTakedList.add(num);
                ans.addAll(myfunc(nums, ownTakedList,index+1));
            }
        }
        return ans;
    }

    public List<List<Integer>> permute2(int[] nums) {
        //list本来就是带有顺序的啊
        List<List<Integer>> res = new ArrayList<>();
        ArrayList list = new ArrayList<Integer>();
        permute2(res,list,nums,0);
        return res;
    }
    public void permute2(List<List<Integer>> res,ArrayList<Integer> list,int []nums,int index) {
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
                    permute2(res,list,nums,index+1);
                    list.remove(list.size() - 1);
                }
            }
        }
    }

    public static void main(String[] args) {
        Subject_46 demo = new Subject_46();
        demo.permute2(new int[]{1,2,3});
    }
}
