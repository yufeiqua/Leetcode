package com.leetcode.UnionFind;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.leetcode.Utils.DIRECTIONS;

class UnionFind {
    private int[] parent;
    private int[] size;
    int[] value;
    int count;

    public UnionFind(int n) {
        this.parent = new int[n];
        for (int i = 0; i < n; i++) {
            this.parent[i] = i;
        }
        this.size = new int[n];
        Arrays.fill(size, 1);
        this.value = new int[n];
        this.count = 0;
    }

    public void union(int node1, int node2) {
        int root1 = getRoot(node1);
        int root2 = getRoot(node2);
        if (size[root1] < size[root2]) {
            parent[root1] = root2;
            size[root2] += size[root1];
        } else {
            parent[root2] = root1;
            size[root1] += size[root2];
        }
        this.count--;
    }

    public boolean find(int node1, int node2) {
        return getRoot(node1) == getRoot(node2);
    }

    private int getRoot(int node) {
        int temp = node;
        while (parent[node] != node) {
            parent[node] = parent[parent[node]];
            node = parent[node];
        }
        parent[temp] = node;
        return node;
    }

    public void addIsland(int node) {
        if(this.value[node] == 1) return;
        this.count++;
        this.value[node] = 1;
    }
}

class test305 {
    public static void main(String[] args) {
        LC0305_NumberOfIslandII numberOfIslandII = new LC0305_NumberOfIslandII();
        int m = 3, n = 3;
        int[][] positions = new int[][]{{0, 0}, {0, 1}, {1, 2}, {1, 2}};
        List<Integer> ret = numberOfIslandII.numIslands2(m, n, positions);
        ret.forEach(System.out::println);
    }
}

public class LC0305_NumberOfIslandII {
    public List<Integer> numIslands2(int m, int n, int[][] positions) {
        List<Integer> list = new ArrayList<>();
        UnionFind unionFind = new UnionFind(m * n);
        for (int[] position : positions) {
            int curPosition = position[0] * n + position[1];
            unionFind.addIsland(curPosition);
            for (int[] direct : DIRECTIONS) {
                int next1 = position[0] + direct[0];
                int next2 = position[1] + direct[1];
                if (next1 < 0 || next1 >= m || next2 < 0 || next2 >= n) continue;
                int nextPosition = next1 * n + next2;
                if (unionFind.value[nextPosition] == 1 && !unionFind.find(nextPosition, curPosition)) {
                    unionFind.union(nextPosition, curPosition);
                }
            }
            list.add(unionFind.count);
        }
        return list;
    }
}
