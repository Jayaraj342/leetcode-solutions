class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        List<Integer>[] adj = new List[numCourses];
        for (int i : IntStream.range(0, numCourses).toArray()) {
            adj[i] = new ArrayList<>();
        }
        for(int[] edge : prerequisites) {
            adj[edge[0]].add(edge[1]);
        }

        Set<Integer> visited = new HashSet<>();
        for (int i : IntStream.range(0, numCourses).toArray()) {
            if(!dfs(adj, i, visited)) {
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

        for (int nei : adj[v]) {
            if (!dfs(adj, nei, visited)) {
                return false;
            }
        }
        visited.remove(v);
        adj[v] = new ArrayList<>();

        return true;
    }
}