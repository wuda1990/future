package com.quantumn.future.algorithm.leetcode;

/**
 * 给定一个排序数组，你需要在原地删除重复出现的元素，使得每个元素只出现一次，返回移除后数组的新长度。
 *
 * 不要使用额外的数组空间，你必须在原地修改输入数组并在使用 O(1) 额外空间的条件下完成。
 *
 * 示例 1:
 *
 * 给定数组 nums = [1,1,2],
 *
 * 函数应该返回新的长度 2, 并且原数组 nums 的前两个元素被修改为 1, 2。
 *
 * 你不需要考虑数组中超出新长度后面的元素。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/remove-duplicates-from-sorted-array
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Subject_26 {
    /**
     * 双指针算法，最好用画图的方法，才能清楚指针i，j指针是如何在数组上移动的
     * @param nums
     * @return
     */
    public int removeDuplicates(int[] nums) {
        if (nums.length == 0) return 0;
        int j = 1;
        for (int i = 0; i < nums.length-1; i++) {
            if (nums[i] != nums[i + 1]) {
                nums[j++] = nums[i+1];
            }
        }
        return j;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1,2};
        Subject_26 demo = new Subject_26();
        int len = demo.removeDuplicates(nums);
        for (int i = 0; i < len; i++) {
            System.out.print(nums[i]+",");
        }
    }
}
