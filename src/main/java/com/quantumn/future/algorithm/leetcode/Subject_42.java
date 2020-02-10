package com.quantumn.future.algorithm.leetcode;

import java.util.Stack;

/**
 * 接雨水
 * 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
 * <p>
 * 上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的高度图，在这种情况下，可以接 6 个单位的雨水（蓝色部分表示雨水）。 感谢 Marcos 贡献此图。
 * <p>
 * 示例:
 * <p>
 * 输入: [0,1,0,2,1,0,1,3,2,1,2,1]
 * 输出: 6
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/trapping-rain-water
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */

/**
 * 数组的问题虽然看似简单，乍看一下，已知的条件很少，但我们其实可以知道数组的长度，最大值，最小值，平均值等
 * 这题第一个坎就是将实际问题建模，转化成数学问题，对数组中的每个数去计算面积，formula: Area = Min(max_left,max_right)-height[i]
 * 然后想办法求出每个元素的左边最大值和右边最大值
 */
public class Subject_42 {

    public int trap_review(int[] height) {
        int ans = 0;
        for (int i = 1; i < height.length - 1; i++) {
            int max_left = height[i];
            for (int j = 0; j < i; j++) {
                if (max_left < height[j]) {
                    max_left = height[j];
                }
            }
            int max_right = height[i];
            for (int j = i + 1; j < height.length; j++) {
                if (max_right < height[j]) {
                    max_right = height[j];
                }
            }
            ans += Math.min(max_left, max_right)-height[i];
        }
        return ans;
    }

    //对于每个数，都循环一遍查找max_left,max_right
    public int trap(int[] height) {
        int ans = 0;
        for (int i = 1; i < height.length - 1; i++) {
            //
            int max_left = height[i];
            for (int j = 0; j < i; j++) {
                if (max_left < height[j]) {
                    max_left = height[j];
                }
            }
            int max_right = height[i];
            for (int j = i + 1; j < height.length; j++) {
                if (max_right < height[j]) {
                    max_right = height[j];
                }
            }
            ans += Math.min(max_left, max_right)-height[i];
        }
        return ans;
    }

    //将每个数i对应的max_left,max_right用一次遍历算出并存在数组里，可以达到O(n)的时间复杂度
    //题解中称之为动态规划，不知道准不准确
    public int trap2(int[] height) {
        if(height.length==0) return 0;
        int[] max_left = new int[height.length];
        int[] max_right = new int[height.length];
        max_left[0] = height[0];
        for (int i = 1; i < height.length - 1; i++) {
            max_left[i] = Math.max(max_left[i - 1], height[i]);
        }
        max_right[height.length - 1] = height[height.length - 1];
        for (int i = height.length - 2; i > 0; i--) {
            max_right[i] = Math.max(max_right[i + 1], height[i]);
        }
        int ans = 0;
        for (int i = 1; i < height.length-1; i++) {
            ans += Math.min(max_left[i], max_right[i]) - height[i];
        }
        return ans;
    }

    //双指针法，理解如下公式是关键
    //left<right => max_left(0,left)<max_right(right,n-1)<max_right(left,n-1)
    //right<left => max_right(right,n-1)<max_left(0,left)<max_left(0,right)
    //2020/1/8 不能为了套二分法去做，要逻辑清晰，本题就是想办法求得数组每个节点左边的最大值和右边的最大值
    //trap3是trap2的另一种写法，通过双指针遍历数组，把求最小值的操作放在了比较左右指针值的时候了。但是求左边最大值仍然是公式max(max_left[i-1],height[i])
    public int trap3(int[] height) {
        if (height == null || height.length == 0) return 0;
        int ans = 0;
        int left = 0;
        int right = height.length - 1;
        int max_left = 0;
        int max_right = 0;
        while (left < right) {
            if (height[left] < height[right]) {
                //calculate the max_left
                max_left = Math.max(max_left, height[left]);
                ans += max_left - height[left];
                left++;
            } else {
                //calculate the max_right
                max_right = Math.max(max_right, height[right]);
                ans += max_right - height[right];
                right--;
            }
        }
        return ans;
    }

    //用栈的方法，但难点还是要得到如何计算存水的量，两堵墙取矮的墙计算矩形面积，但要去掉上一次计算的面积
    public int trap4(int[] height) {
        Stack<Integer> stack = new Stack<>();
        int ans = 0;
        int cur = 0;
        while (cur < height.length) {
            /**
             * 当前值大于栈顶的值时，将前一个值出栈，开始计算面积
             */
            while (!stack.isEmpty() && height[cur] > height[stack.peek()]) {
                int h = stack.pop();//当前出栈的元素就是当做谷底，计算能装水面积，栈顶元素是左面的墙，当前元素是右面的墙
                if (stack.isEmpty()) {
                    break;
                }
                //calculate the water capacity from stack.peek to cur
                int distance = cur - stack.peek() - 1;
                int min = Math.min(height[cur], height[stack.peek()]);
                //Here, height[h]  needs to be removed, which is the last capacity calculation result
                ans += distance * (min - height[h]);
            }
            stack.push(cur);
            cur++;
        }
        return ans;

    }

    public static void main(String[] args) {
        Subject_42 demo = new Subject_42();
        System.out.println(demo.trap2(new int[]{0,1,0,2,1,0,1,3,2,1,2,1}));
    }
}
