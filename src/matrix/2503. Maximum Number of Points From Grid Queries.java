// TC : O(m * n * log(m * n))
class Solution {
    int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public int[] maxPoints(int[][] grid, int[] queries) {
        int m = grid.length, n = grid[0].length, k = queries.length;

        // Store queries with their original indices
        List<int[]> sortedQueries = new ArrayList<>(); // [query, index]
        for (int i = 0; i < k; i++) {
            sortedQueries.add(new int[]{queries[i], i});
        }
        sortedQueries.sort(Comparator.comparingInt(a -> a[0]));

        PriorityQueue<int[]> minHeap = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        minHeap.add(new int[]{grid[0][0], 0, 0}); // [gridVal, i, j]

        boolean[][] visited = new boolean[m][n];
        visited[0][0] = true;

        int[] res = new int[k];
        int cnt = 0;

        for (int idx = 0; idx < k; idx++) {
            int query = sortedQueries.get(idx)[0];
            // Expand reachable area until the smallest grid value is at least the query value
            while (!minHeap.isEmpty() && minHeap.peek()[0] < query) {
                int[] last = minHeap.remove();
                int i = last[1], j = last[2];
                cnt++;

                for (int[] dir : directions) {
                    int ni = i + dir[0], nj = j + dir[1];
                    if (ni >= 0 && nj >= 0 && ni < m && nj < n && !visited[ni][nj]) {
                        minHeap.add(new int[]{grid[ni][nj], ni, nj});
                        visited[ni][nj] = true;
                    }
                }
            }
            int oldIdx = sortedQueries.get(idx)[1];
            res[oldIdx] = cnt;
        }

        return res;
    }
}

// TC : O(k * m * n) => Timeout
class Solution {
    int m, n;

    public int[] maxPoints(int[][] grid, int[] queries) {
        m = grid.length;
        n = grid[0].length;
        int k = queries.length;
        int[] res = new int[k];
        for (int i = 0; i < k; i++) {
            res[i] = dfs(grid, 0, 0, new HashSet<>(), queries[i]);
        }

        return res;
    }

    private int dfs(int[][] grid, int i, int j, Set<String> visited, int query) {
        if (i < 0 || j < 0 || i >= m || j >= n) {
            return 0;
        }
        String key = i + "," + j;
        if (visited.contains(key) || grid[i][j] >= query) {
            return 0;
        }
        visited.add(key);

        return 1 + dfs(grid, i + 1, j, visited, query) + dfs(grid, i - 1, j, visited, query)
                + dfs(grid, i, j + 1, visited, query) + dfs(grid, i, j - 1, visited, query);
    }
}