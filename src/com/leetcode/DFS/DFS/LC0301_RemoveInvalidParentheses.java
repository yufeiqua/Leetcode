package com.leetcode.DFS.DFS;

import java.util.ArrayList;
import java.util.List;

public class LC0301_RemoveInvalidParentheses {
    /**
     * 考虑到去重,关键点，保证留下来的答案具有相同的prefix或者suffix, 类似题目 90 Subset2
     * Follow up 找到任意一个解.
     *
     */
    public List<String> removeInvalidParentheses(String s) {
        List<String> res = new ArrayList<>();
        if(s == null) return res;
        if(s.length() == 0) {
            res.add(s);
            return res;
        }
        char[] str = s.toCharArray();
        int[] count = countRemoveLeftAndRightParentheses(str);
        if(count[0] == 0 && count[1] == 0) {
            res.add(s);
            return res;
        }
        dfs(res, str, 0, new StringBuilder(), 0, count[0], count[1]);
        return res;
    }

    private int[] countRemoveLeftAndRightParentheses(char[] str) {
        int right = 0;
        int left = 0;
        for (char ele : str) {
            if (ele == '(') {
                left++;
            } else if (ele == ')') {
                if (left == 0) {
                    right++;
                }
                else{
                    left--;
                }
            }
        }
        return new int[]{left, right};
    }

    private void dfs(List<String> res, char[] str, int index, StringBuilder path, int delta, int rmL, int rmR) {
        if (delta == 0 && rmL == 0 && rmR == 0 && index == str.length) {
            res.add(path.toString());
            return;
        }
        if (index >= str.length || delta < 0 || rmL < 0 || rmR < 0) return;

        char ch = str[index];
        int length = path.length();
        if (ch == '(') {
            // remove left parentheses, it needs to avoid duplicate result
            int firstIndexIsNotLeftParentheses = index;
            while (firstIndexIsNotLeftParentheses < str.length && str[firstIndexIsNotLeftParentheses] == '(') {
                firstIndexIsNotLeftParentheses++;
            }
            int count = firstIndexIsNotLeftParentheses - index;
            dfs(res, str, firstIndexIsNotLeftParentheses, path, delta, rmL - count, rmR);
            //keep right parentheses
            path.append("(");
            dfs(res, str, index + 1, path, delta + 1, rmL, rmR);
            path.setLength(length);
        } else if (ch == ')') {
            //remove right parentheses, it needs to avoid duplicate result
            int firstIndexIsNotRightParentheses = index;
            while (firstIndexIsNotRightParentheses < str.length && str[firstIndexIsNotRightParentheses] == ')') {
                firstIndexIsNotRightParentheses++;
            }
            int count = firstIndexIsNotRightParentheses - index;
            dfs(res, str, firstIndexIsNotRightParentheses, path, delta, rmL, rmR - count);
            //keep right parentheses
            path.append(")");
            dfs(res, str, index + 1, path, delta - 1, rmL, rmR);
            path.setLength(length);
        } else {
            path.append(ch);
            dfs(res, str, index + 1, path, delta, rmL, rmR);
            path.setLength(length);
        }
    }

}
