package com.leetcode.Interval;

import java.util.*;

class TestSkyline {
    public static void main(String[] args) {
        LC0218_SkyLineProblems skyLineProblems = new LC0218_SkyLineProblems();
        int[][] buildings = new int[][]{{0, 2, 3}, {2, 5, 3}};
        List<List<Integer>> ret = skyLineProblems.getSkyline(buildings);
        for (List<Integer> r : ret) {
            System.out.println(r.get(0) + " " + r.get(1));
        }

    }
}

public class LC0218_SkyLineProblems {

    class Building implements Comparable<Building> {
        private int position;
        private int height;
        private boolean isEnd;

        public Building(int position, int height, boolean isEnd) {
            this.position = position;
            this.height = height;
            this.isEnd = isEnd;
        }

        public int getPosition() {
            return this.position;
        }

        public int getHeight() {
            return this.height;
        }

        public boolean getIsEnd() {
            return this.isEnd;
        }

        @Override
        public int compareTo(Building o) {
            if (this.position == o.position) {
                if (!this.isEnd && !o.isEnd) {
                    return o.height - this.height;
                } else if (this.isEnd && o.isEnd) {
                    return this.height - o.height;
                } else {
                    return this.isEnd ? 1 : -1;
                }
            } else {
                return this.getPosition() - o.getPosition();
            }

        }
    }

    public List<List<Integer>> getSkyline(int[][] buildings) {
        List<List<Integer>> ret = new ArrayList<>();
        List<Building> buildingList = new ArrayList<>();
        for (int[] building : buildings) {
            buildingList.add(new Building(building[0], building[2], false));
            buildingList.add(new Building(building[1], building[2], true));
        }
        Collections.sort(buildingList);
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);
        pq.offer(0);
        for (Building building : buildingList) {
            if (!building.getIsEnd()) {
                int peek = pq.peek();
                pq.offer(building.getHeight());
                if (building.getHeight() > peek) {
                    ret.add(Arrays.asList(building.getPosition(), building.getHeight()));
                }
            } else {
                int peek_height = pq.peek();
                pq.remove(building.getHeight());
                if (pq.peek() < peek_height) {
                    if (pq.isEmpty()) {
                        ret.add(Arrays.asList(building.getPosition(), 0));
                    } else {
                        ret.add(Arrays.asList(building.getPosition(), pq.peek()));
                    }
                }
            }
        }
        return ret;
    }
}

