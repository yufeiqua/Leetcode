package com.leetcode.DFS.Prunning_DP;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LC0139_WordBreak1 {
    public boolean wordBreak(String s, List<String> wordDict) {
        boolean[] dp = new boolean[s.length() + 1];
        dp[s.length()] = true;
        Set<String> dict = new HashSet<>(wordDict);
        for (int i = s.length() - 1; i >= 0; i--) {
            for (int j = i + 1; j <= s.length(); j++) {
                String subString = s.substring(i, j);
                if(dict.contains(subString) && dp[j]) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[0];
    }
}
