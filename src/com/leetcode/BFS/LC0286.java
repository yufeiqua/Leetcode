package com.leetcode.BFS;

import java.util.*;

import static com.leetcode.Utils.DIRECTIONS;

public class LC0286 {
    public int rowLength;
    public int colLength;

    public void wallsAndGates(int[][] rooms) {
        if (rooms == null || rooms.length == 0 || rooms[0].length == 0) return;
        rowLength = rooms.length;
        colLength = rooms[0].length;
        Queue<Integer> allPossibleSearchLoc = initializeSearch(rooms);
        fillOutMinLength2Gate(rooms, allPossibleSearchLoc);
    }

    private Queue<Integer> initializeSearch(int[][] rooms) {
        Queue<Integer> allPossibleSearchLoc = new LinkedList<>();
        for (int row = 0; row < rooms.length; row++) {
            for (int col = 0; col < rooms[row].length; col++) {
                if (rooms[row][col] == 0) {
                    allPossibleSearchLoc.offer(row * colLength + col);
                }
            }
        }
        return allPossibleSearchLoc;
    }

    private void fillOutMinLength2Gate(int[][] rooms, Queue<Integer> allPossibleSearchLoc) {
        int size;
        int currLoc;
        int minLength = 1;
        Set<Integer> visited = new HashSet<>();
        while (!allPossibleSearchLoc.isEmpty()) {
            size = allPossibleSearchLoc.size();
            while (size-- > 0) {
                currLoc = allPossibleSearchLoc.poll();
                for (int[] direction : DIRECTIONS) {
                    int nextRow = currLoc / colLength + direction[0];
                    int nextCol = currLoc % colLength + direction[1];
                    if (nextRow < 0 || nextRow >= rowLength || nextCol < 0 || nextCol >= colLength ||
                            rooms[nextRow][nextCol] == 0 || rooms[nextRow][nextCol] == -1 || !visited.add(nextRow * colLength + nextCol)) {
                        continue;
                    }
                    rooms[nextRow][nextCol] = minLength;
                    allPossibleSearchLoc.offer(nextRow * colLength + nextCol);
                }
            }
            minLength++;
        }
    }

}
