package com.quantumn.future.algorithm.leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 给定一个非负整数 numRows，生成杨辉三角的前 numRows 行。
 *
 * 在杨辉三角中，每个数是它左上方和右上方的数的和。
 * @author: huajun.wu
 * @create: 2020-01-14
 **/
public class Subject_118 {
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> ans = new ArrayList<>();
        for (int i = 0; i < numRows; i++) {
            if (ans.size() == i) {
                ans.add(new ArrayList<>());
            }
            ans.get(i).add(1);
            for (int j = 1; j < i; j++) {
                int sum = ans.get(i - 1).get(j - 1) + ans.get(i - 1).get(j);
                ans.get(i).add(sum);
            }
            if (i >= 1) {
                ans.get(i).add(1);
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        Subject_118 demo = new Subject_118();
        List<List<Integer>> ans = demo.generate(6);
        for (List<Integer> list : ans) {
            for (Integer t : list) {
                System.out.print(t+",");
            }
            System.out.println();
        }
    }
}
