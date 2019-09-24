package com.quantumn.future.algorithm.leetcode;

/**
 * 给定 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点 (i, ai) 。
 * 在坐标内画 n 条垂直线，垂直线 i 的两个端点分别为 (i, ai) 和 (i, 0)。
 * 找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
 *
 * 说明：你不能倾斜容器，且 n 的值至少为 2。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/container-with-most-water
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Subject_11 {
    public int maxArea(int[] height) {
        int n = height.length;
        int max_area = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i+1; j < n; j++) {
                int min_height = Math.min(height[i],height[j]);
                int value = min_height*(j-i);
                max_area = Math.max(max_area,value);
            }
        }
        return max_area;
    }

    /**
     * 双指针法，
     * @param args
     */
    public int maxArea2(int [] height){
        int i=0; int j= height.length-1;
        int max_area = 0;
        while (i<j){
            int distance = j-i;
            int min_height;
            if (height[i]<height[j]){
                min_height = height[i++];
            }else {
                min_height = height[j--];
            }
            max_area = Math.max(max_area, min_height * distance);
        }
        return max_area;
    }

    public static void main(String[] args) {
        Subject_11 demo = new Subject_11();
        System.out.println(demo.maxArea2(new int[]{1,8,6,2,5,4,8,3,7}));
    }
}
