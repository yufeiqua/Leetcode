package com.leetcode.Google.Interval;

import java.util.*;

class Interval {
    public int start;
    public int end;

    public Interval() {
    }

    public Interval(int _start, int _end) {
        start = _start;
        end = _end;
    }
}

class LC0759_EmployeeFreeTimeTest {
    public static void main(String[] args) {
        List<List<Interval>> schedules = Arrays.asList(
                Arrays.asList(new Interval(1, 3), new Interval(5, 8)),
                Collections.singletonList(new Interval(1, 10)));
        List<Interval> ret = LC0759_EmployeeFreeTime.employeeFreeTime(schedules);
        ret.forEach(k -> System.out.println("[" + k.start + " " + k.end + "]"));

    }
}

public class LC0759_EmployeeFreeTime {
    //edge case:
    // [1,10], [2,4], [5,8]
    public static List<Interval> employeeFreeTime(List<List<Interval>> schedules) {
        List<Interval> allSchedules = new ArrayList<>();
        List<Interval> ret = new ArrayList<>();
        schedules.forEach(allSchedules::addAll);
        allSchedules.sort(Comparator.comparingInt(o -> o.start));
        allSchedules.forEach(k -> System.out.println("[" + k.start + " " + k.end + "]"));
        Interval prev = allSchedules.get(0);
        for (Interval schedule : allSchedules) {
            if (prev.end < schedule.start) {
                ret.add(new Interval(prev.end, schedule.start));
                prev = schedule;
            }else{
                prev = prev.end < schedule.end ? schedule : prev;
            }
        }
        return ret;
    }
}
