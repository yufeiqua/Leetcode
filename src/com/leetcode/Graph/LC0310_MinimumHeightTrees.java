package com.leetcode.Graph;

import java.util.*;

public class LC0310_MinimumHeightTrees {
    /**
     * 无向图 -> clarify
     **/
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        List<Integer> ret = new ArrayList<>();
        if(n == 0){
            ret.add(0);
            return ret;
        }
        if(edges == null || edges.length == 0 || edges[0] == null || edges[0].length == 0){
            ret.add(0);
            return ret;
        }
        List<Integer>[] graph = (ArrayList<Integer>[]) new ArrayList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }
        int[] indegree = new int[n];
        buildGraph(graph, edges, indegree);
        return helper(n, graph, indegree);
    }

    private List<Integer> helper(int n, List<Integer>[] graph, int[] indegree) {
        Queue<Integer> queue = new LinkedList<>();
        for(int i = 0; i < indegree.length; i++) {
            if(indegree[i] == 1) {
                queue.offer(i);
            }
        }
        List<Integer> ret = new ArrayList<>();
        int count = n;
        while (count > 2) {
            int size = queue.size();
            count -= size;
            while (size-- > 0) {
                int node = queue.poll();
                indegree[node]--;
                for (int next : graph[node]) {
                    indegree[next]--;
                    if (indegree[next] == 1) {
                        queue.offer(next);
                    }

                }
            }
        }
        ret.addAll(queue);
        return ret;
    }

    private void buildGraph(List<Integer>[] graph, int[][] edges, int[] indegree) {
        for (int[] edge : edges) {
            int node1 = edge[0];
            int node2 = edge[1];
            graph[node1].add(node2);
            indegree[node2]++;
            graph[node2].add(node1);
            indegree[node1]++;
        }

    }

}
