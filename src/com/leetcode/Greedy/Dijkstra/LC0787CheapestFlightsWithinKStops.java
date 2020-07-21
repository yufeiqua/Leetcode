package com.leetcode.Greedy.Dijkstra;

import com.leetcode.DFS.Prunning_DP.LC0329_LongestIncreasingPath;
import com.leetcode.TreeNode;

import java.util.*;

public class LC0787CheapestFlightsWithinKStops {
    class Node implements Comparable<Node> {
        int name;
        int totalCost;
        int totalStopFromSrc;

        public Node(int name, int totalCost, int totalStopFromSrc) {
            this.name = name;
            this.totalCost = totalCost;
            this.totalStopFromSrc = totalStopFromSrc;
        }

        @Override
        public int compareTo(Node o) {
            return this.totalCost - o.totalCost;
        }
    }


    private Map<Integer, List<Node>> preProcessing(int[][] edges) {
        Map<Integer, List<Node>> graph = new HashMap<>();
        for (int[] row : edges) {
            graph.computeIfAbsent(row[0], k -> new ArrayList<>());
            graph.get(row[0]).add(new Node(row[1], row[2], 0));
        }
        return graph;
    }

    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {
        Map<Integer, List<Node>> graph = preProcessing(flights);
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(src, 0, 0));
        Set<Node> visitedCities = new HashSet<>();
        while (!pq.isEmpty()) {
            Node node = pq.poll();
            if (node.name == dst) return node.totalCost;
            List<Node> next = graph.get(node.name);
            if (!visitedCities.add(node)) continue;
            if (node.totalStopFromSrc >= K + 1) {
                continue;
            }
            if (next == null) continue;
            for (Node destination : next) {
                pq.offer(new Node(destination.name, node.totalCost + destination.totalCost, node.totalStopFromSrc + 1));
            }
        }
        return -1;
    }

}
