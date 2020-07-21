package com.leetcode.DFS.Prunning_DP;

public class LC0072_EditDistance_DFS {
    public int minDistance(String word1, String word2) {
        if (word1 == null && word2 == null) return 0;
        if (word1 == null || word2 == null) return word1 == null ? word2.length() : word1.length();
        if (word1.equals(word2)) return 0;
        int[][] memo = new int[word1.length() + 1][word2.length() + 1];
        return dfs(word1, word2, 0, 0, memo);
    }

    private int dfs(String w1, String w2, int idx1, int idx2, int[][] memo) {
        if (idx1 == w1.length() && idx2 == w2.length()) {
            return 0;
        }
        if (idx1 == w1.length()) {
            return w2.length() - idx2;
        }
        if (idx2 == w2.length()) {
            return w1.length() - idx1;
        }
        if (memo[idx1][idx2] != 0) return memo[idx1][idx2];
        int ret = Math.max(w1.length(), w2.length());

        if (w1.charAt(idx1) != w2.charAt(idx2)) {
            //add
            ret = Math.min(dfs(w1, w2, idx1, idx2 + 1, memo), ret);
            //remove
            ret = Math.min(dfs(w1, w2, idx1 + 1, idx2, memo), ret);
            //replace
            ret = Math.min(dfs(w1, w2, idx1 + 1, idx2 + 1, memo), ret);
            memo[idx1][idx2] = ret + 1;
            return ret + 1;
        }
        ret = dfs(w1, w2, idx1 + 1, idx2 + 1, memo);
        memo[idx1][idx2] = ret;
        return ret;
    }
}
