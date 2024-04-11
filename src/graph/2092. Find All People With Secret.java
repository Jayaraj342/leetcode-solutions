class Solution {
    public List<Integer> findAllPeople(int n, int[][] meetings, int firstPerson) {
        Map<Integer, Map<Integer, List<Integer>>> map = new TreeMap<>(); // time -> peopleWithMeetings
        for (int[] meet : meetings) {
            int time = meet[2], u = meet[0], v = meet[1];
            Map<Integer, List<Integer>> adj = map.getOrDefault(time, new HashMap<>());

            List<Integer> listu = adj.getOrDefault(u, new ArrayList<>());
            listu.add(v);
            adj.put(u, listu);

            List<Integer> listv = adj.getOrDefault(v, new ArrayList<>());
            listv.add(u);
            adj.put(v, listv);

            map.put(time, adj);
        }
        Set<Integer> secrets = new HashSet<>();
        secrets.add(0);
        secrets.add(firstPerson);
        for (Map<Integer, List<Integer>> adj : map.values()) {
            Set<Integer> visited = new HashSet<>();
            for (int node : adj.keySet()) {
                if (secrets.contains(node)) {
                    dfs(adj, node, secrets, visited);
                }
            }
        }

        return new ArrayList<>(secrets);
    }

    private void dfs(Map<Integer, List<Integer>> adj, int src, Set<Integer> secrets, Set<Integer> visited) {
        if (!visited.contains(src)) {
            visited.add(src);
            secrets.add(src);
            for (int nei : adj.get(src)) {
                dfs(adj, nei, secrets, visited);
            }
        }
    }
}