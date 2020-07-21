package com.leetcode.Google;

import java.util.*;

public class LC1088_ConfusingNumber {
    private static final List<Integer> CONFUSING_NUMBER = Arrays.asList(0, 1, 6, 8, 9);
    private int count = 0;

    public int confusingNumberII(int N) {
        List<String> res = new ArrayList<>();
        dfs(N, new StringBuilder(), res);
        System.out.println("result: " + res.size());
        res.forEach(k -> System.out.println(k));
        return count;
    }

    private void dfs(int max, StringBuilder sb, List<String> res) {

        if (sb.length() > 0 && isValidConfusingNumber(sb.toString())) {
            res.add(sb.toString());
            count++;
        }

        for (int i = 0; i < CONFUSING_NUMBER.size(); i++) {
            int length = sb.length();
            if (sb.length() == 0 && i == 0) {
                continue;
            }
            sb.append(CONFUSING_NUMBER.get(i));
            String number = sb.toString();
            long num = Long.parseLong(number);
            if (num > max) {
                sb.setLength(length);
                break;
            }
            dfs(max, sb, res);
            sb.setLength(length);
        }

    }

    private boolean isValidConfusingNumber(String s) {
        char lastCharacter = s.charAt(s.length() - 1);
        int lastNumber = lastCharacter - '0';

        if (s.length() == 1 && (lastNumber == 1 || lastNumber == 8)) {
            return false;
        }
        return s.length() <= 1 || !isPalindromeNumber(s);
    }

    private boolean isPalindromeNumber(String s) {
        int start = 0;
        int end = s.length() - 1;
        while (start < end) {
            if (s.charAt(start) == s.charAt(end) && s.charAt(start) != '6' && s.charAt(start) != '9'
                    || s.charAt(start) == '6' && s.charAt(end) == '9' || s.charAt(start) == '9' && s.charAt(end) == '6') {
                start++;
                end--;
            } else {
                return false;
            }
        }
        if (start == end) {

            if(s.charAt(start) == '6' || s.charAt(start) == '9') {
                System.out.println(Integer.MAX_VALUE);
                return false;
            }
        }
        System.out.println(Integer.MAX_VALUE);
        return true;

    }


}
