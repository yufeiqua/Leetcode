package com.leetcode.DFS.DFS;

import java.util.HashSet;
import java.util.Set;

import static com.leetcode.Utils.DIRECTIONS;

public class LC0200_NumberOfIslands {
    int rowLength;
    int colLength;

    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0 || grid[0] == null || grid[0].length == 0) return 0;
        rowLength = grid.length;
        colLength = grid[0].length;
        Set<Integer> visited = new HashSet<>();
        int count = 0;
        for (int i = 0; i < rowLength; i++) {
            for (int j = 0; j < colLength; j++) {
                if (grid[i][j] == '1' && visited.add(i * colLength + j)) {
                    dfs(grid, i, j, visited);
                    for(int position :visited) {
                        System.out.print(position + " ");
                    }
                    System.out.println();
                    count++;
                }
            }
        }
        return count;
    }

    private void dfs(char[][] grid, int row, int col, Set<Integer> visited) {
        if (row < 0 || row >= rowLength || col < 0 || col >= colLength || grid[row][col] == '0' ||
                !visited.add(row * colLength + col)) {
            return;
        }
        for (int[] direct : DIRECTIONS) {
            dfs(grid, row + direct[0], col + direct[1], visited);
        }

    }
}
