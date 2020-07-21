package com.leetcode.DFS.DFS;

import java.util.ArrayList;
import java.util.List;

public class LC0022_GenerateParentheses {
    public List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        dfs(n, res, 0, 0, new StringBuilder());
        return res;
    }

    private void dfs(int n, List<String> res, int delta, int index, StringBuilder path) {
        if (index == 2 * n && delta == 0) {
            res.add(path.toString());
            return;
        }
        if (delta < 0) return;
        int len = path.length();
        path.append('(');
        dfs(n, res, delta + 1, index + 1, path);
        path.setLength(len);
        path.append(')');
        dfs(n, res, delta - 1, index + 1, path);
    }
}
