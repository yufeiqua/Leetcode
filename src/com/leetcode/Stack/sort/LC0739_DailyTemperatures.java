package com.leetcode.Stack.sort;

import java.util.Stack;

class LC0739Test {
    public static void main(String[] args) {
        LC0739_DailyTemperatures dailyTemperatures = new LC0739_DailyTemperatures();
        int[] T = new int[] {4,1,0,2,3,7,0,6,4,5,1,5};
        int[] res = new int[]{7,2,2,3,7,0,6,0,5,0,5,0};
        int[] actual = dailyTemperatures.dailyTemperatures(T);
        for(int ele : actual) System.out.println(ele);
    }
}

public class LC0739_DailyTemperatures {
    public int[] dailyTemperatures(int[] T) {
        if(T == null || T.length == 0) return new int[0];
        int[] res = new int[T.length];
        Stack<Integer> indexOfIncreaseTemp = new Stack<>();
        for (int i = 0; i < T.length; i++) {
            if (indexOfIncreaseTemp.isEmpty()
                    || T[indexOfIncreaseTemp.peek()] >= T[i]) {
                indexOfIncreaseTemp.push(i);
            } else {
                while (!indexOfIncreaseTemp.isEmpty() && T[indexOfIncreaseTemp.peek()] < T[i]) {
                    int index = indexOfIncreaseTemp.pop();
                    res[index] = i - index;
                }
                indexOfIncreaseTemp.push(i);
            }
        }
        while (!indexOfIncreaseTemp.isEmpty()) {
            res[indexOfIncreaseTemp.pop()] = 0;
        }
        return res;
    }
}
