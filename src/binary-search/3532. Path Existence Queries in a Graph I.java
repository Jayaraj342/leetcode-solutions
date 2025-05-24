class Solution {
    public boolean[] pathExistenceQueries(int n, int[] nums, int maxDiff, int[][] queries) {
        int[] group = new int[n];
        int groupId = 0;

        // Assign group IDs based on maxDiff constraint
        for (int i = 1; i < n; i++) {
            if (nums[i] - nums[i - 1] <= maxDiff) {
                group[i] = groupId;
            } else {
                groupId++;
                group[i] = groupId;
            }
        }

        boolean[] result = new boolean[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int u = queries[i][0], v = queries[i][1];
            result[i] = group[u] == group[v];
        }

        return result;
    }
}

class Solution {
    public boolean[] pathExistenceQueries(int n, int[] nums, int maxDiff, int[][] queries) {
        // Identify discontinuities based on maxDiff
        List<Integer> cutPoints = new ArrayList<>();
        cutPoints.add(0); // Start of the first segment
        for (int i = 1; i < n; ++i) {
            if (nums[i] - nums[i - 1] > maxDiff) {
                cutPoints.add(i); // Start of a new segment
            }
        }

        boolean[] result = new boolean[queries.length];
        for (int i = 0; i < queries.length; ++i) {
            int from = queries[i][0];
            int to = queries[i][1];

            // Find the segment index for 'from' and 'to'
            int fromSegment = findSegment(cutPoints, from + 1);
            int toSegment = findSegment(cutPoints, to + 1);

            result[i] = (fromSegment == toSegment);// there should not be any cut between 2 points
        }

        return result;
    }

    // Helper to find the insertion point using binary search
    private int findSegment(List<Integer> cutPoints, int position) {
        int idx = Collections.binarySearch(cutPoints, position);
        return idx < 0 ? -idx - 1 : idx;
    }
}

// timeout
class Solution {
    public boolean[] pathExistenceQueries(int n, int[] nums, int maxDiff, int[][] queries) {
        // [0, 1, 2, 3]
        // find till which idx does path exist - union all in the exist range -> (start, end)
        int[] parent = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }

        for (int i = 0; i < n; i++) {
            int j = getEnd(i, nums, maxDiff);
            for (int k = i; k <= j; k++) {
                union(i, j, parent);
            }
        }

        int m = queries.length;
        boolean[] res = new boolean[m];
        for (int i = 0; i < m; i++) {
            int u = queries[i][0], v = queries[i][1];
            res[i] = find(u, parent) == find(v, parent);
        }

        return res;
    }

    private int getEnd(int start, int[] nums, int maxDiff) {
        int lo = start, hi = nums.length - 1;
        int res = start;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            int diff = Math.abs(nums[start] - nums[mid]);
            if (diff <= maxDiff) {
                res = mid;
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }

        return res;
    }

    private void union(int u, int v, int[] parent) {
        int pu = find(u, parent), pv = find(v, parent);
        if (pu != pv) {
            parent[pu] = pv;
        }
    }

    private int find(int u, int[] parent) {
        if (parent[u] != u) {
            parent[u] = find(parent[u], parent);
        }

        return parent[u];
    }
}