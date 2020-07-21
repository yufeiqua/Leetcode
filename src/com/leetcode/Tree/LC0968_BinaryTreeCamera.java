package com.leetcode.Tree;

import com.leetcode.TreeNode;

import java.util.HashMap;
import java.util.Map;

public class LC0968_BinaryTreeCamera {
    enum STATE {
        CAMERA, MONITORED, INITIAL
    }

    class Status {
        STATE state;
        int camera;

        public Status(int camera, STATE state) {
            this.state = state;
            this.camera = camera;
        }
    }

    public int minCameraCover(TreeNode root) {
        Status status = dfs(root);
        if(status.state == STATE.INITIAL) {
            return status.camera++;
        }
        return status.camera;
    }

    private Status dfs(TreeNode root) {
        if (root == null) {
            return new Status(0, STATE.MONITORED);
        }
        Status left = dfs(root.left);
        Status right = dfs(root.right);
        Status curr = new Status(left.camera + right.camera, STATE.INITIAL);
        if (left.state == STATE.INITIAL || right.state == STATE.INITIAL) {
            curr = new Status(left.camera + right.camera + 1, STATE.CAMERA);
        }
        if (left.state == STATE.CAMERA || right.state == STATE.CAMERA) {
            curr = new Status(left.camera + right.camera + 1, STATE.MONITORED);
        }
        return curr;
    }
}
