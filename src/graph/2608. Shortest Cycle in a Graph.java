class Solution {
    public int findShortestCycle(int n, int[][] edges) {
        // create adjacency list
        List<Integer>[] adj = new List[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }

        for (int[] edge : edges) {
            int u = edge[0], v = edge[1];
            adj[u].add(v);
            adj[v].add(u);
        }

        // for each vertex - find shortest way back
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            min = Math.min(min, bfs(adj, i));
        }

        return min != Integer.MAX_VALUE ? min : -1;
    }

    private int bfs(List<Integer>[] adj, int src) {
        Queue<int[]> queue = new LinkedList<>();// [node, prev, dist]
        queue.add(new int[] {src, -1, 0});

        while (!queue.isEmpty()) {
            int[] last = queue.remove();
            int curr = last[0], prev = last[1], dist = last[2];
            for (int nei : adj[curr]) {
                if (nei != prev) {
                    if (nei == src) {
                        return dist + 1;
                    }
                    queue.add(new int[] {nei, curr, dist + 1});
                }
            }
        }

        return Integer.MAX_VALUE ;
    }
}