package com.leetcode.DFS.Prunning_DP;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class LC0294_FlipGame2 {
    private Map<String, Boolean> memo = new HashMap<>();

    public boolean canWin(String s) {
        return dfs(s.toCharArray());
    }

    private boolean dfs(char[] str) {
        Boolean value = memo.get(Arrays.toString(str));
        System.out.print(Arrays.toString(str));
        if(value != null) {
            return value;
        }
        if (!couldFlip(str)) {
            memo.put(Arrays.toString(str), false);
            return false;
        }

        for (int i = 0; i < str.length - 1; i++) {
            if (str[i] == '+' && str[i + 1] == '+') {
                /**
                 * TODO: 不管输赢都要reset back，因为这是博弈问题，每一层都要最优方案，对手一位不愿意去输。
                 */
                str[i] = '-';
                str[i + 1] = '-';
                boolean ret = dfs(str);
                str[i] = '+';
                str[i + 1] = '+';
                if (!ret) {
                    memo.put(Arrays.toString(str), true);
                    return true;
                }

            }
        }
        memo.put(Arrays.toString(str), false);
        return false;
    }

    private boolean couldFlip(char[] str) {
        for (int i = 0; i < str.length - 1; i++) {
            if (str[i] == '+' && str[i + 1] == '+') return true;
        }
        return false;
    }
}
