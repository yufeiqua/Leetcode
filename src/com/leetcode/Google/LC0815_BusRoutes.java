package com.leetcode.Google;

import java.util.*;

public class LC0815_BusRoutes {
    public int numBusesToDestination(int[][] routes, int S, int T) {
        Map<Integer, Set<Integer>> stopAndBuses = new HashMap<>();
        int row = routes.length;
        int col = routes[0].length;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                stopAndBuses.computeIfAbsent(routes[i][j], k -> new HashSet<>());
                stopAndBuses.get(routes[i][j]).add(i);
            }
        }

        int start;
        int end;
        if (stopAndBuses.get(S).size() > stopAndBuses.get(T).size()) {
            start = T;
            end = S;
        } else {
            start = S;
            end = T;
        }
        Queue<Integer> queue = new LinkedList<>(stopAndBuses.get(start)); //add all buses into queue.
        Set<Integer> seenBus = new HashSet<>(stopAndBuses.get(start));
        int level = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                int curBus = queue.poll();
                for (int stop : routes[curBus]) {
                    if (stop == end) return level;
                    Set<Integer> buses = stopAndBuses.get(stop);
                    for (int bus : buses) {
                        if (!seenBus.add(bus)) continue;
                        queue.add(bus);
                    }
                }

            }
            level++;
        }
        return -1;
    }
}
















