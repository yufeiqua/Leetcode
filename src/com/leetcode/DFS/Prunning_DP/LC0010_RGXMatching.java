package com.leetcode.DFS.Prunning_DP;

public class LC0010_RGXMatching {
    private static final char repeatPreceding = '*';
    private static final char matchingSingleChar = '.';

    public boolean isMatch(String text, String pattern) {
//        Boolean[][] memo = new Boolean[s.length() + 1][p.length() + 1];
//        return dfs(s, p, 0, 0, memo);
        boolean[][] dp = new boolean[text.length() + 1][pattern.length() + 1];
        dp[text.length()][pattern.length()] = true;

        for (int i = text.length(); i >= 0; i--){
            for (int j = pattern.length() - 1; j >= 0; j--){
                boolean first_match = (i < text.length() &&
                        (pattern.charAt(j) == text.charAt(i) ||
                                pattern.charAt(j) == '.'));
                if (j + 1 < pattern.length() && pattern.charAt(j+1) == '*'){
                    dp[i][j] = dp[i][j+2] || first_match && dp[i+1][j];
                } else {
                    dp[i][j] = first_match && dp[i+1][j+1];
                }
            }
        }
        for(boolean[] row:dp) {
            for(boolean col : row) {
                System.out.print(col + " ");
            }
           System.out.println();
        }
        return dp[0][0];
    }

    private boolean dp(String s, String p) {
        boolean[][] dp = new boolean[s.length() + 1][p.length() + 1];
        dp[s.length()][p.length()] = true;
        for (int i = s.length() - 1; i >= 0; i--) {
            for (int j = p.length() - 1; j >= 0; j--) {
                boolean isMatched = i < s.length() &&
                        (p.charAt(j) == s.charAt(i) || p.charAt(j) == matchingSingleChar);
                if (j + 1 == p.length() || p.charAt(j + 1) != repeatPreceding) {
                    dp[i][j] |= isMatched && dp[i + 1][j + 1];
                } else {
                   dp[i][j] |= isMatched && dp[i + 1][j + 2] || dp[i][j + 2];
                }
            }
        }
        return dp[0][0];

    }

    private boolean dfs(String s, String p, int idxS, int idxP, Boolean[][] memo) {
        if (idxP == p.length()) {
            return idxS == s.length();
        }
        if (memo[idxS][idxP] != null) return memo[idxS][idxP];
        if (idxP == p.length() - 1 || idxP + 1 < p.length() && p.charAt(idxP + 1) != repeatPreceding) {
            if (idxS < s.length() && charIsMatch(s, p, idxS, idxP) && dfs(s, p, idxS + 1, idxP + 1, memo)) {
                memo[idxS][idxP] = true;
                return true;
            }
        } else {
            int start = idxS - 1;
            while (start < idxS || start < s.length() && charIsMatch(s, p, start, idxP)) {
                if (dfs(s, p, start + 1, idxP + 2, memo)) {
                    memo[idxS][idxP] = true;
                    return true;
                }
                start++;
            }
        }
        memo[idxS][idxP] = false;
        return false;
    }

    private boolean charIsMatch(String s, String p, int idxS, int idxP) {
        return p.charAt(idxP) == matchingSingleChar || s.charAt(idxS) == p.charAt(idxP);
    }

}
