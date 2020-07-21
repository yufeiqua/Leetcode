package com.leetcode.DFS.Prunning_DP;

import java.util.ArrayList;
import java.util.List;

public class LC0139_PalindromePartitioning {
    public List<List<String>> partition(String s) {
        List<List<String>> res = new ArrayList<>();
        dfs(s, res, new ArrayList<>(), 0);
        return res;
    }

    private void dfs(String s, List<List<String>> res, List<String> path, int currIndex) {
        if (currIndex == s.length()) {
            res.add(new ArrayList<>(path));
            return;
        }
        for (int i = currIndex + 1; i <= s.length(); i++) {
            String subString = s.substring(currIndex, i);
            if (isPalindrome(subString)) {
                path.add(subString);
                dfs(s, res, path, i);
                path.remove(path.size() - 1);
            }
        }
    }

    private boolean isPalindrome(String s) {
        int start = 0;
        int end = s.length() - 1;
        while (start < end) {
            if (s.charAt(start) == s.charAt(end)) {
                start++;
                end--;
            } else {
                return false;
            }
        }
        return true;
    }
}
