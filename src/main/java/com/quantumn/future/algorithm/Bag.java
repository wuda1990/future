package com.quantumn.future.algorithm;

public class Bag {
    //背包问题，n件物品，背包总容量为V
    public int dpSolution(int n,int V,int[] v, int[] w) {
        int[][] c = new int[n][V+1];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= V; j++) {
                if (j == 0) {
                    c[i][j]=0;
                    continue;
                }
                if (i == 0) {
                    c[i][j] = j < v[i] ? 0 : w[i];
                    continue;
                }
                if (j >= v[i]) {
                    int tmp = c[i - 1][j - v[i]] + w[i];
                    if (tmp > c[i - 1][j]) {
                        c[i][j] = tmp;
                        continue;
                    }
                }
                c[i][j] = c[i - 1][j];
            }
        }
        return c[n-1][V];
    }

    public static void main(String[] args) {
        Bag bag = new Bag();
        int result = bag.dpSolution(4, 8, new int[]{2, 3, 4, 5}, new int[]{3, 4, 5, 6});
        System.out.println(result);
    }
}
