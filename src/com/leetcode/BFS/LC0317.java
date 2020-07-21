package com.leetcode.BFS;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

import static com.leetcode.Utils.DIRECTIONS;

public class LC0317 {
    int rowLength;
    int colLength;

    public int shortestDistance(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return -1;
        }
        rowLength = grid.length;
        colLength = grid[0].length;
        int[][] result = new int[rowLength][colLength];
        return findShortestDistance(grid, result);
    }

    private int findShortestDistance(int[][] grid, int[][] result) {
        Queue<Integer> allPossibleSearchingPoint = new LinkedList<>();
        for (int r = 0; r < rowLength; r++) {
            for (int c = 0; c < colLength; c++) {
                if (grid[r][c] == 1) {
                    allPossibleSearchingPoint.offer(r * colLength + c);
                    searchMinDistance2EmptySpace(allPossibleSearchingPoint, grid, result);

                }
            }
        }
        int minDist = Integer.MAX_VALUE;
        for (int r = 0; r < rowLength; r++) {
            for (int c = 0; c < colLength; c++) {
                if (grid[r][c] == 0) {
                    minDist = Math.min(minDist, result[r][c]);
                }
            }
        }
        return minDist == Integer.MAX_VALUE ? -1 : minDist;
    }

    private void searchMinDistance2EmptySpace(Queue<Integer> allPossibleSearchingPoint, int[][] grid, int[][] result) {
        int curLoc;
        int size;
        int minLen = 1;
        Set<Integer> visited = new HashSet<>();
        while (!allPossibleSearchingPoint.isEmpty()) {
            size = allPossibleSearchingPoint.size();
            while (size-- > 0) {
                curLoc = allPossibleSearchingPoint.poll();
                for (int[] direct : DIRECTIONS) {
                    int nextRow = curLoc / colLength + direct[0];
                    int nextCol = curLoc % colLength + direct[1];
                    if (nextRow < 0 || nextRow >= rowLength || nextCol < 0 || nextCol >= colLength
                            || grid[nextRow][nextCol] != 0 || !visited.add(nextRow * colLength + nextCol)) {
                        continue;
                    }
                    allPossibleSearchingPoint.offer(nextRow * colLength + nextCol);
                    result[nextRow][nextCol] += minLen;
                }
            }
            minLen++;
        }
        for (int r = 0; r < rowLength; r++) {
            for (int c = 0; c < colLength; c++) {
                if (grid[r][c] == 0 && visited.add(r * colLength + c)) {
                    grid[r][c] = 2;
                }
            }
        }
    }

}
