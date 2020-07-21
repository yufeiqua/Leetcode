package com.leetcode.DFS.Prunning_DP;

public class LC0091_DecodeWays {
    public int numDecodings(String s) {
        if (s == null || s.length() == 0) return 0;
        if (s.charAt(0) == '0') return 0;
        int length = s.length();
        int[] dp = new int[length];
        //dp[i] = dp[i - 1] + dp[i - 2]
        /**
         * TODO:
         * 如果当前不和前面combine，就等于维持上一个所有组合的原貌加上单个的我。如过和前面的combine就相当于维持上上种组合的原貌加上我的组合
         * 所以是[i-1] + [i-2]。
         */
        for (int i = 0; i < length; i++) {
            int currentNumber = s.charAt(i) - '0';
            if (i == 0) {
                if (currentNumber == 0) return 0;
                dp[i] = 1;
                continue;
            }
            int prevNum = s.charAt(i - 1) - '0';
            int combinedNumber = prevNum * 10 + currentNumber;
            if (combinedNumber == 0) return 0;
            if (currentNumber == 0 && combinedNumber > 26) return 0;
            if (i == 1) {
                if (currentNumber == 0 || combinedNumber > 26) {
                    dp[i] = 1;
                } else {
                    dp[i] = dp[i - 1] + 1;
                }
                continue;
            }
            if (currentNumber == 0) {
                dp[i] = dp[i - 2];
                continue;
            }
            if (prevNum == 0 || combinedNumber > 26) {
                dp[i] = dp[i - 1];
                continue;
            }
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[length - 1];
    }

    private int dfs(String s, int index) {
        if (index == s.length()) {
            return 0;
        }
        if (index == 0 && s.charAt(index) == '0') return 0;
        if (s.charAt(index) == '0') {
            if (s.charAt(index - 1) <= '2') {
                return dfs(s, index + 1);
            } else {
                return 0;
            }
        }
        int number = index == 0 ? 0 : (s.charAt(index - 1) - '0') * 10 + s.charAt(index) - '0';
        if (number > 0 && number <= 26) return 1 + dfs(s, index + 1);
        if (number == 0) return 0;
        return dfs(s, index + 1);
    }

}
