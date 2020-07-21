package com.leetcode.BinarySearch;

import com.leetcode.TwoPointers.LC0340_LongestSubstringAtMostKDistinctCharacters;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class testLC300 {
    public static void main(String[] args) {
        LC0300_LongestIncreasingSubsequence lc0300_longestIncreasingSubsequence = new LC0300_LongestIncreasingSubsequence();
        int[] nums = new int[]{2, 5, 3, 7, 18, 0, 4, 6};
        int length = lc0300_longestIncreasingSubsequence.lengthOfLIS(nums);
        System.out.println(length);
    }
}

public class LC0300_LongestIncreasingSubsequence {
    private Map<Integer, Integer> graph = new HashMap<>();
    private List<Integer> tempBuffer = new ArrayList<>();

    public int lengthOfLIS(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int length = findLength(nums);
        String path = constructPath();
        System.out.println(path);
        return length;
    }

    private String constructPath() {
        StringBuilder sb = new StringBuilder();
        int start = tempBuffer.get(tempBuffer.size() - 1);
        while (graph.get(start) != null) {
            sb.insert(0, start);
            sb.insert(0, "->");
            start = graph.get(start);
        }
        sb.insert(0, start);

        return sb.toString();
    }

    private int findLength(int[] nums) {
        for (int ele : nums) {
            if (tempBuffer.isEmpty()) {
                tempBuffer.add(ele);
                continue;
            }
            int index = findMinElementLargerThanCurrElement(tempBuffer, ele);
            if (index == tempBuffer.size()) {
                tempBuffer.add(ele);
            } else {
                tempBuffer.set(index, ele);
            }
            if (index > 0) {
                graph.put(ele, tempBuffer.get(index - 1));
            }
        }
        return tempBuffer.size();
    }

    private int findMinElementLargerThanCurrElement(List<Integer> buffer, int target) {
        int start = 0, end = buffer.size() - 1;
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (buffer.get(mid) < target) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        return start;
    }
}
