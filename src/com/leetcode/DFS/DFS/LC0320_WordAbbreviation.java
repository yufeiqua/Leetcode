package com.leetcode.DFS.DFS;

import java.util.ArrayList;
import java.util.List;

public class LC0320_WordAbbreviation {
    /**
     * 之前忘记在base case里面check 要不要加入count 导致漏解.
     * @param word
     * @return
     */
    public List<String> generateAbbreviations(String word) {
        List<String> res = new ArrayList<>();
        if(word == null || word.length() == 0){
            res.add(word);
            return res;
        }
        dfs(word.toCharArray(), res, 0, new StringBuilder(), 0);
        return res;
    }

    private void dfs(char[] word, List<String> res, int index, StringBuilder path, int count) {
        if(index == word.length) {
            int length = path.length();
            if(count != 0) {
                path.append(count);
            }
            res.add(path.toString());
            path.setLength(length);
            return;
        }
        int length = path.length();
        if(count != 0) {
            path.append(count);
        }
        path.append(word[index]);
        dfs(word, res, index + 1, path, 0);
        path.setLength(length);
        dfs(word, res, index + 1, path, count + 1);
    }

}
