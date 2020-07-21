package com.leetcode.BFS;

import java.util.*;

public class LC0126_WordLadder2 {
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        Set<String> dictionary = new HashSet<>(wordList);
        if (!dictionary.contains(endWord)) return new ArrayList<>();
        Map<String, List<String>> graph = constructGraph(beginWord, endWord, dictionary);
        if (graph.isEmpty()) return new ArrayList<>();
        return constructShortestPaths(endWord, beginWord, graph);
    }

    private Map<String, List<String>> constructGraph(String beginWord, String endWord, Set<String> dictionary) {
        Queue<String> allPossibleTransformedWords = new LinkedList<>();
        dictionary.remove(beginWord);
        allPossibleTransformedWords.offer(beginWord);
        Map<String, List<String>> graph = new HashMap<>();
        Set<String> levelVisitedWords = new HashSet<>();
        int size;
        boolean foundEndWord = false;
        String currWord;
        while (!allPossibleTransformedWords.isEmpty()) {
            size = allPossibleTransformedWords.size();
            while (size-- > 0) {
                currWord = allPossibleTransformedWords.poll();
                char[] wordArray = currWord.toCharArray();
                for (char replaceChar = 'a'; replaceChar <= 'z'; replaceChar++) {
                    for (int i = 0; i < wordArray.length; i++) {
                        char tempChar = wordArray[i];
                        wordArray[i] = replaceChar;
                        String newWord = new String(wordArray);
                        if (replaceChar != tempChar && dictionary.contains(newWord)) {
                            if (levelVisitedWords.add(newWord)) {
                                allPossibleTransformedWords.offer(newWord);
                            }
                            List<String> path = graph.computeIfAbsent(newWord, k -> new ArrayList<>());
                            path.add(currWord);
                            if (newWord.equals(endWord)) {
                                foundEndWord = true;
                            }
                        }
                        wordArray[i] = tempChar;
                    }
                }
            }
            if (foundEndWord) return graph;
            dictionary.removeAll(levelVisitedWords);
            levelVisitedWords.clear();
        }
        return graph;
    }

    private List<List<String>> constructShortestPaths(String start, String end, Map<String, List<String>> graph) {
        List<List<String>> res = new ArrayList<>();
        if (start.equals(end)) {
            List<String> path = new ArrayList<>();
            path.add(end);
            res.add(path);
            return res;
        }
        if (graph.get(start) == null) {
            return res;
        }
        for (String nextWord : graph.get(start)) {
            List<List<String>> paths = constructShortestPaths(nextWord, end, graph);
            for (List<String> path : paths) {
                path.add(start);
            }
            res.addAll(paths);
        }
        return res;
    }

    private void constructShortestPaths2(String start, String end, Map<String, List<String>> graph, List<String> path, List<List<String>> res) {
        if (start.equals(end)) {
            List<String> level = new LinkedList<>(path);
            res.add(level);
            return;
        }
        if (graph.get(start) == null) {
            return;
        }
        List<String> next = graph.get(start);
        for (String nextWord : next) {
            path.add(0, nextWord);
            constructShortestPaths2(nextWord, end, graph, path, res);
            path.remove(0);
        }
    }
}


