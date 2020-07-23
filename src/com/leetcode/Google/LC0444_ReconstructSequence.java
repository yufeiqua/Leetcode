package com.leetcode.Google;

import java.util.*;

class testLC0444 {
    public static void main(String[] args) {
        LC0444_ReconstructSequence lc0444_reconstructSequence = new LC0444_ReconstructSequence();
        int[] org = new int[]{4, 1, 5, 2, 6, 3};
        List<List<Integer>> seqs = new ArrayList<>();
        seqs.add(Arrays.asList(5, 2, 6, 3));
        seqs.add(Arrays.asList(4, 1, 5, 2));
        boolean ret1 = lc0444_reconstructSequence.sequenceReconstruction(org, seqs);
        System.out.println(ret1);

        org = new int[]{1, 2, 3};
        seqs = new ArrayList<>();
        seqs.add(Arrays.asList(1, 2));
        seqs.add(Arrays.asList(1, 3));
        seqs.add(Arrays.asList(2, 3));
        boolean ret2 = lc0444_reconstructSequence.sequenceReconstruction(org, seqs);
        System.out.println(ret2);

        org = new int[]{1, 2, 3, 4};
        seqs = new ArrayList<>();
        seqs.add(Arrays.asList(1, 2));
        seqs.add(Arrays.asList(1, 3));
        seqs.add(Arrays.asList(2, 3));
        boolean ret3 = lc0444_reconstructSequence.sequenceReconstruction(org, seqs);
        System.out.println(ret3);

        org = new int[]{1,2,3,4};
        seqs = new ArrayList<>();
        seqs.add(Arrays.asList(1,2));
        seqs.add(Arrays.asList(2, 3));
        seqs.add(Arrays.asList(3, 4));
        seqs.add(Arrays.asList(4, 2));
        boolean ret4 = lc0444_reconstructSequence.sequenceReconstruction(org, seqs);
        System.out.println(ret4);
    }
}

public class LC0444_ReconstructSequence {
    public boolean sequenceReconstruction(int[] org, List<List<Integer>> seqs) {

        Map<Integer, List<Integer>> graph = new HashMap<>();
        Map<Integer, Integer> inDegree = new HashMap<>();
        for (List<Integer> seq : seqs) {
            for (int i = 0; i < seq.size(); i++) {
                graph.computeIfAbsent(seq.get(i), k -> new ArrayList<>());
                inDegree.putIfAbsent(seq.get(i), 0);
                if (i + 1 < seq.size()) {
                    graph.get(seq.get(i)).add(seq.get(i + 1));
                    int in = inDegree.getOrDefault(seq.get(i + 1), 0);
                    inDegree.put(seq.get(i + 1), in + 1);
                }
            }
        }
        Queue<Integer> queue = new LinkedList<>();
        int count = 0;
        for (Map.Entry<Integer, Integer> entry : inDegree.entrySet()) {
            if (entry.getValue() == 0) {
                queue.add(entry.getKey());
                count++;
            }
            if (count == 2) return false;
        }

        List<Integer> reconstructSequence = new ArrayList<>();
        while (!queue.isEmpty()) {
            int size = queue.size();
            count = 0;
            while (size-- > 0) {
                int curNode = queue.poll();
                reconstructSequence.add(curNode);
                List<Integer> children = graph.get(curNode);
                for (int child : children) {
                    int in = inDegree.get(child);
                    inDegree.put(child, in - 1);
                    if (inDegree.get(child) == 0) {
                        count++;
                        queue.offer(child);
                    }
                    if (count == 2) return false;
                }
            }
        }
        for(int in : inDegree.values()) {
            if(in != 0) return false;
        }
        reconstructSequence.forEach(s-> System.out.println(s));
        if (reconstructSequence.size() != org.length) return false;
        for (int i = 0; i < org.length; i++) {
            if (reconstructSequence.get(i) != org[i]) return false;
        }
        return true;
    }

}
