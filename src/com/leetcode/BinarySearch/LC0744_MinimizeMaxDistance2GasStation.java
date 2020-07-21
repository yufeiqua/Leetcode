package com.leetcode.BinarySearch;

public class LC0744_MinimizeMaxDistance2GasStation {
    public double minmaxGasDist(int[] stations, int K) {
        double left = 0;
        double right = stations[stations.length - 1] - stations[0];
        double mid;
        int count;
        while (left + 1e-6 <= right) {
            mid = (right + left) / 2;
            count = 0;
            for (int i = 0; i < stations.length - 1; i++) {
                count += Math.ceil((stations[i + 1] - stations[i]) / mid) - 1;
            }
            if(count > K) {
                left = mid;
            }
            else {
                right = mid;
            }
        }
        return right;
    }
}
