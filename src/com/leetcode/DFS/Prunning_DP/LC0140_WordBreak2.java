package com.leetcode.DFS.Prunning_DP;

import java.util.*;

public class LC0140_WordBreak2 {
    /**
     * Time Complexity: 填memo O(N), DFS cost = O(n) O(n*n)
     * @param s
     * @param wordDict
     * @return
     */
    public List<String> wordBreak(String s, List<String> wordDict) {
        Set<String> dictionary = new HashSet<>(wordDict);
        boolean[] memo = new boolean[s.length()];
        Arrays.fill(memo, true);
        List<String> res = new ArrayList<>();
        dfs(s, dictionary, res, new StringBuilder(), 0, memo);
        return res;
    }

    /**
     * Up to bottom DFS
     * bottom to up DFS
     */

    private void dfs(String s, Set<String> dict, List<String> res, StringBuilder path, int index, boolean[] memo) {
        if (index == s.length()) {
            res.add(path.toString());
            return;
        }
        if (!memo[index]) return;
        for (int i = index + 1; i <= s.length(); i++) {
            String word = s.substring(index, i);
            int length = path.length();
            if (dict.contains(word)) {
                if (path.length() == 0) {
                    path.append(word);
                } else {
                    path.append(" ").append(word);
                }
                dfs(s, dict, res, path, i, memo);
                path.setLength(length);
            }
        }
        if (res.size() == 0) {
            memo[index] = false;
        }
    }
}
