package com.quantumn.future.algorithm.leetcode;

/**
 * 最长公共前缀
 * 编写一个函数来查找字符串数组中的最长公共前缀。
 * 如果不存在公共前缀，返回空字符串 ""。
 *
 * 输入: ["flower","flow","flight"]
 * 输出: "fl"
 *
 * 输入: ["dog","racecar","car"]
 * 输出: ""
 */
public class Subject_14 {
    //简单的遍历，但要考虑到每个str的长度是不一样的
    public String longestCommonPrefix(String[] strs) {
        StringBuilder ans = new StringBuilder();
        int str_len = 0; //表示前缀字符串的长度
        if (strs.length == 0) {
            return "";
        }
        while (true) {
            if (str_len == strs[0].length()) {
                return ans.toString();
            }
            char first_str_character = strs[0].charAt(str_len);
            for (int i = 1; i < strs.length; i++) {
                if (str_len == strs[i].length() || first_str_character != strs[i].charAt(str_len)) {
                    return ans.toString();
                }
            }
            ans.append(first_str_character);
            str_len++;
        }
    }

    public static void main(String[] args) {
        Subject_14 demo = new Subject_14();
        System.out.println(demo.longestCommonPrefix(new String[]{"flower","fly"}));
    }
}
