// n, n
class Solution {
    public List<Integer> findAllPeople(int n, int[][] meetings, int firstPerson) {
        int[] parent = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            parent[i] = i;
        }
        parent[firstPerson] = 0;

        int maxTime = 0;
        for (int[] meet : meetings) {
            maxTime = Math.max(maxTime, meet[2]);
        }

        List<int[]>[] timeArray = new List[maxTime + 1];
        for (int[] meet : meetings) {
            if (timeArray[meet[2]] == null) {
                timeArray[meet[2]] = new ArrayList<>();
            }
            timeArray[meet[2]].add(meet);
        }

        for (int i = 1; i < timeArray.length; i++) {
            if (timeArray[i] != null) {
                for (int j = 0; j < timeArray[i].size(); j++) {
                    int u = timeArray[i].get(j)[0];
                    int v = timeArray[i].get(j)[1];
                    union(u, v, parent);
                }
                for (int j = 0; j < timeArray[i].size(); j++) {
                    int u = timeArray[i].get(j)[0];
                    int v = timeArray[i].get(j)[1];
                    if (find(u, parent) != 0) {// reset parent - if someone is unioned before, should not be considered now
                        parent[u] = u;
                    }
                    if (find(v, parent) != 0) {
                        parent[v] = v;
                    }
                }
            }
        }

        List<Integer> results = new ArrayList<>();
        for (int i = 0; i < parent.length; i++) {
            if (parent[i] == 0) {
                results.add(i);
            }
        }

        return results;
    }

    // union such that parent of every one sharing key is 0 (that's why comparison)
    private void union(int u, int v, int[] parent) {
        int uRoot = find(u, parent);
        int vRoot = find(v, parent);
        if (uRoot < vRoot) {
            parent[vRoot] = uRoot;
        } else {
            parent[uRoot] = vRoot;
        }
    }

    private int find(int x, int[] parent) {
        if (parent[x] != x) {
            parent[x] = find(parent[x], parent);
        }

        return parent[x];
    }
}

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
