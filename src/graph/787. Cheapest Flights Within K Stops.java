class Solution {
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        // Create adjacency list
        List<int[]>[] adj = new List[n]; // (neighbor, weight)
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }

        for (int[] flight : flights) {
            int u = flight[0], v = flight[1], w = flight[2];
            adj[u].add(new int[]{v, w});
        }

        // Initialize queue for BFS : (current node, total cost from src, stops used)
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{src, 0, 0});

        // Track the minimum cost to reach each node
        int[] minCost = new int[n];
        Arrays.fill(minCost, Integer.MAX_VALUE);

        // BFS
        while (!queue.isEmpty()) {
            int[] curr = queue.remove();
            int node = curr[0], cost = curr[1], stops = curr[2];

            // If we've exceeded allowed stops or already found a better path, continue
            if (stops > k + 1 || cost > minCost[node]) {
                continue;
            }

            // Update minimum cost for the current node
            minCost[node] = cost;

            // Explore neighboring nodes
            for (int[] nei : adj[node]) {
                int nextNode = nei[0], nextCost = nei[1];
                queue.offer(new int[]{nextNode, cost + nextCost, stops + 1});
            }
        }

        // Return the minimum cost to reach the destination, or -1 if unreachable
        return minCost[dst] == Integer.MAX_VALUE ? -1 : minCost[dst];
    }
}
