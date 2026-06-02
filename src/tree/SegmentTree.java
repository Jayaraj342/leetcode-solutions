// Segment tree
// Sum in a range - with update & query
class SegmentTree {
    int[] tree;
    int n;

    public SegmentTree(int[] arr) {
        n = arr.length;
        tree = new int[4 * n];  // safe size
        build(arr, 0, 0, n - 1);
    }

    private void build(int[] arr, int node, int lo, int hi) {
        if (lo == hi) {
            tree[node] = arr[lo];
        } else {
            int mid = (lo + hi) / 2;
            build(arr, 2 * node + 1, lo, mid);
            build(arr, 2 * node + 2, mid + 1, hi);
            tree[node] = tree[2 * node + 1] + tree[2 * node + 2];
        }
    }

    public int query(int l, int r) {
        return query(0, l, r, 0, n - 1);
    }

    private int query(int node, int i, int j, int lo, int hi) {
        if (i > hi || j < lo) return 0; // no overlap
        if (i <= lo && j >= hi) return tree[node]; // total overlap

        int mid = (lo + hi) / 2;
        int left = query(2 * node + 1, i, j, lo, mid);
        int right = query(2 * node + 2, i, j, mid + 1, hi);
        return left + right;
    }

    public void update(int idx, int val) {
        update(0, idx, val, 0, n - 1);
    }

    private void update(int node, int idx, int val, int lo, int hi) {
        if (lo == hi) {
            tree[node] = val;
        } else {
            int mid = (lo + hi) / 2;
            if (idx <= mid) update(2 * node + 1, idx, val, lo, mid);
            else update(2 * node + 2, idx, val, mid + 1, hi);
            tree[node] = tree[2 * node + 1] + tree[2 * node + 2];
        }
    }

    public static void main(String[] args) {
        int[] arr = {1, 3, 5, 7, 9, 11};
        SegmentTree st = new SegmentTree(arr);

        // [1, 3, 5, 7, 9, 11]
        System.out.println(st.query(1, 3)); // sum from index 1 to 3 → 15
        st.update(1, 10); // update index 1 to value 10
        // [1, 10, 5, 7, 9, 11]
        System.out.println(st.query(1, 3)); // now sum = 22
    }
}