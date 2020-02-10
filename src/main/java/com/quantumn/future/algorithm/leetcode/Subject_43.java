package com.quantumn.future.algorithm.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 字符串相乘（大数相乘）
 * 给定两个以字符串形式表示的非负整数 num1 和 num2，返回 num1 和 num2 的乘积，它们的乘积也表示为字符串形式。
 *
 * 示例 1:
 *
 * 输入: num1 = "2", num2 = "3"
 * 输出: "6"
 *
 * 示例 2:
 *
 * 输入: num1 = "123", num2 = "456"
 * 输出: "56088"
 *
 * 说明：
 *
 *     num1 和 num2 的长度小于110。
 *     num1 和 num2 只包含数字 0-9。
 *     num1 和 num2 均不以零开头，除非是数字 0 本身。
 *     不能使用任何标准库的大数类型（比如 BigInteger）或直接将输入转换为整数来处理。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/multiply-strings
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Subject_43 {
    //模拟竖式乘法的计算
    public String multiply(String num1, String num2) {
        if ("0".equals(num1) || "0".equals(num2)) {
            return "0";
        }
        int m = num1.length();
        int n = num2.length();
        List<StringBuilder> multiList = new ArrayList<>(n);
        for (int i = n - 1; i >= 0; i--) {
            StringBuilder sb = new StringBuilder();
            //补0
            for (int j = 0; j < n - 1 - i; j++) {
                sb.append("0");
            }
            int b = num2.charAt(i) - 48;
            int jw = 0;
            for (int j = m - 1; j >= 0; j--) {
                int a = num1.charAt(j) - 48;
                int c = a * b + jw;
                jw = c / 10;
                c = c % 10;
                sb.append(c);
            }
            if (jw > 0)
                sb.append(jw);
            multiList.add(sb.reverse());
        }

        //sum the multiList
        StringBuilder ans = multiList.get(0);
        for (int i = 1; i < multiList.size(); i++) {
            ans = merge(ans, multiList.get(i));
        }
        return ans.toString();
    }

    private StringBuilder merge(StringBuilder sb1, StringBuilder sb2) {
        StringBuilder result = new StringBuilder();
        int i = sb1.length() - 1;
        int j = sb2.length() - 1;
        int jw = 0;
        while (i >= 0 || j >= 0 || jw > 0) {
            int a = i >= 0 ? sb1.charAt(i--) - 48 : 0;
            int b = j >= 0 ? sb2.charAt(j--) - 48 : 0;
            int c = a + b + jw;
            jw = c / 10;
            c = c % 10;
            result.append(c);
        }
        result.reverse();
        return result;
    }

    //对方法1竖式计算的优化，虽然复杂度是一样的，但是方法1中merge算和有些好性能
    //这里直接定义一个大的数组表示乘法后的结果,将每次乘法后得到的结果和进位直接加到相应的位置上
    public String multiply2(String num1, String num2) {
        if ("0".equals(num1) || "0".equals(num2)) {
            return "0";
        }
        int m = num1.length();
        int n = num2.length();
        //m个数和n个数相乘，结果最多m+n位
        int[] ans = new int[m + n];
        for (int i = n - 1; i >= 0; i--) {
            int b = num2.charAt(i) - '0';
            for (int j = m - 1; j >= 0; j--) {
                int a = num1.charAt(j) - '0';
                int c = a*b;
                ans[i + j + 1] += c % 10;
                ans[i + j] += c / 10;
                ans[i + j] += ans[i + j + 1] / 10;
                ans[i + j + 1] = ans[i + j + 1] % 10;
            }
        }
        StringBuilder sb = new StringBuilder();
        int start = 0;
        while (ans[start]==0) start++;
        for (int i = start; i < ans.length; i++) {
            sb.append(ans[i]);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        Subject_43 demo = new Subject_43();
//        System.out.println(demo.multiply2("28","32"));
        System.out.println(demo.multiply2("123","456"));
    }
}
