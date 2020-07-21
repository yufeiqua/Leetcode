package com.leetcode.DP;

public class LC1140_StoneGame2 {
    public int stoneGameII(int[] piles) {
        int length = piles.length;
        int[] suffixSum = new int[length + 1];
        for (int i = length; i >= 1; i--) {
            suffixSum[i - 1] = piles[i - 1] + suffixSum[i];
        }
        int[][] dp = new int[length + 1][length  + 1];
        for (int i = 0; i <= length; i++) {
            dp[i][length] = suffixSum[i];
        }

        for (int i = length - 1; i >= 0; i--) {
            for (int j = length - 1; j >= 0; j--) {
                for (int x = 1; x <= 2 * j && x + i <= length; x++) {
                    dp[i][j] = Math.max(suffixSum[i] - dp[i + x][Math.max(x, j)], dp[i][j]);
                }
            }
        }
        return dp[0][1];

    }
}
