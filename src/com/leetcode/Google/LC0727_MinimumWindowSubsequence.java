package com.leetcode.Google;

class testLC0727 {
    public static void main(String[] args) {
        LC0727_MinimumWindowSubsequence minimumWindowSubsequence = new LC0727_MinimumWindowSubsequence();
        String S = "ffffeeessa";
        String T = "fesa";
        String ret = minimumWindowSubsequence.minWindow(S, T);
        System.out.println(ret);
    }
}

public class LC0727_MinimumWindowSubsequence {
    public String minWindow(String S, String T) {
        int startIndex = -1, minLength = S.length() + 1;
        int left, right = 0;
        int temp = 0;
        while (right < S.length()) {
            while (right < S.length()) {
                if (S.charAt(right) == T.charAt(temp)) {
                    temp++;
                }
                if (temp == T.length()) break;
                right++;
            }
            if (right == S.length()) break;
            temp--;
            left = right;
            while (temp >= 0) {
                if (S.charAt(left) == T.charAt(temp)) {
                    temp--;
                }
                if (temp < 0) break;
                left--;
            }
            if (right - left + 1 < minLength) {
                minLength = right - left + 1;
                startIndex = left;
            }
            temp = 0;
            right = left + 1;
        }
        if (minLength == S.length() + 1) return "";
        return S.substring(startIndex, startIndex + minLength);
    }
}
