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