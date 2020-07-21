package com.leetcode.DP;

import java.util.Arrays;

/**
 * 思路: 在height相同的情况下，更新左右可以到达的最长边界
 * 001100
 * 011110
 * 111111
 *
 * 当更新到第二个row的时候，到第二个1开始就要跟着上一个row的历史值来更新边界。所以第一个1和第二1对于上个一个row来说是独立的。可以用来
 * 更新当height= 1时的面积
 * 当更新到第三个row的时候，第一个1和最后一个1 是相对独立的，更新height= 1的面积，第二个1倒数第二个1跟row2 更新height= 2 的面积。3，4
 * 更新height=3的面积
 * 所以left[j] = Math.max(left[j], curLeft);
 * right[j] = Math.min(right[j], curRight);
 * curLeft 代表当前层的左边界
 * curRight代表当前层的右边界
 * 只有encounter 0 时，curLeft and curRight 才会更新.
 */
public class LC0085_MaximalRectangle {
    public int maximalRectangle(char[][] matrix) {
        int row = matrix.length;
        int col = matrix[0].length;
        int[] left = new int[col];
        int[] right = new int[col];
        Arrays.fill(right, col);
        int[] height = new int[col];
        int maxArea = 0;
        for (int i = 0; i < row; i++) {
            int curLeft = 0;
            int curRight = col;
            for (int j = 0; j < col; j++) {
                if (matrix[i][j] == '1') {
                    left[j] = Math.max(left[j], curLeft);
                } else {
                    curLeft = j + 1;
                    left[j] = 0;
                }
            }

            for (int j = col - 1; j >= 0; j--) {
                if (matrix[i][j] == '1') {
                    right[j] = Math.min(right[j], curRight);
                } else {
                    right[j] = col;
                    curRight = j;
                }
            }

            for (int j = 0; j < col; j++) {
                if (matrix[i][j] == '1') height[j]++;
                else height[j] = 0;
            }

            for (int j = 0; j < col; j++) {
                maxArea = Math.max(maxArea, (right[j] - left[j]) * height[j]);
            }


        }
        return maxArea;
    }
}
