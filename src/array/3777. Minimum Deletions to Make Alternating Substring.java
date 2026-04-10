// nlog(n), n
class Solution {
    public int[] minDeletions(String s, int[][] queries) {
        int n = s.length();
        char[] arr = s.toCharArray();

        int[] nums = new int[n];
        for (int i = 1; i < n; i++) {
            nums[i] = (arr[i] == arr[i - 1]) ? 1 : 0;
        }

        SegmentTree st = new SegmentTree(nums);
        List<Integer> res = new ArrayList<>();
        for (int[] query : queries) {
            if (query[0] == 1) {
                int idx = query[1];
                arr[idx] = arr[idx] == 'A' ? 'B' : 'A';

                // update nums[idx]
                if (idx > 0) {
                    int val = (arr[idx] == arr[idx - 1]) ? 1 : 0;
                    st.update(idx, val);
                }

                // update nums[idx + 1]
                if (idx + 1 < n) {
                    int val = (arr[idx + 1] == arr[idx]) ? 1 : 0;
                    st.update(idx + 1, val);
                }
            } else {
                int i = query[1], j = query[2];
                if (i == j) {
                    res.add(0);
                } else {
                    res.add(st.query(i + 1, j));
                }
            }
        }

        return res.stream().mapToInt(i -> i).toArray();
    }

    static class SegmentTree {
        int n;
        int[] tree;

        SegmentTree(int[] nums) {
            n = nums.length;
            tree = new int[4 * n];
            build(nums, 0, 0, n - 1);
        }

        void build(int[] nums, int idx, int lo, int hi) {
            if (lo == hi) {
                tree[idx] = nums[lo];
                return;
            }

            int mid = (lo + hi) / 2;
            build(nums, 2 * idx + 1, lo, mid);
            build(nums, 2 * idx + 2, mid + 1, hi);
            tree[idx] = tree[2 * idx + 1] + tree[2 * idx + 2];
        }

        void update(int pos, int val) {
            update(0, 0, n - 1, pos, val);
        }

        void update(int idx, int lo, int hi, int pos, int val) {
            if (lo == hi) {
                tree[idx] = val;
                return;
            }

            int mid = (lo + hi) / 2;
            if (pos <= mid) {
                update(2 * idx + 1, lo, mid, pos, val);
            } else {
                update(2 * idx + 2, mid + 1, hi, pos, val);
            }

            tree[idx] = tree[2 * idx + 1] + tree[2 * idx + 2];
        }

        int query(int i, int j) {
            return query(0, 0, n - 1, i, j);
        }

        int query(int idx, int lo, int hi, int i, int j) {
            if (i > hi || j < lo) return 0;
            if (i <= lo && hi <= j) return tree[idx];
            int mid = (lo + hi) / 2;
            return query(2 * idx + 1, lo, mid, i, j) + query(2 * idx + 2, mid + 1, hi, i, j);
        }
    }
}

// n * q => TLE
class Solution {
    public int[] minDeletions(String s, int[][] queries) {
        char[] arr = s.toCharArray();

        List<Integer> res = new ArrayList<>();
        for (int[] query : queries) {
            int option = query[0];
            if (option == 1) {
                int idx = query[1];
                arr[idx] = arr[idx] == 'A' ? 'B' : 'A';
            } else {
                int deletions = 0;
                char prev = '-';
                int start = query[1], end = query[2];
                for (int i = start; i <= end; i++) {
                    char c = arr[i];
                    if (c == prev) {
                        deletions++;
                    }
                    prev = c;
                }
                res.add(deletions);
            }
        }

        int size = res.size();
        int[] resArr = new int[size];
        for (int i = 0; i < size; i++) {
            resArr[i] = res.get(i);
        }

        return resArr;
    }
}