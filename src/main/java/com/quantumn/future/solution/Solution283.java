package com.quantumn.future.solution;

public class Solution283 {

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
        Solution283 solution283 = new Solution283();
        int[] test = new int[]{0,1,0,3,12};
        solution283.moveZeroes2(test);
        for (int t : test) {
            System.out.print(t+" ");
        }

    }
}
