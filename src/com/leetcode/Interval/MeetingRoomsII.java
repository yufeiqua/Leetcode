package com.leetcode.Interval;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MeetingRoomsII {
    class EndPoint implements Comparable<EndPoint> {
        int val;
        boolean isEnd;

        public EndPoint(int val, boolean isEnd) {
            this.val = val;
            this.isEnd = isEnd;
        }

        @Override
        public int compareTo(EndPoint o) {
            if (this.val == o.val) {
                return this.isEnd ? -1 : 1;
            }
            return this.val < o.val ? -1 : 1;
        }
    }

    public int minMeetingRooms(int[][] intervals) {
        List<EndPoint> meetings = new ArrayList<>();
        for (int[] interval : intervals) {
            meetings.add(new EndPoint(interval[0], false));
            meetings.add(new EndPoint(interval[1], true));
        }
        int count = 0;
        int max = 0;
        for (EndPoint endPoint : meetings) {
            if (!endPoint.isEnd) count++;
            else {
                count--;
            }
            max = Math.max(count, max);
        }
        return max;
    }
}
