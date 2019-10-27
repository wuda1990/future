package com.quantumn.future.algorithm.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *给定一个包括 n 个整数的数组 nums 和 一个目标值 target。找出 nums 中的三个整数，使得它们的和与 target 最接近。返回这三个数的和。假定每组输入只存在唯一答案。
 *
 * 例如，给定数组 nums = [-1，2，1，-4], 和 target = 1.
 *
 * 与 target 最接近的三个数的和为 2. (-1 + 2 + 1 = 2).
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/3sum-closest
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Subject_16 {
    int min = Integer.MAX_VALUE;
    int sum = 0;
    int ans = 0;
    //失败的全部遍历，用这种递归形式的遍历是全排列组合，去重是很耗时间的
    public int threeSumClosest(int[] nums, int target) {
        if (nums.length < 3) {
            return 0;
        }
        func(nums, target, 0,0, new ArrayList<>());
        return sum;
    }

    void func(int[] nums, int target, int depth, int index, List<Integer> list) {
        if (depth == 3) {
            //add the  three numbers
            sum = nums[list.get(0)] + nums[list.get(1)] + nums[list.get(2)];
            int diff = Math.abs(sum - target);
            if (diff < min) {
                min = diff;
                ans = sum;
            }
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            //add the index
            if (!list.contains(i)) {
                list.add(i);
                func(nums, target, depth + 1, index + 1, list);
                list.remove(list.size() - 1);
            }

        }
    }

    //评论区的双指针解法
    //面对这种比大小的题目，有序数组的双指针是个很好的办法
    //不要再用全部遍历这种方法了，把所有三个数的组合都列出来是种蠢办法
    public int threeSumClosest2(int[] nums, int target) {
        Arrays.sort(nums);
        int left;
        int right;
        int ans = nums[0] + nums[1] + nums[2];
        for (int i = 0; i < nums.length - 1; i++) {
            left = i+1; right = nums.length-1;
            while (left < right) {
                int sum = nums[left] + nums[i] + nums[right];
                if (Math.abs(sum - target) < Math.abs(ans - target)) {
                    ans = sum;
                }
                if (sum < target) {
                    left++;
                } else if (sum > target) {
                    right--;
                } else {
                    return ans  ;
                }
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        Subject_16 demo = new Subject_16();
        System.out.println(demo.threeSumClosest2(new int[]{1,2,4,8,16,32,64,128},82));
//        System.out.println(demo.threeSumClosest(new int[]{1,1,-1},0));
//        System.out.println(demo.threeSumClosest(new int[]{1,1,1,0},-100));
    }
}
