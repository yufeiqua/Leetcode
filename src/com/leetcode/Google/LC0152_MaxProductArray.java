package com.leetcode.Google;

public class LC0152_MaxProductArray {
    public int maxProduct(int[] nums) {
        if (nums == null || nums.length == 0) return Integer.MIN_VALUE;
        int minProduct = nums[0];
        int maxProduct = nums[0];
        int ret = maxProduct;
        // Trying to make max product larger and min product smaller.
        // positive * positive => larger and negative * positive => smaller
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > 0) {
                maxProduct = Math.max(nums[i], maxProduct * nums[i]);
                minProduct = Math.min(nums[i], minProduct * nums[i]);
            } else if (nums[i] < 0) {
                int temp = maxProduct;
                maxProduct = Math.max(nums[i], minProduct * nums[i]);
                minProduct = Math.min(nums[i], temp * nums[i]);
            } else {
                maxProduct = 0;
                minProduct = 1;
            }
            ret = Math.max(maxProduct, ret);
        }

        return Math.max(maxProduct, ret);
    }
}

class testLC0152 {
    public static void main(String[] args) {
        LC0152_MaxProductArray maxProductArray = new LC0152_MaxProductArray();
        int[] nums = new int[]{2, 0, 3, -2};
        int ret = maxProductArray.maxProduct(nums);
        System.out.println(ret);
    }
}
