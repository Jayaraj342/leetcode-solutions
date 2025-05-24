import java.util.*;

class Solution {
    int N;
    int[][] ancestor;
    long[] distance; // Use long to avoid overflow
    Map<Integer, List<List<Integer>>> adj;
    boolean[] visited;
    int maxDepth;
    int root = 0;
    int[] depth;

    // Compute LCA using Binary Lifting
    public int lca(int u, int v) {
        if (depth[u] < depth[v]) {
            int temp = u;
            u = v;
            v = temp;
        }

        int diff = depth[u] - depth[v];
        for (int i = maxDepth - 1; i >= 0; i--) {
            if ((diff & (1 << i)) != 0 && u != -1) {
                u = ancestor[u][i];
            }
        }

        if (u == v) return u;

        for (int i = maxDepth - 1; i >= 0; i--) {
            if (ancestor[u][i] != -1 && ancestor[u][i] != ancestor[v][i]) {
                u = ancestor[u][i];
                v = ancestor[v][i];
            }
        }

        return ancestor[u][0];
    }

    // DFS to set up ancestor table, depth, and distance from root
    public void dfs(int node, int parent, long wt, int height) {
        ancestor[node][0] = parent;
        for (int i = 1; i < maxDepth; i++) {
            int par = ancestor[node][i - 1];
            ancestor[node][i] = (par == -1) ? -1 : ancestor[par][i - 1];
        }

        visited[node] = true;
        distance[node] = wt;
        depth[node] = height;

        for (List<Integer> c : adj.getOrDefault(node, Collections.emptyList())) {
            int nextNode = c.get(0);
            int weight = c.get(1);
            if (!visited[nextNode]) {
                dfs(nextNode, node, wt + weight, height + 1);
            }
        }
    }

    // Helper to compute distance between two nodes
    public long getDistance(int u, int v) {
        int lcaNode = lca(u, v);
        return distance[u] + distance[v] - 2 * distance[lcaNode];
    }

    public int[] minimumWeight(int[][] edges, int[][] queries) {
        N = edges.length + 1;
        adj = new HashMap<>();
        distance = new long[N];
        visited = new boolean[N];
        depth = new int[N];
        maxDepth = (int) (Math.log(N) / Math.log(2)) + 1;
        ancestor = new int[N][maxDepth];

        // Build adjacency list
        for (int[] e : edges) {
            adj.computeIfAbsent(e[0], k -> new ArrayList<>()).add(Arrays.asList(e[1], e[2]));
            adj.computeIfAbsent(e[1], k -> new ArrayList<>()).add(Arrays.asList(e[0], e[2]));
        }

        // Setup DFS from root node
        dfs(root, -1, 0L, 0);

        int[] ans = new int[queries.length];
        for (int k = 0; k < queries.length; k++) {
            int a = queries[k][0];
            int b = queries[k][1];
            int c = queries[k][2];

            long d1 = getDistance(a, c);
            long d2 = getDistance(b, c);
            long d3 = getDistance(a, b);

            ans[k] = (int) ((d1 + d2 + d3) / 2); // Minimum weight of triplet path
        }

        return ans;
    }
}
