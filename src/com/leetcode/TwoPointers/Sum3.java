package com.leetcode.TwoPointers;

import java.security.KeyPair;
import java.util.*;

class test3Sum {
    public static void main(String[] args) {
        Sum3 sSum = new Sum3();
        sSum.threeSum(new int[]{-1, 0, 1, 2, -1, -4});
    }
}

public class Sum3 {
    class Pair {
        int val1;
        int val2;

        public Pair(int val1, int val2) {
            this.val1 = val1;
            this.val2 = val2;
        }
    }

    public List<List<Integer>> threeSum(int[] nums) {
        Set<Integer> duplicate = new HashSet<>();
        Set<Pair> set = new HashSet<>();
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i + 2 < nums.length; i++) {
            int curr = nums[i];
            if (!duplicate.add(curr)) continue;
            Set<Integer> seen = new HashSet<>();
            for (int j = i + 1; j < nums.length; j++) {
                int match = -curr - nums[j];
                if (seen.contains(match)) {
                    int v1 = Math.min(match, Math.min(curr, nums[j]));
                    int v2 = Math.max(match, Math.max(curr, nums[j]));
                    if (set.add(new Pair(v1, v2))) {
                        res.add(Arrays.asList(curr, nums[j], match));
                    }
                }
                seen.add(nums[j]);
            }
        }
        return res;
    }

}
