package com.leetcode.DP;

public class LC00256_PaintHouse1 {
    /**
     * DP 定义，图第i个房子的时候，用k种颜色图的最小cost.
     */
    public int minCostII(int[][] costs) {
        if (costs == null || costs.length == 0 || costs[0] == null || costs[0].length == 0) return 0;
        int row = costs.length; // house
        int col = costs[0].length; // color
        int[][] dp = new int[row][col];
        for (int i = 0; i < row; i++) {
            int minColor = -1;
            int secondMinColor = -1;
            for (int j = 0; j < col; j++) { // 上一个房子图的颜色.
                if (i == 0) {
                    dp[0][j] = costs[0][j];
                    continue;
                }
                int cost = dp[i - 1][j];
                if (minColor == -1 || cost < dp[i - 1][minColor]) {
                    secondMinColor = minColor;
                    minColor = j;
                } else if (secondMinColor == -1 || cost < dp[i - 1][secondMinColor]) {
                    secondMinColor = j;
                }
            }
            if (i == 0) continue;
            for (int j = 0; j < col; j++) {
                if (j == minColor) {
                    dp[i][j] = dp[i - 1][secondMinColor] + costs[i][j];
                } else {
                    dp[i][j] = dp[i - 1][minColor] + costs[i][j];
                }
            }
        }

        int ret = Integer.MAX_VALUE;
        for (int i = 0; i < col; i++) {
            ret = Math.min(ret, dp[row - 1][i]);
        }
        return ret;
    }


}
