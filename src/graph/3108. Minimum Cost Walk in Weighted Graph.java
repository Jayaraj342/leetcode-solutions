class Solution {
    public int[] minimumCost(int n, int[][] edges, int[][] query) {
        // parent array
        int[] parent = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }

        // union all edges & store AND results
        for (int[] edge : edges) {
            union(edge[0], edge[1], parent);
        }

        // for each component - calculate bitwise AND of all edges in the component and store in parent
        Map<Integer, Integer> map = new HashMap<>();
        for (int[] edge : edges) {
            int u = edge[0], v = edge[1], w = edge[2];
            int parentOfUV = find(parent, u);
            if (!map.containsKey(parentOfUV)) {
                map.put(parentOfUV, w);
            } else {
                map.put(parentOfUV, map.get(parentOfUV) & w);
            }
        }

        int size = query.length;
        int[] res = new int[size];
        Arrays.fill(res, -1);
        for (int i = 0; i < size; i++) {
            int u = query[i][0], v = query[i][1];
            int parentU = find(parent, u), parentV = find(parent, v);
            if (parentU == parentV) {
                res[i] = map.get(parentU);
            }
        }

        return res;
    }

    private void union(int u, int v, int[] parent) {
        int parentU = find(parent, u);
        int parentV = find(parent, v);
        if (parentU != parentV) {
            parent[parentU] = parentV;
        }
    }

    private int find(int[] parent, int x) {
        if (parent[x] != x) {
            parent[x] = find(parent, parent[x]);
        }

        return parent[x];
    }
}