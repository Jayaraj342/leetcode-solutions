// https://leetcode.com/problems/count-of-unfinished-tasks-after-each-shift
// https://github.com/Jayaraj342/leetcode-solutions/blob/master/src/binary-search/new/4012. Count of Unfinished Tasks After Each Shift.java

class Solution {
    public int[] countTasks(int[] tasks, int[] shifts) {
        int m = tasks.length, n = shifts.length;

        long[] prefix = new long[m];
        for (int i = 0; i < m; i++) {
            prefix[i] = tasks[i] + (i > 0 ? prefix[i - 1] : 0);
        }

        int[] res = new int[n];
        int start = 0;
        long covered = 0;

        for (int j = 0; j < n; j++) {
            long target = covered + shifts[j];

            int lo = start;
            int hi = m - 1;

            // First index where prefix[index] > target
            while (lo <= hi) {
                int mid = lo + (hi - lo) / 2;

                if (prefix[mid] <= target) {
                    lo = mid + 1;
                } else {
                    hi = mid - 1;
                }
            }

            if (lo == m) {
                // Entire array is covered
                res[j] = 0;
                start = 0;
                covered = 0;
            } else {
                start = lo;
                covered = target;
                res[j] = m - start;
            }
        }

        return res;
    }
}