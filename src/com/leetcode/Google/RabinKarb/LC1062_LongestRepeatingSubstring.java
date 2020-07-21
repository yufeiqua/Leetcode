package com.leetcode.Google.RabinKarb;

import java.util.HashSet;
import java.util.Set;

public class LC1062_LongestRepeatingSubstring {
    public int longestRepeatingSubstring(String S) {
        int start = 1, end = S.length() - 1;
        int mid;
        while (start <= end) {
            mid = start + (end - start) / 2;
            if (findDuplicateSubstring(S, mid)) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        return end;
    }

    private boolean findDuplicateSubstring(String s, int length) {
        Set<Long> seen = new HashSet<>();
        long hashing = 0;
        long module = (long)Math.pow(2, 24);
        long pow = 1;
        for (int i = 0; i < s.length(); i++) {
            hashing = (hashing * 26 + (s.charAt(i) - 'a')) % module;
            if (i >= length) {
                hashing = (hashing - ((s.charAt(i - length) - 'a') * pow % module) + module) % module;
            } else {
                pow = pow * 26 % module;
            }
            if (i >= length - 1) {
                if (!seen.add(hashing)) return true;
            }
        }
        return false;
    }
}
