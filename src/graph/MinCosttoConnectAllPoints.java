// Optimized prims - V^2, V
class Solution {
    public int minCostConnectPoints(int[][] points) {
        int n = points.length;
        int[] minDist = new int[n];  // minimum edge weight to connect each point
        Arrays.fill(minDist, Integer.MAX_VALUE);
        boolean[] visited = new boolean[n];

        int cost = 0;
        minDist[0] = 0;  // start from point 0

        for (int i = 0; i < n; i++) {
            // 1️⃣ Find unvisited node with minimum distance
            int u = -1;
            for (int j = 0; j < n; j++) {
                if (!visited[j] && (u == -1 || minDist[j] < minDist[u])) {
                    u = j;
                }
            }

            // 2️⃣ Add that node to MST
            visited[u] = true;
            cost += minDist[u];

            // 3️⃣ Update distances of all unvisited neighbors
            for (int v = 0; v < n; v++) {
                if (!visited[v]) {
                    int dist = Math.abs(points[u][0] - points[v][0])
                            + Math.abs(points[u][1] - points[v][1]);
                    minDist[v] = Math.min(minDist[v], dist);
                }
            }
        }

        return cost;
    }
}

// Prims - min spanning tree
// V^2 = Edges count
// TC : O(V^2 * log(V)), SC : O(V^2)
class Solution {
    public int minCostConnectPoints(int[][] points) {
        int n = points.length;
        List<List<int[]>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }
        for (int i = 0; i < n; i++) {
            int[] curr = points[i];
            for (int j = i + 1; j < n; j++) {
                int[] nei = points[j];
                int w = Math.abs(curr[0] - nei[0]) + Math.abs(curr[1] - nei[1]);
                adj.get(i).add(new int[]{w, j});
                adj.get(j).add(new int[]{w, i});
            }
        }

        //[w, v2]
        PriorityQueue<int[]> minHeap = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        minHeap.add(new int[]{0, 0});

        Set<Integer> visited = new HashSet<>();
        int cost = 0;
        while (visited.size() < n) {
            int[] curr = minHeap.remove();
            int v1 = curr[1];
            int w1 = curr[0];

            if (visited.contains(v1)) {
                continue;
            }
            visited.add(v1);
            cost += w1;

            for (int[] nei : adj.get(v1)) {
                int v2 = nei[1];
                int w2 = nei[0];
                minHeap.add(new int[]{w2, v2});
            }
        }

        return cost;
    }
}