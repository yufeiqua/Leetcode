package com.leetcode.Graph;

import java.util.*;

public class LC01192_CriticalConnectionsInNetwork {
    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        List<List<Integer>> res = new ArrayList<>();
        constructGraph(connections, graph);
        for (int i = 0; i < n; i++) {
            tagNodeAndCheckCriticalEdge(graph, new int[n], -1, i, res);
        }
        return res;
    }

    private void constructGraph(List<List<Integer>> connections, Map<Integer, List<Integer>> graph) {
        for (List<Integer> connection : connections) {
            graph.computeIfAbsent(connection.get(0), k -> new ArrayList<>());
            graph.computeIfAbsent(connection.get(1), k -> new ArrayList<>());
            graph.get(connection.get((0))).add(connection.get(1));
            graph.get(connection.get(1)).add(connection.get(0));
        }
    }

    private int t = 0;

    private int tagNodeAndCheckCriticalEdge(Map<Integer, List<Integer>> graph, int[] ts, int prev, int current, List<List<Integer>> res) {
        if (ts[current] > 0) return ts[current];
        ts[current] = t++;
        int rs = Integer.MAX_VALUE;
        for (int next : graph.get(current)) {
            if (prev == next) continue;
            rs = Math.min(rs, tagNodeAndCheckCriticalEdge(graph, ts, current, next, res));
        }
        if (ts[current] <= rs) res.add(Arrays.asList(prev, current));
        return Math.min(ts[current], rs);
    }

}
