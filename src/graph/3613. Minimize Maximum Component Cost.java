// O(E.logE), O(E)
class Solution {
    public int minCost(int n, int[][] edges, int k) {
        if (k >= n) return 0;

        // Sort edges by weight
        Arrays.sort(edges, (a, b) -> a[2] - b[2]);

        int[] parent = new int[n];
        for (int i = 0; i < n; i++) parent[i] = i;

        int components = n;
        // Kruskal's algorithm: connect components using smallest edges first
        for (int[] edge : edges) {
            int u = edge[0], v = edge[1], w = edge[2];
            if (find(parent, u) != find(parent, v)) {
                union(parent, u, v);
                components--;
            }

            // As soon as we have <= k components, return current weight
            if (components <= k) {
                return w;
            }
        }

        // If we never reached <= k components
        return -1;
    }

    private void union(int[] parent, int u, int v) {
        int pu = find(parent, u);
        int pv = find(parent, v);
        if (pu != pv) {
            parent[pu] = pv;
        }
    }

    private int find(int[] parent, int u) {
        // Path compression for efficiency
        if (parent[u] != u) {
            parent[u] = find(parent, parent[u]);
        }
        return parent[u];
    }
}

// O((E + V) logW), O(E)
class Solution {
    public int minCost(int n, int[][] edges, int k) {
        if (k >= n) return 0;

        // Sort edges by weight
        Arrays.sort(edges, (a, b) -> a[2] - b[2]);

        int lo = edges[0][2], hi = edges[edges.length - 1][2];
        int res = -1;

        // Binary search on edge weight
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;

            // Count components using edges <= mid
            int components = getComponentsWithinWeight(n, edges, mid);

            if (components <= k) {
                res = mid;      // possible answer, try smaller
                hi = mid - 1;
            } else {
                lo = mid + 1;   // need bigger weights
            }
        }

        return res;
    }

    private int getComponentsWithinWeight(int n, int[][] edges, int maxWeight) {
        int[] parent = new int[n];
        for (int i = 0; i < n; i++) parent[i] = i;

        // Union edges with weight <= maxWeight
        for (int[] edge : edges) {
            if (edge[2] > maxWeight) break;
            union(parent, edge[0], edge[1]);
        }

        // Count components
        int cnt = 0;
        for (int i = 0; i < n; i++) {
            if (find(parent, i) == i) cnt++;
        }

        return cnt;
    }

    private void union(int[] parent, int u, int v) {
        int pu = find(parent, u), pv = find(parent, v);
        if (pu != pv) {
            parent[pu] = pv;
        }
    }

    private int find(int[] parent, int u) {
        // Path compression
        if (parent[u] != u) {
            parent[u] = find(parent, parent[u]);
        }
        return parent[u];
    }
}