package com.quantumn.future.algorithm;

import java.util.HashMap;

public class LongestHuiwenSubstr {

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
//                    System.out.println("i:"+i+",j:"+j);
                    start = i;
                    end = j;
                } else {
                    c[i][j] = false;
                }
            }
        }
        return s.substring(start,end+1);
    }


    public static void main(String[] args) {
        LongestHuiwenSubstr demo = new LongestHuiwenSubstr();
        System.out.println(demo.longestPalindrome("babad"));
    }
}
