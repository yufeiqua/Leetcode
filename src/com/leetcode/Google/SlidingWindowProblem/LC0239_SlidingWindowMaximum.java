package com.leetcode.Google.SlidingWindowProblem;

import java.util.Deque;
import java.util.LinkedList;

class LC0239Test {
    public static void main(String[] args) {
        int[] ret = LC0239_SlidingWindowMaximum.maxSlidingWindow(new int[] {1,3,-1,0,5,3,6,7}, 3);
        for(int r : ret) System.out.println(r);
    }
}

public class LC0239_SlidingWindowMaximum {
    public static int[] maxSlidingWindow(int[] nums, int k) {
        Deque<Integer> deque = new LinkedList<>();
        int[] ret = new int[nums.length - k + 1];
        int i = 0;
        for (; i < nums.length; i++) {
            if (!deque.isEmpty() && i - deque.peekFirst() + 1 > k) {
                deque.pollFirst();
            }
            while (!deque.isEmpty() && nums[i] >= nums[deque.peekLast()]) {
                deque.pollLast();
            }
            deque.offerLast(i);
            if(i < k - 1) continue;
            ret[i - k + 1] = nums[deque.peekFirst()];
        }
        System.out.println(i);
        return ret;
    }
}
