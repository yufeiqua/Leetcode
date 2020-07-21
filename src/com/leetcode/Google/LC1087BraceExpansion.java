package com.leetcode.Google;

import java.util.*;

class TestLC1087 {
    public static void main(String[] args) {
        LC1087BraceExpansion lc1087BraceExpansion = new LC1087BraceExpansion();
        String s = "abcd";
        String[] res = lc1087BraceExpansion.expand(s);
        for (String st : res) System.out.println(st);
    }
}

public class LC1087BraceExpansion {
    public String[] expand(String S) {
        List<List<Character>> input = preprocess(S);
        List<String> allOptions = new ArrayList<>();
        generateAllOptionsString(input, new StringBuilder(), 0, allOptions);
        Collections.sort(allOptions);
        return allOptions.toArray(new String[0]);
    }

    private List<List<Character>> preprocess(String s) {
        List<List<Character>> list = new ArrayList<>();
        int index = 0;
        boolean isStart = false;
        List<Character> temp = new ArrayList<>();
        while (index < s.length()) {
            if (s.charAt(index) == ',') {
                index++;
                continue;
            }
            if (s.charAt(index) != '{' && s.charAt(index) != '}' && !isStart) {
                list.add(Collections.singletonList(s.charAt(index)));
            } else if (s.charAt(index) == '{') {
                isStart = true;
            } else if (s.charAt(index) != '{' && s.charAt(index) != '}' && isStart) {
                temp.add(s.charAt(index));
            } else if (s.charAt(index) == '}') {
                list.add(temp);
                temp = new ArrayList<>();
                isStart = false;
            }
            index++;
        }
        return list;
    }

    private void generateAllOptionsString(List<List<Character>> input, StringBuilder stringBuilder, int index, List<String> result) {
        if (index == input.size()) {
            result.add(stringBuilder.toString());
            return;
        }
        List<Character> optionsGroup = input.get(index);
        int length = stringBuilder.length();
        for (Character character : optionsGroup) {
            stringBuilder.append(character);
            generateAllOptionsString(input, stringBuilder, index + 1, result);
            stringBuilder.setLength(length);
        }
    }
}
