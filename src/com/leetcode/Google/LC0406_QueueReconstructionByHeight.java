package com.leetcode.Google;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class testLC0406 {
    public static void main(String[] args) {
        LC0406_QueueReconstructionByHeight lc0406_queueReconstructionByHeight = new LC0406_QueueReconstructionByHeight();
        int[][] people = new int[][]{{2, 4}, {3, 4}, {9, 0}, {0, 6}, {7, 1}, {6, 0}, {7, 3}, {2, 5}, {1, 1}, {8, 0}};
        lc0406_queueReconstructionByHeight.reconstructQueue(people);
    }
}

public class LC0406_QueueReconstructionByHeight {
    public int[][] reconstructQueue(int[][] people) {
        if (people == null || people.length == 0 || people[0] == null || people[0].length == 0) return new int[0][0];
        Arrays.sort(people, (o1, o2) -> o1[0] == o2[0] ? o1[1] - o2[1] : o2[0] - o1[0]);
//        for(int[] x : people) {
//            System.out.println(x[0] + " " + x[1]);
//        }
        List<int[]> res = new ArrayList<>();

        int i = 0;
        while (i < people.length) {
            if (i == 0 || people[i][0] == people[i - 1][0]) res.add(people[i]);
            else if (people[i][0] != people[i - 1][0]) {
                break;
            }
            i++;
        }
//        res.forEach(o -> System.out.println(o[0] + " " + o[1]));
        while (i < people.length) {
            int height = people[i][0];
            int count = people[i][1];
            if(count == 0){
                res.add(0, people[i]);
                i++;
                continue;
            }
            int countPeopleHigherOrEqualToCurrentHeight = 0;
            int k = 0;
            while (k < res.size()) {
                if (res.get(k)[0] >= height) countPeopleHigherOrEqualToCurrentHeight++;
                if (countPeopleHigherOrEqualToCurrentHeight == count) {
                    res.add(k + 1, people[i]);
                    break;
                }
                k++;
            }
            if(k == res.size()) res.add(people[i]);
            i++;
        }
        System.out.println(".............");
        res.forEach(o -> System.out.println(o[0] + " " + o[1]));
        int[][] ret = new int[people.length][people[0].length];
        for (int row = 0; row < people.length; row++) {
            ret[row][0] = res.get(row)[0];
            ret[row][1] = res.get(row)[1];
        }
        return ret;
    }
}
