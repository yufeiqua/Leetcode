package com.leetcode.DFS.Prunning_DP;

import java.util.*;

public class LC0140_WordBreak2ConstructGraph {
    public List<String> wordBreak(String s, List<String> wordDict) {
        Set<String> dictionary = new HashSet<>(wordDict);
        boolean[] memo = new boolean[s.length()];
        Arrays.fill(memo, true);
        List<String> res = new ArrayList<>();
        return res;
    }

}
