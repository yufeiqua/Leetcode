package com.leetcode.Google;

import java.util.*;

public class LC0939_MinAreaRectangle {
    public int minAreaRect(int[][] points) {
        TreeMap<Integer, List<Integer>> xToyMap = new TreeMap<>();
        Arrays.stream(points).forEach(point -> {
            int x = point[0];
            int y = point[1];
            xToyMap.computeIfAbsent(x, k -> new ArrayList<>());
            xToyMap.get(x).add(y);
        });
        Map<Integer, Integer> yToX = new HashMap<>();
        int minArea = Integer.MAX_VALUE;
        for (int key : xToyMap.keySet()) {
            List<Integer> yPoints = xToyMap.get(key);
            Collections.sort(yPoints);
            for (int i = 0; i < yPoints.size(); i++) {
                for (int j = i + 1; j < yPoints.size(); j++) {
                    int y1 = yPoints.get(i);
                    int y2 = yPoints.get(j);
                    int hashing = y1 * 40001 + y2;
                    if(yToX.containsKey(hashing)) {
                        int x = yToX.get(hashing);
                        minArea = Math.min(minArea, (key - x) * (y2 - y1));
                    }
                    yToX.put(hashing, key);
                }
            }
        }
        return minArea == Integer.MAX_VALUE ? 0 : minArea;
    }
}
                                                                                                     