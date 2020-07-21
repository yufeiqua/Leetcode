package com.leetcode.DP;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LC0044 {
    public boolean isMatch(String s, String p) {
        int lenS = s.length(), lenP = p.length();
        int i = 0, j = 0, flag = -1, tempS = 0;
        while (i < lenS) {
            if (j < lenP && (p.charAt(j) == '?' || s.charAt(i) == p.charAt(j))) {
                i++;
                j++;
            } else if (j < lenP && p.charAt(j) == '*') {
                flag = ++j;
                tempS = i;
            } else if (flag != -1) {
                j = flag;
                i = ++tempS;
            } else {
                return false;
            }
            System.out.println(i + " " + j);
        }
        while (j < lenP && p.charAt(j) == '*') j++;
        return j == lenP;
    }

    Set<Integer> daySet = new HashSet<>();
    Integer[] memo = new Integer[366];

    public int mincostTickets(int[] days, int[] costs) {
        for (int day : days) {
            daySet.add(day);
        }
        dp(costs, days[0]);
        return memo[days[days.length - 1]];
    }

    private int dp(int[] costs, int day) {
        if (day > 365) return 0;
        if (memo[day] != null) return memo[day];
        int cost;
        if (daySet.contains(day)) {
            int min = dp(costs, day + 1) + costs[0];
            min = Math.min(dp(costs, day + 7) + costs[1], min);
            min = Math.min(dp(costs, day + 30) + costs[2], min);
            cost = min;
        } else {
            cost = dp(costs, day + 1);
        }
        memo[day] = cost;
        return cost;
    }

    private int dp2(int[] days, int[] costs) {
        Set<Integer> daySet = new HashSet<>();
        for (int day : days) {
            daySet.add(day);
        }
        int[] dp = new int[366];
        for (int i = days[0]; i <= 365; i++) {
            if (daySet.contains(i)) {
                int val1 = i > 1 ? dp[i - 1] + costs[0] : 0;
                int val2 = i > 7 ? dp[i - 7] + costs[1] : 0;
                int val3 = i > 30 ? dp[i - 30] + costs[2] : 0;
                dp[i] = Math.min(val1, Math.min(val2, val3));
            } else {
                dp[i] = dp[i - 1];
            }
        }
        return dp[365];
    }
    private boolean dp(int[] nums) {
        int total = 0;
        for(int num : nums) {
            total += num;
        }
        if(total % 2 != 0) return false;
        total /= 2;
        boolean[][] dp = new boolean[nums.length + 1][total + 1];
        dp[0][0] = true;
        for(int i = 1; i < nums.length; i++) {
            dp[i][0] = true;
        }
        for(int j = 1; j <= total; j++) {
            dp[0][j] = false;
        }
        for(int i = 1 ; i<= nums.length; i++) {
            for(int j = nums[i]; j <= total; j++) {
                dp[i][j] = dp[i - 1][j];
                dp[i][j] |= dp[i - 1][j - nums[i]];
            }
        }
        return dp[nums.length][total];
    }


}
