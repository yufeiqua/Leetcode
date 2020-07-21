package com.leetcode.TwoPointers;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class TestLC0072 {
    public static void main(String[] args) {
        LC0076_MinWindow minWindow = new LC0076_MinWindow();
        System.out.println(minWindow.minWindow("ADOBECODEBANC", "ABC"));
    }
}

public class LC0076_MinWindow {
    public String minWindow(String s, String t) {
        int minLength = s.length() + 1;
        int retStart = -1;
        int[] wordSet = new int[256];
        for (char ch : t.toCharArray()) {
            wordSet[ch]++;
        }
        int[] visited = new int[256];
        int start = 0, end;
        while (start < s.length() && wordSet[s.charAt(start)] == 0) {
            start++;
        }
        if (start == s.length()) return "";
        end = start;
        while (end < s.length()) {
            char cur = s.charAt(end);
            if (wordSet[cur] != 0) {
                visited[cur]++;
                while (arraysAreEqual(visited, wordSet)) {
                    if (minLength > end - start + 1) {
                        minLength = end - start + 1;
                        retStart = start;
                    }
                    if (visited[s.charAt(start)] != 0) visited[s.charAt(start)]--;
                    start++;
                }
            }
            end++;
        }
        return retStart == -1 ? "" : s.substring(retStart, retStart + minLength);
    }

    private boolean arraysAreEqual(int[] visited, int[] wordSet) {
        for (int i = 0; i < 256; i++) {
            if (visited[i] < wordSet[i]) return false;
        }
        return true;
    }
}
