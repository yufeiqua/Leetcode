package com.leetcode.DFS.DFS;

import java.util.ArrayList;
import java.util.List;

public class LC0212_WordSearchII {
    class Trie {
        class TrieNode {
            private char value;
            private TrieNode[] children;

            public TrieNode(char value) {
                this.value = value;
                children = new TrieNode[26];
            }

            public TrieNode[] getChildren() {
                return this.children;
            }
        }

        public void buildTrie(String[] word) {


        }

        public boolean findWord(char[][] board) {
            return false;
        }

    }

    public List<String> findWords(char[][] board, String[] words) {
        Trie trie = new Trie();
        trie.buildTrie(words);
        trie.findWord(board);
        return new ArrayList<>();
    }


}
