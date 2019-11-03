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
        int[] nums1 = new int[]{1,2};
        int[] nums2 = new int[]{3,4};
        Subject_4 demo = new Subject_4();
        System.out.println(demo.findMedianSortedArrays2(nums1, nums2));
    }

    //扁扁熊的题解
    //这题的难度在于抽象，理解了题目的中位数后，即是在数组中间的一个或两个数
    //这里为了不考虑数组大小为偶数或奇数的问题，虚构出一个数组，在每个数前面和后面加"#"
    //m, n, 2m+2n+2,则k = (2m+2n+2)/2 = m+n+1; 求m+n,m+n+1位置的元素即可
    public double findMedianSortedArrays2(int[] nums1, int[] nums2) {
        int n = nums1.length;
        int m = nums2.length;
        if (n > m) {
            return findMedianSortedArrays2(nums2, nums1);
        }
        //遍历较短的数组，进行二分查找
        int low = 0;
        int high = 2*n;
        int lmax1 = 0,rmin1 = 0,lmax2 = 0,rmin2 = 0;
        while (low <= high) {
            int c1 = (low + high) / 2;
            int c2 = m + n - c1;

            lmax1 = (c1 == 0) ? Integer.MIN_VALUE : nums1[(c1 - 1) / 2];
            rmin1 = (c1 == 2 * n) ? Integer.MAX_VALUE : nums1[c1 / 2];
            lmax2 = (c2 == 0) ?Integer.MIN_VALUE:nums2[(c2-1)/2];
            rmin2 = (c2 == 2 * m) ? Integer.MAX_VALUE : nums2[c2 / 2];
            if (lmax1 > rmin2) {
                high = c1 - 1;
            } else if (lmax2 > rmin1) {
                low = c1 + 1;
            } else {
                break;
            }
        }
        return (Math.max(lmax1, lmax2) + Math.min(rmin1, rmin2))/2.0;

    }


}
