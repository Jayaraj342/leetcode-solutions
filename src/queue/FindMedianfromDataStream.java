class MedianFinder {
    // first-half
    PriorityQueue<Integer> maxPQ;
    // second-half
    PriorityQueue<Integer> minPQ;

    public MedianFinder() {
        maxPQ = new PriorityQueue<>((a, b) -> b - a);
        minPQ = new PriorityQueue<>();
    }

    public void addNum(int num) {
        if (maxPQ.size() == minPQ.size()) {
            maxPQ.add(num);
            minPQ.add(maxPQ.remove());
        } else {
            minPQ.add(num);
            maxPQ.add(minPQ.remove());
        }
    }

    public double findMedian() {
        if (maxPQ.size() == minPQ.size()) {
            return (double) (maxPQ.peek() + minPQ.peek()) / 2;
        } else {
            return minPQ.peek();
        }
    }
}

// n^2 won't work - logn for binary search but, to insert element it takes n
class Solution {
    static List<Integer> list = new ArrayList<Integer>();

    public static void insertHeap(int x) {
        if (list.isEmpty()) {
            list.add(x);
        } else {
            // [1, 2, 7, 8] binarySearch(list, 4) => -2-1 => -3
            int pos = Math.abs(Collections.binarySearch(list, x) + 1);
            list.add(pos, x);
        }
    }

    public static void balanceHeaps() {
        // add your code here
    }

    public static double getMedian() {
        int size = list.size();
        if (size % 2 == 0) {
            return (double) (list.get(size / 2 - 1) + list.get(size / 2)) / 2;
        } else {
            return list.get(size / 2);
        }
    }
}