package com.leetcode.TwoPointers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class testLC0254 {
    public static void main(String[] args) {
        LC0254_ShortestWordDistanceII shortestWordDistanceII = new LC0254_ShortestWordDistanceII();
        String[] words = new String[]{"this","is","a","long","sentence","is","fun","day","today","sunny","weather","is","a","day","tuesday","this","sentence","run","running","rainy"};
        String word1 = "this";
        String word2 = "is";
        System.out.println(shortestWordDistanceII.shortestWordDistance(words, word1, word2));
    }
}

public class LC0254_ShortestWordDistanceII {
    public int shortestWordDistance(String[] words, String word1, String word2) {
        Map<String, List<Integer>> map = new HashMap<>();
        int index = 0;
        for (String word : words) {
            map.computeIfAbsent(word, k -> new ArrayList<>());
            map.get(word).add(index++);
        }
        List<Integer> index1 = map.get(word1);
        List<Integer> index2 = map.get(word2);
//        System.out.println(index1);
//        System.out.println(index2);
        if (word1.equals(word2)) {
            return findClosetIndex(index1);
        }
        int minLength = words.length + 1;
        for (int idx : index1) {
            int idx21 = binarySearchFindClosetIndex1(index2, idx);
            int idx22 = binarySearchFindClosetIndex2(index2, idx);
            minLength = Math.min(minLength, Math.min(Math.abs(idx - idx21), Math.abs(idx - idx22)));
        }
        return minLength;
    }

    private int findClosetIndex(List<Integer> list) {
        int min = Integer.MAX_VALUE;
        for (int i = 0; i + 1 < list.size(); i++) {
            int prev = list.get(i);
            int next = list.get(i + 1);
            min = Math.min(min, (next - prev));
        }
        return min;
    }


    private int binarySearchFindClosetIndex1(List<Integer> list, int target) {
        System.out.println(list);
        System.out.println(target);
        int start = 0, end = list.size() - 1;
        int mid;
        int min = Integer.MAX_VALUE;
        while (start <= end) {
            mid = start + (end - start) / 2;
            if (Math.abs(list.get(mid) - target) <= min) {
                min = Math.abs(list.get(mid) - target);
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        System.out.println(list.get(end));
        return list.get(end);
    }
    private int binarySearchFindClosetIndex2(List<Integer> list, int target) {
        System.out.println(list);
        System.out.println(target);
        int start = 0, end = list.size() - 1;
        int mid;
        int min = Integer.MAX_VALUE;
        while (start <= end) {
            mid = start + (end - start) / 2;
            if (Math.abs(list.get(mid) - target) >= min) {
                min = Math.abs(list.get(mid) - target);
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        System.out.println(list.get(start));
        return list.get(start);
    }
}
