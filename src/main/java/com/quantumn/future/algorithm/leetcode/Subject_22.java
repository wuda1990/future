package com.quantumn.future.algorithm.leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 给出 n 代表生成括号的对数，请你写出一个函数，使其能够生成所有可能的并且有效的括号组合。
 *
 * 例如，给出 n = 3，生成结果为：
 *
 * [
 *   "((()))",
 *   "(()())",
 *   "(())()",
 *   "()(())",
 *   "()()()"
 * ]
 *
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/generate-parentheses
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Subject_22 {
    public List<String> generateParenthesis(int n) {
        List<String> ans = new ArrayList<>();
        LinkedList<Character> stack = new LinkedList<>();
        StringBuilder sb = new StringBuilder();
        permute(ans,sb,stack,n);
        return ans;
    }

    int leftCount=0;
    int rightCount=0;

    private void permute(List<String> ans,StringBuilder sb, LinkedList<Character> stack, int n){
        if (sb.length() > 0) {
            char value = sb.charAt(sb.length() - 1);
            if ('(' == value) {
                stack.push(value);
                leftCount++;
            } else {
                char top = stack.isEmpty() ? '#' : stack.pop();
                if (')' == value && '(' != top) {
                    return;
                }
                rightCount++;
            }
        }
        if(n==0)  {
            String str = sb.toString();
            ans.add(str);
            return;
        }

        if (leftCount < n) {
            sb.append('(');
            permute(ans,sb,stack,n-1);
            sb.deleteCharAt(sb.length() - 1);
        }

        sb.append(')');
        permute(ans,sb,stack,n-1);
    }

    private boolean isValid(String s) {
        LinkedList<Character> stack = new LinkedList<>();
        for (int i = 0; i < s.length(); i++) {

            char value = s.charAt(i);
            if ('(' == value) {
                stack.push(value);
                continue;
            }
            char top = stack.isEmpty() ? '#' : stack.pop();
            if (')' == value &&'(' != top) {
                return false;
            }
        }
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        Subject_22 demo = new Subject_22();
        List<String> ans = demo.generateParenthesis(3);
        ans.forEach(System.out::println);
    }

}
