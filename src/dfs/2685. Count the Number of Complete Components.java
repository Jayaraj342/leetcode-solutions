class Solution {
    public int countCompleteComponents(int n, int[][] edges) {
        // create adjacency list
        List<Integer>[] adj = new List[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }
        for (int[] edge : edges) {
            int u = edge[0], v = edge[1];
            adj[u].add(v);
            adj[v].add(u);
        }

        // Do DFS and get all vertices of a connected component
        Set<Integer> visited = new HashSet<>();
        int res = 0;
        for (int i = 0; i < n; i++) {
            if (!visited.contains(i)) {
                List<Integer> component = new ArrayList<>();
                dfs(i, adj, component, visited);

                boolean isCurrComponentComplete = true;
                for (int vertex : component) {
                    if (adj[vertex].size() != component.size() - 1) {
                        isCurrComponentComplete = false;
                        break;
                    }
                }
                if (isCurrComponentComplete) {
                    res++;
                }
            }
        }

        return res;
    }

    private void dfs(int curr, List<Integer>[] adj, List<Integer> component, Set<Integer> visited) {
        if (visited.contains(curr)) {
            return;
        }
        visited.add(curr);
        component.add(curr);

        for (int nei : adj[curr]) {
            dfs(nei, adj, component, visited);
        }
    }
}