package com.leetcode.Tree;

import com.leetcode.TreeNode;

import java.util.Stack;

public class TreeCombination {
    public int countNodes(TreeNode root) {
        if (root == null) return 0;
        int leftHeight = getHeight(root.left);
        int rightHeight = getHeight(root.right);
        int count = 0;
        if (leftHeight == rightHeight + 1) {
            count = (1 << rightHeight) - 1 + countNodes(root.left);
        } else if (leftHeight == rightHeight) {
            count = (1 << leftHeight) - 1 + countNodes(root.right);
        }
        return count;
    }

    private int getHeight(TreeNode root) {
        if (root == null) return 0;
        TreeNode cur = root;
        int height = 1;
        while (cur != null) {
            cur = cur.left;
            height++;
        }
        return height;
    }


    public void flatten(TreeNode root) {
        TreeNode left = root.left;
        root.right = left;
        root.left = null;
    }

    TreeNode prev = null;

    private void helper(TreeNode root) {
        if (root == null) return;
        helper(root.right);
        helper(root.left);
        root.right = prev;
        root.left = null;
        prev = root;
    }

    private void helper2(TreeNode root) {
        if (root == null) return;
        helper2(root.left);
        helper2(root.right);

        TreeNode temp = root.left;
        if (temp == null) return;
        while (temp.right != null) {
            temp = temp.right;
        }
        temp.right = root.right;
        root.right = root.left;
        root.left = null;
    }

    public void helper3(TreeNode root) {
        if (root == null) return;
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        while (cur != null) {
            if (cur.right != null)
                stack.push(cur.right);
            if (cur.left != null) {
                cur.right = cur.left;
                cur.left = null;
            } else {
                if (!stack.isEmpty()) {
                    TreeNode top = stack.pop();
                    cur.right = top;
                }
            }
            cur = cur.right;
        }
    }


    class Node {
        public int val;
        public Node prev;
        public Node next;
        public Node child;

        public Node(int val) {
            this.val = val;
        }
    }

    public Node helper(Node head) {
        if (head == null) return null;
        if (head.next == null && head.child == null) return head; // as tail;
        if (head.child != null) {
            Node tail = helper(head.child);
            Node tempNext = head.next;
            head.next = null;
            tempNext.prev = null;

            head.next = head.child;
            head.child.prev = head;
            head.child = null;

            tail.next = tempNext;
            tempNext.prev = tail;
        }
        return helper(head.next);
    }

    private static Node flattenList(Node current) {

        if (current == null)
            return current;

        Node next = current.next;
        Node last = current;

        //go dfs on child
        if (current.child != null) {

            //attach them
            last.next = current.child;
            current.child.prev = last;

            //dfs
            last = flattenList(current.child);

            //nullify child
            current.child = null;
        }

        //process next now
        if (next != null) {
            last.next = next;
            next.prev = last;

            last = flattenList(next);
        }

        return last;

    }

    public Node flatten(Node head) {
        if (head == null) return null;
        Stack<Node> stack = new Stack<>();
        Node cur = head;
        while (cur != null) {
            if (cur.child != null) {
                if (cur.next != null) {
                    stack.push(cur.next);
                }
                cur.next = cur.child;
                cur.child.prev = cur;
                cur.child = null;
            } else if (cur.next == null && !stack.isEmpty()) {
                Node pop = stack.pop();
                cur.next = pop;
                pop.prev = cur;
            }
            cur = cur.next;
        }
        return head;
    }

    public int maximalSquare(char[][] matrix) {
        int row = matrix.length;
        int col = matrix[0].length;
        int maxLength = 0;
        int[][] dp = new int[row + 1][col + 1];
        for (int i = 1; i <= row; i++) {
            for (int j = 1; j <= col; j++) {
                if (matrix[i - 1][j - 1] == '0') {
                    dp[i][j] = 0;
                } else {
                    dp[i][j] = Math.min(Math.min(dp[i][j - 1], dp[i - 1][j]), dp[i - 1][j - 1]) + 1;
                    maxLength = Math.max(maxLength, dp[i][j]);
                }
            }
        }
        return maxLength * maxLength;

    }


}
