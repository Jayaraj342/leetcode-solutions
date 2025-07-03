// Kahn's algorithm for topological sorting order
// V + E, V + E
class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        List<Integer>[] adj = new List[numCourses];
        for (int i = 0; i < numCourses; i++) {
            adj[i] = new ArrayList<>();
        }

        int[] inDegree = new int[numCourses];
        for (int[] edge : prerequisites) {
            adj[edge[1]].add(edge[0]); // prereq -> course
            inDegree[edge[0]]++;
        }

        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {// Add all nodes with in-degree 0 to a queue — these have no prerequisites
            if (inDegree[i] == 0) queue.add(i);
        }

        int completed = 0;
        while (!queue.isEmpty()) {
            int course = queue.remove();
            completed++;
            for (int next : adj[course]) {
                if (--inDegree[next] == 0) {// pre-decrement
                    queue.add(next);
                }
            }
        }

        return completed == numCourses;
    }
}

// directed graph - so, no prev tracking is required
// V + E => not V * (V + E), bacause redundant computations are removed using adj[v] = new ArrayList<>();
// V + E for space
class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        List<Integer>[] adj = new List[numCourses];
        for (int i = 0; i < numCourses; i++) {
            adj[i] = new ArrayList<>();
        }

        for (int[] pr : prerequisites) {
            adj[pr[1]].add(pr[0]);// reverse also works, but this is correct way i.e 0 -> 1 also works
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

        adj[v] = new ArrayList<>();// remove redandant computations
        return true;
    }
}