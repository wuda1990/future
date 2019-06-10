package com.quantumn.future.algorithm;

import java.util.ArrayList;
import java.util.List;

public class HuiwenPartition {
    Huiwen huiwen;
    List<List<String>> result = new ArrayList<List<String>>();

    public static void main(String[] args) {
        HuiwenPartition demo = new HuiwenPartition();
        demo.partition("cdd");
    }

    public List<List<String>> partition(String s) {

        huiwen = new Huiwen();
        func(s,null);
        for (List<String> rs : result) {
            for (String str : rs) {
                System.out.print(str+",");
            }
            System.out.println();
        }
        return result;
    }

    public void func(String s,List<String> oldPartition) {
        if (s.length()==0) {
            result.add(oldPartition);
            return;
        }
        String subStr = null;
        for (int endIndex = 1; endIndex <= s.length(); endIndex++) {
            subStr = s.substring(0, endIndex);
            if (huiwen.isPalindrome(subStr)) {
                List<String> newPartition = new ArrayList<String>();
                if (oldPartition!=null){
                    newPartition.addAll(oldPartition);
                }
                newPartition.add(subStr);
                func(s.substring(endIndex),newPartition);
            }
        }

    }

}
