package com.quantumn.future.algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class YanghuiAngle {
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> result = new ArrayList<>();
        int[][] c = new int[numRows][numRows];
        for (int row = 0; row < numRows; row++) {
            c[row][0]=1;
            c[row][row]=1;
        }
        for (int row = 2; row < numRows; row++) {
            for (int j = 1; j < row; j++) {
                c[row][j] = c[row - 1][j - 1] + c[row - 1][j];
            }
        }

        for (int row = 0; row < numRows; row++) {
            List<Integer> elementList = new ArrayList<>(row + 1);
            for (int j = 0; j <= row; j++) {
                elementList.add(0,c[row][j]);
            }
            result.add(elementList);
        }
        return result;
    }

    public static void main(String[] args) {
        YanghuiAngle angle = new YanghuiAngle();
        List<List<Integer>> result = angle.generate(5);
    }
}
