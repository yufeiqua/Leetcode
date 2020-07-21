package com.leetcode.BFS;

import java.util.LinkedList;
import java.util.Queue;

import static com.leetcode.Utils.DIRECTIONS;


public class LC0542 {
    public int rowLength;
    public int colLength;

    public int[][] updateMatrix(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return null;
        }
        rowLength = matrix.length;
        colLength = matrix[0].length;
        Queue<Integer> store0Location = initializeSearch(matrix);
        return findMinLengthFrom1to0(store0Location, matrix);
    }

    private Queue<Integer> initializeSearch(int[][] matrix) {
        Queue<Integer> queue = new LinkedList<>();
        for (int r = 0; r < rowLength; r++) {
            for (int c = 0; c < colLength; c++) {
                if (matrix[r][c] == 0) {
                    queue.add(r * colLength + c);
                }
            }
        }
        return queue;
    }


    private int[][] findMinLengthFrom1to0(Queue<Integer> storeAll0Location, int[][] matrix) {
        int[][] result = new int[rowLength][colLength];
        int minDist = 1;
        int size;
        int rowLoc;
        int colLoc;
        int currentLoc;
        while (!storeAll0Location.isEmpty()) {
            size = storeAll0Location.size();
            while (size-- > 0) {
                currentLoc = storeAll0Location.poll();
                rowLoc = currentLoc / colLength;
                colLoc = currentLoc % colLength;
                for (int[] direction : DIRECTIONS) {
                    int nextRow = rowLoc + direction[0];
                    int nextCol = colLoc + direction[1];
                    if (nextRow < 0 || nextRow >= rowLength || nextCol < 0 || nextCol >= colLength
                            || result[nextRow][nextCol] != 0 || matrix[nextRow][nextCol] == 0) {
                        continue;
                    }
                    storeAll0Location.offer(nextRow * colLength + nextCol);
                    result[nextRow][nextCol] = minDist;
                }
            }
            minDist++;
        }
        return result;
    }
}
