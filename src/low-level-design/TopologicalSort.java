class TopologicalSort {
    public static void main(String[] args) {
        // c -> [b, e] (c should come before b and e)
        // b -> [e]
        // e -> [d]
        // a -> []
        // sorted -> a,c,b,e,d

        Map<Character, List<Character>> adj = new HashMap<>();
        adj.put('b', List.of('e'));
        adj.put('c', List.of('b', 'e'));
        adj.put('e', List.of('d'));
        adj.put('d', List.of());
        adj.put('a', List.of());

        Set<Character> visited = new HashSet<>();
        List<Character> result = new ArrayList<>();

        // a, cannot be last, because letters with no priority should go in normal lexicographical order
        List<Character> allVertexes = new ArrayList<>(adj.keySet());
        allVertexes.sort(Collections.reverseOrder());
        for (char v : allVertexes) {
            dfs(adj, v, visited, result);
        }

        Collections.reverse(result);
        System.out.println(result);
    }

    private static void dfs(Map<Character, List<Character>> adj, char v, Set<Character> visited, List<Character> result) {
        if (visited.contains(v)) {
            return;
        }

        visited.add(v);
        for (char nei : adj.get(v)) {
            dfs(adj, nei, visited, result);
        }
        result.add(v);
    }
}