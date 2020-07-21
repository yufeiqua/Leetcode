package com.leetcode.Graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LC0267_PalindromePermutationII {
    public List<String> generatePalindromes(String s) {
        if (s == null || s.length() == 0) return new ArrayList<>();
        Map<Character, Integer> map = new HashMap<>();
        countCharacterFrequency(s, map);
        String midChar = "";
        List<Character> container = new ArrayList<>();
        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            if (entry.getValue() % 2 != 0) {
                if (midChar.equals("")) {
                    midChar = String.valueOf(entry.getKey());
                } else {
                    return new ArrayList<>();
                }
            }
        }
        map.forEach((k, v) -> {
            for (int i = 0; i < v / 2; i++) {
                container.add(k);
            }
        });

        List<String> ret = new ArrayList<>();
        generatePalindromes(container, midChar, new StringBuilder(), ret, new boolean[container.size()]);
        return ret;
    }

    private void generatePalindromes(List<Character> container, String mid, StringBuilder sb, List<String> ret, boolean[] used) {
        if (sb.length() == container.size()) {
            ret.add(sb.toString() + mid + sb.reverse().toString());
            sb.reverse();
            return;
        }

        for (int i = 0; i < container.size(); i++) {
            if (i > 0 && container.get(i) == container.get(i - 1) && !used[i - 1]) continue;
            if (!used[i]) {
                used[i] = true;
                sb.append(container.get(i));
                generatePalindromes(container, mid,  sb, ret, used);
                used[i] = false;
                sb.deleteCharAt(sb.length() - 1);
            }
        }
    }

    private void countCharacterFrequency(String s, Map<Character, Integer> map) {
        for (char ch : s.toCharArray()) {
            map.computeIfAbsent(ch, k -> 0);
            int count = map.get(ch);
            map.put(ch, count + 1);
        }
    }

}
