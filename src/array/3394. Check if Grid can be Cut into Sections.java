class Solution {
    // merge intervals - cnt in merged list should be >= 3
    public boolean checkValidCuts(int n, int[][] rectangles) {
        List<int[]> xIntervals = new ArrayList<>();
        List<int[]> yIntervals = new ArrayList<>();

        for (int[] rect : rectangles) {
            xIntervals.add(new int[]{rect[0], rect[2]});
            yIntervals.add(new int[]{rect[1], rect[3]});
        }

        xIntervals.sort(Comparator.comparingInt(a -> a[0]));
        yIntervals.sort(Comparator.comparingInt(a -> a[0]));

        return Math.max(countNonOverlappingIntervals(xIntervals), countNonOverlappingIntervals(yIntervals)) >= 3;
    }

    private int countNonOverlappingIntervals(List<int[]> intervals) {
        int count = 0, prevEnd = -1;

        for (int[] interval : intervals) {
            if (interval[0] >= prevEnd) {
                count++;
            }
            prevEnd = Math.max(prevEnd, interval[1]);
        }

        return count;
    }
}
