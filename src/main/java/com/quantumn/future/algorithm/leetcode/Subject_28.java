package com.quantumn.future.algorithm.leetcode;

/**
 * 实现 strStr() 函数。
 *
 * 给定一个 haystack 字符串和一个 needle 字符串，在 haystack 字符串中找出 needle 字符串出现的第一个位置 (从0开始)。
 * 如果不存在，则返回  -1。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/implement-strstr
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Subject_28 {
    //执行用时 :4 ms, 在所有 Java 提交中击败了33.51% 的用户
    public int strStr(String haystack, String needle) {
        if(needle.length() == 0) return 0;
        for (int i = 0; i < haystack.length()-needle.length()+1; i++) {
            int j = i;//haystack字符串指针
            int k = 0;//needle字符串指针
            while (j < haystack.length() && k < needle.length()) {
                if (haystack.charAt(j) != needle.charAt(k)) {
                    break;
                }
                j++;k++;
            }
            if (k == needle.length()) {
                return i;
            }
        }
        return -1;
    }

    //用subString()函数
    public int strStr2(String haystack, String needle) {
        if (needle.length() == 0) return 0;
        if (haystack.length()<needle.length()) return -1;
        int start = 0;
        int end = needle.length()-1;
        while (end < haystack.length()) {
            if (haystack.substring(start, end + 1).equals(needle)) {
                return start;
            }
            start++;
            end++;
        }
        return -1;
    }

    //Todo
    //题解里有用KMP,有用Sunday算法的，鉴于过于复杂，等有时间了再研究

    public static void main(String[] args) {
        Subject_28 demo = new Subject_28();
//        System.out.println(demo.strStr("hello","lo"));
        System.out.println(demo.strStr("mississippi","a"));
    }
}
