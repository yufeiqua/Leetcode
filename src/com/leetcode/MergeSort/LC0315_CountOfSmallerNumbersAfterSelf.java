package com.leetcode.MergeSort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


class TestMergeSort {
    public static void main(String[] args) {
        int[] nums = new int[]{2,1,3,7,0,6,4,5,1,5};
        LC0315_CountOfSmallerNumbersAfterSelf countOfSmallerNumbersAfterSelf = new LC0315_CountOfSmallerNumbersAfterSelf();
        countOfSmallerNumbersAfterSelf.countSmaller(nums);
    }

}
class Pair {
    private int index;
    private int value;

    public Pair(int index, int value) {
        this.index = index;
        this.value = value;
    }

    public Pair() {
    }

    public int getIndex() {
        return this.index;
    }

    public int getValue() {
        return this.value;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public void setValue(int value) {
        this.value = value;
    }
}

public class LC0315_CountOfSmallerNumbersAfterSelf {
    public List<Integer> countSmaller(int[] nums) {
        if(nums.length == 0) return new ArrayList<>();
        Pair[] numsPair = new Pair[nums.length];
        for (int i = 0; i < nums.length; i++) {
            numsPair[i] = new Pair(i, nums[i]);
        }
        Integer[] smaller = new Integer[nums.length];
        Arrays.fill(smaller, 0);
        mergeAndSort(numsPair, smaller);
        return Arrays.asList(smaller);
    }

    private Pair[] mergeAndSort(Pair[] pairs, Integer[] smaller) {
        int length = pairs.length;
        if (length == 1) return pairs;
        int middle = length / 2;
        Pair[] left = mergeAndSort(Arrays.copyOfRange(pairs, 0, middle), smaller);
        Pair[] right = mergeAndSort(Arrays.copyOfRange(pairs, middle, length), smaller);
        return merge(left, right, smaller);
    }

    private Pair[] merge(Pair[] left, Pair[] right, Integer[] smaller) {
        Pair[] pairs = new Pair[left.length + right.length];
        int i = 0, j = 0, k = 0;
        while (i < left.length && j < right.length) {
            if (left[i].getValue() <= right[j].getValue()) {
                pairs[k] = new Pair(left[i].getIndex(), left[i].getValue());
                smaller[left[i].getIndex()] += j;
                i++;
            } else {
                pairs[k] = new Pair(right[j].getIndex(), right[j].getValue());
                j++;
            }
            k++;
        }
        while (i < left.length) {
            pairs[k++] = new Pair(left[i].getIndex(), left[i].getValue());
            smaller[left[i].getIndex()] += right.length;
            i++;
        }
        while (j < right.length) {
            pairs[k++] = new Pair(right[j].getIndex(), right[j].getValue());
            j++;
        }
        return pairs;
    }
}
