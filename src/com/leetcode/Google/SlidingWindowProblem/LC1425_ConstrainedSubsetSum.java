package com.leetcode.Google.SlidingWindowProblem;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

class LC1425_ConstrainedSubsetSumTest {
    public static void main(String[] args) {
        int sum = LC1425_ConstrainedSubsetSum.constrainedSubsetSum(new int[]{100,0,0,0,1,2,3}, 2);
        System.out.println(sum);
    }
}

public class LC1425_ConstrainedSubsetSum {
    public static int constrainedSubsetSum(int[] nums, int k) {
        Deque<Integer> deque = new LinkedList<>();
        int[] ret = Arrays.copyOf(nums, nums.length);
        int maxSum = 0;
        for (int i = 0; i < nums.length; i++) {
            ret[i] += deque.isEmpty() ? 0 : deque.peekFirst();
            maxSum = Math.max(maxSum, ret[i]);
            while (!deque.isEmpty() && deque.peekLast() < ret[i]) {
                deque.pollLast();
            }
            if (ret[i] > 0) deque.offerLast(ret[i]);
            if (i >= k && !deque.isEmpty() && deque.peekFirst() == ret[i - k]) deque.pollFirst();
        }
        return maxSum;
    }
}

