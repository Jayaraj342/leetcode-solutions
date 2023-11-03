//Given n sets each containing m integers.
//You need to create MinDiff set by picking exactly 1 element from each set.
//The difference between the maximum and minimum elements in the MinDiff set should be minimum.
//eg:for the sets{30,20,10,40},{22,33,44,99},{16,17,18,19}.
//min diff set is{19,20,22}.Max and min elements in MinDiff set are 22 and 19 and their difference is 3.

// http://writeulearn.com/minimize-max-min-difference-picking-element-arrays/

class Solution {
    static int minDiff(int[][] arr) {

        PriorityQueue<List<Integer>> maxMinHeap = new PriorityQueue<>((a, b) -> a.size() != b.size() ? b.size() - a.size() : a.get(0) - b.get(0));
        for (int[] temp : arr) {
            Arrays.sort(temp);
            List<Integer> list = new LinkedList<>();
            for (int i : temp) {
                list.add(i);
            }
            maxMinHeap.add(list);
        }

        // of size (window - 1)
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        int window = arr.length;
        int res = Integer.MAX_VALUE;
        while (!maxMinHeap.isEmpty()) {
            List<Integer> currList = maxMinHeap.remove();
            Integer currValue = currList.remove(0);

            if (maxHeap.size() == window - 1) {
                res = Math.min(res, Math.abs(maxHeap.peek() - currValue));
                maxHeap.remove();
            }
            maxHeap.add(currValue);
            if (!currList.isEmpty()) {
                maxMinHeap.add(currList);
            }
        }

        return res;
    }

    public static void main(String[] args) {
        System.out.println(minDiff(new int[][]{{30, 20, 10, 40}, {22, 33, 44, 99}, {16, 17, 18, 19}}));
        System.out.println(minDiff(new int[][]{{30, 20, 40}, {33, 44, 99}, {16, 17, 18}}));
    }
}