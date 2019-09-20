package com.quantumn.future.algorithm.leetcode;

import static java.lang.Math.abs;

/**
 * 给出一个 32 位的有符号整数，你需要将这个整数中每位上的数字进行反转。
 * 321->123
 */
public class Subject_7 {
    public int reverse(int x) {
        int temp = x;
        int result = 0;
        while (temp !=0) {
            int pop = temp % 10;
            if (temp> Integer.MAX_VALUE/10 || temp == Integer.MAX_VALUE/10 && pop>7) {
                return 0;
            }
            if (temp < Integer.MIN_VALUE / 10 || temp == Integer.MIN_VALUE / 10 && pop < -8) {
                return 0;
            }
            result = result * 10 + pop;
            temp = temp /10;
        }
        return result;
    }

    public static void main(String[] args) {
        Subject_7 demo = new Subject_7();
        System.out.println(demo.reverse(-123));
    }
}
