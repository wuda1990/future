package com.quantumn.future.algorithm.leetcode;

/**
 * 编写一个函数来查找字符串数组中的最长公共前缀。
 *
 * 如果不存在公共前缀，返回空字符串 ""。
 */
public class Subject_14 {
    public String longestCommonPrefix(String[] strs) {
        StringBuilder ans = new StringBuilder();
        int index = 0;
        if (strs.length == 0) {
            return "";
        }
        while (true) {
            if (index == strs[0].length()) {
                return ans.toString();
            }
            char first_str_character = strs[0].charAt(index);
            for (int i = 1; i < strs.length; i++) {
                if (index == strs[i].length() || first_str_character != strs[i].charAt(index)) {
                    return ans.toString();
                }
            }
            ans.append(first_str_character);
            index++;
        }
    }

    public static void main(String[] args) {
        Subject_14 demo = new Subject_14();
        System.out.println(demo.longestCommonPrefix(new String[]{"flower","fly"}));
    }
}
