package com.leetcode.DFS.DFS;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LC0090_Subsets2 {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            List<Integer> path = new ArrayList<>();
            res.add(path);
            return res;
        }
        int[] sortedArray = nums.clone();
        Arrays.sort(sortedArray);
        dfs(sortedArray, res, new ArrayList<>(), 0);
        return res;
    }

    private void dfs(int[] nums, List<List<Integer>> res, List<Integer> path, int index) {
        if (index == nums.length) {
            res.add(new ArrayList<>(path));
            return;
        }
        int element = nums[index];
        path.add(element);
        dfs(nums, res, path, index + 1);
        path.remove(path.size() - 1);
        int firstIndexIsNotPointElement = index;
        while (firstIndexIsNotPointElement < nums.length && nums[firstIndexIsNotPointElement] == element) {
            firstIndexIsNotPointElement++;
        }
        dfs(nums, res, path, firstIndexIsNotPointElement);
    }
}
