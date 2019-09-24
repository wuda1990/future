package com.quantumn.future.algorithm.leetcode;

/**
 * 实现一个 atoi 函数，使其能将字符串转换成整数。
 *
 * 首先，该函数会根据需要丢弃无用的开头空格字符，直到寻找到第一个非空格的字符为止。
 *
 * 当我们寻找到的第一个非空字符为正或者负号时，则将该符号与之后面尽可能多的连续数字组合起来，作为该整数的正负号；假如第一个非空字符是数字，则直接将其与之后连续的数字字符组合起来，形成整数。
 *
 * 该字符串除了有效的整数部分之后也可能会存在多余的字符，这些字符可以被忽略，它们对于函数不应该造成影响。
 *
 * 注意：假如该字符串中的第一个非空格字符不是一个有效整数字符、字符串为空或字符串仅包含空白字符时，则你的函数不需要进行转换。
 *
 * 在任何情况下，若函数不能进行有效的转换时，请返回 0。
 *
 * 说明：
 *
 * 假设我们的环境只能存储 32 位大小的有符号整数，那么其数值范围为 [−231,  231 − 1]。如果数值超过这个范围，请返回  INT_MAX (231 − 1) 或 INT_MIN (−231) 。
 *
 *
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/string-to-integer-atoi
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Subject_8 {
    public int myAtoi(String str){
        int ans = 0;
        int isNegative = 1;
        for (int i = 0; i < str.length(); i++) {
            char tmp = str.charAt(i);
            if (Character.isSpaceChar(tmp))
                continue;

            int j;//do a record
            if (tmp=='-'){
                j = i+1;
                isNegative = -1;
            } else if (tmp == '+') {
                j= i+1;
            }else if (!Character.isDigit(tmp)){
                return 0;
            }else {
                j=i;
            }
            //calculate the int value
            while(j<str.length() && Character.isDigit(str.charAt(j))){
                int tmpValue = (int)str.charAt(j++) - 48;
                if (ans > Integer.MAX_VALUE/10 || ans == Integer.MAX_VALUE/10 && tmpValue>7) {
                    return isNegative==1?Integer.MAX_VALUE:Integer.MIN_VALUE;
                }
                ans = ans *10 + tmpValue;

            }
            break;//ignore back string
        }

        return ans*isNegative;

    }

    public static void main(String[] args) {
        Subject_8 demo = new Subject_8();
//        System.out.println(demo.myAtoi("123"));
        System.out.println(demo.myAtoi("-56"));
    }
}
