package com.leetcode.Google;

import com.leetcode.TreeNode;

public class LC1120_MaxAverageSubtree {
    class Node {
        double count;
        double sum;

        public Node(double count, double sum) {
            this.count = count;
            this.sum = sum;
        }
    }

    private double max = 0.0;

    public double maximumAverageSubtree(TreeNode root) {
        if (root == null) return 0.0;
        dfs(root);
        return max;
    }

    private Node dfs(TreeNode root) {
        if (root == null) return new Node(0.0, 0.0);
        Node left = dfs(root.left);
        Node right = dfs(root.right);
        double totalCount = left.count + right.count + 1;
        double totalSum = left.sum + right.sum + root.val;
        double value = totalSum / totalCount;
        max = Math.max(value, max);
        return new Node(totalCount, totalSum);
    }
}
