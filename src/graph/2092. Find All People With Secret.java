// m.log(m), m
class Solution {
    public List<Integer> findAllPeople(int n, int[][] meetings, int firstPerson) {
        // time -> graph of people meeting at that time
        Map<Integer, Map<Integer, List<Integer>>> timeToGraph = new TreeMap<>();

        // Build time-based graphs
        for (int[] meeting : meetings) {
            int u = meeting[0], v = meeting[1], time = meeting[2];
            Map<Integer, List<Integer>> graph = timeToGraph.computeIfAbsent(time, k -> new HashMap<>());
            graph.computeIfAbsent(u, k -> new ArrayList<>()).add(v);
            graph.computeIfAbsent(v, k -> new ArrayList<>()).add(u);
        }

        Set<Integer> secretHolders = new HashSet<>();
        secretHolders.add(0);
        secretHolders.add(firstPerson);

        for (Map<Integer, List<Integer>> graph : timeToGraph.values()) {
            Set<Integer> visited = new HashSet<>();

            // Try to propagate secret only from known secret holders
            for (int person : graph.keySet()) {
                if (secretHolders.contains(person) && !visited.contains(person)) {
                    dfs(graph, person, secretHolders, visited);
                }
            }
        }

        return new ArrayList<>(secretHolders);
    }

    private void dfs(Map<Integer, List<Integer>> graph, int person, Set<Integer> secretHolders, Set<Integer> visited) {
        visited.add(person);
        secretHolders.add(person);
        for (int neighbor : graph.getOrDefault(person, Collections.emptyList())) {
            if (!visited.contains(neighbor)) {
                dfs(graph, neighbor, secretHolders, visited);
            }
        }
    }
}
