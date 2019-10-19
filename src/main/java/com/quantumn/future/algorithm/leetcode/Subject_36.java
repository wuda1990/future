package com.quantumn.future.algorithm.leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * 判断一个 9x9 的数独是否有效。只需要根据以下规则，验证已经填入的数字是否有效即可。
 *
 *     数字 1-9 在每一行只能出现一次。
 *     数字 1-9 在每一列只能出现一次。
 *     数字 1-9 在每一个以粗实线分隔的 3x3 宫内只能出现一次。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/valid-sudoku
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Subject_36 {
    public boolean isValidSudoku(char[][] board) {
        Set set = new HashSet(9);
        //Check if only exists once by row
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (set.contains(board[i][j])) {
                    return false;
                } else if (board[i][j] != '.'){
                    set.add(board[i][j]);
                }
            }
            set.clear();
        }

        //Check if only exists once by column
        for (int j = 0; j < 9; j++) {
            for (int i = 0; i < 9; i++) {
                if (set.contains(board[i][j])) {
                    return false;
                } else if (board[i][j] != '.'){
                    set.add(board[i][j]);
                }
            }
            set.clear();
        }

        //Check if only exist once by 3*3 square
        for (int k = 0; k < 9; k += 3) {
            for (int l = 0; l < 9; l += 3) {
                for (int i = k + 0; i < k + 3; i++) {
                    for (int j = l + 0; j < l + 3; j++) {
                        if (set.contains(board[i][j])) {
                            return false;
                        } else if (board[i][j] != '.'){
                            set.add(board[i][j]);
                        }
                    }
                }
                set.clear();
            }

        }

        return true;
    }

    /**
     * leetCode解法，通过一次遍历，但是要定义三个数组
     * 1、使用了2进制的9个位数，如果是第一个数是1，那么统计标志就是0000000010(二进制 1左移1位)，
     * 如果第二个数是3那么统计标识变为0000001010(二进制 1左移3位再加上原来的)，每次判断有没有重复就右移相应位数之后整除2即可。
     * 2、同官方解法int boxNum = i / 3 * 3 + j / 3;如果是0,1,2行的话整除3就是0，然后再加上列数整除3，
     * 这样就把整个9*9分为了编号0-8的9个3*3的区域。
     * @param board
     * @return
     */
    public boolean isValidSudoku2(char[][] board) {
        int[] rowCnt = new int[9];
        int[] colCnt = new int[9];
        int[] boxCnt = new int[9];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if ('.' == board[i][j]) {
                    continue;
                }
                int num = board[i][j] - '0';
                //take rows
                if ((rowCnt[i] >> num) % 2 == 1) {
                    return false;
                } else {
                    rowCnt[i] += 1 << num;
                }
                //take columns
                if ((colCnt[j] >> num) % 2 == 1) {
                    return false;
                } else {
                    colCnt[j] += 1 << num;
                }
                // 处理框
                int boxNum = i / 3 * 3 + j / 3;
                if ((boxCnt[boxNum] >> num) % 2 == 1) {
                    return false;
                } else {
                    boxCnt[boxNum] += 1 << num;
                }
            }
        }
        return true;
    }

    
    
    public static void main(String[] args) {
        Subject_36 demo = new Subject_36();
        System.out.println(demo.isValidSudoku(new char[][]{
                {'.','.','4','.','.','.','6','3','.'},{'.','.','.','.','.','.','.','.','.'},{'5','.','.','.','.','.','.','9','.'},
                {'.','.','.','5','6','.','.','.','.'},{'4','.','3','.','.','.','.','.','1'},{'.','.','.','7','.','.','.','.','.'},
                {'.','.','.','5','.','.','.','.','.'},{'.','.','.','.','.','.','.','.','.'},{'.','.','.','.','.','.','.','.','.'}

        }));

    }
}
