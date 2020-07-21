package com.leetcode.Interval;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class MergeInterval {
    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });
        List<int[]> ret = new ArrayList<>();
        int[] prev = intervals[0];
        for (int[] cur : intervals) {
            if (cur[0] <= prev[1]) {
                prev = new int[]{Math.min(prev[0], cur[0]), Math.max(prev[1], cur[1])};
            } else {
                ret.add(prev);
                prev = cur;
            }
        }
        ret.add(prev);
        int[][] ret2 = new int[ret.size()][2];
        for (int i = 0; i < ret.size(); i++) {
            ret2[i][0] = ret.get(i)[0];
            ret2[i][1] = ret.get(i)[1];
        }
        return ret2;
    }
}
