package com.quantumn.future.algorithm;

public class Huiwen {
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
        Huiwen huiwen = new Huiwen();
        System.out.println(huiwen.isPalindrome("A man, a plan, a canal: Panama"));
        System.out.println(huiwen.isPalindrome("aba"));
    }

    public  boolean isLetterDigit(Character c) {
//        String regex = "^[a-z0-9A-Z]+$";//其他需要，直接修改正则表达式就好
//        return str.matches(regex);
        return Character.isDigit(c) || Character.isLowerCase(c) || Character.isUpperCase(c);
    }


}
