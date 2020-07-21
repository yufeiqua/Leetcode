package com.leetcode.BinarySearch;

public class LC0875_KOKOEatingBananas {
    public int minEatingSpeed(int[] piles, int H) {
        int max = Integer.MIN_VALUE;
        for (int pile : piles) {
            max = Math.max(max, pile);
        }
        int left = 1, right = max;
        int mid;
        int hours;
        while (left <= right) {
            mid = (left + right) / 2;
            hours = calculateHours(mid, piles);
            if (hours > H) {
                left = mid + 1;
            } else {
                right = mid + 1;
            }
        }
        // post process:
        int retLeft = calculateHours(left, piles);
        int retRight = calculateHours(right, piles);
        if(retLeft <= H && retRight <= H) return Math.min(left, right);
        if(retLeft <= H) return left;
        if(retRight <= H) return right;
        return -1;
    }

    private int calculateHours(int rate, int[] piles) {
        int hours = 0;
        for (int pile : piles) {
            hours += pile / rate + 1;
        }
        return hours;
    }
}
