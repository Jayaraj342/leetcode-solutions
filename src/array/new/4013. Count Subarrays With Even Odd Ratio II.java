// https://leetcode.com/problems/count-subarrays-with-even-odd-ratio-ii
// https://github.com/Jayaraj342/leetcode-solutions/blob/master/src/array/new/4013. Count Subarrays With Even Odd Ratio II.java
// Can use merge sort too - cnt no. elements >= currPrefix while sorting
class Solution {
    public long countRatioSubarrays(int[] nums, int a, int b) {
        int n = nums.length;

        // Transform:
        // odd  -> -a
        // even ->  b
        for (int i = 0; i < n; i++) {
            if (nums[i] % 2 == 0) {
                nums[i] = b;
            } else {
                nums[i] = -a;
            }
        }

        // prefix[0] = 0
        long[] prefix = new long[n + 1];
        for (int i = 0; i < n; i++) {
            prefix[i + 1] = prefix[i] + nums[i];
        }

        // Coordinate compression
        long[] sorted = prefix.clone();
        Arrays.sort(sorted);

        int m = 0;
        Map<Long, Integer> idxMap = new HashMap<>();
        for (long num : sorted) {
            if (m == 0 || sorted[m - 1] != num) {
                idxMap.put(num, m);
                sorted[m++] = num;
            }
        }

        SegmentTree sg = new SegmentTree(m);
        // Add prefix[0] = 0
        sg.update(idxMap.get(0L));

        long res = 0;
        for (int i = 1; i <= n; i++) {
            int idxOfPrefix = idxMap.get(prefix[i]);

            // Count previous prefix sums >= current prefix sum
            res += sg.query(idxOfPrefix, m - 1);

            // Add current prefix sum
            sg.update(idxOfPrefix);
        }

        return res;
    }

    static class SegmentTree {
        int[] tree;
        int n;

        public SegmentTree(int n) {
            this.n = n;
            tree = new int[4 * n];
        }

        public int query(int l, int r) {
            if (l > r) {
                return 0;
            }

            return query(0, l, r, 0, n - 1);
        }

        private int query(int node, int i, int j, int lo, int hi) {

            // No overlap
            if (i > hi || j < lo) {
                return 0;
            }

            // Complete overlap
            if (i <= lo && hi <= j) {
                return tree[node];
            }

            int mid = lo + (hi - lo) / 2;
            int left = query(2 * node + 1, i, j, lo, mid);
            int right = query(2 * node + 2, i, j, mid + 1, hi);

            return left + right;
        }

        public void update(int idx) {
            update(0, idx, 0, n - 1);
        }

        private void update(int node, int idx, int lo, int hi) {
            if (lo == hi) {
                tree[node]++;
                return;
            }

            int mid = lo + (hi - lo) / 2;
            if (idx <= mid) {
                update(2 * node + 1, idx, lo, mid);
            } else {
                update(2 * node + 2, idx, mid + 1, hi);
            }

            tree[node] = tree[2 * node + 1] + tree[2 * node + 2];
        }
    }
}