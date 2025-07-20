// Q * (V + E), Q + V + E
class Solution {
    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        // Build the graph from the given equations and values
        Map<String, List<Edge>> adj = buildGraph(equations, values);

        // Evaluate each query using DFS
        double[] result = new double[queries.size()];
        for (int i = 0; i < queries.size(); i++) {
            String start = queries.get(i).get(0);
            String end = queries.get(i).get(1);

            // If either variable is not in the graph, the result is -1.0
            if (!adj.containsKey(start) || !adj.containsKey(end)) {
                result[i] = -1.0;
            } else {
                result[i] = dfs(adj, start, end, new HashSet<>());
            }
        }

        return result;
    }

    private Map<String, List<Edge>> buildGraph(List<List<String>> equations, double[] values) {
        Map<String, List<Edge>> graph = new HashMap<>();

        for (int i = 0; i < equations.size(); i++) {
            String u = equations.get(i).get(0);
            String v = equations.get(i).get(1);
            double w = values[i];

            // Add edge a -> b with weight
            graph.computeIfAbsent(u, k -> new ArrayList<>()).add(new Edge(v, w));

            // Add reverse-edge b -> a with inverse weight
            graph.computeIfAbsent(v, k -> new ArrayList<>()).add(new Edge(u, 1.0 / w));
        }

        return graph;
    }

    // DFS traversal to find the product from current to target
    private double dfs(Map<String, List<Edge>> graph, String curr, String target, Set<String> visited) {
        // Base case: if source and destination are same
        if (curr.equals(target)) return 1.0;

        visited.add(curr);

        for (Edge edge : graph.getOrDefault(curr, Collections.emptyList())) {
            if (!visited.contains(edge.to)) {
                // Recursively search from neighbor to target
                double product = dfs(graph, edge.to, target, visited);
                if (product != -1.0) {
                    return edge.weight * product;
                }
            }
        }

        // Path not found
        return -1.0;
    }

    // Helper class to represent a weighted edge in the graph
    private static class Edge {
        String to;
        double weight;

        Edge(String to, double weight) {
            this.to = to;
            this.weight = weight;
        }
    }
}