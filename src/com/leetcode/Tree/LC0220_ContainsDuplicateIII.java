package com.leetcode.Tree;

import javax.swing.event.TreeSelectionEvent;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;

public class LC0220_ContainsDuplicateIII {
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        if (nums == null || nums.length == 0) return false;
        TreeSet<Integer> set = new TreeSet<>();
        for (int i = 0; i < nums.length; i++) {
            if (i > k) {
                set.remove(nums[i - k - 1]);
            }
            int newEle = nums[i];
            int lowerBound = newEle - t;
            int upperBound = newEle + t;
            Integer result = set.floor(upperBound);
            if (result == null) continue;
            if (result >= lowerBound) {
                return true;
            }
            set.add(nums[i]);
        }
        return false;
    }

    /**
     * Handle Duplicate 5,3,5,6,7,10  k = 5
     */
    private boolean helper(int[] nums, int k, int t) {
        if (nums == null || nums.length == 0) return false;
        TreeMap<Long, Integer> map = new TreeMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (i > k) {
                long oldEle = nums[i - k - 1];
                int count = map.get(oldEle);
                if (count - 1 == 0) map.remove(oldEle);
                map.put(oldEle, count - 1);
            }
            long newEle = nums[i];
            long lowerBound = newEle - t;
            long upperBound = newEle + t;
            Long result = map.floorKey(upperBound);
            int count = map.getOrDefault(newEle, 0);
            map.put(newEle, count + 1);
            if (result == null) continue;
            if (result >= lowerBound) {
                return true;
            }

        }
        return false;
    }

    private boolean bucketSort(int[] nums, int k, int t) {
        if (nums == null || nums.length == 0) return false;
        if (t < 0) return false;
        int startPoint = Integer.MIN_VALUE;
        Map<Long, Long> bucket = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (i > k) {
                long lastBucket = ((long) nums[i - k - 1] - Integer.MIN_VALUE) / ((long) t + 1);
                bucket.remove(lastBucket);
            }
            long newEle = (long) nums[i] - startPoint;
            long bucketNum = newEle / (t + 1);
            if (bucket.containsKey(bucketNum)) return true;
            long leftBucket = bucketNum - 1, rightBucket = bucketNum + 1;
            if (bucket.containsKey(leftBucket) && Math.abs(bucket.get(leftBucket) - newEle) <= t) return true;
            if (bucket.containsKey(rightBucket) && Math.abs(bucket.get(rightBucket) - newEle) <= t) return true;
            bucket.put(bucketNum, newEle);
        }
        return false;
    }
}
