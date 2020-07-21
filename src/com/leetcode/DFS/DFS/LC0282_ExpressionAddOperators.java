package com.leetcode.DFS.DFS;

import java.util.ArrayList;
import java.util.List;

public class LC0282_ExpressionAddOperators {
    public List<String> addOperators(String num, int target) {
        if (num == null || num.length() == 0) {
            return new ArrayList<>();
        }
        List<String> res = new ArrayList<>();
        dfs(num, target, 0, res, 0, new StringBuilder(), 0);
        return res;
    }

    // keep last value.
    private void dfs(String num, int target, int index, List<String> allResults, int result, StringBuilder path, int lastValue) {
        if (index == num.length()) {
            if (result == target) {
                allResults.add(path.toString());
            }
            return;
        }

        int value = 0;

        for (int i = index; i < num.length(); i++) {
            /**
             * TODO: 溢出
             */
            int length = path.length();
            if (value > Integer.MAX_VALUE / 10) break;
            value *= 10;
            if (value > Integer.MAX_VALUE - (num.charAt(i) - '0')) break;
            value += num.charAt(i) - '0';
            if (path.length() == 0) {
                path.append(value);
                dfs(num, target, i + 1, allResults, result + value, path, value);
                path.setLength(length);
            } else {
                path.append("+").append(value);
                dfs(num, target, i + 1, allResults, result + value, path, value);
                path.setLength(length);

                path.append("-").append(value);
                dfs(num, target, i + 1, allResults, result - value, path, -1 * value);
                path.setLength(length);
                /**
                 * TODO
                 * result = result - lastValue + lastValue * value 为什么这样不可以？
                 */
                path.append("*").append(value);
                dfs(num, target, i + 1, allResults, result - lastValue + lastValue * value, path, lastValue * value);
                path.setLength(length);
            }
            if (value == 0) break;
        }

    }
}
