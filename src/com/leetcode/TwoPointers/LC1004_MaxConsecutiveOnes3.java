package com.leetcode.TwoPointers;

public class LC1004_MaxConsecutiveOnes3 {
    public int longestOnes(int[] A, int K) {
        return helper(K, A);
    }

    private int helper(int k, int[] s) {
        int count0 = 0;
        int count1 = 0;
        int index0 = -1;
        int index1 = -1;
        int maxLength = 0;
        int start = 0;
        for (int i = 0; i < s.length; i++) {
            if (s[i] == 0) {
                count0++;
            } else {
                count1++;
            }
            while (count0 > k) {
                if (s[start++] == 0) {
                    count0--;
                }
            }
            maxLength = Math.max(maxLength, i - start + 1);
        }
        return maxLength;
    }
}
