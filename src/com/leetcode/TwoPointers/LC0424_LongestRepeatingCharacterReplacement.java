package com.leetcode.TwoPointers;

class TestLC0424 {
    public static void main(String[] args) {
        LC0424_LongestRepeatingCharacterReplacement test = new LC0424_LongestRepeatingCharacterReplacement();
        String s = "BAAA";
        int k = 0;
        System.out.println(test.characterReplacement(s, k));
    }
}

public class LC0424_LongestRepeatingCharacterReplacement {
    public int characterReplacement(String s, int k) {
        int maxLength = 1;
        char maxFreqChar;
        int[] count = new int[26];
        int start = 0, end = 0; // [start, end)
        while (end < s.length()) {
            char curr = s.charAt(end);
            count[curr - 'A']++;
            maxFreqChar = findMaxFrequencyChar(count);
            while (end - start + 1 - count[maxFreqChar - 'A'] > k) {
                char leftMostChar = s.charAt(start++);
                count[leftMostChar - 'A']--;
                if (leftMostChar == maxFreqChar) {
                    maxFreqChar = findMaxFrequencyChar(count);
                }
            }
            maxLength = Math.max(maxLength, end - start + 1);
            end++;
        }
        return maxLength;
    }

    private char findMaxFrequencyChar(int[] count) {
        int maxCount = 0;
        int index = 0;
        for (int i = 0; i < count.length; i++) {
            if (count[i] > maxCount) {
                maxCount = count[i];
                index = i;
            }
        }
        return (char) ('A' + index);
    }
}
