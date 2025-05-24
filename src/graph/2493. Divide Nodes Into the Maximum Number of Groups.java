class Solution {
    public int magnificentSets(int n, int[][] edges) {
        // create adjacency list
        List<Integer>[] adj = new List[n + 1];
        for (int i = 1; i <= n; i++) {
            adj[i] = new ArrayList<>();
        }
        for (int[] edge : edges) {
            int u = edge[0], v = edge[1];
            adj[u].add(v);
            adj[v].add(u);
        }

        Set<Integer> visited = new HashSet<>();
        int totalGroups = 0;
        for (int i = 1; i <= n; i++) {
            if (visited.contains(i)) {
                continue;
            }

            visited.add(i);
            List<Integer> component = getConnectedComponent(adj, i, visited);
            // for each node as src, find max length groups that can be made
            // because we don't know from which node we are starting ex1 for ref
            int maxGroupSize = 0;
            for (int src : component) {
                int groupSize = bfsMaxDepth(adj, src);
                if (groupSize == -1) {
                    return -1;// Odd-length cycle detected
                }
                maxGroupSize = Math.max(maxGroupSize, groupSize);
            }
            totalGroups += maxGroupSize;
        }

        return totalGroups;
    }

    private List<Integer> getConnectedComponent(List<Integer>[] adj, int src, Set<Integer> visited) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(src);

        List<Integer> component = new ArrayList<>();
        while (!queue.isEmpty()) {
            int last = queue.remove();
            component.add(last);
            for (int nei : adj[last]) {
                if (visited.contains(nei)) {
                    continue;
                }
                visited.add(nei);
                queue.add(nei);
            }
        }

        return component;
    }

    private int bfsMaxDepth(List<Integer>[] adj, int src) {
        Map<Integer, Integer> dist = new HashMap<>();

        int maxLen = 0;
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{src, 1});
        dist.put(src, 1);
        while (!queue.isEmpty()) {
            int[] last = queue.remove();
            int node = last[0], len = last[1];
            maxLen = len;

            for (int nei : adj[node]) {
                if (dist.containsKey(nei)) {
                    // one way to check if loop is odd length - keep dist map for this reason
                    if (dist.get(nei) == len) {
                        return -1;
                    }
                    continue;
                }
                dist.put(nei, len + 1);
                queue.add(new int[]{nei, len + 1});
            }
        }

        return maxLen;
    }
}