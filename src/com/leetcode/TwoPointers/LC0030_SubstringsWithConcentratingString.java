package com.leetcode.TwoPointers;

import java.util.*;

class TestLC0030 {
    public static void main(String[] args) {
        LC0030_SubstringsWithConcentratingString test1 = new LC0030_SubstringsWithConcentratingString();
        String s = "aaabbaaaa";
        String[] words = new String[]{"aa", "bb", "aa"};
        List<Integer> ret = test1.findSubstring(s, words);
        for (Integer r : ret) System.out.println(r);
    }
}

public class LC0030_SubstringsWithConcentratingString {
    public List<Integer> findSubstring(String s, String[] words) {
        if (s == null || s.length() == 0) return new ArrayList<>();
        if (words == null || words.length == 0) return new ArrayList<>();
        Map<String, Integer> wordSet = new HashMap<>();
        for (String word : words) {
            wordSet.put(word, wordSet.getOrDefault(word, 0) + 1);
        }
        Map<String, Integer> visitedWords = new HashMap<>();
        List<Integer> res = new ArrayList<>();
        int length = words[0].length();
        for (int i = 0; i + length <= s.length(); i++) {
            int start = i;
            while (start + length <= s.length()) {
                String curString = s.substring(start, start + length);
                if (wordSet.containsKey(curString) && visitedWords.getOrDefault(curString, 0) < wordSet.get(curString)) {
                    visitedWords.put(curString, visitedWords.getOrDefault(curString, 0) + 1);
                    if (visitedWords.equals(wordSet)) {
                        res.add(i);
                        visitedWords.clear();
                        break;
                    }
                    start += length;
                } else {
                    break;
                }
            }
            visitedWords.clear();
        }
        return res;
    }
}
