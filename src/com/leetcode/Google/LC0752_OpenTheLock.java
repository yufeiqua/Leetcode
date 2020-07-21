package com.leetcode.Google;

import java.util.*;

class testLC0752 {
    public static void main(String[] args) {
        LC0752_OpenTheLock openTheLock = new LC0752_OpenTheLock();
        String[] dead = new String[]{"0000"};
        String target = "8888";
        int ret = openTheLock.openLock(dead, target);
        System.out.println(ret);
    }

}

public class LC0752_OpenTheLock {
    private final static int NUMBER_OF_SLOTS = 10;
    private final static int BACK = -1;
    private final static int FORWARD = 1;
    private final static String INITIAL = "0000";
    private final static int[] STEP = new int[]{BACK, FORWARD};

    public int openLock(String[] deadends, String target) {
        Queue<String> passwordOptions = new LinkedList<>();
        passwordOptions.add(INITIAL);
        Set<String> deadendsSet = new HashSet<>(Arrays.asList(deadends));
        if (deadendsSet.contains(target) || deadendsSet.contains(INITIAL)) return -1;
        if (target.equals(INITIAL)) return 0;
        return findMinStepToOpenLock(passwordOptions, deadendsSet, target);
    }

    private int findMinStepToOpenLock(Queue<String> passwordOptions, Set<String> deadends, String target) {
        int step = 0;
        List<String> allPossibleOptionsInNextStep;
        Set<String> visited = new HashSet<>();
        visited.add(INITIAL);
        while (!passwordOptions.isEmpty()) {
            int size = passwordOptions.size();
            while (size-- > 0) {
                String curPassword = passwordOptions.poll();
                char[] curPasswordArray = curPassword.toCharArray();
                allPossibleOptionsInNextStep = generateAllPossibleOptions(curPasswordArray, deadends, visited, target);
                for (String possibleOptions : allPossibleOptionsInNextStep) {
                    if (possibleOptions.equals(target)) return step + 1;
                }
                passwordOptions.addAll(allPossibleOptionsInNextStep);
            }
            step++;
        }
        return -1;
    }

    private List<String> generateAllPossibleOptions(char[] curPasswordArray, Set<String> deadends, Set<String> visited, String target) {
        Set<String> allPossibleOptionsInNextStep = new HashSet<>();
        for (int i = 0; i < 4; i++) {
            char cur = curPasswordArray[i];
            int curDigit = cur - '0';
            for (int step : STEP) {
                int nextDigit;
                if (curDigit == 0 && step == -1) {
                    nextDigit = 9;
                } else {
                    nextDigit = (curDigit + step) % NUMBER_OF_SLOTS;
                }
                char nextDigitChar = (char) (nextDigit + '0');
                curPasswordArray[i] = nextDigitChar;
                String nextOption = new String(curPasswordArray);
                if (!deadends.contains(nextOption) && visited.add(nextOption)) {
                    allPossibleOptionsInNextStep.add(nextOption);
                    if (nextOption.equals(target)) {
                        return new ArrayList<>(allPossibleOptionsInNextStep);
                    }
                }
            }
            //reset;
            curPasswordArray[i] = cur;
        }
        return new ArrayList<>(allPossibleOptionsInNextStep);
    }
}
