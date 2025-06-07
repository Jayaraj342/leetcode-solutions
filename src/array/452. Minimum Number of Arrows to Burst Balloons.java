class Solution {
    public int findMinArrowShots(int[][] points) {
        // overflows for -inf, inf case
        Arrays.sort(points, (a, b) -> {
            long diff = (long) a[0] - b[0];
            return diff > 0 ? 1 : (diff < 0 ? -1 : 0);
        });
        int[] prev = points[0];
        int n = points.length, res = n;
        for (int i = 1; i < n; i++) {
            int[] curr = points[i];
            if (curr[0] <= prev[1]) {
                res--;
                prev[1] = Math.min(prev[1], curr[1]);
            } else {
                prev = curr;
            }
        }

        return res;
    }
}