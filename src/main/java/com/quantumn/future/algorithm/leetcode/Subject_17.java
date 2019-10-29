package com.quantumn.future.algorithm.leetcode;

import java.util.*;

/**
 * 电话号码的字母组合
 * 给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。
 *
 * 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
 */
public class Subject_17 {
    public List<String> letterCombinations(String digits) {
        List<String> ans = new ArrayList<>();
        if(digits.length()==0) return ans;
        permute(ans,digits,new StringBuilder(),0);
        return ans;
    }

    private static final Map<Character,String> telMap;
    static {
        telMap = new HashMap<>();
        telMap.put('2', "abc");
        telMap.put('3', "def");
        telMap.put('4', "ghi");
        telMap.put('5', "jkl");
        telMap.put('6', "mno");
        telMap.put('7', "pqrs");
        telMap.put('8', "tuv");
        telMap.put('9', "wxyz");

    }

    private void permute(List<String> ans, String digits, StringBuilder stringBuilder, int index) {
        if (index == digits.length()) {
            ans.add(stringBuilder.toString());
        } else {
            String telStr = telMap.get(digits.charAt(index));
            for (int j = 0; j < telStr.length(); j++) {
                stringBuilder.append(telStr.charAt(j));
                permute(ans, digits, stringBuilder, index + 1);
                stringBuilder.deleteCharAt(stringBuilder.length()-1);
            }

        }
    }

    public static void main(String[] args) {
        Subject_17 demo = new Subject_17();
        List<String> ans = demo.letterCombinations2("234");
        ans.forEach(str->System.out.println(str.toString()));
    }

    /**
     * 队列实现
     */
    public List<String> letterCombinations2(String digits) {
        LinkedList<String> ans = new LinkedList<String>();
        if(digits.isEmpty()) return ans;
        String[] mapping = new String[] {"0", "1", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        ans.add("");
        for(int i =0; i<digits.length();i++){
            int x = Character.getNumericValue(digits.charAt(i));
            while(ans.peek().length()==i){
                String t = ans.remove();
                for(char s : mapping[x].toCharArray())
                    ans.add(t+s);
            }
        }
        return ans;
    }


}
