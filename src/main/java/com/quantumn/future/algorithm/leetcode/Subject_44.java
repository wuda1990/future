package com.quantumn.future.algorithm.leetcode;

/**
 * 通配符匹配
 * 给定一个字符串 (s) 和一个字符模式 (p) ，实现一个支持 '?' 和 '*' 的通配符匹配。
 *
 * '?' 可以匹配任何单个字符。
 * '*' 可以匹配任意字符串（包括空字符串）。
 *
 * 两个字符串完全匹配才算匹配成功。
 *
 * 说明:
 *
 *     s 可能为空，且只包含从 a-z 的小写字母。
 *     p 可能为空，且只包含从 a-z 的小写字母，以及字符 ? 和 *。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/wildcard-matching
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Subject_44 {
    //这种对s和p用两个指针分别指向的解法，看似简单，但复杂度远不是连个指针那么简单，用start和match去记录位置
    //不画图不可能搞清楚，再涉及到这么多if else， 细节更容易出错
    //双指针的问题永远没那么简单，一定要搞清楚存在多少种情况，才知道会有多少个变量，才能开始编程
    public boolean isMatch(String s, String p) {
        int i = 0;
        int j = 0;
        int start = -1;// record the characters after * in string p
        int match = 0;// 记录字符串s中匹配p中*后字符串的位置
        while (i < s.length()) {
            if (j < p.length() &&( p.charAt(j) == '?' || s.charAt(i) == p.charAt(j))) {
                i++;
                j++;
            }
            else if (j < p.length() && p.charAt(j) == '*') {
                start = j;
                match = i;
                j++;
            } else if (start != -1){
                //两个字符串的当前字符不相等，也不存在?，则开始匹配*
                //这种情况需要用match来记录字符串s的位置，用i是不妥当的，因为还没有发生比较
                j = start +1 ;
                match++;
                i = match;
            }else {
                return false;
            }
        }
        while (j < p.length()) {
            if (p.charAt(j)!='*') return false;
            j++;
        }
        return true;
    }

    /**
     * powcai的方法，动态规划
     * 太难了，难以理解转换公式
     * dp[i][j]表示s到i位置,p到j位置是否匹配!
     *
     * 初始化:
     *
     *     dp[0][0]:什么都没有,所以为true
     *     第一行dp[0][j],换句话说,s为空,与p匹配,所以只要p开始为*才为true
     *     第一列dp[i][0],当然全部为False
     *
     * 动态方程:
     *
     *     如果(s[i] == p[j] || p[j] == "?") && dp[i-1][j-1] ,有dp[i][j] = true
     *
     *     如果p[j] == "*" && (dp[i-1][j] = true || dp[i][j-1] = true) 有dp[i][j] = true
     *
     *     ​ note:
     *
     *     ​ dp[i-1][j],表示*代表是空字符,例如ab,ab*
     *
     *     ​ dp[i][j-1],表示*代表非空任何字符,例如abcd,ab*
     * @param s
     * @param p
     * @return
     */
    public boolean isMatch2(String s, String p) {
        boolean[][] dp = new boolean[s.length() + 1][p.length() + 1];
        dp[0][0] = true;
        for (int j = 1; j < p.length() + 1; j++) {
            if (p.charAt(j - 1) == '*') {
                dp[0][j] = dp[0][j - 1];
            }
        }
        for (int i = 1; i < s.length() + 1; i++) {
            for (int j = 1; j < p.length() + 1; j++) {
                if (s.charAt(i - 1) == p.charAt(j - 1) || p.charAt(j - 1) == '?') {
                    dp[i][j] = dp[i - 1][j - 1];
                } else if (p.charAt(j - 1) == '*') {
                    dp[i][j] = dp[i][j - 1] || dp[i - 1][j];
                }
            }
        }
        return dp[s.length()][p.length()];
    }


    public static void main(String[] args) {
        Subject_44 demo = new Subject_44();
        System.out.println(demo.isMatch2("abefcdgiescdfimde", "ab*cd?i*de"));
//        System.out.println(demo.isMatch("aa", "*"));
//        System.out.println(demo.isMatch("aaaa", "***a"));
//        System.out.println(demo.isMatch("cb", "?a"));
//        System.out.println(demo.isMatch("adceb", "a*b"));
//        System.out.println(demo.isMatch("acdcb", "a*c?b"));
    }
}
