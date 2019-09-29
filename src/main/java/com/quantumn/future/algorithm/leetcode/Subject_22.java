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
        StringBuilder sb = new StringBuilder();
        char[] str = new char[2 * n];
        permute(ans,str,0,0,0);
//        permute(ans, sb, 0, n);
//        permute(ans, sb, 0, 0, n);
//        permute(ans, "", 0, 0, n);
        return ans;
    }

    /**
     * 其实这就是生成一个完全二叉树的情况，高度为n
     * str代表了从根节点到叶子节点的一条路径
     *  理解：当一个子递归执行完了，执行链路会自动回退到上一个递归；也就是当一个节点所有的子节点都遍历完了，才会访问这个节点的下一个节点，
     *  即深度遍历
     * @param ans  返回的list
     * @param str  存储当前递归路径
     * @param pos  当前递归的深度
     * @param sum  当前递归路径上所有数的和,左括号加1,右括号减1
     * @param left_count 当前递归路径上左括号的个数
     */
    private void permute(List<String> ans,char[] str ,int pos,int sum,int left_count){
        if (sum < 0 || left_count>str.length/2)  return;
        if (str.length == pos) {
            if (sum == 0) {
                ans.add(new String(str));
            }
            return;
        }
        str[pos] = '(';
//        sum++;left_count++;
        permute(ans, str, pos+1,sum+1,left_count+1);
        /**
         *  当要执行下一个递归函数时（循环迭代到下一轮时），即要访问当前节点的下一个节点时，需要把当前节点的状态清除，因为该状态只能
         *  影响到当前节点
         * 这种情况可以简化即当前节点的变量保持不变，将其传递到子节点中，因为直到叶子节点才会进行真正的计算
         */
//        sum--;left_count--;
        //开始右括号遍历
        str[pos] = ')';
//        sum--;
        permute(ans,str,pos+1,sum-1,left_count);
        //清除当前节点的状态
//        sum++;
    }

    /**
     * 参考了以前递归的方法，但为掌握核心
     * 以前之所以用StringBuilder 或 list去存递归路径上的值，是因为在具体添加元素到递归路径的时候，逻辑里（可能是个判断)需要用到这个递归路径
     * str[pos], sb.apend都是添加元素到递归路径里，pos增加，sb增加，递归的深度也就增加了
     * 在一个方法里，执行几次递归，则代表了每次递归时会出现多少种情况，如左子树，右子树；又如(,)；又如电话号码2代表的a,b,c
     * 其实就是一个节点会有多少子节点
     * 理解：当一个子递归执行完了，执行链路会自动回退到上一个递归；也就是当一个节点所有的子节点都遍历完了，才会访问这个节点的下一个节点，
     * 即深度遍历
     * @param ans
     * @param sb
     * @param index
     * @param n
     */
    private void permute(List<String> ans, StringBuilder sb, int index,int n) {
        if (sb.length() == 2*n) {
            String str = sb.toString();
            if (isValid(str.toCharArray())){
                ans.add(str);
            }
            return;
        }
        sb.append('(');
        permute(ans, sb, index + 1,n);
        sb.deleteCharAt(sb.length() - 1);
        sb.append(')');
        permute(ans, sb, index + 1,n);
        sb.deleteCharAt(sb.length() - 1);
    }

    /**
     *
     * @param ans 返回list
     * @param sb  递归当前路径
     * @param open 左括号的个数
     * @param close 右括号的个数
     * @param n
     */
    private void permute(List<String> ans, StringBuilder sb, int open, int close, int n) {
        if (sb.length() == 2 * n) {
            ans.add(sb.toString());
            return;
        }
        if (open < n) {
            permute(ans,sb.append('('),open+1,close,n);
            sb.deleteCharAt(sb.length() - 1);
        }
        if (close < open) {
            permute(ans,sb.append(')'),open,close+1,n);
            sb.deleteCharAt(sb.length() - 1);
        }
    }

    /**
     *
     * @param ans 返回list
     * @param cur  递归当前字符串
     * @param open 左括号的个数
     * @param close 右括号的个数
     * @param n
     */
    private void permute(List<String> ans, String cur, int open, int close, int n) {
        if (cur.length() == 2 * n) {
            ans.add(cur);
            return;
        }
        //这两个if分别是递归里两个节点，互不影响
        if (open < n) {
            permute(ans,cur+'(',open+1,close,n);
        }
        if (close < open) {
            permute(ans,cur+')',open,close+1,n);
        }
    }

    private boolean isValid(char[] str) {
        int sum = 0;
        for (int i = 0; i < str.length; i++) {
            char value = str[i];
            if ('(' == value) {
                sum++;
            } else {
               sum--;
            }
            if (sum<0) return false;
        }
        return sum==0;
    }

    public static void main(String[] args) {
        Subject_22 demo = new Subject_22();
        List<String> ans = demo.generateParenthesis(3);
        ans.forEach(System.out::println);
    }

}
