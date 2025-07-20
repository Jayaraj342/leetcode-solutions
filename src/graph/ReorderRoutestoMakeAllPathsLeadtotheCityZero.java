// E, E
class Solution {
    public int minReorder(int n, int[][] connections) {
        List<Integer>[] adj = new List[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }

        // Build the adjacency list with direction info
        for (int[] con : connections) {
            int from = con[0], to = con[1];
            adj[from].add(to);    // original direction
            adj[to].add(-from);   // reverse edge (mark with negative)
        }

        Set<Integer> visited = new HashSet<>();
        return dfs(0, adj, visited);
    }

    private int dfs(int curr, List<Integer>[] adj, Set<Integer> visited) {
        visited.add(curr);
        int changes = 0;

        for (int nei : adj[curr]) {
            int next = Math.abs(nei);
            if (visited.contains(next)) continue;

            // If edge is forward (original direction), we need to reverse it
            if (nei > 0) {
                changes++;
            }

            changes += dfs(next, adj, visited);
        }

        return changes;
    }
}
