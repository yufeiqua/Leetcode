package com.leetcode.TwoPointers;

import java.util.Arrays;

public class LC0259_TreeSumSmaller {
    public int threeSumSmaller(int[] nums, int target) {
        Arrays.sort(nums);
        int count = 0;
        for (int i = 0; i < nums.length - 2; i++) {
            int twoSumTarget = target - nums[i];
            int start = i + 1;
            int end = nums.length - 1;
            while (start < end) {
                if (nums[start] + nums[end] >= twoSumTarget) {
                    end--;
                } else {
                    count += end - start;
                    start++;
                }
            }
        }
        return count;
    }
}
