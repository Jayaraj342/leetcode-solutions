// n - TODO
class Solution {
    public long countMajoritySubarrays(int[] A, int target) {
        int n = A.length, pre = n + 1;
        long res = 0;
        int[] count = new int[2 * n + 2];
        int[] acc = new int[2 * n + 2];
        count[pre] = acc[pre] = 1;
        for (int a : A) {
            pre += (a == target ? 1 : -1);
            count[pre]++;
            acc[pre] = acc[pre - 1] + count[pre];
            res += acc[pre - 1];
        }

        return res;
    }
}

// n.log(n) - Segment tree
class Solution {
    public long countMajoritySubarrays(int[] nums, int target) {
        int n = nums.length, net = n + 1;
        // if we init net = 0, final range varies from -n -> n (so we change it to 0 -> 2n + 2)
        long res = 0;
        int[] cnt = new int[n];
        for (int i = 0; i < n; i++) {
            int num = nums[i];
            if (num == target) {
                net++;
            } else {
                net--;
            }
            cnt[i] = net;
        }

        SegmentTree segmentTree = new SegmentTree(2 * n + 2);
        segmentTree.update(0, n + 1, 0, 2 * n + 1);
        for (int i = 0; i < n; i++) {
            res += segmentTree.query(0, 0, cnt[i] - 1, 0, 2 * n + 1);
            segmentTree.update(0, cnt[i], 0, 2 * n + 1);
        }

        return res;
    }

    static class SegmentTree {
        int[] tree;
        int n;

        public SegmentTree(int n) {
            this.n = n;
            tree = new int[4 * n];  // safe size
        }

        private void update(int idx, int num, int lo, int hi) {
            if (lo == hi) {
                tree[idx]++;
            } else {
                int mid = (lo + hi) / 2;
                if (num <= mid) {
                    update(2 * idx + 1, num, lo, mid);
                } else {
                    update(2 * idx + 2, num, mid + 1, hi);
                }

                tree[idx] = tree[2 * idx + 1] + tree[2 * idx + 2];
            }
        }

        public int query(int idx, int i, int j, int lo, int hi) {
            if (j < lo || i > hi) return 0; // no overlap
            if (i <= lo && j >= hi) return tree[idx]; // total overlap

            int mid = (lo + hi) / 2;
            int left = query(2 * idx + 1, i, j, lo, mid);
            int right = query(2 * idx + 2, i, j, mid + 1, hi);
            return left + right;
        }
    }
}

// O(n^2) - TLE
class Solution {
    public long countMajoritySubarrays(int[] nums, int target) {
        int n = nums.length, net = n + 1;
        // if we init net = 0, final range varies from -n -> n (so we change it to 0 -> 2n + 2)
        long res = 0;
        int[] cnt = new int[n];
        for (int i = 0; i < n; i++) {
            int num = nums[i];
            if (num == target) {
                net++;
            } else {
                net--;
            }
            cnt[i] = net;
        }

        for (int i = 0; i < n; i++) {
            if (cnt[i] > n + 1) res++; // subarrays starting at 0
        }

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (cnt[j] < cnt[i]) {
                    res++;
                }
            }
        }

        return res;
    }
}