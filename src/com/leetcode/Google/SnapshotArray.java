package com.leetcode.Google;

import java.util.Arrays;
import java.util.TreeMap;

class TestSnapShot {
    // only store the change.
    public static void main(String[] args) {
        SnapshotArray snapshotArray = new SnapshotArray(4);
        snapshotArray.snap(); // 0 =>  0 0 0 0
        snapshotArray.set(0, 15); // 15 0 0 0
        snapshotArray.set(3, 0); // 15 0 0 0
        snapshotArray.set(3, 18); // 15 0 0 18
        snapshotArray.snap(); //1 => // 15 0 0 18
        snapshotArray.set(0, 2);// 2 0 0 18
        snapshotArray.set(1, 1);  // 2 1 0 18
        snapshotArray.snap(); //2 => // 2 1 0 18
        snapshotArray.get(1, 2); // 1
        snapshotArray.snap(); //3 => // 2 1 0 18
        snapshotArray.snap(); //4 => // 2 1 0 18
        snapshotArray.get(3, 2); // 0
        snapshotArray.snap(); //5 => // 2 1 0 18
        snapshotArray.get(0, 3); //2

    }
}

class SnapshotArray {
    public TreeMap<Integer, Integer>[] snapShots;
    private int snapCount;

    public SnapshotArray(int length) {
        this.snapShots = new TreeMap[length];
        for (int i = 0; i < length; i++) {
            this.snapShots[i] = new TreeMap<>();
            this.snapShots[i].put(0, 0);
        }
        Arrays.stream(snapShots).forEach(k -> System.out.print(k.toString()));
        this.snapCount = 0;
    }

    public void set(int index, int val) {
        int newVersion = this.snapCount;
        snapShots[index].put(newVersion, val);
    }

    public int snap() {
        snapCount += 1;
        return snapCount - 1;
    }

    public int get(int index, int snap_id) {
        return snapShots[index].floorEntry(snap_id).getValue();
    }
}
