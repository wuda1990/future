package com.quantumn.future.algorithm.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 给定一个非负索引 k，其中 k ≤ 33，返回杨辉三角的第 k 行。
 * 示例:
 *
 * 输入: 3
 * 输出: [1,3,3,1]
 *
 * 进阶：
 *
 * 你可以优化你的算法到 O(k) 空间复杂度吗？
 * @author: huajun.wu
 * @create: 2020-01-14
 **/
public class Subject_119 {

    public List<Integer> getRow(int rowIndex) {
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i <= rowIndex; i++) {
            if (i == 0) {
                ans.add(1);
            }
            int tmp = 1;
            for (int j = 1; j < i; j++) {
                int sum = tmp + ans.get(j);
                tmp = ans.get(j);
                ans.set(j, sum);
            }
            if (i >= 1) {
                ans.add(1);
            }
        }
        return ans;
    }

    //这个就是利用滚动数组优化动态规划的思路
    public List<Integer> getRow2(int rowIndex) {
        Integer[] dp = new Integer[rowIndex + 1];
        Arrays.fill(dp,1);
        for(int i = 2;i < dp.length;i++){
            for(int j = i - 1;j > 0;j--)
                dp[j] = dp[j] + dp[j - 1];
        }
        List<Integer> res = Arrays.asList(dp);
        return res;
    }

    //优化方法1
    public List<Integer> getRow3(int rowIndex) {
        Integer[] dp = new Integer[rowIndex + 1];
        Arrays.fill(dp,1);
        for (int i = 0; i <= rowIndex; i++) {
            int tmp = 1;
            for (int j = 1; j < i; j++) {
                int sum = tmp + dp[j];
                tmp = dp[j];
                dp[j] = sum;
            }
        }
        return Arrays.asList(dp);
    }

    public static void main(String[] args) {
        Subject_119 demo = new Subject_119();
        List<Integer> ans = demo.getRow2(4);
        for (Integer t : ans) {
            System.out.print(t+",");
        }
    }
}
