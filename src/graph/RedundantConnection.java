// O(n)
class Solution {
    public int[] findRedundantConnection(int[][] edges) {
        int n = edges.length;

        int[] parent = IntStream.range(0, n).toArray();

        int[] rank = new int[n];
        Arrays.fill(rank, 1);

        int[] res = {};
        for (int[] edge : edges) {
            if (union(edge[0] - 1, edge[1] - 1, parent, rank)) {
                res = edge;
            }
        }

        return res;
    }

    private boolean union(int v1, int v2, int[] parent, int[] rank) {
        int p1 = find(v1, parent);
        int p2 = find(v2, parent);
        if (p1 != p2) {
            if (rank[p1] > rank[p2]) {
                parent[p2] = p1;
                rank[p1] += rank[p2];
            } else {
                parent[p1] = p2;
                rank[p2] += rank[p1];
            }
            return false;
        }
        return true;
    }

    private int find(int v, int[] parent) {
        if (parent[v] != v) {
            parent[v] = parent[parent[v]];
            return find(parent[v], parent);
        }
        return v;
    }
}

// O(n^2)
class Solution {
    Set<Integer> visited = new HashSet<>();

    public int[] findRedundantConnection(int[][] edges) {
        Map<Integer, List<Integer>> adj = new HashMap<>();

        for (int[] edge : edges) {
            visited.clear();
            if (dfs(adj, edge[0], edge[1])) {
                return edge;
            }
            adj.putIfAbsent(edge[0], new ArrayList<>());
            adj.get(edge[0]).add(edge[1]);

            adj.putIfAbsent(edge[1], new ArrayList<>());
            adj.get(edge[1]).add(edge[0]);
        }

        return null;
    }

    private boolean dfs(Map<Integer, List<Integer>> adj, int src, int dest) {
        if (src == dest) {
            return true;
        }
        visited.add(src);

        for (int nei : adj.getOrDefault(src, List.of())) {
            if (!visited.contains(nei) && dfs(adj, nei, dest)) {
                return true;
            }
        }

        return false;
    }
}