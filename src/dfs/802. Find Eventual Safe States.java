// O(V + E)
class Solution {
    private Map<Integer, Boolean> memo;

    public List<Integer> eventualSafeNodes(int[][] graph) {
        int n = graph.length;
        memo = new HashMap<>();
        List<Integer> result = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            if (isSafe(i, graph, new HashSet<>())) {
                result.add(i);
            }
        }
        return result;
    }

    private boolean isSafe(int node, int[][] graph, Set<Integer> visiting) {
        if (memo.containsKey(node)) {
            return memo.get(node);
        }
        if (!visiting.add(node)) { // If already visiting, it's a cycle
            return false;
        }

        for (int neighbor : graph[node]) {
            if (!isSafe(neighbor, graph, visiting)) {
                memo.put(node, false);
                return false;
            }
        }

        visiting.remove(node);
        memo.put(node, true);
        return true;
    }
}

class Solution {
    int[] state;

    public List<Integer> eventualSafeNodes(int[][] graph) {
        int n = graph.length;
        state = new int[n];

        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (isSafe(i, graph)) {
                result.add(i);
            }
        }

        return result;
    }

    private boolean isSafe(int node, int[][] graph) {
        // Already processed
        if (state[node] == 2) return true;
        if (state[node] == 3) return false;

        // Cycle detected
        if (state[node] == 1) return false;

        // Mark as currently visiting
        state[node] = 1;

        for (int neighbor : graph[node]) {
            if (!isSafe(neighbor, graph)) {
                state[node] = 3;
                return false;
            }
        }

        // All neighbors are safe
        state[node] = 2;
        return true;
    }
}