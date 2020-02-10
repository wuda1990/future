package com.quantumn.future.algorithm.leetcode;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Objects;

/**
 * 两数之和
 * 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
 *
 * 你可以假设每种输入只会对应一个答案。但是，你不能重复利用这个数组中同样的元素。
 *
 * 给定 nums = [2, 7, 11, 15], target = 9
 *
 * 因为 nums[0] + nums[1] = 2 + 7 = 9
 * 所以返回 [0, 1]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/two-sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Subject_1 {
    //暴力解法
    public int[] twoSum(int[] nums, int target) {
        int[] result=new int[2];
        for(int i=0;i<nums.length;i++){
            for(int j=i+1;j<nums.length;j++){
                if(nums[i]+nums[j]==target){
                    result[0]=i;
                    result[1]=j;
                    return result;
                }
            }
        }
        return null;
    }
    //Todo, 题解中用HashMap的方法
    public int[] twoSumByHashMap(int[] nums, int target) {
        HashMap<Integer, Integer> map = new HashMap();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target - nums[i])) {
                return new int[]{map.get(target - nums[i]), i};
            }
            map.put(nums[i], i);
        }
        return null;
    }

    //Todo
    //先排序，再用双指针
    //排序之后，原数组的顺序打乱了，所以需要copy一份原数组
    public int[] twoSumByDoublePtr(int[] nums, int target) {
        int[] tmp = new int[nums.length];
        System.arraycopy(nums,0,tmp,0,nums.length);
        Arrays.sort(nums);
        int left = 0;
        int right = nums.length-1;
        while (left < right) {
            if (nums[left] + nums[right] == target) {
                break;
            } else if (nums[left] + nums[right] < target) {
                left++;
            } else {
                right--;
            }
        }
//        System.out.println("left:" + left + "right:" + right);
        int[] ans = new int[2];
        for (int i = 0; i < tmp.length; i++) {
            if (tmp[i] == nums[left]) {
                ans[0] = i;
                break;
            }
        }
        for (int i = 0; i < tmp.length; i++) {
            if (tmp[i] == nums[right] && i!=ans[0]) {
                ans[1] = i;
                break;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        Subject_1 demo = new Subject_1();
        int[] ans = demo.twoSumByHashMap(new int[]{ 5,5}, 11);
        if (ans!=null)
            Arrays.stream(ans).filter(Objects::nonNull).forEach(value -> System.out.print(value+","));
    }
}
