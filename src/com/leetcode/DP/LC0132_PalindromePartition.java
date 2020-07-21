package com.leetcode.DP;

import java.util.Map;

public class LC0132_PalindromePartition {
    public int minCut(String s) {
        return minCut3(s);
    }
//    public int minCut1(String s) {
//        boolean[][] palindromeBoard = preProcess(s);
//        int length = s.length();
//        int[] dp = new int[length + 1];
//        for (int i = 1; i <= length; i++) {
//            dp[i] = i;
//            for (int j = 0; j < i; j++) {
//                if (palindromeBoard[j][i - 1]) {
//                    dp[i] = Math.min(dp[i], dp[j] + 1);
//                }
//                String subString = s.substring(j, i);
//                if (isPalindrome(subString)) {
//                    dp[i] = Math.min(dp[i], dp[j] + 1);
//                }
//            }
//        }
//        return dp[length] - 1;
//    }

    //    public int minCut2(String s) {
//        boolean[][] palindromeBoard = preProcess(s);
//        int length = s.length();
//        int[] dp = new int[length + 1];
//        for (int i = 1; i <= length; i++) {
//            dp[i] = i;
//            for (int j = 0; j < i; j++) {
//                if (palindromeBoard[j][i - 1]) {
//                    dp[i] = Math.min(dp[i], dp[j] + 1);
//                }
//            }
//        }
//        return dp[length] - 1;
//    }
    public int minCut3(String s) {
        int len = s.length();
        boolean[][] palindromeBoard = new boolean[len][len];
        int[] dp = new int[s.length() + 1];
        for (int i = len - 1; i >= 0; i--) {
            dp[i] = len - i;
            for (int j = i; j < len; j++) {
                if (i == j) {
                    palindromeBoard[i][j] = true;
                } else {
                    boolean ab = s.charAt(i) == s.charAt(j);
                    if (i + 1 == j) {
                        palindromeBoard[i][j] = ab;
                    } else {
                        palindromeBoard[i][j] = ab && palindromeBoard[i + 1][j - 1];
                    }
                }
                if (palindromeBoard[i][j]) {
                    dp[i] = Math.min(dp[i], dp[j + 1] + 1);
                }
            }
        }
        return dp[0] - 1;
    }


    private boolean isPalindrome(String s) {
        char[] str = s.toCharArray();
        int start = 0;
        int end = str.length - 1;
        while (start < end) {
            if (str[start] == str[end]) {
                start++;
                end--;
            } else {
                return false;
            }
        }
        return true;
    }

    private boolean[][] preProcess(String s) {
        int len = s.length();
        boolean[][] isPalindromeBoard = new boolean[len][len];
        for (int i = len - 1; i >= 0; i--) {
            for (int j = i; j < len; j++) {
                if (i == j) {
                    isPalindromeBoard[i][j] = true;
                } else {
                    boolean ab = s.charAt(i) == s.charAt(j);
                    if (i + 1 == j) {
                        isPalindromeBoard[i][j] = ab;
                    } else {
                        isPalindromeBoard[i][j] = ab && isPalindromeBoard[i + 1][j - 1];
                    }
                }
            }
        }
        return isPalindromeBoard;
    }

}
