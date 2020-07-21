package com.leetcode.Graph;

import java.util.*;


public class LC0685_RedundantConnections2 {
    class UnionFind {
        int[] parents;
        int[] size;

        public UnionFind(int n) {
            this.parents = new int[n];
            for (int i = 0; i < n; i++) {
                this.parents[i] = i;
            }
            this.size = new int[n];
        }

        public boolean find(int n, int m) {
            return root(n) == root(m);
        }

        public void union(int n, int m) {
            int rootn = root(n);
            int rootm = root(m);
            if (size[rootn] < size[rootm]) {
                parents[rootn] = rootm;
                size[rootm] += size[rootn];
            } else {
                parents[rootm] = rootn;
                size[rootn] += size[rootm];
            }
        }

        private int root(int n) {
            int temp = n;
            while (parents[temp] != temp) {
                parents[temp] = parents[parents[temp]];
                temp = parents[temp];
            }
            parents[n] = temp;
            return temp;
        }
    }

    public int[] findRedundantDirectedConnection(int[][] edges) {
        Map<Integer, Integer> childToParent = new HashMap<>();
        List<int[]> candidate = new ArrayList<>();
        for (int[] edge : edges) {
            int n = edge[0];
            int m = edge[1];
            if (childToParent.get(m) == null) {
                childToParent.put(m, n);
            } else {
                candidate.add(new int[]{n, m});
                candidate.add(new int[]{childToParent.get(m), m});
            }
        }
        UnionFind unionFind = new UnionFind(edges.length);
        for (int[] edge : edges) {
            if (candidate.size() != 0 && (edge[0] == candidate.get(0)[0] && edge[1] == candidate.get(0)[1] || edge[0] == candidate.get(1)[0] && edge[1] == candidate.get(1)[1]))
                continue;
            if (unionFind.find(edge[0], edge[1])) {
                if (candidate.size() == 0) return edge;
            } else {
                unionFind.union(edge[0], edge[1]);
            }
        }

        for (int i = 1; i >= 0; i--) {
            int[] c = candidate.get(i);
            if (!unionFind.find(c[0], c[1])) {
                unionFind.union(c[0], c[1]);
            } else {
                return c;
            }

        }
        return new int[0];
    }


}
