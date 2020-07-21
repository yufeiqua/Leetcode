package com.leetcode.DP;
class test312{
   public static void main(String[] args) {
       LC0312_BurstBalloons burstBalloons = new LC0312_BurstBalloons();
       int ret = burstBalloons.maxCoins(new int[] {3,1,5,8});
       System.out.print(ret);
   }
}
public class LC0312_BurstBalloons {
    public int maxCoins(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int len = nums.length;
        int[][] dp = new int[len][len];
        dp[len - 1][len - 1] = nums[len - 1];
        for (int i = len - 1; i >= 0; i--) {
            for (int j = i; j < len; j++) {
                for (int k = i; k <= j; k++) {
                    int left = i == k ? 1 : nums[i];
                    int right = j == k ? 1 : nums[j];
                    int val = i + 1 == len || k - 1 < 0 ? 0 : dp[i + 1][k - 1];
                    val += k + 1 == len || j - 1 < 0 ? 0 : dp[k + 1][j - 1];
                    dp[i][j] = val + left * nums[k] * right;

//                    int value = 0;
//                    value += i == k ? 0 : dp[i][k - 1];
//                    value += j == k ? 0 : dp[k + 1][j];
//                    value += (i == 0 ? 1 : nums[i - 1])
//                            * (j == len - 1 ? 1 : nums[j + 1])
//                            * nums[k];
//                    dp[i][j] = Math.max(value, dp[i][j]);
                }
            }
        }
        for(int[] row : dp) {
            for(int col : row)  {
                System.out.print(col + " ");
            }
            System.out.println();
        }

        return dp[0][len - 1];
//        if (nums == null || nums.length == 0) return 0;
//        int len = nums.length;
//        int[][] dp = new int[len][len];
//        for (int i = 0; i < len; i++) {
//            dp[i][i] = nums[i];
//        }
//        for (int i = len - 1; i >= 0; i--) {
//            for (int j = i; j < len; j++) {
//                for(int k = i; k <= j; k++) {
//                    int left = i == k ? 1 : nums[i];
//                    int right = j == k ? 1 : nums[j];
//                    int val = i + 1 == len || k - 1 < 0 ? 0 : dp[i + 1][k - 1];
//                    val += k + 1 == len || j - 1 < 0 ? 0 : dp[k + 1][j - 1];
//                    dp[i][j] = val + left * nums[k] * right;
//                }
//            }
//        }
//        return dp[0][len - 1];

    }
}
// dp[i][j] = Math.max(dp[i][j], dp[i][k - 1] + dp[k + 1][j] + nums[k] * nums[i - 1] * nums[j + 1]);
