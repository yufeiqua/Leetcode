package com.leetcode.Google;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LC0679_24Games {
    public boolean judgePoint24(int[] nums) {
        List<Double> remaining = Arrays.asList((double) nums[0], (double) nums[1], (double) nums[2], (double) nums[3]);
        return dfs(remaining);
    }

    private boolean dfs(List<Double> remaining) {
        if (remaining.size() == 1) {
            return Math.abs(remaining.get(0) - 24.0) < 0.0001;
        }
        for (int i = 0; i < remaining.size(); i++) {
            for (int j = 0; j < remaining.size(); j++) {
                if (i == j) continue;
                for (Double next : generateAllPossibleOperations(remaining.get(i), remaining.get(j))) {
                    List<Double> nextLevelList = new ArrayList<>();
                    for (int k = 0; k < remaining.size(); k++) {
                        if (k == i || k == j) continue;
                        nextLevelList.add(remaining.get(k));
                    }
                    nextLevelList.add(next);
                    if (dfs(nextLevelList)) return true;
                }
            }
        }
        return false;
    }

    private List<Double> generateAllPossibleOperations(double i, double j) {
        List<Double> ret = new ArrayList<>();
        ret.add(i + j);
        ret.add(i * j);
        ret.add(i - j);
        if (Math.abs(j - 0.0) > 0.0001) ret.add(i / j);
        return ret;
    }
}
