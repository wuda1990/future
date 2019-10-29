package com.quantumn.future.algorithm.leetcode;

/**
 * 给定一个字符串，验证它是否是回文串，只考虑字母和数字字符，可以忽略字母的大小写。
 *
 * 说明：本题中，我们将空字符串定义为有效的回文串。
 *
 * 示例 1:
 *
 * 输入: "A man, a plan, a canal: Panama"
 * 输出: true
 *
 * 示例 2:
 *
 * 输入: "race a car"
 * 输出: false
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/valid-palindrome
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Subject_125 {
    public boolean isPalindrome(String s) {
        int i=0;
        int j=s.length()-1;
        while (i < j) {
            while (i<j&&!isLetterDigit(s.charAt(i))&& i<s.length()) {
                i++;
            }
            while (i<j&&(!isLetterDigit(s.charAt(j))) && j<s.length()) {
                j--;
            }
            if (Character.toLowerCase(s.charAt(i) )!= Character.toLowerCase(s.charAt(j))) {
                return false;
            }
            i++;j--;
        }
        return true;
    }

    public static void main(String[] args) {
        Subject_125 demo = new Subject_125();
        System.out.println(demo.isPalindrome("A man, a plan, a canal: Panama"));
        System.out.println(demo.isPalindrome("aba"));
    }

    public  boolean isLetterDigit(Character c) {
//        String regex = "^[a-z0-9A-Z]+$";//其他需要，直接修改正则表达式就好
//        return str.matches(regex);
        return Character.isDigit(c) || Character.isLowerCase(c) || Character.isUpperCase(c);
    }
}
