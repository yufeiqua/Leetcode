package com.leetcode.DP;

public class LC0115_DistinctSubsequences {
    /**
     * TODO: 重新思考 -> 见笔记
     */
    public int numDistinct(String s, String t) {
        int len1 = t.length();
        int len2 = s.length();
        int[][] dp = new int[len1 + 1][len2 + 1];
        dp[0][0] = 1;
        for (int i = 1; i <= len1; i++) { // s is empty ""
            dp[i][0] = 0;
        }
        for (int j = 1; j <= len2; j++) { // t is empty ""
            dp[0][j] = 1;
        }
        for (int i = 1; i <= len1; i++) {
            for (int j = i; j <= len2; j++) {
                dp[i][j] = dp[i][j - 1];
                if (t.charAt(i - 1) == s.charAt(j - 1)) {
                    dp[i][j] += dp[i - 1][j - 1];
                }
            }
        }
        return dp[len1][len2];
    }
}
