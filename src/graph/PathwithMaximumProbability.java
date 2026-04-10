// E.log(E), E + V
// Dijkstra's
class Solution {
    public double maxProbability(int n, int[][] edges, double[] succProb, int start, int end) {
        // Build adjacency list
        List<List<Pair>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < edges.length; i++) {
            int u = edges[i][0];
            int v = edges[i][1];
            double prob = succProb[i];
            graph.get(u).add(new Pair(v, prob));
            graph.get(v).add(new Pair(u, prob));
        }

        // Max heap based on probability
        PriorityQueue<Pair> maxHeap = new PriorityQueue<>((a, b) -> Double.compare(b.probability, a.probability));
        maxHeap.offer(new Pair(start, 1.0));

        boolean[] visited = new boolean[n];

        while (!maxHeap.isEmpty()) {
            Pair current = maxHeap.poll();

            if (visited[current.node]) continue;
            visited[current.node] = true;

            if (current.node == end) {
                return current.probability;
            }

            for (Pair neighbor : graph.get(current.node)) {
                if (!visited[neighbor.node]) {
                    double newProb = current.probability * neighbor.probability;
                    maxHeap.offer(new Pair(neighbor.node, newProb));
                }
            }
        }

        return 0.0;
    }

    static class Pair {
        int node;
        double probability;

        Pair(int node, double probability) {
            this.node = node;
            this.probability = probability;
        }
    }
}