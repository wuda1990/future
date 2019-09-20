package com.quantumn.future.algorithm.leetcode;

import java.util.*;

public class Subject3 {
    public static int lengthOfLongestSubstring(String s) {

        LinkedHashSet<Character> charsSet = new LinkedHashSet<Character>();
        int maxLength = 0;
        int subStrLength = 0;
        for (int i=0;i<s.length();i++) {
            char tmp = s.charAt(i);
            if (charsSet.contains(tmp)) {
                if (subStrLength > maxLength) {
                    maxLength = subStrLength;
                }
//                charsSet.clear();
                HashSet<Character> removedSet = new HashSet<Character>();
                Iterator<Character> iterable = charsSet.iterator();
                while (iterable.hasNext()) {
                    char element = iterable.next();
//                    System.out.println(element);
                    removedSet.add(element);
                    if (tmp == element) {
                        break;
                    }
                }
                charsSet.removeAll(removedSet);
                subStrLength = charsSet.size();
            }
            charsSet.add(tmp);
            subStrLength++;
        }
        if (subStrLength > maxLength) {
            maxLength = subStrLength;
        }
        return maxLength;
    }

    public int getLength2(String s) {
        int n = s.length(), ans = 0;
        Map<Character, Integer> map = new HashMap<>(); // current index of character
        // try to extend the range [i, j]
        for (int j = 0, i = 0; j < n; j++) {
            if (map.containsKey(s.charAt(j))) {
                i = Math.max(map.get(s.charAt(j)), i);
            }
            ans = Math.max(ans, j - i + 1);
            map.put(s.charAt(j), j + 1);
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(getLengthOfLongestSubString("abcabcbb"));
        System.out.println(getLengthOfLongestSubString("abcbbcbb"));
        System.out.println(getLengthOfLongestSubString("bbbbb"));
        System.out.println(getLengthOfLongestSubString("pwwkew"));
        System.out.println(getLengthOfLongestSubString(" "));
        System.out.println(getLengthOfLongestSubString("dvdf"));
    }

    //基于滑动窗口的模式去查找字符串的子串
    public static int getLengthOfLongestSubString(String s){
        int i = 0,  j = 0, ans = 0;
        HashSet set = new HashSet();
        int len = s.length();
        while (i < len && j < len) {
            if (!set.contains(s.charAt(j))) {
                set.add(s.charAt(j++));
                ans = Math.max(ans, j - i);
            } else {
                set.remove(s.charAt(i++));
            }
        }
        return ans;
    }
}
