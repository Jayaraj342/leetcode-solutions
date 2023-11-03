class Solution {
    static class Pair {
        int v;
        double p;

        Pair(int v, double p) {
            this.v = v;
            this.p = p;
        }
    }

    public double maxProbability(int n, int[][] edges, double[] succProb, int start, int end) {
        List<List<Pair>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }

        for (int i = 0; i < edges.length; i++) {
            int[] edge = edges[i];
            adj.get(edge[0]).add(new Pair(edge[1], succProb[i]));
            adj.get(edge[1]).add(new Pair(edge[0], succProb[i]));
        }

        PriorityQueue<Pair> maxHeap = new PriorityQueue<>((a, b) -> Double.compare(b.p, a.p));
        maxHeap.add(new Pair(start, 1));

        double res = 0;
        Set<Integer> visited = new HashSet<>();
        while (!maxHeap.isEmpty()) {
            Pair curr = maxHeap.remove();
            if (visited.contains(curr.v)) {
                continue;
            }
            visited.add(curr.v);
            if (curr.v == end) {
                res = Math.max(res, curr.p);
            }

            for (Pair nei : adj.get(curr.v)) {
                maxHeap.add(new Pair(nei.v, nei.p * curr.p));
            }
        }

        return res;
    }
}