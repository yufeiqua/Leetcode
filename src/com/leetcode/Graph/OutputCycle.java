package com.leetcode.Graph;

import java.util.*;

class Test {
    public static void main(String[] args) {
        OutputCycle outputCycle = new OutputCycle();
        List<List<Integer>> graph = new ArrayList<>();
        graph.add(Arrays.asList(0, 2));
        graph.add(Arrays.asList(0, 1));
        graph.add(Arrays.asList(1, 3));
        graph.add(Arrays.asList(1, 6));
        graph.add(Arrays.asList(3, 5));
        graph.add(Arrays.asList(6, 5));
        graph.add(Arrays.asList(3, 4));
        graph.add(Arrays.asList(4, 2));
        List<String> res = outputCycle.outputCycle(7, graph);
        for(String str : res) {
            System.out.println(str);
        }
    }
}

public class OutputCycle {
    /**
     * There is only one cycle in the graph.
     * How about 2 or more?
     */
    public List<String> outputCycle(int n, List<List<Integer>> connections) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        constructGraph(connections, graph);
        List<String> res = new ArrayList<>();
        tagNodeAndCheckCriticalEdge(graph, new int[n], -1, 0, new StringBuilder(), res);
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

    private int t = 1;

    private int tagNodeAndCheckCriticalEdge(Map<Integer, List<Integer>> graph, int[] ts, int prev, int current, StringBuilder sb, List<String> res) {
        if (ts[current] > 0) {
            if (sb.length() == 0)
                sb.append(current);
            return ts[current];
        }
        ts[current] = t++;
        System.out.println(current + " " + ts[current]);
        int rs = Integer.MAX_VALUE;
        for (int next : graph.get(current)) {
            if (prev == next) {
                continue;
            }
            int ret = tagNodeAndCheckCriticalEdge(graph, ts, current, next, sb, res);
            rs = Math.min(rs, ret);
            if (ts[current] > ret) {
                sb.append(current);
                System.out.println(sb.toString());
                if (rs == ts[prev]) {
                    res.add(sb.toString());
                    sb = new StringBuilder();
                }
            }
        }
        return Math.min(ts[current], rs);
    }
}
