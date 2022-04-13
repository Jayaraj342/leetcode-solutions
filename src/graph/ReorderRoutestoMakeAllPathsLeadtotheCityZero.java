class Solution {
    public int minReorder(int n, int[][] connections) {
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }

        for (int[] edge : connections) {
            adj.get(edge[0]).add(edge[1]);
            adj.get(edge[1]).add(-edge[0]);
        }

        Set<Integer> visited = new HashSet<>();
        return bfs(visited, adj, 0);
    }

    private int bfs(Set<Integer> visited, List<List<Integer>> adj, int v1) {
        if (visited.contains(v1)) {
            return 0;
        }
        visited.add(v1);

        int changed = 0;
        for (Integer v2 : adj.get(v1)) {
            if (!visited.contains(v2) && v2 > 0) {
                changed += 1;
            }
            changed += bfs(visited, adj, Math.abs(v2));
        }

        return changed;
    }
}