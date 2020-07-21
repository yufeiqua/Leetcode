package com.leetcode.Tree;

import com.leetcode.TreeNode;

public class LC0549_0298_FollowUp {
    public int longestConsecutive(TreeNode root) {
        if (root == null) return 0;
        dfs(root);
        return count;
    }

    private int count = 1;

    // decrease/increase
    private int[] dfs(TreeNode root) {
        if (root.left == null && root.right == null) return new int[]{1, 1};
        int[] size = new int[2];
        int increase = 0; // child is smaller than root.
        int decrease = 0; // child is larger than root.
        if (root.left != null) {
            int[] left = dfs(root.left);
            if (root.left.val == root.val + 1) { // child is larger than root
                size[0] = left[0];
                decrease += left[0];
            }
            if (root.left.val == root.val - 1) { //child is smaller than root.
                size[1] = left[1];
                increase += left[1];
            }
        }

        if (root.right != null) {
            int[] right = dfs(root.right);
            if (root.right.val == root.val + 1) { // child is larger than root
                size[0] = Math.max(size[0], right[0]);
                increase += right[0];
            }
            if (root.right.val == root.val - 1) { //child is smaller than root.
                size[1] = Math.max(size[1], right[1]);
                decrease += right[1];
            }
        }
        count = Math.max(count, Math.max(decrease, increase) + 1);
        size[0] += 1;
        size[1] += 1;
        return size;
    }

}
