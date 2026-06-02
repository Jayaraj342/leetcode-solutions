// https://chatgpt.com/c/69f764ea-55f4-8321-9a75-0d4c0c249ee8

class Solution {
    public int minSteps(int[][] grid) {
        int n = grid.length, m = grid[0].length;

        int sr = -1, sc = -1;
        int dr = -1, dc = -1;

        // Map each package cell to an index
        Map<Integer, Integer> packageIndex = new HashMap<>();
        int idx = 0;
        // Step 1: Scan grid
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 2) {
                    sr = i;
                    sc = j;
                } else if (grid[i][j] == 4) {
                    dr = i;
                    dc = j;
                } else if (grid[i][j] == 3) {
                    packageIndex.put(i * m + j, idx++);
                }
            }
        }

        int k = idx;
        int fullMask = (1 << k) - 1;

        // Edge case: no packages → simple BFS
        if (k == 0) {
            return bfsDistance(grid, sr, sc, dr, dc);
        }

        // visited[row][col][mask]
        boolean[][][] visited = new boolean[n][m][1 << k];

        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{sr, sc, 0, 0}); // r, c, mask, steps
        visited[sr][sc][0] = true;

        int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

        while (!queue.isEmpty()) {
            int[] curr = queue.remove();
            int r = curr[0], c = curr[1], mask = curr[2], steps = curr[3];

            // Correct termination
            if (r == dr && c == dc && mask == fullMask) {
                return steps;
            }

            for (int[] d : dirs) {
                int nr = r + d[0];
                int nc = c + d[1];

                if (nr < 0 || nc < 0 || nr >= n || nc >= m) continue;
                if (grid[nr][nc] == 1) continue; // obstacle

                int newMask = mask;

                // If it's a package, update mask
                if (grid[nr][nc] == 3) {
                    int key = nr * m + nc;
                    int pIdx = packageIndex.get(key);
                    newMask |= (1 << pIdx);
                }

                if (!visited[nr][nc][newMask]) {
                    visited[nr][nc][newMask] = true;
                    queue.offer(new int[]{nr, nc, newMask, steps + 1});
                }
            }
        }

        return -1; // unreachable
    }

    // Helper for no-package case
    private int bfsDistance(int[][] grid, int sr, int sc, int dr, int dc) {
        int n = grid.length, m = grid[0].length;
        boolean[][] visited = new boolean[n][m];

        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{sr, sc, 0});
        visited[sr][sc] = true;

        int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

        while (!q.isEmpty()) {
            int[] curr = q.poll();
            int r = curr[0], c = curr[1], steps = curr[2];

            if (r == dr && c == dc) return steps;

            for (int[] d : dirs) {
                int nr = r + d[0];
                int nc = c + d[1];

                if (nr < 0 || nc < 0 || nr >= n || nc >= m) continue;
                if (grid[nr][nc] == 1 || visited[nr][nc]) continue;

                visited[nr][nc] = true;
                q.offer(new int[]{nr, nc, steps + 1});
            }
        }

        return -1;
    }
}

class Solution {
    public int minSteps(int[][] grid) {
        int n = grid.length, m = grid[0].length;

        List<int[]> nodes = new ArrayList<>();

        // Step 1: collect start, packages, destination
        int[] start = null, dest = null;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 2) start = new int[]{i, j};
                else if (grid[i][j] == 3) nodes.add(new int[]{i, j});
                else if (grid[i][j] == 4) dest = new int[]{i, j};
            }
        }

        int k = nodes.size();

        // Node list: start + packages + dest
        List<int[]> all = new ArrayList<>();
        all.add(start);
        all.addAll(nodes);
        all.add(dest);

        int size = all.size();

        // Step 2: distance matrix
        int[][] dist = new int[size][size];

        for (int i = 0; i < size; i++) {
            int[] d = bfs(grid, all.get(i));
            for (int j = 0; j < size; j++) {
                int r = all.get(j)[0], c = all.get(j)[1];
                dist[i][j] = d[r * m + c];
                if (dist[i][j] == -1) return -1;
            }
        }

        // Step 3: TSP DP
        int[][] dp = new int[1 << k][k];
        for (int[] row : dp) Arrays.fill(row, Integer.MAX_VALUE);

        // Initialize
        for (int i = 0; i < k; i++) {
            dp[1 << i][i] = dist[0][i + 1];
        }

        // Fill DP
        for (int mask = 0; mask < (1 << k); mask++) {
            for (int i = 0; i < k; i++) {
                if ((mask & (1 << i)) == 0) continue;

                for (int j = 0; j < k; j++) {
                    if ((mask & (1 << j)) != 0) continue;

                    int newMask = mask | (1 << j);
                    dp[newMask][j] = Math.min(
                            dp[newMask][j],
                            dp[mask][i] + dist[i + 1][j + 1]
                    );
                }
            }
        }

        // Final step → go to destination
        int fullMask = (1 << k) - 1;
        int ans = Integer.MAX_VALUE;

        for (int i = 0; i < k; i++) {
            ans = Math.min(ans,
                    dp[fullMask][i] + dist[i + 1][k + 1]
            );
        }

        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

    // BFS returning distance from src to all cells
    private int[] bfs(int[][] grid, int[] src) {
        int n = grid.length, m = grid[0].length;
        int[] dist = new int[n * m];
        Arrays.fill(dist, -1);

        Queue<int[]> q = new LinkedList<>();
        q.offer(src);

        dist[src[0] * m + src[1]] = 0;

        int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int r = cur[0], c = cur[1];

            for (int[] d : dirs) {
                int nr = r + d[0], nc = c + d[1];

                if (nr < 0 || nc < 0 || nr >= n || nc >= m) continue;
                if (grid[nr][nc] == 1) continue;

                if (dist[nr * m + nc] == -1) {
                    dist[nr * m + nc] = dist[r * m + c] + 1;
                    q.offer(new int[]{nr, nc});
                }
            }
        }

        return dist;
    }
}