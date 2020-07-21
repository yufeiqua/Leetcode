package com.leetcode.MergeSort;

class testLC0493 {
    public static void main(String[] args) {
        LC0493_ReversePairs reversePairs = new LC0493_ReversePairs();
        int[] nums = new int[]{1,3,2,3,1};
        int ret = reversePairs.reversePairs(nums);
        System.out.println(ret);
    }
}

public class LC0493_ReversePairs {
    public int reversePairs(int[] nums) {
        if (nums == null || nums.length == 0) return 0;

        return mergeSort(nums, 0 , nums.length - 1);

    }


    private int mergeSort(int[] pairs, int left, int right) {
        if (left >= right) return 0;
        int middle = left + (right - left) / 2;
        int leftCount = mergeSort(pairs, left, middle);
        int rightCount = mergeSort(pairs, middle + 1, right);
        int[] temp = new int[right - left + 1];
        int l = left, r = middle + 1, k = 0;
        //count elements
        // merge 是谁小移动谁但是 count应该是找到第一个大于2 * right的元素之后，再移动right。再移动left找到第一个大于2 * right的元素。
        // 移动的规则不一样，因此不能放在一起去计算。
        int count = 0;
        while(l <= middle && r <= right) {
            if((long)pairs[l] <= 2 *(long)pairs[r]) {
                System.out.println(pairs[l] + "  @ " + pairs[r]);
                l++;
            }
            else {
                System.out.println(pairs[l] + " " + pairs[r]);
                System.out.println(middle + "  " + l);
                count += (middle - l) + 1;
                System.out.println("count " + ((middle - l) + 1));
                r++;
            }
        }
        l = left; r = middle + 1;
        while (l <= middle && r <= right) {
            if (pairs[l] < pairs[r]) {
                temp[k++] = pairs[l++];
            } else {
                temp[k++] = pairs[r++];
            }
        }
        while (r <= right) {
            temp[k++] = pairs[r++];
        }
        while (l <= middle) {
            temp[k++] = pairs[l++];
        }

        if (temp.length >= 0) System.arraycopy(temp, 0, pairs, left, temp.length);
        return count + leftCount + rightCount;
    }
}
