package com.quantumn.future.algorithm.leetcode;

/**
 * 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
 *
 * 示例:
 *
 * 输入: [0,1,0,3,12]
 * 输出: [1,3,12,0,0]
 *
 * 说明:
 *
 *     必须在原数组上操作，不能拷贝额外的数组。
 *     尽量减少操作次数。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/move-zeroes
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Subject_283 {

    public void moveZeroes(int[] nums) {

        for (int i = 0; i < nums.length; i++) {
            int k=i;
            while (k<nums.length && nums[k] == 0) {
                k++;
            }
            if(k==nums.length) return;
            nums[i] = nums[k];
            if(i!=k){
                nums[k] = 0;
            }
        }
    }

    public void moveZeroes2(int[] nums) {
        int i=0;
        for (int j = 0; j < nums.length; j++) {
            if (nums[j] != 0) {
                nums[i] = nums[j];
                i++;
            }
        }
        for (int p = i; p < nums.length; p++) {
            nums[p]=0;
        }
    }

    public static void main(String[] args) {
        Subject_283 solution283 = new Subject_283();
        int[] test = new int[]{0,1,0,3,12};
        solution283.moveZeroes2(test); 
        for (int t : test) {
            System.out.print(t+" ");
        }

    }
}
