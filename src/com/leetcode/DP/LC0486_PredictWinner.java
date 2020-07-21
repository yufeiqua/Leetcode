package com.leetcode.DP;

import java.util.Arrays;

public class LC0486_PredictWinner {
    public boolean PredictTheWinner(int[] nums) {
        if (nums == null || nums.length == 0) return false;
        int len = nums.length;
        int[][] dp = new int[len][len];
        int total = 0;
        for (int i = len - 1; i >= 0; i--) {
            total += nums[i];
            for (int j = i; j < len; j++) {
                int val1 = (i + 2) <= j ? dp[i + 2][j] : 0;
                int val2 = (i + 1 <= j - 1) ? dp[i + 1][j - 1] : 0;
                int val3 = (i <= j - 2) ? dp[i][j - 2] : 0;
                dp[i][j] = Math.max(nums[i] + Math.min(val1, val2), nums[j] + Math.min(val2, val3));
            }
        }
        return total - dp[0][len - 1] < dp[0][len - 1];
    }

    public int coinChange(int[] coins, int amount) {
        Arrays.sort(coins);
        int[] dp = new int[amount + 1];
        dp[0] = 0;
        for (int count = 1; count <= amount; count++) {
            int minNumber = amount;
            for (int coin : coins) {
                if (count >= coin) {
                    minNumber = Math.min(dp[count - coin], minNumber);
                }
            }
            dp[count] = minNumber + 1;
        }
        return dp[amount];
    }
}
