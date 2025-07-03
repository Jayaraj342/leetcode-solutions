// Dijkstra's TC : O((V + E). log(V)) => might be log(V + E) but dijkstra's optimization in avg case gives log(V), SC : O(E + V) => heap - E, visited set - V
class Solution {
    public int networkDelayTime(int[][] times, int n, int k) {
        Map<Integer, List<int[]>> adj = new HashMap<>();

        for (int[] time : times) {
            int v1 = time[0];
            int v2 = time[1];
            int w = time[2];

            adj.putIfAbsent(v1, new ArrayList<>());
            adj.get(v1).add(new int[]{v2, w});
        }

        // v1 -> [w, v2]
        PriorityQueue<int[]> minHeap = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        minHeap.add(new int[]{0, k});

        int max = 0;
        Set<Integer> visited = new HashSet<>();

        while (!minHeap.isEmpty()) {
            int[] current = minHeap.remove();
            int wTotal = current[0];
            int v1 = current[1];

            if (visited.contains(v1)) {
                continue;
            }
            visited.add(v1);
            max = wTotal;

            for (int[] neighbour : adj.getOrDefault(v1, new ArrayList<>())) {
                int v2 = neighbour[0];
                int wCurrent = neighbour[1];

                if (!visited.contains(v2)) minHeap.add(new int[]{wTotal + wCurrent, v2});// makes sure only V nodes are there in heap
            }
        }

        return visited.size() == n ? max : -1;
    }
}

// O(V * E) : Bellman-ford
class Solution {
    public int networkDelayTime(int[][] times, int n, int k) {
        int[] dist = new int[n + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[k] = 0;
        for (int i = 0; i <= n; i++) {
            boolean updated = false;
            for (int[] time : times) {
                int v1 = time[0], v2 = time[1];
                int w = time[2];
                if (dist[v1] == Integer.MAX_VALUE || dist[v1] + w >= dist[v2]) {
                    continue;
                }
                dist[v2] = dist[v1] + w;
                updated = true;
            }
            if (!updated) break;
        }

        int max = 0;
        for (int i = 1; i <= n; i++) {
            max = Math.max(max, dist[i]);
        }

        return max == Integer.MAX_VALUE ? -1 : max;
    }
}