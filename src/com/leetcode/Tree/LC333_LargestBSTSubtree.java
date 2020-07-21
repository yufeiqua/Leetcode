package com.leetcode.Tree;

import com.leetcode.TreeNode;

public class LC333_LargestBSTSubtree {
    class Result {
        private int leftMin;
        private int rightMax;
        private int size;

        public Result() {
        }

        public Result(int leftMin, int rightMax, int size) {
            this.leftMin = leftMin;
            this.rightMax = rightMax;
            this.size = size;
        }

        public void setLeftMin(int val) {
            this.leftMin = val;
        }

        public void setRightMax(int val) {
            this.rightMax = val;
        }

        public void setSize(int val) {
            this.size = val;
        }

    }

    public int largestBSTSubtree(TreeNode root) {
        if (root == null) return 0;
        dfs(root);
        return maxNodeSize;
    }

    private int maxNodeSize = 1;

    private Result dfs(TreeNode root) {
        if (root.left == null && root.right == null) {
            return new Result(root.val, root.val, 1);
        }
        Result left = new Result(0, 0, 0), right = new Result(0, 0, 0);
        if (root.left != null) {
            left = dfs(root.left);
        }
        if (root.right != null) {
            right = dfs(root.right);
        }
        if (left == null || right == null) {
            return null;
        }
        boolean isBST = true;
        if (left.size != 0) {
            isBST = left.rightMax < root.val;
        }
        if (right.size != 0) {
            isBST = isBST && right.leftMin > root.val;
        }

        if (isBST) {
            maxNodeSize = Math.max(maxNodeSize, left.size + right.size + 1);
            return new Result(
                    left.size == 0 ? root.val : left.leftMin,
                    right.size == 0 ? root.val : right.rightMax,
                    left.size + right.size + 1
            );
        }
        return null;
    }

}
