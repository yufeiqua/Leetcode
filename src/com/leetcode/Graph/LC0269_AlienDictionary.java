package com.leetcode.Graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LC0269_AlienDictionary {
    public String alienOrder(String[] words) {
        Map<Character, List<Character>> graph = buildGraph(words);
        if(graph.isEmpty()) return "";
        Map<Character, State> stateMap = new HashMap<>();
        for (char c = 'a'; c <= 'z'; c++) {
            stateMap.put(c, State.INITIAL);
        }
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<Character, List<Character>> entry : graph.entrySet()) {
            Character k = entry.getKey();
            if (stateMap.get(k).equals(State.INITIAL)) {
                if (checkCycleAndSortByOrder(graph, k, stateMap, sb)) {
                    return "";
                }
            }
        }

        return sb.reverse().toString();
    }

    private boolean checkCycleAndSortByOrder(Map<Character, List<Character>> graph, Character curNode, Map<Character, State> stateMap, StringBuilder path) {
        if (stateMap.get(curNode).equals(State.VISITING)) return true;
        if (stateMap.get(curNode).equals(State.DONE)) return false;
        stateMap.put(curNode, State.VISITING);
        for (Character child : graph.get(curNode)) {
            if (checkCycleAndSortByOrder(graph, child, stateMap, path)) return true;
        }
        stateMap.put(curNode, State.DONE);
        path.append(curNode);
        return false;
    }

    /**
     * abc
     * ab
     *
     * a
     * a
     */
    private Map<Character, List<Character>> buildGraph(String[] words) {
        Map<Character, List<Character>> graph = new HashMap<>();
        for (int i = 0; i < words.length - 1; i++) {
            String str1 = words[i];
            String str2 = words[i + 1];
            if(str1.equals(str2)) {
               continue;
            }
            int minLength = Math.min(str1.length(), str2.length());
            int j;
            for (j = 0; j < minLength; j++) {
                if (str1.charAt(j) == str2.charAt(j)) continue;
                graph.computeIfAbsent(str1.charAt(j), k -> new ArrayList<>()).add(str2.charAt(j));
                break;
            }
            if (j == minLength && minLength == str2.length()) {
                return new HashMap<>();
            }
        }
        for (String word : words) {
            for (char ch : word.toCharArray()) {
                graph.computeIfAbsent(ch, k -> new ArrayList<>());
            }
        }
        return graph;
    }


}
