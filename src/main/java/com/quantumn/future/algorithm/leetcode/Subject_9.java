package com.quantumn.future.algorithm.leetcode;

/**
 * 判断一个整数是否是回文数。回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。
 *
 * 示例 1:
 *
 * 输入: 121
 * 输出: true
 */
public class Subject_9 {
    //consider overflow,but tranverse the whole array
    public boolean isPalindrome(int x) {
        if(x<0) return false;
        int temp = x;
        int reverse = 0;
        while (x > 0) {
            int m = x % 10;
            if (reverse > (Integer.MAX_VALUE - m) / 10) {
                //overflow
                return false;
            }
            reverse = reverse*10 + m;
            x = x / 10;
        }
        if (reverse == temp) {
            return true;
        }
        return false;
    }

    //tranverse half of the array
    public boolean isPalindrome2(int x) {
        if (x < 0 || (x != 0 && x % 10 == 0)) {
            return false;
        }
        int reverse = 0;
        while (x > reverse) {
            reverse = reverse * 10 + x % 10;
            x = x/10;
        }

        /**
         *  当数字长度为奇数时，我们可以通过 revertedNumber/10 去除处于中位的数字。
         *  例如，当输入为 12321 时，在 while 循环的末尾我们可以得到 x = 12，revertedNumber = 123，
         *  由于处于中位的数字不影响回文（它总是与自己相等），所以我们可以简单地将其去除。
         */
        return (x == reverse || x == reverse / 10);
    }

    public static void main(String[] args) {
        Subject_9 demo = new Subject_9();
//        System.out.println(demo.isPalindrome(121));
//        System.out.println(demo.isPalindrome(-121));
//        System.out.println(demo.isPalindrome(10));
        System.out.println(demo.isPalindrome2(2147483647));
    }
}
