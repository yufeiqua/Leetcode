package com.leetcode.TwoPointers;

class testLC0378 {
    public static void main(String[] args) {
        LC0378 test = new LC0378();
        int[][] matrix = new int[][]{{1, 5, 9}, {10, 11, 13}, {12, 13, 15}};
        int ret = test.kthSmallest(matrix, 7);

        System.out.println(ret);
    }
}

public class LC0378 {
    private int row;
    private int col;

    public int kthSmallest(int[][] matrix, int k) {
        row = matrix.length;
        col = matrix[0].length;
        return findKth(matrix, 0, (row - 1) * col + col - 1, k);
    }

    private int findKth(int[][] matrix, int start, int end, int k) {
        if (start == end) return matrix[end / col][end % col];
        int pivot = partition(matrix, start, end);
        int rank = pivot - start + 1;
        if (rank == k) return matrix[pivot / col][pivot % col];
        if (rank < k) {
            return findKth(matrix, pivot + 1, end, k - rank);
        } else {
            return findKth(matrix, start, pivot - 1, k);
        }
    }

    private int partition(int[][] matrix, int start, int end) {
        int pivot = matrix[end / col][end % col];
        int left = start;
        for (int i = start; i <= end; i++) {
            if (matrix[i / col][i % col] < pivot) {
                swap(matrix, left++, i);
            }
        }
        return left - 1;
    }

    //    private int partition(int[][] matrix, int start, int end) {
//        int pivot = matrix[end / col][end % col];
//        int startTemp = start;
//        int endTemp = end;
//        while (startTemp < endTemp) {
//            while (startTemp < endTemp && matrix[startTemp / col][startTemp % col] < pivot) startTemp++;
//            while (startTemp < endTemp && matrix[endTemp / col][endTemp % col] >= pivot) endTemp--;
//            swap(matrix, startTemp, endTemp);
//        }
//        swap(matrix, startTemp, end);
//        return startTemp;
//    }
//
    private void swap(int[][] matrix, int start, int end) {
        if (start >= end) return;
        int temp = matrix[start / col][start % col];
        matrix[start / col][start % col] = matrix[end / col][end % col];
        matrix[end / col][end % col] = temp;
    }
}
