// Segment tree : https://www.youtube.com/watch?v=-dUiRtJ8ot0
class Solution {
    public long maxTotalValue(int[] nums, int k) {
        int n = nums.length;
        SegmentTree segMax = new SegmentTree(nums, false);
        SegmentTree segMin = new SegmentTree(nums, true);

        PriorityQueue<int[]> maxHeap = new PriorityQueue<>((a, b) -> b[0] - a[0]);
        Set<String> visited = new HashSet<>();

        // Start with the full array
        int maxEle = segMax.rangeQuery(0, n - 1);
        int minEle = segMin.rangeQuery(0, n - 1);
        maxHeap.add(new int[]{maxEle - minEle, 0, n - 1});
        visited.add("0," + (n - 1));

        long ans = 0;
        while (!maxHeap.isEmpty() && k > 0) {
            int[] top = maxHeap.poll();
            int diff = top[0], l = top[1], r = top[2];
            ans += diff;
            k--;

            // Shrink the current subarray by removing the first element
            if (l + 1 <= r) {
                String key = (l + 1) + "," + r;
                if (!visited.contains(key)) {
                    int maxi = segMax.rangeQuery(l + 1, r);
                    int mini = segMin.rangeQuery(l + 1, r);
                    maxHeap.add(new int[]{maxi - mini, l + 1, r});
                    visited.add(key);
                }
            }

            // Shrink the current subarray by removing the last element
            if (l <= r - 1) {
                String key = l + "," + (r - 1);
                if (!visited.contains(key)) {
                    int maxi = segMax.rangeQuery(l, r - 1);
                    int mini = segMin.rangeQuery(l, r - 1);
                    maxHeap.add(new int[]{maxi - mini, l, r - 1});
                    visited.add(key);
                }
            }
        }

        return ans;
    }

    static class SegmentTree {
        int n;
        int[] tree, arr;
        boolean isMin;

        SegmentTree(int[] nums, boolean modeMin) {
            n = nums.length;
            arr = nums;
            isMin = modeMin;
            tree = new int[4 * n];
            build(0, 0, n - 1);
        }

        int combine(int left, int right) {
            return isMin ? Math.min(left, right) : Math.max(left, right);
        }

        void build(int idx, int lo, int hi) {
            if (lo == hi) {
                tree[idx] = arr[lo];
            } else {
                int mid = (lo + hi) / 2;
                build(2 * idx + 1, lo, mid);
                build(2 * idx + 2, mid + 1, hi);
                tree[idx] = combine(tree[2 * idx + 1], tree[2 * idx + 2]);
            }
        }

        int query(int idx, int lo, int hi, int qlo, int qhi) {
            if (qhi < lo || qlo > hi) return isMin ? Integer.MAX_VALUE : Integer.MIN_VALUE; // no overlap
            if (qlo <= lo && qhi >= hi) return tree[idx]; // total overlap

            int mid = (lo + hi) / 2;
            int left = query(2 * idx + 1, lo, mid, qlo, qhi);
            int right = query(2 * idx + 2, mid + 1, hi, qlo, qhi);
            return combine(left, right);
        }

        int rangeQuery(int lo, int hi) {
            return query(0, 0, n - 1, lo, hi);
        }
    }
}