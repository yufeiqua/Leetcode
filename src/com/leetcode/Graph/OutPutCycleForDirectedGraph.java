package com.leetcode.Graph;

import java.util.*;

class TestMain {
    public static void main(String[] args) {
        OutPutCycleForDirectedGraph outPutCycleForDirectedGraph = new OutPutCycleForDirectedGraph();
        Map<Integer, List<Integer>> graph = new HashMap<>();
        graph.put(1, Arrays.asList(2));
        graph.put(2, Arrays.asList(3, 5));
        graph.put(3, Arrays.asList(4));
        graph.put(4, Arrays.asList(1));
        graph.put(5, Arrays.asList(6));
        graph.put(6, Arrays.asList(3));
        List<String> res = outPutCycleForDirectedGraph.outputAllCycleInDirectedGraph(graph);
        res.forEach(System.out::println); // 1234 125634

        Map<Integer, List<Integer>> graph2 = new HashMap<>();
        graph2.put(1, Arrays.asList(2));
        graph2.put(2, Arrays.asList(3));
        graph2.put(3, Arrays.asList(2, 4));
        graph2.put(4, Arrays.asList(5));
        graph2.put(5, Arrays.asList(6));
        graph2.put(6, Arrays.asList(4));
        List<String> res2 = outPutCycleForDirectedGraph.outputAllCycleInDirectedGraph(graph2);
        res2.forEach(System.out::println); //23 456

        Map<Integer, List<Integer>> graph3 = new HashMap<>();
        graph3.put(1, Arrays.asList(2));
        graph3.put(2, Arrays.asList(3));
        graph3.put(3, Arrays.asList(4));
        graph3.put(4, Arrays.asList(2, 5));
        graph3.put(5, Arrays.asList(6));
        graph3.put(6, Arrays.asList(2));
        List<String> res3 = outPutCycleForDirectedGraph.outputAllCycleInDirectedGraph(graph3);
        res3.forEach(System.out::println); //234 23456

    }
}

public class OutPutCycleForDirectedGraph {
    /**
     *
     * @param graph, assume graph are connected, and they are only 6 node tag from 1 - 6
     * @return List of String, all path
     */

    public List<String> outputAllCycleInDirectedGraph(Map<Integer, List<Integer>> graph) {
        List<String> res = new ArrayList<>();
        Map<Integer, State> state = new HashMap<>();
        state.put(1, State.INITIAL);
        state.put(2, State.INITIAL);
        state.put(3, State.INITIAL);
        state.put(4, State.INITIAL);
        state.put(5, State.INITIAL);
        state.put(6, State.INITIAL);
        checkCycleAndGenerateAllPath(graph, state, 1, res, new StringBuilder());
        return res;
    }

    private boolean checkCycleAndGenerateAllPath(Map<Integer, List<Integer>> graph, Map<Integer, State> state, int curNode, List<String> res, StringBuilder sb) {
        if (state.get(curNode) == State.VISITING) {
            int index = sb.indexOf(String.valueOf(curNode));
            sb.subSequence(index, sb.length());
            res.add(sb.subSequence(index, sb.length()).toString());
            return true;
        }
        if (state.get(curNode) == State.DONE) {
            return false;
        }
        state.put(curNode, State.VISITING);
        sb.append(curNode);
        boolean hasCycle = false;
        if (graph.get(curNode) != null) {
            for (int next : graph.get(curNode)) {
                hasCycle |= checkCycleAndGenerateAllPath(graph, state, next, res, sb);
            }
        }
        sb.setLength(sb.length() - 1);
        if (hasCycle) {
            state.put(curNode, State.INITIAL);
            return true;
        }
        state.put(curNode, State.DONE);
        return false;
    }
}
