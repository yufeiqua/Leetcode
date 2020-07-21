package com.leetcode.DFS.Prunning_DP;

import java.util.PriorityQueue;

import static com.leetcode.Utils.DIRECTIONS;

public class LC0329_LongestIncreasingPath {
    /**
     * 面试前一定要先问移动方向和起点终点，不要做假设。
     * From each cell, you can either move to four directions:
     * left, right, up or down.
     * You may NOT move diagonally or move outside of the boundary (i.e. wrap-around is not allowed).
     *
     * @param matrix
     * @return
     */
    int rowLength;
    int colLength;
    int[][] memo;

    public int longestIncreasingPath(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) return 0;
        rowLength = matrix.length;
        colLength = matrix[0].length;
//        memo = new int[rowLength + 1][colLength + 1];
//        int ret = Integer.MIN_VALUE;
//        for (int i = 0; i < rowLength; i++) {
//            for (int j = 0; j < colLength; j++) {
//                ret = Math.max(ret, dfs(matrix, i, j, Integer.MIN_VALUE));
//            }
//        }
//        return ret;
        return helper(matrix);
    }

    private int dfs(int[][] matrix, int row, int col, int prevValue) {
        if (row == rowLength || row < 0 || col == colLength || col < 0 || matrix[row][col] <= prevValue) {
            return 0;
        }

        if (memo[row][col] != 0) return memo[row][col];
        int res = 0;
        for (int[] direct : DIRECTIONS) {
            res = Math.max(res, dfs(matrix, row + direct[0], col + direct[1], matrix[row][col]));
        }
        memo[row][col] = res + 1;
        return res + 1;
    }

    class ObjectNode implements Comparable<ObjectNode> {
        int i;
        int j;
        int value;

        public ObjectNode(int i, int j, int value) {
            this.i = i;
            this.j = j;
            this.value = value;
        }

        @Override
        public int compareTo(ObjectNode o) {
            return this.value - o.value;
        }

    }

    private int helper(int[][] matrix) {
        PriorityQueue<ObjectNode> queue = new PriorityQueue<>();
        for (int i = 0; i < rowLength; i++) {
            for (int j = 0; j < colLength; j++) {
                queue.offer(new ObjectNode(i, j, matrix[i][j]));
            }
        }
        int[][] memo = new int[rowLength][colLength];
        while (!queue.isEmpty()) {
            ObjectNode cur = queue.poll();
            int ret = 0;
            for (int[] direct : DIRECTIONS) {
                int nextI = cur.i + direct[0];
                int nextJ = cur.j + direct[1];
                if (nextI < 0 || nextI >= rowLength || nextJ < 0 || nextJ >= colLength || memo[nextI][nextJ] == 0 || matrix[cur.i][cur.j] == matrix[nextI][nextJ]) {
                    continue;
                }
                ret = Math.max(ret, memo[nextI][nextJ]);
                memo[cur.i][cur.j] = ret;
            }
            memo[cur.i][cur.j] = ret + 1;
        }
        int result = Integer.MIN_VALUE;
        for (int[] row : memo) {
            for (int path : row) {
                System.out.print(path + " ");
                result = Math.max(result, path);
            }
            System.out.println();
        }
        return result;
    }
}
