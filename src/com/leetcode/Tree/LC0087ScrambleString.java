package com.leetcode.Tree;

import java.util.ArrayList;
import java.util.List;

public class LC0087ScrambleString {
    class Node {
        Node left;
        Node right;
        String value;

        public Node(String value) {
            this.value = value;
        }
    }

    public boolean isScramble(String s1, String s2) {
        if (s1 == null && s2 == null) return true;
        if (s1 == null || s2 == null) return false;
        if (s1.equals(s2)) return true;
        if (s1.length() != s2.length()) return false;

        return false;
    }


//    private boolean isSameTree(Node p, Node q) {
//        if (p == null && q == null) return true;
//        if (p == null || q == null) return false;
//        if (!p.value.equals(q.value) && !switchCouldBeSame(p.value, q.value)) {
//            return false;
//        }
//        boolean left = isSameTree(p.left, q.left);
//        boolean right = isSameTree(p.right, q.right);
//        return left && right;
//    }
//
//    private boolean switchCouldBeSame(String p, String q) {
//        int[] array = new int[26];
//        for (char ch : p.toCharArray()) {
//            array[ch - 'a']++;
//        }
//        for (char ch : q.toCharArray()) {
//            if (array[ch - 'a'] == 0) return false;
//            array[ch - 'a']--;
//        }
//        for (int ele : array) {
//            if (ele > 0) return false;
//        }
//        return true;
//    }
//
//    private List<Node> constructStringToTree(String input, int start, int end) {
//        List<Node> res = new ArrayList<>();
//        if (start > end) {
//            return res;
//        }
//        if (start == end) {
//            res.add(new Node("" + input.charAt(start)));
//            return res;
//        }
//        String rootValue = input.substring(start, end + 1);
//        for (int i = start; i <= end; i++) {
//            List<Node> left = constructStringToTree(input, start, i - 1);
//            List<Node> right = constructStringToTree(input, i + 1, end);
//            Node root = new Node(rootValue);
//            for (Node l : left) {
//                for (Node r : right) {
//                    root.left = l;
//                    root.right = r;
//                    res.add(root);
//                }
//            }
//        }
//        return res;
//    }




}
