package com.leetcode.Google.SlidingWindowProblem;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

class LC1438LongestSubArrayWithinLimitTest {
    public static void main(String[] args) {
        int[] nums = new int[]{10, 1, 2, 4, 7, 2};
//        int ret = LC_01438LongestSubArrayWithinLimit.longestSubarray(nums, 5);
//        System.out.println(ret);
    }
}

public class LC1438LongestSubArrayWithinLimit {
    public static int longestSubarray_bruteForce(int[] nums, int limit) {
        Map<Integer, int[]> map = new HashMap();
        int size = 1;
        for (int i = 0; i < nums.length; i++) {
            int[] maxAndMin = map.computeIfAbsent(i, k -> new int[2]);
            maxAndMin[0] = nums[i];
            maxAndMin[1] = nums[i];
            for (int j = 0; j < i; j++) {
                int[] maxAndMinRecord = map.get(j);
                maxAndMinRecord[0] = Math.max(maxAndMinRecord[0], nums[i]);
                maxAndMinRecord[1] = Math.min(maxAndMinRecord[1], nums[i]);
                if (maxAndMinRecord[0] - maxAndMinRecord[1] <= limit) {
                    size = Math.max(size, i - j + 1);
                }
            }
        }
        return size;
    }

    public static int longestSubarray(int[] nums, int limit) {
        Deque<Integer> minDeq = new LinkedList<>();
        Deque<Integer> maxDeq = new LinkedList<>();
        int size = 1;
        int start = 0;
        for (int i = 0; i < nums.length; i++) {
            while (!minDeq.isEmpty() && nums[minDeq.peekLast()] >= nums[i]) {
                minDeq.pollLast();
            }
            while (!maxDeq.isEmpty() && nums[maxDeq.peekLast()] <= nums[i]) {
                maxDeq.pollLast();
            }
            maxDeq.offerLast(i);
            minDeq.offerLast(i);
            if (nums[maxDeq.peekFirst()] - nums[minDeq.peekFirst()] <= limit) {
                size = Math.max(size, i - start + 1);
            }
            while (nums[maxDeq.peekFirst()] - nums[minDeq.peekFirst()] > limit) {
                if (!maxDeq.isEmpty() && maxDeq.peekFirst() == start) maxDeq.pollFirst();
                if (!minDeq.isEmpty() && minDeq.peekFirst() == start) minDeq.pollFirst();
                start++;
            }
        }
        return size;
    }
}
