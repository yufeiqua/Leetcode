package com.leetcode.Graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LC0210_CourseScheduleII {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        Map<Integer, List<Integer>> courseDependency = new HashMap<>();
        Map<Integer, State> stateMap = new HashMap<>();
        buildGraph(courseDependency, stateMap, numCourses, prerequisites);
        int[] res = new int[numCourses];
        int[] index = new int[1];
        for (int i = 0; i < numCourses; i++) {
            if (stateMap.get(i).equals(State.INITIAL)) {
                if (checkCycleAndSortCourse(courseDependency, stateMap, i, res, index)) return new int[0];
            }
        }
        return res;
    }

    private boolean checkCycleAndSortCourse(Map<Integer, List<Integer>> courseDependency, Map<Integer, State> stateMap, int curCourse, int[] courseOrder, int[] index) {
        if (stateMap.get(curCourse).equals(State.DONE)) return false;
        if (stateMap.get(curCourse).equals(State.VISITING)) return true;
        stateMap.put(curCourse, State.VISITING);
        if (courseDependency.get(curCourse) != null) {
            for (int nextCourse : courseDependency.get(curCourse)) {

                if (checkCycleAndSortCourse(courseDependency, stateMap, nextCourse, courseOrder, index)) {
                    return true;
                }
            }
        }
        stateMap.put(curCourse, State.DONE);
        courseOrder[index[0]] = curCourse;
        index[0] += 1;
        return false;
    }

    private void buildGraph(Map<Integer, List<Integer>> courseDependency, Map<Integer, State> stateMap, int numCourses, int[][] prerequisites) {
        for (int[] row : prerequisites) {
            int course1 = row[0];
            int course2 = row[1];
            courseDependency.computeIfAbsent(course1, k -> new ArrayList<>()).add(course2);
        }
        for (int i = 0; i < numCourses; i++) {
            courseDependency.computeIfAbsent(i, k -> new ArrayList<>());
            stateMap.put(i, State.INITIAL);
        }
    }
}
