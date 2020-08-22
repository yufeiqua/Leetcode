package com.leetcode.Google;

import java.util.*;

class GoogleDocsTest {
    public static void main(String[] args) {
        List<int[]> commentRange = Arrays.asList(new int[]{0, 3}, new int[]{2, 4}, new int[]{5, 6});
        List<Character> comments = Arrays.asList('A', 'B', 'C');
        List<String> ret = GoogleDocs.groupComments(commentRange, comments);
        ret.forEach(System.out::println);
        System.out.println("----------------------------");
        commentRange = Arrays.asList(new int[]{0, 5}, new int[]{2, 4}, new int[]{3, 4}, new int[]{5, 6});
        comments = Arrays.asList('A', 'B', 'C', 'D');
        ret = GoogleDocs.groupComments(commentRange, comments);
        ret.forEach(System.out::println);
        System.out.println("----------------------------");
        commentRange = Arrays.asList(new int[]{0, 5}, new int[]{2, 4}, new int[]{4, 5});
        comments = Arrays.asList('A', 'B', 'C');
        ret = GoogleDocs.groupComments(commentRange, comments);
        ret.forEach(System.out::println);
        System.out.println("----------------------------");
        commentRange = Arrays.asList(new int[]{1, 2}, new int[]{2, 3}, new int[]{3, 4});
        comments = Arrays.asList('A', 'B', 'C');
        ret = GoogleDocs.groupComments(commentRange, comments);
        ret.forEach(System.out::println);
    }
}

public class GoogleDocs {
    /**
     * Example 1:
     * Input:
     * (0, 3): A
     * (2, 4): B
     * (5, 6): C
     * <p>
     * Output:
     * (0, 2): [A]
     * (2, 3): [A, B]
     * (3, 4): [B]
     * (5, 6): [C]
     * <p>
     * Example 2:
     * Input:
     * (0, 3): A
     * (0, 3): B
     * (2, 4): C
     * (5, 6): D
     * <p>
     * Output:
     * (0, 2): [A, B]
     * (2, 3): [A, B, C]
     * (3, 4): [C]
     * (5, 6): [D]
     */
    public static List<String> groupComments(List<int[]> commentRange, List<Character> comments) {
        //N * logN + N * logK
        TreeMap<Integer, List<EndPoint>> map = new TreeMap<>();
        for (int i = 0; i < commentRange.size(); i++) {
            int[] range = commentRange.get(i);
            char comment = comments.get(i);
            int start = range[0], end = range[1];
            List<EndPoint> groupedStartEndPoints = map.computeIfAbsent(start, k -> new ArrayList<>());
            List<EndPoint> groupedEndEndPoints = map.computeIfAbsent(end, k -> new ArrayList<>());
            groupedStartEndPoints.add(new EndPoint(start, comment, false));
            groupedEndEndPoints.add(new EndPoint(end, comment, true));
        }

        List<String> ret = new ArrayList<>();
        Set<Character> set = new HashSet<>();
        Integer start = null;
        Integer end = null;
        for (Map.Entry<Integer, List<EndPoint>> groupedEPs : map.entrySet()) { // M * K * LogK
            List<EndPoint> eps = groupedEPs.getValue();
            eps.sort((o1, o2) -> { // K * LogK
                if (!o1.isEnd && !o2.isEnd) return o1.value - o2.value;
                if (o1.isEnd) return -1;
                return 1;
            });
            // K
            for (EndPoint ep : eps) {
                if (!ep.isEnd) {
                    end = ep.value;
                    String commentGroup = buildCommentGroupString(start, end, set);
                    if (commentGroup != null) {
                        ret.add(commentGroup);
                    }
                    set.add(ep.comment);
                    start = ep.value;
                } else {
                    end = ep.value;
                    String commentGroup = buildCommentGroupString(start, end, set);
                    if (commentGroup != null) {
                        ret.add(commentGroup);
                    }
                    set.remove(ep.comment);
                    start = ep.value;
                }
            }
        }
        return ret;
    }

    private static String buildCommentGroupString(Integer start, Integer end, Set<Character> set) {
        if (set.isEmpty() || start.equals(end)) return null;
        StringBuilder group = new StringBuilder();
        group.append("[");
        for (Character character : set) {
            group.append(character).append(",");
        }
        group.setLength(group.length() - 1);
        group.append("]");
        return "(" + start + " ," + end + ")" + ": " + group;
    }

}

class EndPoint {
    int value;
    char comment;
    boolean isEnd;

    public EndPoint(int value, char comment, boolean isEnd) {
        this.value = value;
        this.isEnd = isEnd;
        this.comment = comment;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
