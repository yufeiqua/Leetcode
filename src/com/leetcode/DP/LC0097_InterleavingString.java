package com.leetcode.DP;

public class LC0097_InterleavingString {
    public boolean isInterleave(String s1, String s2, String s3) {
        int len1 = s1.length();
        int len2 = s2.length();
        int len3 = s3.length();
        if (len3 == 0 && len1 == 0 && len2 == 0) return true;
        if(len3 != len1 + len2) return false;
        if (len1 == 0 && !s3.equals(s2)) return false;
        if (len2 == 0 && !s1.equals(s3)) return false;


        boolean[][] dp = new boolean[len1 + 1][len2 + 1];

        for (int i = 0; i <= len1; i++) {
            for (int j = 0; j <= len2; j++) {
                if (i == 0 && j == 0) {
                    dp[0][0] = true;
                    continue;
                }
                if (i == 0) {
                    dp[0][j] = s2.charAt(j - 1) == s3.charAt( j - 1) && dp[0][j - 1];
                    continue;
                }
                if (j == 0) {
                    dp[i][0] = s1.charAt(i - 1) == s3.charAt(i - 1) && dp[i - 1][0];
                    continue;
                }
                int k = i + j;
                if(k == s3.length()) return false;
                //System.out.println(i + "  " + j + " " + k);
                char matchedChar = s3.charAt(k - 1);
                //System.out.println(s1.charAt(i - 1) + " " + s2.charAt(j - 1) + " " + matchedChar);
                if (s1.charAt(i - 1) == matchedChar) {
                    dp[i][j] = dp[i - 1][j];
                }
                if (s2.charAt(j - 1) == matchedChar) {
                    dp[i][j] |= dp[i][j - 1];
                }
            }
        }
        return dp[len1][len2];
    }
}
