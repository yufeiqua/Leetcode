package com.leetcode.DFS.DFS;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class LC0039_CombinationSum {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        if(candidates == null || candidates.length == 0) return new ArrayList<>();
        List<List<Integer>> res = new ArrayList<>();
        dfs(res, new ArrayList<>(), candidates, 0, target, 0);
        return res;
    }

    private void dfs(List<List<Integer>> res, List<Integer> possibleSolution, int[] candidates, int index, int target, int currValue) {
        if (currValue == target) {
            res.add(new ArrayList<>(possibleSolution));
            return;
        }
        if (index == candidates.length || currValue > target) return;
        for (int i = index; i < candidates.length; i++) {
            possibleSolution.add(candidates[i]);
            dfs(res, possibleSolution, candidates, i, target, currValue + candidates[i]);
            possibleSolution.remove(possibleSolution.size() - 1);
        }
    }
}
