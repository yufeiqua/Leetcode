package com.leetcode.DFS.DFS;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class LC0291_WordPattern2 {

    /**
     * 问清楚是怎么映射？
     * TODO: Here follow means a full match, such that there is a bijection between a letter in pattern and a non-empty substring in str
     * 只在试探没有成功的时候删去mapping.
     */

    public boolean wordPatternMatch(String pattern, String str) {
        if (pattern == null && str == null || pattern.length() == 0 && str.length() == 0) return true;
        if (pattern == null || pattern.length() == 0 || str == null || str.length() == 0) return false;
        Map<Character, String> patterns = new HashMap<>();
        Set<String> patterns2 = new HashSet<>();

        return dfs(pattern, 0, str, 0, patterns, patterns2);
    }

    private boolean dfs(String pattern, int indexI, String str, int indexJ, Map<Character, String> patterns, Set<String> patterns2) {
        if (indexJ == str.length() && indexI == pattern.length()) return true;
        if (indexJ == str.length() || indexI == pattern.length()) return false;

        char ch = pattern.charAt(indexI);
        String matched = patterns.get(ch);
        String substring;
        if (matched != null) {
            /**
             * 在这里做DFS不去backtrack,因为这里不是第一次试探.
             */
            if (!str.startsWith(matched, indexJ)) return false;
            return dfs(pattern, indexI + 1, str, indexJ + matched.length(), patterns, patterns2);
        }
        for (int j = indexJ + 1; j <= str.length(); j++) {
            substring = str.substring(indexJ, j);
            if (patterns2.contains(substring)) continue;
            patterns.put(ch, substring);
            patterns2.add(substring);
            if (dfs(pattern, indexI + 1, str, j, patterns, patterns2)) return true;
            patterns2.remove(substring);
            patterns.remove(ch);
        }
        return false;
    }
}
