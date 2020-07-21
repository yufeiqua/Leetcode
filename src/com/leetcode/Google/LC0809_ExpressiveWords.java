package com.leetcode.Google;

import com.leetcode.TreeNode;

class TrieNode {
    public char value;
    public TrieNode[] children;
    boolean isEnd;

    public TrieNode(char value) {
        this.value = value;
        this.children = new TrieNode[26];
    }
}

class Trie {
    private final TrieNode root;

    public Trie() {
        this.root = new TrieNode('\0');
    }

    public void addWord(String word) {
        TrieNode temp = root;
        for (char ch : word.toCharArray()) {
            if (root.children[ch - '0'] == null) {
                root.children[ch - '0'] = new TrieNode(ch);
            }
            temp = root.children[ch - '0'];
        }
        temp.isEnd = true;
    }
}

class TestLC809 {
    public static void main (String[] args) {
        LC0809_ExpressiveWords expressiveWords = new LC0809_ExpressiveWords();
        String S = "heeelllooo";
    }
}

public class LC0809_ExpressiveWords {

    public int expressiveWords(String S, String[] words) {
        int count = 0;
        for (String word : words) {
            if (checkIsExpressiveWord(word, S)) count++;
        }
        return count;
    }

    private boolean checkIsExpressiveWord(String query, String target) {
        int index = 0;
        int indexTarget = 0;
        boolean flag = false;
        while (index < query.length() && indexTarget < target.length()) {
            if (query.charAt(index) == target.charAt(indexTarget)) {
                index++;
                indexTarget++;
            } else {
                if (index > 0 && query.charAt(index - 1) != query.charAt(index)) return false;
                if (index > 0 && query.charAt(index - 1) == query.charAt(index)) {
                    int temp = index - 1;
                    while (temp < query.length() && query.charAt(temp) == query.charAt(index - 1)) {
                        temp++;
                    }
                    if (temp - index + 1 >= 3) flag = true;
                    index = temp;
                }
            }
        }
        return flag && index == query.length() && indexTarget == target.length();
    }
}
