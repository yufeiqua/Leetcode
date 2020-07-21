package com.leetcode.Tree;

import com.leetcode.TreeNode;

import java.util.*;

public class LC0241_DifferentWays2AddParentheses {
    final class SyntaxTreeNode {
        private final Character operator;
        private final Integer value;

        public SyntaxTreeNode left;
        public SyntaxTreeNode right;

        public SyntaxTreeNode(Character operator) {
            this.operator = operator;
            this.value = null;
        }

        public SyntaxTreeNode(Integer value) {
            this.value = value;
            this.operator = null;
        }

        public boolean isOperator() {
            return operator != null;
        }

        public boolean isNumber() {
            return value != null;
        }

        public Integer getValue() {
            return this.value;
        }

        public Character getOperator() {
            return this.operator;
        }

    }

    private Set<Character> operatorSet = new HashSet<>(Arrays.asList('+', '-', '*'));

    public List<Integer> diffWaysToCompute(String input) {
        return dfs(input, 0, input.length() - 1);
    }

    private List<Integer> dfs(String input, int start, int end) {
        if(start > end) return new ArrayList<>();
        String str = input.substring(start, end - start + 1);
        List<Integer> res = new ArrayList<>();
        if (isNumber(str)) {
            res.add(evaluateString(str));
            return res;
        }
        for (int curNode = start; curNode <= end; curNode++) {
            char potentialOperator = input.charAt(curNode);
            if (operatorSet.contains(potentialOperator)) {
                List<Integer> left = dfs(input, start, curNode - 1);
                List<Integer> right = dfs(input, curNode + 1, end);
                for (int leftElement : left) {
                    for (int rightElement : right) {
                        int number = evaluateOperator(leftElement, rightElement, potentialOperator);
                        res.add(number);
                    }
                }
            }
        }
        return res;

    }

    private boolean isNumber(String s) {
        for (char ch : s.toCharArray()) {
            if (operatorSet.contains(ch)) return false;
        }
        return true;
    }

    private int evaluateString(String s) {
        int number = 0;
        int index = 0;
        while (index < s.length()) {
            number = number * 10 + (s.charAt(index) - '0');
            index++;
        }
        return number;
    }

    private int evaluateOperator(int a, int b, char operator) {
        switch (operator) {
            case '+':
                return a + b;
            case '-':
                return a - b;
            case '*':
                return a * b;
        }
        throw new RuntimeException("could not find matched operator");
    }


}
