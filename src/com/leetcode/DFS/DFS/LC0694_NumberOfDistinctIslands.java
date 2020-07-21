package com.leetcode.DFS.DFS;

import java.util.HashSet;
import java.util.Set;

import static com.leetcode.Utils.DIRECTIONS;

public class LC0694_NumberOfDistinctIslands {
    private Set<String> pattern = new HashSet<>();
    private Set<Integer> visited = new HashSet<>();
    private int rowLength;
    private int colLength;

    public int numDistinctIslands(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0] == null || grid[0].length == 0) return 0;
        rowLength = grid.length;
        colLength = grid[0].length;
        for (int i = 0; i < rowLength; i++) {
            for (int j = 0; j < colLength; j++) {
                if (grid[i][j] == 1 && !visited.contains(i * colLength + j)) {
                    StringBuilder path = new StringBuilder();
                    dfs(grid, i, j, path);
                    pattern.add(path.toString());
                }
            }
        }
        return pattern.size();
    }

    private void dfs(int[][] grid, int row, int col, StringBuilder path) {
        if (row < 0 || row == rowLength || col < 0 || col >= colLength || grid[row][col] == 0
                || !visited.add(row * colLength + col)) {
            return;
        }
        dfs(grid, row + DIRECTIONS[0][0], col + DIRECTIONS[0][1], path.append("1->"));
        dfs(grid, row + DIRECTIONS[1][0], col + DIRECTIONS[1][1], path.append("2->"));
        dfs(grid, row + DIRECTIONS[2][0], col + DIRECTIONS[2][1], path.append("3->"));
        dfs(grid, row + DIRECTIONS[3][0], col + DIRECTIONS[3][1], path.append("4->"));
    }
}
