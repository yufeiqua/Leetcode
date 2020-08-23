package com.leetcode.Google;

class RLEIteratorTest {
    public static void main(String[] args) {
        RLEIterator rleIterator = new RLEIterator(new int[]{5733, 839, 3188, 174, 1041, 509, 311, 357, 838, 251});
        System.out.println(rleIterator.next(5039));//5733
        System.out.println(rleIterator.next(62)); // 5101
        System.out.println(rleIterator.next(3640));
        System.out.println(rleIterator.next(671));
        System.out.println(rleIterator.next(67));
        System.out.println(rleIterator.next(395));
        System.out.println(rleIterator.next(262));
        System.out.println(rleIterator.next(839));
        System.out.println(rleIterator.next(74));
        System.out.println(rleIterator.next(43));
        System.out.println(rleIterator.next(42));
        System.out.println(rleIterator.next(77));
        System.out.println(rleIterator.next(13));
        System.out.println(rleIterator.next(6));
        System.out.println(rleIterator.next(3));

        //[5039],[62],[3640],[671],[67],[395],[262],[839],[74],[43],[42],[77],[13],[6],[3]
    }
}

class RLEIterator {
    private int count;
    private int number;
    private int index;
    private int[] A;

    public RLEIterator(int[] A) {
        this.A = A;
        index = 0;
        count = A[index];
        number = A[index + 1];
    }

    public int next(int n) {
        if (count - n >= 0) {
            count -= n;
            return number;
        }
        if (index + 2 >= A.length) {
            count = 0;
            return -1;
        }
        int restOfConsumerNumber = n - count;
        while (restOfConsumerNumber != 0 && index + 2 < A.length) {
            index += 2;
            count = A[index];
            number = A[index + 1];
            if (restOfConsumerNumber >= count) {
                restOfConsumerNumber -= count;
                count = 0;
            } else {
                count -= restOfConsumerNumber;
                restOfConsumerNumber = 0;
            }
        }
        return number;
    }
}
