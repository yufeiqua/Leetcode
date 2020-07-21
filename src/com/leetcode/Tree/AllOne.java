package com.leetcode.Tree;

import java.util.*;

public class AllOne {
    private Map<String, Integer> numberContainer;
    //todo: make bucket's value as treeset instead of set
    private Map<Integer, Set<String>> bucket;
    private List<String> maxKeySet;
    private List<String> minKeySet;
    private LinkedList<Integer> max;
    private LinkedList<Integer> min;

    /**
     * Initialize your data structure here.
     */
    public AllOne() {
        this.numberContainer = new HashMap<>();
        this.bucket = new HashMap<>();
        this.maxKeySet = new ArrayList<>();
        this.minKeySet = new ArrayList<>();
        this.max = new LinkedList<>();
        this.min = new LinkedList<>();
    }

    private void setMaxKeySet(Set<String> newList) {
        this.maxKeySet = new ArrayList<>(newList);
    }

    private void setMinKeySet(Set<String> newList) {
        this.minKeySet = new ArrayList<>(newList);
    }

    /**
     * Inserts a new key <Key> with value 1. Or increments an existing key by 1.
     */
    public void inc(String key) {
        if (!numberContainer.containsKey(key)) {
//            if (min.getFirst() == null || min.getFirst() != 1) min.addFirst(1);
//            if (max.getLast() == null || max.getLast() != 1) max.addLast(1);
            numberContainer.put(key, 1);
            bucket.computeIfAbsent(1, k -> new HashSet<>());
            bucket.get(1).add(key);
        } else {
            int count = numberContainer.get(key);
            numberContainer.put(key, count + 1);
            bucket.computeIfAbsent(count, k -> new HashSet<>());
            bucket.get(count).add(key);
            bucket.get(count).remove(key);
            if (bucket.get(count).isEmpty()) {
                bucket.remove(count);
            }
        }
        int currentCount = numberContainer.get(key);
        if (min.getFirst() != null) {
            if (currentCount < min.getFirst()) {
                min.addFirst(currentCount);
            } else if (currentCount > min.getLast()) {
                min.addLast(currentCount);
            }
        } else {
            min.addFirst(1);
        }
        if (max.getFirst() != null) {
            if (currentCount < max.getFirst()) {
                max.addFirst(currentCount);
            } else if (currentCount > max.getFirst()) {
                max.addLast(currentCount);
            }
        } else {
            max.addFirst(1);
        }
    }

    /**
     * Decrements an existing key by 1. If Key's value is 1, remove it from the data structure.
     */
    public void dec(String key) {


    }

    /**
     * Returns one of the keys with maximal value.
     */
    public String getMaxKey() {
        System.out.println(this.maxKeySet.isEmpty() ? "" : maxKeySet.get(0));
        return this.maxKeySet.isEmpty() ? "" : maxKeySet.get(0);
    }

    /**
     * Returns one of the keys with Minimal value.
     */
    public String getMinKey() {
        System.out.println(this.minKeySet.isEmpty() ? "" : minKeySet.get(0));
        return this.minKeySet.isEmpty() ? "" : minKeySet.get(0);
    }
}