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
            max = Math.max(max, wTotal);

            for (int[] neighbour : adj.getOrDefault(v1, new ArrayList<>())) {
                int v2 = neighbour[0];
                int wCurrent = neighbour[1];

                minHeap.add(new int[]{wTotal + wCurrent, v2});
            }
        }

        return visited.size() == n ? max : -1;
    }
}