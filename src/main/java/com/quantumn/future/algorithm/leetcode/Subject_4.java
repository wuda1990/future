package com.quantumn.future.algorithm.leetcode;
/**
 * 寻找两个有序数组的中位数
 * 给定两个大小为 m 和 n 的有序数组 nums1 和 nums2。
 *  请你找出这两个有序数组的中位数，并且要求算法的时间复杂度为 O(log(m + n))。
 * 你可以假设 nums1 和 nums2 不会同时为空。
 */

public class Subject_4 {
    /*
    暴力解法，归并排序，复杂度为O（m+n)
     */
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int size = nums1.length + nums2.length;
        int[] nums = new int[size];
        int left = 0;
        int right = 0;
        int i = 0;
        while (left < nums1.length && right < nums2.length) {
            if (nums1[left]<nums2[right]){
                nums[i++] = nums1[left++];
            }else {
                nums[i++] = nums2[right++];
            }
        }
        while (left < nums1.length) {
            nums[i++] = nums1[left++];
        }
        while (right < nums2.length) {
            nums[i++] = nums2[right++];
        }
        if (size % 2 != 0) {
            return nums[size/ 2];
        }else {
            return (nums[size/2]+nums[size/2-1])/2.0;
        }
    }

    public static void main(String[] args) {
        int[] nums1 = new int[]{1,3};
        int[] nums2 = new int[]{};
        Subject_4 demo = new Subject_4();
        System.out.println(demo.findMedianSortedArrays(nums1, nums2));
    }


}
