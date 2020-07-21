package com.leetcode;

import java.util.*;

class TestGeneral {
    public static void main(String[] args) {
//        General general = new General();
//        general.threeSum(new int[]{0, 0, 0});
//        System.out.println(general.mySqrt(80));
//        (((cur - '0') + j) % NUMBER_OF_SLOTS + '0');
        char cur = '9';
        int digit = cur - '0';
        int j = 1;
        int nextDigit = (digit + j) % 10;
        char nextDigitChar = (char) (nextDigit + '0');
        Map<Integer, String> map = new HashMap<>();
//        map.put(1, "1");
        map.remove(1);
//        System.out.print(map.remove(1));
//        System.out.print(nextDigitChar);
    }
}

public class General {
    public String LC_0069_OutputStringWith2Decimal(int n) {
        return mySqrt(n);
    }

    public String mySqrt(int x) {
        if (x == 0 || x == 1) return String.valueOf(x);
        int start = 0;
        int end = x / 2;
        int mid = 1;
        while (start <= end) {
            mid = start + (end - start) / 2;
            long value1 = (long) mid * mid;
            long value2 = (long) (mid + 1) * (mid + 1);

            if (value1 <= x && value2 > x) {
                break;
            }
            if (value1 < x) {
                start = mid + 1;
            } else if (value1 > x) {
                end = mid - 1;
            }
        }
        if (mid * mid == x) return String.valueOf(mid);
        double start1 = 0.00;
        double end1 = 0.99;
        double mid1;
        double val = 0.00;
        while (start1 <= end1) {
            mid1 = start1 + (end1 - start1) / 2;
            val = mid + mid1;
            if (val * val <= x && (val + 0.01) * (val + 0.01) > x) break;
            if (val * val < x) {
                start1 = mid1 + 0.01;
            } else {
                end1 = mid1 - 0.01;
            }
        }
        String res = String.valueOf(val);
        int index = res.indexOf('.');
        return res.substring(0, index + 3);
    }
    // 3 3 3 3  0, 1,2 1,3   1, 2,3
    // 0 1 2 3

    public List<List<Integer>> threeSum(int[] nums) {
        Set<Integer> duplicate = new HashSet<>();
        Map<Integer, Integer> matched = new HashMap<>();
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> path;
        for (int i = 0; i + 3 < nums.length; i++) {
            int curr = nums[i];
            int target = -curr;
            if (duplicate.add(curr)) {
                for (int j = i + 1; j < nums.length; j++) {
                    int match = target - nums[j];
                    if (matched.containsKey(match) && matched.get(match) == null) {
                        matched.put(match, nums[j]);
                        matched.put(nums[j], match);
                        path = new ArrayList<>();
                        path.add(curr);
                        path.add(match);
                        path.add(nums[j]);
                        res.add(path);
                    } else if (!matched.containsKey(match)) {
                        matched.put(nums[j], null);
                    }
                }
            }
        }
        return res;
    }

    private int row;
    private int col;

    public int kthSmallest(int[][] matrix, int k) {
        row = matrix.length;
        col = matrix[0].length;
        return findKth(matrix, 0, (row - 1) * col + col - 1, k);
    }

    private int findKth(int[][] matrix, int start, int end, int k) {
        if (start == end) return matrix[end / col][end % col];
        int pivot = partition(matrix, start, end);
        int rank = pivot - start + 1;
        if (rank == k) return matrix[pivot / col][pivot % col];
        if (rank < k) {
            return findKth(matrix, pivot + 1, end, k - rank);
        } else {
            return findKth(matrix, start, pivot - 1, k);
        }
    }

    private int partition(int[][] matrix, int start, int end) {
        int pivot = matrix[end / col][end % col];
        int startTemp = start;
        int endTemp = end;
        while (startTemp < endTemp) {
            while (startTemp < endTemp && matrix[startTemp / col][startTemp % col] <= pivot) startTemp++;
            while (startTemp < endTemp && matrix[endTemp / col][endTemp % col] >= pivot) endTemp--;
            swap(matrix, startTemp++, endTemp--);
        }
        swap(matrix, startTemp, end);
        return startTemp;
    }

