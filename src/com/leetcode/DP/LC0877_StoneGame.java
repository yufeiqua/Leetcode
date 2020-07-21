package com.leetcode.DP;

public class LC0877_StoneGame {
    public boolean stoneGame(int[] piles) {
        int length = piles.length;
        int[][] dp = new int[length][length];
        int sum = 0;
        for (int i = 0; i < length; i++) {
            dp[i][i] = piles[i];
            sum += piles[i];
        }
        for (int i = length - 1; i >= 0; i--) {
            for (int j = i; j < length; j++) {
                dp[i][j] = Math.max(piles[i] + Math.min(i + 2 >= length ? 0 : dp[i + 2][j], i + 1 >= length || j - 1 < 0 ? 0 : dp[i + 1][j - 1]),
                        piles[j] + Math.min((i + 1 >= length || j - 1 < 0 ? 0 : dp[i + 1][j - 1]), j - 2 < 0 ? 0 : dp[i][j - 2]));
            }
        }
        return sum - dp[0][length - 1] < dp[0][length - 1];
    }

}
