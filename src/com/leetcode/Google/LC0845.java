package com.leetcode.Google;

public class LC0845 {
    public int longestMountain(int[] A) {
        if (A.length < 3) return 0;
        if (A.length == 3) {
            if (A[0] < A[1] && A[1] > A[2]) return 3;
            return 0;
        }
        int[] left = new int[A.length];
        int[] right = new int[A.length];
        for (int i = 1; i < A.length; i++) {
            if (A[i] > A[i - 1]) {
                left[i] = left[i - 1] + 1;
            } else {
                left[i] = 0;
            }
        }

        int max = 0;

        for (int i = A.length - 2; i >= 0; i--) {
            if (A[i] > A[i + 1]) {
                right[i] = right[i + 1] + 1;
                max = Math.max(right[i] + left[i] + 1, max);
            } else {
                right[i] = 0;
            }

        }
        return max;
    }
}
