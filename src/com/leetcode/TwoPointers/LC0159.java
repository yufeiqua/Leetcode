package com.leetcode.TwoPointers;

class Test159 {
    public static void main(String[] args) {
        LC0159 lc0159 = new LC0159();
        String s = "cdaba";
        System.out.println(lc0159.lengthOfLongestSubstringTwoDistinct(s));
    }
}

public class LC0159 {
    public int lengthOfLongestSubstringTwoDistinct(String s) {
        if (s == null || s.length() == 0) return 0;
        char ch1 = '\0';
        char ch2 = '\0';
        int index1 = -1;
        int index2 = -1;
        int start = 0;
        int maxLength = 0;
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch1 == '\0' || ch == ch1) {
                ch1 = ch;
                index1 = i;
            } else if (ch2 == '\0' || ch == ch2) {
                ch2 = ch;
                index2 = i;
            } else {
                if (index1 < index2) {
                    start = index1 + 1;
                    index1 = i;
                    ch1 = ch;
                } else {
                    start = index2 + 1;
                    index2 = i;
                    ch2 = ch;
                }

            }
            maxLength = Math.max(maxLength, i - start + 1);
        }
        return maxLength;
    }
}
