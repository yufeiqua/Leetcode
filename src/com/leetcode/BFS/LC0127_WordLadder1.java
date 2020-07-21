package com.leetcode.BFS;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LC0127_WordLadder1 {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        if (beginWord == null || beginWord.length() == 0 || endWord == null || endWord.length() == 0) return 0;
        if (beginWord.equals(endWord)) return 0;
        Set<String> dictionary = new HashSet<>(wordList);
        dictionary.remove(beginWord);
        if (!dictionary.contains(endWord)) return 0;
        return searchDictionary2FindMinPath(beginWord, endWord, dictionary);
    }

    private int searchDictionary2FindMinPath(String beginWord, String endWord, Set<String> dictionary) {
        Set<String> startQueue = new HashSet<>();
        Set<String> endQueue = new HashSet<>();
        int minLength = 1;
        startQueue.add(beginWord);
        endQueue.add(endWord);

        while (!startQueue.isEmpty()) {
            Set<String> temp = new HashSet<>();
            for (String word : startQueue) {
                char[] wordArray = word.toCharArray();
                for (char replaceChar = 'a'; replaceChar <= 'z'; replaceChar++) {
                    for (int i = 0; i < wordArray.length; i++) {
                        char tempChar = wordArray[i];
                        wordArray[i] = replaceChar;
                        String newWord = new String(wordArray);
                        if (replaceChar != tempChar && dictionary.contains(newWord)) {
                            System.out.println(newWord);
                            if (endQueue.contains(newWord)) {
                                return minLength + 1;
                            }
                            temp.add(newWord);
                            dictionary.remove(newWord);
                        }
                        wordArray[i] = tempChar;
                    }
                }
            }
            startQueue = Set.copyOf(temp);
            switchSearchQueue(startQueue, endQueue);
            minLength++;
        }
        return 0;
    }

    private void switchSearchQueue(Set<String> startQueue, Set<String> endQueue) {
        Set<String> temp;
        if (startQueue.size() > endQueue.size()) {
            temp = Set.copyOf(startQueue);
            startQueue = Set.copyOf(endQueue);
            endQueue = Set.copyOf(temp);
        }
    }
}
