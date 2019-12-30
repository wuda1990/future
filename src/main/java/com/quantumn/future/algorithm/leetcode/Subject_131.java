package com.quantumn.future.algorithm.leetcode;

import com.quantumn.future.algorithm.HuiwenPatition2;

import java.util.ArrayList;
import java.util.List;

/**
 * 分割回文串
 * 给定一个字符串 s，将 s 分割成一些子串，使每个子串都是回文串。
 *
 * 返回 s 所有可能的分割方案。
 *
 * 示例:
 *
 * 输入: "aab"
 * 输出:
 * [
 *   ["aa","b"],
 *   ["a","a","b"]
 * ]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/palindrome-partitioning
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Subject_131 {

    public List<List<String>> partition(String s) {
        List<List<String>> result = new ArrayList<List<String>>();
        func(s,null,result);
        return result;
    }

    public void func(String s,List<String> oldPartition,List<List<String>> result) {
        if (s.length()==0) {
            result.add(oldPartition);
            return;
        }
        String subStr = null;
        for (int endIndex = 1; endIndex <= s.length(); endIndex++) {
            subStr = s.substring(0, endIndex);
            if (isPalindrome(s,0,endIndex)) {
                List<String> newPartition = new ArrayList<String>();
                if (oldPartition!=null){
                    newPartition.addAll(oldPartition);
                }
                newPartition.add(subStr);
                func(s.substring(endIndex),newPartition,result);
            }
        }
    }



    public static void main(String[] args) {
        Subject_131 demo = new Subject_131();
        demo.partition("aab");
    }

    public List<List<String>> partition2(String s) {
        //从头到尾递归+回溯。
        List<List<String>> ans = new ArrayList<>();
        //这个是满足的每一个集合
        List<String>ll=new ArrayList<>();
        dfs(s,ans,ll,0);
        return ans;
    }
    public void dfs(String s,List<List<String>> ans,List<String>ll,int index){
        if(index==s.length())
        {
            ans.add(new ArrayList<>(ll));
            return;
        }
        //i从index开始是因为单个字符也是回文子串
        for(int i=index;i<s.length();i++)
        {
            //如果是回文
            if(isPalindrome(s,index,i)){
                //把当前的回文子串s(index,i)加进去
                ll.add(s.substring(index,i+1));
                dfs(s,ans,ll,i+1);
                //把加进去的回文子串去处。和上面加进去的回文子串是同一个回文子串。
                ll.remove(ll.size()-1);
            }

        }
    }
    public boolean isPalindrome(String s,int start,int end){
        while(start<end){
            if(s.charAt(start)!=s.charAt(end))
                return false;
            start++;
            end--;
        }
        return true;
    }
}
