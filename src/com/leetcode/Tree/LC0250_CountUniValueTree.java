package com.leetcode.Tree;

import com.leetcode.TreeNode;

public class LC0250_CountUniValueTree {
    public int countUnivalSubtrees(TreeNode root) {
        if (root == null) {
            return 0;
        }
        dfs(root);
        return count;
    }

    private int count = 0;

    private boolean dfs(TreeNode root) {
        if (root == null) return true;
        boolean left = dfs(root.left);
        boolean right = dfs(root.right);
        if (!left || !right) return false;

        boolean isUniValSubtree = root.left == null || root.val == root.left.val;
        if (!isUniValSubtree) return false;
        isUniValSubtree = root.right == null || root.val == root.right.val;
        if (!isUniValSubtree) return false;
        count++;
        return true;
    }

}
