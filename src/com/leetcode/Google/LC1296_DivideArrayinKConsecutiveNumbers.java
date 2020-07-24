package com.leetcode.Google;

import java.util.TreeMap;

class testLC1296 {
    public static void main(String[] args) {
        LC1296_DivideArrayinKConsecutiveNumbers divideArrayinKConsecutiveNumbers = new LC1296_DivideArrayinKConsecutiveNumbers();
        int[] nums = {1, 2, 3, 6, 2, 3, 4, 7, 8};
        int k = 3;
        boolean ret = divideArrayinKConsecutiveNumbers.isPossibleDivide(nums, k);
        System.out.println(ret);
    }
}

public class LC1296_DivideArrayinKConsecutiveNumbers {
    public boolean isPossibleDivide(int[] array, int size) {
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for (int num : array) {
            int count = map.getOrDefault(num, 0);
            map.put(num, count + 1);
        }
        while (!map.isEmpty()) {
            int firstElement = map.firstKey();
            for (int offset = 0; offset < size; offset++) {
                if (!map.containsKey(firstElement + offset)) {
                    return false;
                }
                int count = map.get(firstElement + offset);
                if (count == 1) map.remove(firstElement + offset);
                else {
                    map.put(firstElement + offset, count - 1);
                }
            }
        }
        return true;
    }
}
