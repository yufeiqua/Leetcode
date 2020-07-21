package com.leetcode;

import com.leetcode.BFS.LC0126_WordLadder2;
import com.leetcode.BFS.LC0127_WordLadder1;
import com.leetcode.DFS.DFS.*;
import com.leetcode.DFS.Prunning_DP.*;
import com.leetcode.DP.*;
import com.leetcode.Google.LC1088_ConfusingNumber;
import com.leetcode.Greedy.Dijkstra.LC0787CheapestFlightsWithinKStops;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        // write your code here
//        Leetcode317 lc317 = new Leetcode317();
//        int[][] temp = new int[][] {{1,0,2,0,1}, {0,0,0,0,0}, {0,0,1,0,0}};
//        System.out.println(lc317.shortestDistance(temp));

        LC0127_WordLadder1 word_ladder = new LC0127_WordLadder1();
        String beginWord = "hit";
        String endWord = "cog";
        List<String> wordList = Arrays.asList("hot", "dot", "dog", "lot", "log");
//        int result = word_ladder.ladderLength(beginWord, endWord, wordList);
//        System.out.println(result);

        LC0126_WordLadder2 word_ladder_2 = new LC0126_WordLadder2();
//        List<List<String>> path = word_ladder_2.findLadders(beginWord, endWord, wordList);
//        for (List<String> re : path) {
//            System.out.println(re.toString());
//        }

        LC0079_WordSearch wordSearch = new LC0079_WordSearch();
        char[][] board = {{'A', 'B', 'C', 'E'}, {'S', 'F', 'C', 'S'}, {'A', 'D', 'E', 'E'}};
        String word = "ABCB";
//        System.out.println(wordSearch.exist(board, word));

        LC0282_ExpressionAddOperators expressionAddOperators = new LC0282_ExpressionAddOperators();
        String num = "3456237490";
        int target = 9191;
//        List<String> res = expressionAddOperators.addOperators(num, target);
//        res.forEach(System.out::println);
        LC0301_RemoveInvalidParentheses removeInvalidParentheses = new LC0301_RemoveInvalidParentheses();
        String s = "()";
//        List<String> res = removeInvalidParentheses.removeInvalidParentheses(s);
//        res.forEach(System.out::println);
        LC0090_Subsets2 subsets2 = new LC0090_Subsets2();
        int[] nums = new int[]{1, 2, 2};
//        List<List<Integer>> res = subsets2.subsetsWithDup(nums);
//        res.forEach(System.out::println);
        LC0093_RestoreIPAddress restoreIPAddress = new LC0093_RestoreIPAddress();
//        List<String> res = restoreIPAddress.restoreIpAddresses("025511135");
//        res.forEach(System.out::println);
        LC0010_RGXMatching rgxMatching = new LC0010_RGXMatching();
        String s1 = "aa";
        String p1 = "a*";
//        System.out.println(rgxMatching.isMatch(s1, p1));
        LC0329_LongestIncreasingPath lc0329_longestIncreasingPath = new LC0329_LongestIncreasingPath();
        int[][] matrix = {{9, 9, 4}, {6, 6, 8}, {2, 1, 1}};
//        int res = lc0329_longestIncreasingPath.longestIncreasingPath(matrix);
//        System.out.println(res);
        LC0694_NumberOfDistinctIslands lc0694_numberOfDistinctIslands = new LC0694_NumberOfDistinctIslands();
        int[][] grid = {{1, 1, 0, 0, 0}, {1, 1, 0, 0, 0}, {0, 0, 0, 1, 1}, {0, 0, 0, 0, 1}};
//        int res = lc0694_numberOfDistinctIslands.numDistinctIslands(grid);
//        System.out.print(res);
        LC0464_CanIWin canIWin = new LC0464_CanIWin();
//        canIWin.canIWin(11, 25);
        LC0294_FlipGame2 lc0294_flipGame2 = new LC0294_FlipGame2();
//        lc0294_flipGame2.canWin("++++");
        LC0403_FogJump fogJump = new LC0403_FogJump();
