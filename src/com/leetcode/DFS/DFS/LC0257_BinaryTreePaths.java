package com.leetcode.DFS.DFS;

import com.leetcode.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class LC0257_BinaryTreePaths {
    public List<String> binaryTreePaths(TreeNode root) {
        return helper(root);
    }

    private List<String> helper(TreeNode root) {
        List<String> ret = new LinkedList<>();
        if (root.left == null && root.right == null) {
            ret.add("" + root.val);
            return ret;
        }
        if (root.left != null) {
            List<String> left = helper(root.left);
            combineToGenerateFullPath(left, root.val);
            ret.addAll(left);
        }
        if (root.right != null) {
            List<String> right = helper(root.right);
            combineToGenerateFullPath(right, root.val);
            ret.addAll(right);
        }
        return ret;
    }

    private void combineToGenerateFullPath(List<String> path, int currentNode) {
        for (int i = 0; i < path.size(); i++) {
            path.set(i, currentNode + "->" + path.get(i));
        }
    }

}
