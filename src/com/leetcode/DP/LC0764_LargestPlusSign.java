package com.leetcode.DP;

import java.util.HashSet;
import java.util.Set;

public class LC0764_LargestPlusSign {
    public int orderOfLargestPlusSign(int N, int[][] mines) {
        int[][] dp = new int[N][N];
        Set<Integer> minesSet = new HashSet<>();
        for (int[] mine : mines) {
            if (mine.length == 0) continue;
            minesSet.add(mine[0] * N + mine[1]);
        }
        for (int i = 0; i < N; i++) {
            // left 2 right
            for (int j = 0; j < N; j++) {
                if (!minesSet.contains(i * N + j)) {
                    if (j == 0) {
                        dp[i][0] = 1;
                        continue;
                    }
                    dp[i][j] = dp[i][j - 1] + 1;
                } else {
                    dp[i][j] = 0;
                }

            }
            // right 2 left
            for (int j = N - 1; j >= 0; j--) {
                if (!minesSet.contains(i * N + j)) {
                    if (j == N - 1) {
                        dp[i][N - 1] = Math.min(dp[i][N - 1], 1);
                        continue;
                    }
                    dp[i][j] = Math.min(dp[i][j], dp[i][j + 1] + 1);
                } else {
                    dp[i][j] = 0;
                }
            }
        }
        for (int j = 0; j < N; j++) {
            // top 2 bottom
            for (int i = 0; i < N; i++) {
                if (!minesSet.contains(i * N + j)) {
                    if (i == 0) {
                        dp[0][j] = Math.min(dp[0][j], 1);
                        continue;
                    }
                    dp[i][j] = Math.min(dp[i][j], dp[i - 1][j] + 1);
                } else {
                    dp[i][j] = 0;
                }
            }
            // bottom 2 top
            for (int i = N - 1; i >= 0; i--) {
                if (!minesSet.contains(i * N + j)) {
                    if (i == N - 1) {
                        dp[N - 1][j] = Math.min(dp[N - 1][j], 1);
                        continue;
                    }
                    dp[i][j] = Math.min(dp[i][j], dp[i + 1][j] + 1);
                } else {
                    dp[i][j] = 0;
                }
            }
        }
        int value = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                value = Math.max(value, dp[i][j]);
            }
        }
        return value;
    }
}
