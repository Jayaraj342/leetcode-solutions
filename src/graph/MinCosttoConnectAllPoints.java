class Solution {
    public int minCostConnectPoints(int[][] points) {
        int n = points.length;
        List<List<int[]>> adj = new ArrayList<>();
        for (int i : IntStream.range(0, n).toArray()) {
            adj.add(i, new ArrayList<>());
        }

        for (int i : IntStream.range(0, n).toArray()) {
            int x1 = points[i][0];
            int y1 = points[i][1];
            for (int j : IntStream.range(i + 1, n).toArray()) {
                int x2 = points[j][0];
                int y2 = points[j][1];
                int dist = Math.abs(x1 - x2) + Math.abs(y1 - y2);

                adj.get(i).add(new int[]{dist, j});
                adj.get(j).add(new int[]{dist, i});
            }
        }

        // (weight, v2)
        PriorityQueue<int[]> minHeap = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        minHeap.add(new int[]{0, 0});

        Set<Integer> visited = new HashSet<>();
        int cost = 0;
        while (visited.size() < n) {
            int[] current = minHeap.remove();
            int w = current[0];
            int v1 = current[1];

            if (visited.contains(v1)) {
                continue;
            }
            visited.add(v1);
            cost += w;

            for (int[] nei : adj.get(v1)) {
                minHeap.add(new int[]{nei[0], nei[1]});
            }
        }

        return cost;
    }
}