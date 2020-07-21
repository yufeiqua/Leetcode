package com.leetcode.Google;

import java.util.ArrayList;
import java.util.List;

class TestLC0296 {
    public static void main(String[] args) {
        LC0296_BestMeetingPoints meetingPoints = new LC0296_BestMeetingPoints();
        int[][] grid = new int[][]{{1, 0, 0, 0, 1}, {0, 0, 0, 0, 0}, {0, 0, 1, 0, 0}};
        int result = meetingPoints.minTotalDistance(grid);
        System.out.print(result);
    }
}

public class LC0296_BestMeetingPoints {

    public int minTotalDistance(int[][] grid) {
        List<Integer> pointX = new ArrayList<>();
        List<Integer> pointY = new ArrayList<>();
        int row = grid.length;
        int col = grid[0].length;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (grid[i][j] == 1) {
                    pointX.add(i);
                    pointY.add(j);
                }
            }
        }
        int size = pointX.size();
        if (size % 2 != 0) {
            int medianX = findMedianNumber(pointX, 0, size - 1, size / 2 + 1);
            int medianY = findMedianNumber(pointY, 0, size - 1, size / 2 + 1);
            return calculateDistance(pointX, pointY, medianX, medianY);
        }

        int mx2 = findMedianNumber(pointX, 0, size - 1, size / 2);
        int my2 = findMedianNumber(pointY, 0, size - 1, size / 2);
        return calculateDistance(pointX, pointY, mx2, my2);

    }

    private int calculateDistance(List<Integer> x, List<Integer> y, int px, int py) {
        int distance = 0;
        for (int p : x) {
            distance += Math.abs(p - px);
        }
        for (int p : y) {
            distance += Math.abs(p - py);
        }
        return distance;
    }

    private int findMedianNumber(List<Integer> list, int start, int end, int k) {
        int pivotIndex = helper(list, start, end);

        int rank = pivotIndex - start + 1;

        if (rank < k) {
            return findMedianNumber(list, pivotIndex + 1, end, k - rank);
        } else if (rank > k) {
            return findMedianNumber(list, start, end - 1, k);
        } else {
            return list.get(pivotIndex);
        }
    }

    private int helper(List<Integer> list, int start, int end) {
        int pivot = list.get(end);
        int left = start - 1;
        for (int i = start; i < end; i++) {
            if (list.get(i) <= pivot) {
                swap(list, ++left, i);
            }
        }
        swap(list, ++left, end);
        return left;
    }

    private void swap(List<Integer> list, int i, int j) {
        int temp = list.get(i);
        list.set(i, list.get(j));
        list.set(j, temp);
    }

}
