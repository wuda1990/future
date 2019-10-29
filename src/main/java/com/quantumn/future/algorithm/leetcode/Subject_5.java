package com.quantumn.future.algorithm.leetcode;

/**
 * 最长回文子串
 * 给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。
 * <p>
 * 示例 1：
 * 输入: "babad"
 * 输出: "bab"
 * 注意: "aba" 也是一个有效答案。
 * <p>
 * 示例 2：
 * 输入: "cbbd"
 * 输出: "bb"
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-palindromic-substring
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Subject_5 {
    //通常暴力法会给我们提供思路，然后在暴力法上进行优化
    //这一题是将判断回文的判断从O(n)到O(1)，是通过重用以前计算的结果，即动态规划的思路来解决的
    public String longestPalindrome(String s) {
        int len = s.length();
        if (len == 0) {
            return "";
        }
        boolean[][] c = new boolean[len][len];
        for (int i = 0; i < len; i++) {
            c[i][i] = true;
        }
        int start = 0;
        int end = 0;
        for (int i = 0; i < len - 1; i++) {
            if (s.charAt(i) == s.charAt(i+1)) {
                c[i][i + 1] = true;
                start = i;
                end = i + 1;
            } else {
                c[i][i + 1] = false;
            }
        }

        for (int k = 2; k < len; k++) {
            for (int i = 0; i< len-k;i++) {
                int j=i+k;
                if (s.charAt(i) == s.charAt(j) && c[i + 1][j - 1]) {
                    c[i][j] = true;
                    start = i;
                    end = j;
                } else {
                    c[i][j] = false;
                }
            }
        }
        return s.substring(start,end+1);
    }

    //powcai大神的动态规划方法，优雅
    public String longestPalindrome2(String s) {
        int n = s.length();
        boolean[][] dp = new boolean[n][n];
        String ans = "";
        //两层循环，dp[j][i]利用了dp[j+1][i-1]的计算结果
        //用i-j<=2判断一些初始化的情况，巧妙
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= i; j++) {
                if (s.charAt(j) == s.charAt(i) && (i - j <= 2 || dp[j + 1][i - 1])) {
                    dp[j][i] = true;
                }
                if (dp[j][i] && i - j + 1 > ans.length()) {
                    ans = s.substring(j, i + 1);
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        Subject_5 demo = new Subject_5();
        System.out.println(demo.longestPalindrome2("babad"));
    }
}
