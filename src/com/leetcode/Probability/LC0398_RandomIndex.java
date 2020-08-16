package com.leetcode.Probability;

import java.util.Random;

public class LC0398_RandomIndex {
    class Solution {
        private int[] nums;
        private final Random random;

        {
            random = new Random();
        }

        public Solution(int[] nums) {
            this.nums = nums;
        }

        public int pick(int target) {
            Integer index = null;
            int cnt = 0;
            for (int i = 0; i < nums.length; i++) {
                if (nums[i] == target) {
                    cnt++;
                    int randomIndex = random.nextInt(cnt);
                    if (randomIndex == 0) {
                        index = i;
                    }
                }
            }
            assert index != null;
            return index;
        }
    }
}
