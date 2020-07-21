package com.leetcode.BinarySearch;

class testLC0410 {
    public static void main(String[] args) {
        LC0410_SplitArrayWithMaxSum lc0410_splitArrayWithMaxSum = new LC0410_SplitArrayWithMaxSum();
        int[] nums = new int[]{7, 2, 5, 10, 8};
        int m = 2;
        int ret = lc0410_splitArrayWithMaxSum.splitArray(nums, m);
        System.out.println(ret);
    }

}

public class LC0410_SplitArrayWithMaxSum {
    public int splitArray(int[] nums, int m) {
        int right = 0;
        int left = Integer.MIN_VALUE;
        for (int n : nums) {
            right += n;
            left = Math.max(n, left);
        }
//        System.out.println(left + " " + right);
        int mid;
        int ans = left;
        while (left <= right) {
            mid = left + (right - left) / 2;
            System.out.println(mid);
            if (canSplitArray(nums, m, mid)) {
                right = mid - 1;
//                ans = Math.min(ans, mid);
            } else {
                left = mid + 1;
            }
        }
        return right;
    }

    private boolean canSplitArray(int[] nums, int target, int maxSum) {
        int temp = 0;
        int count = 0;
        for (int num : nums) {
            if (temp + num > maxSum) {
                count++;
                temp = num;
            } else {
                temp += num;
            }
        }
        System.out.println(count >= target);
        return count <= target;
    }

}
