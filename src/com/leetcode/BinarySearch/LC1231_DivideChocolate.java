package com.leetcode.BinarySearch;

class testLC1231 {
    public static void main(String[] args) {
        LC1231_DivideChocolate divideChocolate = new LC1231_DivideChocolate();
        int[] arr = new int[]{1,2,3,4,5,6,7,8,9};
        int ret = divideChocolate.maximizeSweetness(arr, 5);
        System.out.println(ret);
    }
}

public class LC1231_DivideChocolate {
    public int maximizeSweetness(int[] sweetnesses, int cut) {
        int minSweetness = Integer.MAX_VALUE;
        int maxSweetness = 0;
        int mid;
        for(int swet : sweetnesses) {
            minSweetness = Math.min(minSweetness, swet);
            maxSweetness += swet;
        }
        while (minSweetness <= maxSweetness) {
            mid = minSweetness + (maxSweetness - minSweetness) / 2;
            if (couldGetEnoughPiece(sweetnesses, mid, cut)) {
                minSweetness = mid + 1;
            } else {
                maxSweetness = mid - 1;
            }
        }
        return maxSweetness;
    }

    private boolean couldGetEnoughPiece(int[] sweetnesses, int sweetness, int cut) {
        int count = 0;
        int temp = 0;
        for (int value : sweetnesses) {
            temp += value;
            if (temp >= sweetness) {
                temp = 0;
                count++;
            }
        }
        return count >= cut + 1;
    }
}
