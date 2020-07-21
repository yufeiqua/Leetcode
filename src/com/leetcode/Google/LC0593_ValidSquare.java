package com.leetcode.Google;

import java.util.ArrayList;
import java.util.List;

class TestValidSquare {
    public static void main(String[] args) {
        LC0593_ValidSquare lc0593_validSquare = new LC0593_ValidSquare();
        int[] p1 = {1, 0};
        int[] p2 = {-1, 0};
        int[] p3 = {0, 1};
        int[] p4 = {0, -1};
        lc0593_validSquare.validSquare(p1, p2, p3, p4);
    }
}

public class LC0593_ValidSquare {
    public boolean validSquare(int[] p1, int[] p2, int[] p3, int[] p4) {
        List<int[]> list = new ArrayList<>();
        list.add(p1);
        list.add(p2);
        list.add(p3);
        list.add(p4);
        list.sort(((o1, o2) -> o1[0] == o2[0] ? o1[1] - o2[1] : o1[0] - o2[0]));
        int[] ps1 = list.get(0);
        int[] ps2 = list.get(1);
        int[] ps3 = list.get(2);
        int[] ps4 = list.get(3);
        long distanceBetweenP1AndP2 = calculateDistance(ps2, ps1);
        long distanceBetweenP2AndP4 = calculateDistance(ps4, ps2);
        long distanceBetweenP3AndP4 = calculateDistance(ps4, ps3);
        long distanceBetweenP1AndP3 = calculateDistance(ps3, ps1);
        if (!(distanceBetweenP1AndP2 == distanceBetweenP2AndP4 && distanceBetweenP2AndP4 == distanceBetweenP3AndP4
                && distanceBetweenP3AndP4 == distanceBetweenP1AndP3)
                || distanceBetweenP1AndP2 == 0
                || distanceBetweenP2AndP4 == 0
                || distanceBetweenP3AndP4 == 0
                || distanceBetweenP1AndP3 == 0)
            return false;
        long distanceBetweenP1AndP4 = calculateDistance(ps4, ps1);
        long distanceBetweenP2AndP3 = calculateDistance(ps3, ps2);
        if (distanceBetweenP1AndP4 != distanceBetweenP2AndP3) return false;
        if (distanceBetweenP1AndP3 + distanceBetweenP3AndP4 != distanceBetweenP1AndP4) return false;
        return distanceBetweenP1AndP2 + distanceBetweenP2AndP4 == distanceBetweenP1AndP4;
    }

    private long calculateDistance(int[] p1, int[] p2) {
        return (long) (p2[0] - p1[0]) * (p2[0] - p1[0]) + (p2[1] - p1[1]) * (p2[1] - p1[1]);
    }
}
