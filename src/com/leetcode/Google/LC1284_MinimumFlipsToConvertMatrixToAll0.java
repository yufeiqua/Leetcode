package com.leetcode.Google;

import java.util.*;

import static com.leetcode.Utils.DIRECTIONS;

class Points {
    int x;
    int y;
    int[][] state;
    Set<Integer> visited;

    public Points(int x, int y, int[][] matrix, Set<Integer> prevState) {
        this.x = x;
        this.y = y;
        int row = matrix.length;
        int col = matrix[0].length;
        visited = new HashSet<>(prevState);
        visited.add(x * col + y);
        state = new int[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                state[i][j] = matrix[i][j];
            }
        }
    }
}

public class LC1284_MinimumFlipsToConvertMatrixToAll0 {
    private int row;
    private int col;
    private String target;

    public int minFlips(int[][] mat) {
        row = mat.length;
        col = mat[0].length;
        return BFS(mat);

    }

    private int BFS(int[][] mat) {
        Queue<Points> queue = new LinkedList<>();
        boolean all0 = true;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                queue.offer(new Points(i, j, mat, new HashSet<>()));
                if (mat[i][j] == 1) all0 = false;
            }
        }
        if (all0) return 0;
        int minNum = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                Points cur = queue.poll();
                int[][] matrix = cur.state;
                int curx = cur.x;
                int cury = cur.y;
                Set<Integer> curSeen = cur.visited;
                flipMatrix(matrix, curx, cury);
                boolean isAll0 = true;
                for (int i = 0; i < row; i++) {
                    for (int j = 0; j < col; j++) {
                        if (matrix[i][j] == 1) {
                            isAll0 = false;
                            if (!curSeen.contains(i * col + j)) {
                                queue.offer(new Points(i, j, matrix, curSeen));
                            }
                        }
                    }
                }
                if (isAll0) return minNum + 1;
            }
            minNum++;
        }
        return -1;
    }

    private void flipMatrix(int[][] matrix, int curx, int cury) {
        for (int[] direct : DIRECTIONS) {
            int nextx = curx + direct[0];
            int nexty = cury + direct[1];
            matrix[nextx][nexty] = matrix[nextx][nexty] ^ 1;
        }
    }
}
