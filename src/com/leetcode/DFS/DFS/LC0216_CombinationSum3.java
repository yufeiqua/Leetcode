package com.leetcode.DFS.DFS;

import java.util.ArrayList;
import java.util.List;

public class LC0216_CombinationSum3 {
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> res = new ArrayList<>();
        dfs(res, new ArrayList<>(), k, 0, n, 0);
        return res;
    }

    private void dfs(List<List<Integer>> res, List<Integer> path, int k, int number, int targetSum, int currentSum) {
        if (path.size() == k && targetSum == currentSum) {
            res.add(new ArrayList<>(path));
            return;
        }
        if (path.size() == k || currentSum > targetSum) return;

        for (int i = number; i < 9; i++) {
            path.add(i);
            dfs(res, path, k,i + 1, targetSum, currentSum + i);
            path.remove(path.size() - 1);
        }
    }


}
