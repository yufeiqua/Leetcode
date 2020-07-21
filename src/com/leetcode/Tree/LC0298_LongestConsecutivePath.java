package com.leetcode.Tree;

import com.leetcode.TreeNode;

import java.util.HashMap;
import java.util.Map;

public class LC0298_LongestConsecutivePath {
    public int longestConsecutive(TreeNode root) {
        if (root == null) return 0;
        dfs(root);
        return count;
    }

    private int count = 1;

    private int dfs(TreeNode root) {
        if (root.left == null && root.right == null) return 1;
        int size = 0;
        if (root.left != null) {
            int left = dfs(root.left);
            if (root.left.val == root.val + 1) {
                size = left;
            }
        }

        if (root.right != null) {
            int right = dfs(root.right);
            if (root.right.val == root.val + 1) {
                size = Math.max(size, right);
            }
        }
        count = Math.max(count, size + 1);
        return size + 1;
    }

    /**
     * Follow up :
     * 1. 如果path可以是从左子树到右子树或者从右子树到左子树，最长的path? LC549
     * 2. output any longest path
     * 3. output all longest path
     */
    // output any longest path:
    Map<TreeNode, TreeNode> graph = new HashMap<>();
    private TreeNode startNode = null;

    private int dfs1(TreeNode root) {
        if (root.left == null && root.right == null) {
            graph.computeIfAbsent(root, k -> null);
            return 1;
        }
        int size = 0;
        graph.put(root, null);
        if (root.left != null) {
            int left = dfs(root.left);
            if (root.left.val == root.val + 1) {
                graph.put(root, root.left);
                size = left;
            }
        }

        if (root.right != null) {
            int right = dfs(root.right);
            if (root.right.val == root.val + 1) {
                if (right > size) {
                    graph.put(root, root.right);
                    size = right;
                }
            }
        }
        if (count < size + 1) {
            startNode = root;
            count = size + 1;
        }
        return size + 1;
    }

}
