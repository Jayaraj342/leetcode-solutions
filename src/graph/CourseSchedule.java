class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        List<Integer>[] adj = new List[numCourses];
        for (int i = 0; i < numCourses; i++) {
            adj[i] = new ArrayList<>();
        }

        for (int[] pr : prerequisites) {
            adj[pr[0]].add(pr[1]);
        }

        Set<Integer> visited = new HashSet<>();
        for (int i = 0; i < numCourses; i++) {
            if (!dfs(adj, i, visited)) {
                return false;
            }
        }

        return true;
    }

    private boolean dfs(List<Integer>[] adj, int v, Set<Integer> visited) {
        if (visited.contains(v)) {
            return false;
        }
        if (adj[v].isEmpty()) {
            return true;
        }

        visited.add(v);
        for (int pr : adj[v]) {
            if (!dfs(adj, pr, visited)) {
                return false;
            }
        }
        visited.remove(v);

        adj[v] = new ArrayList<>();
        return true;
    }
}