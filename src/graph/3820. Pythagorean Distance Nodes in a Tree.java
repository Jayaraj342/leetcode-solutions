class Solution {
    public int specialNodes(int n, int[][] edges, int x, int y, int z) {
        List<Integer>[] adj = new List[n];
        for (int i = 0; i < n; i++) adj[i] = new ArrayList<>();

        for (int[] edge : edges) {
            int u = edge[0], v = edge[1];
            adj[u].add(v);
            adj[v].add(u);
        }

        int[] dx = bfs(adj, n, x);
        int[] dy = bfs(adj, n, y);
        int[] dz = bfs(adj, n, z);

        int res = 0;
        for (int i = 0; i < n; i++) {
            int a = dx[i], b = dy[i], c = dz[i];

            // sort 3 values manually
            int min = Math.min(a, Math.min(b, c));
            int max = Math.max(a, Math.max(b, c));
            int mid = a + b + c - min - max;

            if (min * min + mid * mid == max * max) {
                res++;
            }
        }

        return res;
    }

    private int[] bfs(List<Integer>[] adj, int n, int src) {
        int[] dist = new int[n];
        Arrays.fill(dist, -1);

        ArrayDeque<Integer> queue = new ArrayDeque<>();
        queue.add(src);
        dist[src] = 0;

        while (!queue.isEmpty()) {
            int u = queue.remove();
            for (int v : adj[u]) {
                if (dist[v] == -1) {
                    dist[v] = dist[u] + 1;
                    queue.add(v);
                }
            }
        }

        return dist;
    }
}
