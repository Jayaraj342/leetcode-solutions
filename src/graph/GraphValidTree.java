// V + E, V + E => From adjacency list
class Solution {
    public boolean validTree(int n, int[][] edges) {
        List<Integer>[] adj = new List[n];
        // Never use Arrays.fill - as it'll copy one list to all array indexes
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }

        for (int[] edge : edges) {
            adj[edge[0]].add(edge[1]);
            adj[edge[1]].add(edge[0]);
        }

        Set<Integer> visited = new HashSet<>();
        return !haveLoop(adj, visited, 0, -1) && visited.size() == n;
    }

    private boolean haveLoop(List<Integer>[] adj, Set<Integer> visited, int v1, int prev) {
        if (visited.contains(v1)) {
            return true;
        }
        visited.add(v1);

        for (int v2 : adj[v1]) {
            if (v2 == prev) {
                continue;
            }
            if (haveLoop(adj, visited, v2, v1)) {
                return true;
            }
        }

        return false;
    }
}