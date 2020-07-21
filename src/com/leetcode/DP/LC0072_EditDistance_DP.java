package com.leetcode.DP;

import java.util.Arrays;

public class LC0072_EditDistance_DP {
    public int minDistance(String word1, String word2) {
        int len1 = word1.length();
        int len2 = word2.length();
        int[][] dp = new int[len1 + 1][len2 + 1];

        for (int i = 0; i <= len1; i++) {
            for (int j = 0; j <= len2; j++) {
                if (i == 0 && j == 0) {
                    dp[i][j] = 0;
                    continue;
                }
                if (i == 0) {
                    //represents w1 is empty string;
                    dp[i][j] = j;
                    continue;
                }
                if (j == 0) {
                    //represents w2 is empty string;
                    dp[i][j] = i;
                    continue;
                }
                if (word1.charAt(i - 1) != word2.charAt(j - 1)) {
                    dp[i][j] = Math.max(len1, len2);
                    dp[i][j] = Math.min(dp[i][j], dp[i][j - 1]);
                    dp[i][j] = Math.min(dp[i][j], dp[i - 1][j]);
                    dp[i][j] = Math.min(dp[i][j], dp[i - 1][j - 1]);
                    dp[i][j] += 1;
                } else {
                    dp[i][j] = dp[i - 1][j - 1];
                }
            }
        }
        for (int[] row : dp) {
            for (int col : row) {
                System.out.print(col + " ");
            }
            System.out.println();
        }
        return dp[len1][len2];
    }
}
