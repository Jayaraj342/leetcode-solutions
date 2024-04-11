class Solution {
    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        Map<String, List<Node>> adj = new HashMap<>();
        int n = equations.size();
        for (int i = 0; i < n; i++) {
            String u = equations.get(i).get(0), v = equations.get(i).get(1);
            double w = values[i];
            List<Node> listNum = adj.getOrDefault(u, new ArrayList<>());
            listNum.add(new Node(v, w));
            adj.put(u, listNum);

            List<Node> listDenom = adj.getOrDefault(v, new ArrayList<>());
            listDenom.add(new Node(u, 1 / w));
            adj.put(v, listDenom);
        }

        int m = queries.size();
        double[] res = new double[m];
        for (int i = 0; i < m; i++) {
            String u = queries.get(i).get(0), v = queries.get(i).get(1);
            res[i] = adj.containsKey(u) && adj.containsKey(v) ? dfs(adj, u, v, new HashSet<>()) : -1;
        }

        return res;
    }

    private double dfs(Map<String, List<Node>> adj, String src, String tgt, Set<String> visited) {
        if (visited.contains(src)) {
            return -1;
        }
        visited.add(src);
        if (Objects.equals(src, tgt)) {
            return 1;
        }

        for (Node nei : adj.getOrDefault(src, new ArrayList<>())) {
            double temp = dfs(adj, nei.v, tgt, visited);
            if (temp != -1) {
                return nei.w * temp;
            }
        }

        return -1;
    }

    static class Node {
        String v;
        double w;

        Node(String v, double w) {
            this.v = v;
            this.w = w;
        }
    }
}