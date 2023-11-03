class Solution {
    public int minReorder(int n, int[][] connections) {
        List<Integer>[] adj = new List[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList();
        }
        for (int[] con : connections) {
            adj[con[0]].add(con[1]);
            adj[con[1]].add(-con[0]);
        }

        Set<Integer> visited = new HashSet<>();
        return dfs(adj, 0, visited);
    }

    private int dfs(List<Integer>[] adj, int curr, Set<Integer> visited) {
        visited.add(curr);

        int count = 0;
        for (int nei : adj[curr]) {
            if (!visited.contains(Math.abs(nei))) {
                if (nei > 0) {
                    count += 1;
                }
                count += dfs(adj, Math.abs(nei), visited);
            }
        }

        return count;
    }
}