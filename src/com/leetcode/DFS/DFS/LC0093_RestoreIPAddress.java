package com.leetcode.DFS.DFS;

import java.util.ArrayList;
import java.util.List;

public class LC0093_RestoreIPAddress {
    public List<String> restoreIpAddresses(String s) {
        List<String> res = new ArrayList<>();
        dfs(s, res, new StringBuilder(), 0, 0);
        return res;
    }

    private void dfs(String s, List<String> res, StringBuilder path, int index, int partNumber) {
        if (index == s.length() && partNumber == 4) {
            res.add(path.toString());
            return;
        }
        if(index == s.length() || partNumber > 4) return;
        for (int l = 1; l <= 3; l++) {
            int length = path.length();
            if(index + l > s.length()) {
                break;
            }
            String part = s.substring(index, index + l);
            int number = Integer.parseInt(part);
            if(number > 255) break;
            if(length == 0) {
                path.append(part);
            }
            else{
                path.append('.').append(part);
            }
            dfs(s, res, path,index + l, partNumber + 1);
            path.setLength(length);
            if(number == 0) break;
        }
    }
}
