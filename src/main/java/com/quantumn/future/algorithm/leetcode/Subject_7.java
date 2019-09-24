package com.quantumn.future.algorithm.leetcode;

import static java.lang.Math.abs;

/**
 * 给出一个 32 位的有符号整数，你需要将这个整数中每位上的数字进行反转。
 * 321->123
 *
 * 从ans * 10 + pop > MAX_VALUE这个溢出条件来看
 *
 *     当出现 ans > MAX_VALUE / 10 且 还有pop需要添加 时，则一定溢出
 *     当出现 ans == MAX_VALUE / 10 且 pop > 7 时，则一定溢出，7是2^31 - 1的个位数
 *
 * 从ans * 10 + pop < MIN_VALUE这个溢出条件来看
 *
 *     当出现 ans < MIN_VALUE / 10 且 还有pop需要添加 时，则一定溢出
 *     当出现 ans == MIN_VALUE / 10 且 pop < -8 时，则一定溢出，8是-2^31的个位数
 *
 * 作者：guanpengchn
 * 链接：https://leetcode-cn.com/problems/reverse-integer/solution/hua-jie-suan-fa-7-zheng-shu-fan-zhuan-by-guanpengc/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 */
public class Subject_7 {
    public int reverse(int x) {
        int result = 0;

        while (x != 0) {
            int pop = x % 10;
            x = x /10;
            if (result > Integer.MAX_VALUE/10 || result == Integer.MAX_VALUE/10 && pop>7) {
                return 0;
            }
            if (result < Integer.MIN_VALUE / 10 || result == Integer.MIN_VALUE / 10 && pop < -8) {
                return 0;
            }
            result = result * 10 + pop;

        }
        return result;
    }

    public static void main(String[] args) {
        Subject_7 demo = new Subject_7();
        System.out.println(demo.reverse(1534236469));
    }
}