//        System.out.print(fogJump.canCross(new int[]{0,1,3,6,10,13,15,18}));
        LC0291_WordPattern2 lc0291_wordPattern2 = new LC0291_WordPattern2();
//        String pattern =  "itwasthebestoft";
//        String str = "ittwaastthhebesttooftt";
        String pattern = "itwasthebestoftimes";
        String str = "ittwaastthhebesttoofttimesss";
//        boolean ret = lc0291_wordPattern2.wordPatternMatch(pattern, str);
//        System.out.print(ret);
        LC0132_PalindromePartition palindromePartition = new LC0132_PalindromePartition();
//        String string = "abcbd";
//        System.out.print(palindromePartition.minCut(string));

        LC1088_ConfusingNumber confusingNumber = new LC1088_ConfusingNumber();
//        confusingNumber.confusingNumberII(666);
        LC0072_EditDistance_DFS editDistance_dfs = new LC0072_EditDistance_DFS();
        String w1 = "horse";
        String w2 = "ros";
//        System.out.println(editDistance_dfs.minDistance(w1, w2));
        LC0072_EditDistance_DP editDistance_dp = new LC0072_EditDistance_DP();
//        editDistance_dp.minDistance(w1, w2);
        LC0174_DungeonGame lc0174_dungeonGame = new LC0174_DungeonGame();
        int[][] input = new int[][]{{0, 0, 0}, {1, 1, -1}};
//        System.out.println(lc0174_dungeonGame.calculateMinimumHP(input));
        LC0097_InterleavingString lc0097_interleavingString = new LC0097_InterleavingString();
        String s11 = "db";
        String s2 = "b";
        String s3 = "cbb";
//        System.out.print(lc0097_interleavingString.isInterleave(s11, s2, s3));
        LC0115_DistinctSubsequences lc0115_distinctSubsequences = new LC0115_DistinctSubsequences();
        String s111 = "baagg";
        String t111 = "bag";
//        System.out.println(lc0115_distinctSubsequences.numDistinct(s111, t111));
        LC00256_PaintHouse1 paintHouse1 = new LC00256_PaintHouse1();
        int[][] array = {{6, 10, 16, 25, 7}, {7, 16, 18, 30, 16, 28}};
//        paintHouse1.minCostII(array);
        LC0044 lc0044 = new LC0044();
        String ss = "adceba";
        String tt = "*a";
//        lc0044.isMatch(ss, tt);
        LC0787CheapestFlightsWithinKStops cheapestFlightsWithinKStops = new LC0787CheapestFlightsWithinKStops();
//        4
//                [[0,1,1],[0,2,5],[1,2,1],[2,3,1]]
//        0
//        3
//        1
        int n = 4;
        int[][] edges = new int[][]{{0, 1, 1}, {0, 2, 5}, {1, 2, 1}, {2, 3, 1}};
        int src = 0;
        int dst = 3;
        int k = 1;
//        cheapestFlightsWithinKStops.findCheapestPrice(n, edges, src, dst, k);
//        AllOne allOne = new AllOne();
//        allOne.inc("a");
//        allOne.inc("b");
//        allOne.inc("b");
//        allOne.inc("c");
//        allOne.inc("c");
//        allOne.getMaxKey(); // b or c
//        allOne.getMinKey(); // a
//        allOne.inc("c");
//        allOne.dec("b");
//        allOne.getMaxKey(); // c
//        allOne.getMinKey(); // a or b
//        allOne.dec("b");
//        allOne.getMaxKey(); //c
//        allOne.getMinKey(); // a
//        allOne.dec("a");
//        allOne.getMaxKey(); //c
//        allOne.getMinKey(); //c
//        List<Integer> list = new ArrayList<>();
//        list.add(null);
//        list.add(null);
//        list.add(1);
//        System.out.println(list.size());
        LC0764_LargestPlusSign lc0764_largestPlusSign = new LC0764_LargestPlusSign();
        lc0764_largestPlusSign.orderOfLargestPlusSign(1, new int[][]{{0,0}});


    }
}


