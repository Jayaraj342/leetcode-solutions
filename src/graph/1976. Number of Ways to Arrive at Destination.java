class Solution {
    public int countPaths(int n, int[][] roads) {
        List<int[]>[] adj = new List[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }
        for (int[] edge : roads) {
            int u = edge[0], v = edge[1], wt = edge[2];
            adj[u].add(new int[]{v, wt});
            adj[v].add(new int[]{u, wt});
        }

        // use dijkstra's to find the shortest path => minHeap[v, wt]
        PriorityQueue<long[]> minHeap = new PriorityQueue<>((a, b) -> Long.compare(a[1], b[1]));
        minHeap.add(new long[]{0, 0});

        long[] countWays = new long[n];
        countWays[0] = 1;

        long[] costToReach = new long[n];
        Arrays.fill(costToReach, Long.MAX_VALUE);

        while (!minHeap.isEmpty()) {
            long[] last = minHeap.remove();
            int v = (int) last[0];
            long cost = last[1];

            for (int[] nei : adj[v]) {
                int neiNode = nei[0];
                long neiCost = nei[1];
                if (cost + neiCost < costToReach[neiNode]) {
                    costToReach[neiNode] = cost + neiCost;
                    countWays[neiNode] = countWays[v];
                    minHeap.add(new long[]{neiNode, cost + neiCost});
                } else if (cost + neiCost == costToReach[neiNode]) {
                    countWays[neiNode] = (countWays[neiNode] + countWays[v]) % 1000_000_007;
                }
            }
        }

        return (int) countWays[n - 1];
    }
}