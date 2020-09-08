package com.quantumn.future.algorithm.leetcode;

import java.util.LinkedList;

/**
 * 有效的括号
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。
 *
 * 有效字符串需满足：
 *
 *     左括号必须用相同类型的右括号闭合。
 *     左括号必须以正确的顺序闭合。
 *
 * 注意空字符串可被认为是有效字符串。
 *输入: "()[]{}"
 * 输出: true
 * 输入: "([)]"
 * 输出: false
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/valid-parentheses
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Subject_20 {
    //这里用到栈的弹入弹出
    public boolean isValid(String s) {
        LinkedList<Character> stack = new LinkedList<>();
        for (int i = 0; i < s.length(); i++) {
            char value = s.charAt(i);
            if ('(' == value || '{' == value || '[' == value) {
                stack.push(value);
                continue;
            }
            if (')' == value && !stack.isEmpty() && '(' == stack.peek()) {
                stack.pop();
                continue;
            }
            if ('}' == value && !stack.isEmpty() && '{' == stack.peek()) {
                stack.pop();
                continue;
            }
            if (']' == value && !stack.isEmpty() && '[' == stack.peek()) {
                stack.pop();
                continue;
            }
            return false;
        }
        return stack.isEmpty();
    }

    //当栈为空的时候，弹出'#'代替
    public boolean isValid2(String s) {
        LinkedList<Character> stack = new LinkedList<>();
        for (int i = 0; i < s.length(); i++) {

            char value = s.charAt(i);
            if ('(' == value || '{' == value || '[' == value) {
                stack.push(value);
                continue;
            }
            //当前字符是有括号，但栈为空，肯定不是有效的括号，直接返回false
            char top = stack.isEmpty() ? '#' : stack.pop();
            if (')' == value &&'(' != top) {
                return false;
            }
            if ('}' == value  && '{'!= top) {
                return false;
            }
            if (']' == value && '[' != top) {
                return false;
            }
        }
        return stack.isEmpty();
    }


    public static void main(String[] args) {
        Subject_20 demo = new Subject_20();
        System.out.println(demo.isValid("){"));
    }
}
