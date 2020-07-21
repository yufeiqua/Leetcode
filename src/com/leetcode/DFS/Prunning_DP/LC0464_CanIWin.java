package com.leetcode.DFS.Prunning_DP;

public class LC0464_CanIWin {
    private Boolean[] memo;
    public boolean canIWin(int maxChoosableInteger, int desiredTotal) {
        int size = 1 << maxChoosableInteger;
        if((1 + maxChoosableInteger) * maxChoosableInteger / 2 < desiredTotal) return false;
        memo = new Boolean[size];
        int pool = size - 1;
        return dfs(pool, maxChoosableInteger, 0, desiredTotal);
    }

    private boolean dfs(int pool, int maxInteger, int curSum, int desiredTotal) {
        if(memo[pool] != null) return memo[pool];
        for(int i = 0; i < maxInteger; i++) {
            int mask = 1 << i;
            if((pool & mask) != 0) {
                if(curSum + i + 1 >= desiredTotal) {
                    memo[pool] = true;
                    return true;
                }
                if(!dfs( pool - mask, maxInteger, curSum + i + 1, desiredTotal)) {
                    memo[pool] = true;
                    return true;
                }
            }
        }
        memo[pool] = false;
        return false;
    }
}
