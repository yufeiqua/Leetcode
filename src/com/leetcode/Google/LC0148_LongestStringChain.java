package com.leetcode.Google;

import java.util.*;

class testLC0148 {
    public static void main(String[] args) {
        LC0148_LongestStringChain longestStringChain = new LC0148_LongestStringChain();
        String[] words = new String[]{"ab", "ba", "bca", "bda", "bdca" };

    }

}

public class LC0148_LongestStringChain {
    public int longestStrChain(String[] words) {
        Map<String, Integer> dp = new HashMap<>();
        Arrays.sort(words, Comparator.comparingInt(String::length));
        int res = 0;
        for (String word : words) {
            int best = 0;
            for (int i = 0; i < word.length(); i++) {
                String potentialPredecessor = word.substring(0, i) + word.substring(i + 1);
                int length = dp.getOrDefault(potentialPredecessor, 0);
                best = Math.max(best, length);
            }
            dp.put(word, best + 1);
            res = Math.max(res, best + 1);
        }
        return res;
    }

}
