package com.leetcode.Google;

public class LC1277_CountSquareOfAllOnes {
    public int countSquares(int[][] matrix) {
        int row = matrix.length;
        int col = matrix[0].length;
        int[][] dp = new int[row][col];
        int count = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (i == 0 && j == 0) {
                    dp[0][0] = matrix[i][j];
                    count += dp[0][0];
                    continue;
                }
                if (i == 0) {
                    dp[0][j] = matrix[0][j];
                    count += dp[0][j];
                    continue;
                }
                if (j == 0) {
                    dp[i][0] = matrix[i][0];
                    count += dp[i][0];
                    continue;
                }
                if (matrix[i][j] == 0) {
                    dp[i][j] = 0;
                } else {
                    dp[i][j] = Math.min(Math.min(dp[i - 1][j - 1], dp[i - 1][j]), dp[i][j - 1]) + 1;
                }
                count += dp[i][j];
            }
        }
        return count;
    }
}
