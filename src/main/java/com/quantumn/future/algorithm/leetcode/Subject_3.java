package com.quantumn.future.algorithm.leetcode;

import java.util.*;

/**
 * 无重复字符的最长子串
 * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
 * 输入: "abcabcbb"
 * 输出: 3
 * 输入: "bbbbb"
 * 输出: 1
 * 输入: "pwwkew"
 * 输出: 3
 */
public class Subject_3 {
    //解决数组或字符串问题的一个技巧是多用变量去定义位置，这里又叫滑动窗口的办法
    //map经常用在这种子串重复，或数组比较大小的场景,set则经常用作子串重复的场景
    //自己写的方法，用charset存储当前循环的子串，removedSet存储需要去掉的子串
    //其实removedSet去掉子串可以通过把charset前面的一部分删除，可以节省一个Set的空间，见方法3
    public  int lengthOfLongestSubstring(String s) {
        LinkedHashSet<Character> charsSet = new LinkedHashSet<Character>();
        int maxLength = 0;
        int subStrLength = 0;
        for (int i=0;i<s.length();i++) {
            char tmp = s.charAt(i);
            if (charsSet.contains(tmp)) {
                if (subStrLength > maxLength) {
                    maxLength = subStrLength;
                }
                HashSet<Character> removedSet = new HashSet<Character>();
                Iterator<Character> iterable = charsSet.iterator();
                while (iterable.hasNext()) {
                    char element = iterable.next();
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

    //滑动窗口法，通过map存储当前的字符存在过得位置
    //这里用i记录子串start位置，j定义子串end位置
    public int lengthOfLongestSubstring2(String s) {
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

    //滑动窗口法，通过一个set存储当前循环的子串
    //这里用i记录子串start位置，j定义子串end位置
    public int lengthOfLongestSubstring3(String s) {
        int n = s.length();
        Set<Character> set = new HashSet<>();
        int ans = 0, i = 0, j = 0;
        while (i < n && j < n) {
            // try to extend the range [i, j]
            if (!set.contains(s.charAt(j))){
                set.add(s.charAt(j++));
                ans = Math.max(ans, j - i);
            }
            else {
                set.remove(s.charAt(i++));
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        Subject_3 demo = new Subject_3();
        System.out.println(demo.lengthOfLongestSubstring3("abcabcbb"));
        System.out.println(demo.lengthOfLongestSubstring3("abcbbcbb"));
        System.out.println(demo.lengthOfLongestSubstring3("bbbbb"));
        System.out.println(demo.lengthOfLongestSubstring3("pwwkew"));
        System.out.println(demo.lengthOfLongestSubstring3(" "));
        System.out.println(demo.lengthOfLongestSubstring3("dvdf"));
    }
}
