package com.leetcode.TwoPointers;

import java.util.HashMap;

class Test340 {
    public static void main(String[] args) {
        LC0340_LongestSubstringAtMostKDistinctCharacters test = new LC0340_LongestSubstringAtMostKDistinctCharacters();
        String s = "abcabcabc";
        int k = 2;
        System.out.println(test.lengthOfLongestSubstringKDistinct(s, k));
    }
}

public class LC0340_LongestSubstringAtMostKDistinctCharacters {
    class Node {
        Node prev;
        Node next;
        int index;
        char ch;

        public Node(char ch, int index) {
            this.ch = ch;
            this.index = index;
        }
    }

    private HashMap<Character, Node> map = new HashMap<>();
    private Node head = null;
    private Node tail = null;

    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        if (s == null || s.length() <= k) {
            return s.length();
        }
        if (k == 0) return 0;
        int start = 0;
        int maxLength = 0;

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            Node newNode = new Node(ch, i);
            if (head == null) {
                map.put(ch, newNode);
                head = newNode;
                tail = head;
                continue;
            }
            Node oldNode = map.get(ch);
            /**
             * special case aa -> 如果不先加 node，直接delete掉head再加node，就会出现head = tail = null; -> NPE
             */
            addNewNodeToTail(newNode);
            if (map.size() == k) {
                if (oldNode == null) {
                    start = head.index + 1;
                    map.remove(head.ch);
                    head = head.next;
                } else {
                    deleteNode(oldNode);
                }
            } else if (map.size() < k && oldNode != null) {
                deleteNode(oldNode);
            }
            map.put(ch, newNode);
            maxLength = Math.max(maxLength, i - start + 1);
        }
        return maxLength;
    }

    private void addNewNodeToTail(Node newNode) {
        tail.next = newNode;
        newNode.prev = tail;
        tail = tail.next;
    }

    private void deleteNode(Node oldNode) {
        if (oldNode == head) {
            head = head.next;
            return;
        }
        oldNode.prev.next = oldNode.next;
        oldNode.next.prev = oldNode.prev;
        oldNode.prev = null;
        oldNode.next = null;
    }

    /**
     * 解法二
     *
     * @param s
     * @param k
     * @return max length of substring
     */
    public int lengthOfLongestSubstringKDistinct2(String s, int k) {
        if (s == null || s.length() <= k) {
            return s.length();
        }
        if (k == 0) return 0;
        int wk = 0;
        int[] count = new int[256];
        int start = 0;
        int maxLength = 0;
        for (int i = 0; i < s.length(); i++) {
            if (count[s.charAt(i)]++ == 0) {
                wk++;
            }
            while (wk > k) {
                if (--count[s.charAt(start++)] == 0) wk--;
            }
            maxLength = Math.max(maxLength, i - start + 1);
        }
        return maxLength;
    }
}
