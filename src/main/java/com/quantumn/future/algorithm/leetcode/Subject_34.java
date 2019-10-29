package com.quantumn.future.algorithm.leetcode;

/**
 * 在排序数组中查找元素的第一个和最后一个位置
 * 给定一个按照升序排列的整数数组 nums，和一个目标值 target。找出给定目标值在数组中的开始位置和结束位置。
 *
 * 你的算法时间复杂度必须是 O(log n) 级别。
 *
 * 如果数组中不存在目标值，返回 [-1, -1]。
 *
 * 示例 1:
 *
 * 输入: nums = [5,7,7,8,8,10], target = 8
 * 输出: [3,4]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/find-first-and-last-position-of-element-in-sorted-array
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Subject_34 {
    public int[] searchRange(int[] nums, int target) {
        return search(nums, 0, nums.length - 1, target);
    }

    private int[] search(int[] nums, int left, int right, int target) {
        if (right < left) {
            return new int[]{-1, -1};
        }
        int mid = (left + right) / 2;
        if (target == nums[mid]) {
            return getRange(nums, target, mid, left, right);
        }
        if (target > nums[mid]) {
            return search(nums, mid + 1, right, target);
        } else {
            return search(nums, left, mid - 1, target);
        }
    }

    //找到某个等于target的位置，分别向左和向右找到边界.
    private int[] getRange(int[] nums ,int target,int mid, int left, int right){
        int[] ans = new int[]{mid,mid};
        //获取左边界位置
        int low = left;
        int high = mid-1;

        while (low <= high) {
            int middle = (low + high) / 2;
            //middle值等于target,则继续像左半边查找，否则向右半边查找
            if (target == nums[middle]) {
                ans[0] = middle;
                high = middle-1;
            }else {
                low = middle + 1;
            }
        }

        //获取右边界位置
        low = mid + 1;
        high = right;
        while (low <= high) {
            int middle = (low + high) / 2;
            //middle值等于target,则继续像右半边查找，否则向左半边查找
            if (target == nums[middle]) {
                ans[1] = middle;
                low = middle + 1;
            }else {
                high = middle - 1;
            }
        }

        return ans;
    }


    public static void main(String[] args) {
        Subject_34 demo = new Subject_34();
        int[] ans = demo.searchRange(new int[]{1,1,1,2,4,4,4,5,5,5,5,6,7,8,8,9,9,9,9,9,9,10},9);
        System.out.println(ans[0]+","+ans[1]);
    }
}
