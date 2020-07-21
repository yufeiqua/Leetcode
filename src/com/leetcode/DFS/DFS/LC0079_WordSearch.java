package com.leetcode.DFS.DFS;

import static com.leetcode.Utils.DIRECTIONS;

public class LC0079_WordSearch {
    private int row;
    private int col;

    public boolean exist(char[][] board, String word) {
        if (word == null || word.length() == 0) return false;
        row = board.length;
        col = board[0].length;
        boolean[][] visited = new boolean[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (board[i][j] == word.charAt(0) && dfs(board, i, j, 0, word, visited)) {
                    return true;
                }
            }
        }
        return false;
    }

    //TC: 4 4^L
    //SC:

    private boolean dfs(char[][] board, int curI, int curJ, int curIndex, String word, boolean[][] visited) {
        if (curIndex == word.length()) {
            return true;
        }
        if (curI < 0 || curI >= row || curJ < 0 || curJ >= col || visited[curI][curJ] || board[curI][curJ] != word.charAt(curIndex)) {
            return false;
        }
        visited[curI][curJ] = true;
        boolean ret = false;
        for (int[] direct : DIRECTIONS) {
            int nextI = curI + direct[0];
            int nextJ = curJ + direct[1];
            ret = dfs(board, nextI, nextJ, curIndex + 1, word, visited);
            //why not if (dfs) return ?
            if (ret) {
                break;
            }
        }
        visited[curI][curJ] = false;
        return ret;
    }
}