    private void swap(int[][] matrix, int start, int end) {
        int temp = matrix[start / col][start % col];
        matrix[start / col][start % col] = matrix[end / col][end % col];
        matrix[end / col][end % col] = temp;
    }

    public boolean backspaceCompare(String S, String T) {
        return evaluateString(S).equals(evaluateString(T));

    }

    private String evaluateString(String s) {
        Stack<Character> stack = new Stack<>();
        StringBuilder sb = new StringBuilder();
        for (char ch : s.toCharArray()) {
            if (ch == '#' && !stack.isEmpty()) {
                stack.pop();
            } else if (ch != '#') {
                stack.push(ch);
            }
        }
        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }
        return sb.toString();
    }

    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> count = new HashMap<>();
        for (int num : nums) {
            int c = count.getOrDefault(num, 0);
            count.put(num, c + 1);
        }
        int[] res = new int[Math.min(k, count.size())];
        PriorityQueue<Map.Entry<Integer, Integer>> pq = new PriorityQueue<>((o1, o2) -> o1.getValue() - o2.getValue());
        for (Map.Entry<Integer, Integer> entry : count.entrySet()) {
            if (pq.size() < k) {
                pq.offer(entry);
            } else {
                if (entry.getValue() > pq.peek().getValue()) {
                    pq.poll();
                    pq.offer(entry);
                }
            }
        }
        int index = 0;
        while (!pq.isEmpty()) {
            res[index++] = pq.poll().getKey();
        }
        return res;
    }

    private final Set<Integer> toBeDeletedNode = new HashSet<>();

    public List<TreeNode> delNodes(TreeNode root, int[] to_delete) {
        for (int ele : to_delete) {
            toBeDeletedNode.add(ele);
        }
        helper(root, toBeDeletedNode);
        if (toBeDeletedNode.contains(root.val)) return node;
        node.add(root);
        return node;
    }

    private final List<TreeNode> node = new ArrayList<>();

    private TreeNode helper(TreeNode root, Set<Integer> toBeDeletedNode) {
        if (root == null) return null;
        TreeNode left = helper(root.left, toBeDeletedNode);
        TreeNode right = helper(root.right, toBeDeletedNode);

        if (toBeDeletedNode.contains(root.val)) {
            if (left != null) node.add(left);
            if (right != null) node.add(right);
            return null;
        }
        root.left = left;
        root.right = right;
        return root;
    }

    public TreeNode removeLeafNodes(TreeNode root, int target) {
        return findAndRemoveTarget(root, target);
    }

    private TreeNode findAndRemoveTarget(TreeNode root, int target) {
        if (root == null) return null;
        TreeNode left = findAndRemoveTarget(root.left, target);
        TreeNode right = findAndRemoveTarget(root.right, target);
        root.left = left;
        root.right = right;
        if (root.val == target && root.left == null && root.right == null) return null;
        return root;
    }

    public int countBattleships(char[][] board) {
        int row = board.length;
        int col = board[0].length;
        int count = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (board[i][j] == 'X') {
                    if (i == 0 && j == 0) count++;
                    else if (i == 0) {
                        if (board[i][j - 1] != 'X') count++;
                    } else if (j == 0) {
                        if (board[i - 1][j] != 'X') count++;
                    } else {
                        if (board[i - 1][j] != 'X' && board[i][j - 1] != 'X') count++;
                    }
                }
            }
        }
        return count;
    }


    public int twoCitySchedCost(int[][] costs) {
        int people = costs.length / 2;
        int count = 0;
        Arrays.sort(costs, Comparator.comparingInt(cost -> (cost[0] - cost[1])));
        for (int i = 0; i < people; i++) {
            count += costs[i][0];
        }
        for (int i = people; i < costs.length; i++) {
            count += costs[i][1];
        }
        return count;
    }



}