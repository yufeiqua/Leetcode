package com.leetcode.DP;

public class LC0174_DungeonGame {
    public int calculateMinimumHP(int[][] dungeon) {
        int row = dungeon.length;
        int col = dungeon[0].length;

        int[][] dp = new int[row][col];
        for (int i = row - 1; i >= 0; i--) {
            for (int j = col - 1; j >= 0; j--) {
                if (i == row - 1 && j == col - 1) {
                    dp[i][j] = dungeon[i][j] < 0 ? Math.abs(dungeon[i][j]) + 1: 0;
                    continue;
                }
                int ret = Integer.MAX_VALUE;
                if (j < col - 1) {
                    ret = Math.min(ret, dp[i][j + 1] <= dungeon[i][j] ? 1 : dp[i][j + 1] - dungeon[i][j]);
                }
                if (i < row - 1) {
                    ret = Math.min(ret, dp[i + 1][j] <= dungeon[i][j] ? 1 : dp[i + 1][j] - dungeon[i][j]);
                }
                dp[i][j] = ret;
            }
        }
        for(int[] r : dp) {
            for(int ele : r) {
                System.out.print(ele + " ");
            }
            System.out.println();
        }
        return dp[0][0];
    }
}
