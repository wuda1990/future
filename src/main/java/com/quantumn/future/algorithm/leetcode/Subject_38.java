package com.quantumn.future.algorithm.leetcode;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 报数
 * 报数序列是一个整数序列，按照其中的整数的顺序进行报数，得到下一个数。其前五项如下：
 *
 * 1.     1
 * 2.     11
 * 3.     21
 * 4.     1211
 * 5.     111221
 *
 * 1 被读作  "one 1"  ("一个一") , 即 11。
 * 11 被读作 "two 1s" ("两个一"）, 即 21。
 * 21 被读作 "one 2",  "one 1" （"一个二" ,  "一个一") , 即 1211。
 *
 * 给定一个正整数 n（1 ≤ n ≤ 30），输出报数序列的第 n 项。
 *
 * 注意：整数顺序将表示为一个字符串。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/count-and-say
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Subject_38 {
    /**
     * 该题两个方面，一个是递归到n-1的情况
     * 一个是如何对连续数字的计数
     * @param n
     * @return
     */
    public String countAndSay(int n) {
        if (n==1) return "1";
        String str = countAndSay(n - 1);
        StringBuilder sb = new StringBuilder();
        int cnt = 0;
        for (int i = 0; i < str.length(); i++) {
            cnt++;
            if (i == str.length() - 1 || str.charAt(i) != str.charAt(i+1) ) {
                sb.append(cnt).append(str.charAt(i));
                cnt = 0;
            }

        }
        return sb.toString();

    }

    public static void main(String[] args) {
        Subject_38 demo = new Subject_38();
        System.out.println(demo.countAndSay(6));
    }
}
