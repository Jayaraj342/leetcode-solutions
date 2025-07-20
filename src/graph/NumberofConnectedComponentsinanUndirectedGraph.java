import java.util.*;

// N + E, N + E
public class ConnectedComponents {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();        // number of nodes
        int edges = sc.nextInt();    // number of edges

        List<List<Integer>> adj = buildGraph(sc, n, edges);

        boolean[] visited = new boolean[n];

        int componentCount = 0;
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                dfs(i, adj, visited);
                componentCount++;
            }
        }

        System.out.println(componentCount);
        sc.close();
    }

    private static List<List<Integer>> buildGraph(Scanner sc, int n, int edges) {
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }

        for (int i = 0; i < edges; i++) {
            int u = sc.nextInt() - 1;
            int v = sc.nextInt() - 1;
            adj.get(u).add(v);
            adj.get(v).add(u);  // undirected graph
        }

        return adj;
    }

    private static void dfs(int node, List<List<Integer>> adj, boolean[] visited) {
        visited[node] = true;
        for (int neighbor : adj.get(node)) {
            if (!visited[neighbor]) {
                dfs(neighbor, adj, visited);
            }
        }
    }
}

import java.util.*;

// α(N) => Ammortized time - inverse Ackermann function, N
public class ConnectedComponentsUF {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();        // number of nodes
        int edges = sc.nextInt();    // number of edges

        UnionFind uf = new UnionFind(n);

        for (int i = 0; i < edges; i++) {
            int u = sc.nextInt() - 1;
            int v = sc.nextInt() - 1;
            uf.union(u, v);
        }

        System.out.println(uf.getCount());
        sc.close();
    }

    static class UnionFind {
        int[] parent;
        int count;

        public UnionFind(int n) {
            parent = new int[n];
            count = n; // Initially n components
            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
        }

        public int find(int x) {
            if (x != parent[x]) {
                parent[x] = find(parent[x]); // Path compression
            }
            return parent[x];
        }

        public void union(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);

            if (rootX != rootY) {
                parent[rootX] = rootY; // No rank comparison
                count--;
            }
        }

        public int getCount() {
            return count;
        }
    }
}
