package com.leetcode.Google;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

class testLC0767 {
    public static void main(String[] args) {
        LC0767_ReorganizeString reorganizeString = new LC0767_ReorganizeString();
        String ret = reorganizeString.reorganizeString("aaaabbcc");
        System.out.println(ret);
    }
}

public class LC0767_ReorganizeString {
    public String reorganizeString(String S) {
        int totalChars = S.length();
        int[] map = new int[26];
        for (char ch : S.toCharArray()) {
            map[ch - 'a']++;
        }
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o2[1] - o1[1]);
        for (int i = 0; i < 26; i++) {
            if (map[i] != 0) pq.offer(new int[]{i, map[i]});
        }
        StringBuilder sb = new StringBuilder();
        while (!pq.isEmpty()) {
            int size = pq.size();
            if (size == 1) {
                int[] lastOne = pq.poll();
                if (lastOne[1] > 1) return "";
                sb.append((char) (lastOne[0] + 'a'));
                break;
            }
            List<int[]> levelStore = new ArrayList<>();
            int[] first = pq.peek();
            if (totalChars - first[1] < first[1]) size = 2;
            for (int i = 0; i < size; i++) {
                int[] cur = pq.poll();
                sb.append((char) (cur[0] + 'a'));
                cur[1]--;
                totalChars--;
                if (cur[1] > 0) levelStore.add(cur);
            }
            pq.addAll(levelStore);
        }
        return sb.toString();
    }
}
