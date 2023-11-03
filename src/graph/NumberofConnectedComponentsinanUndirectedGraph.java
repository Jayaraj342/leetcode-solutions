import java.util.*;

class TestClass {
    public static void main(String args[]) throws Exception {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        int edges = sc.nextInt();
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }
        for (int i = 0; i < edges; i++) {
            int v1 = sc.nextInt() - 1;
            int v2 = sc.nextInt() - 1;
            adj.get(v1).add(v2);
            adj.get(v2).add(v1);
        }

        Set<Integer> visited = new HashSet<>();
        int count = 0;
        for (int v = 0; v < n; v++) {
            if (!visited.contains(v)) {
                dfs(adj, v, visited);
                count++;
            }
        }

        System.out.println(count);
    }

    private static void dfs(List<List<Integer>> adj, int v, Set<Integer> visited) {
        if (visited.contains(v)) {
            return;
        }
        visited.add(v);
        for (Integer neighbor : adj.get(v)) {
            dfs(adj, neighbor, visited);
        }
    }
}

import java.util.*;
import java.util.stream.IntStream;

class TestClass {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        int edgesCount = sc.nextInt();
        List<int[]> edges = new ArrayList<>();
        for (int i = 0; i < edgesCount; i++) {
            int v1 = sc.nextInt() - 1;
            int v2 = sc.nextInt() - 1;
            edges.add(new int[]{v1, v2});
        }

        int[] parent = IntStream.range(0, n).toArray();

        int[] rank = new int[n];
        Arrays.fill(rank, 1);

        int res = n;
        for (int[] edge : edges) {
            res -= union(edge[0], edge[1], parent, rank);
        }

        System.out.println(res);
    }

    private static int union(int v1, int v2, int[] parent, int[] rank) {
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
            return 1;
        }
        return 0;
    }

    private static int find(int v, int[] parent) {
        if (parent[v] != v) {
            parent[v] = parent[parent[v]];
            return find(parent[v], parent);
        }
        return v;
    }
}