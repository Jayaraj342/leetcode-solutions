// m * n, m * n
class Solution {
    private int m, n;
    private int[][] memo;
    private static final int[][] DIRS = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    public int longestIncreasingPath(int[][] matrix) {
        if (matrix == null || matrix.length == 0) return 0;

        m = matrix.length;
        n = matrix[0].length;
        memo = new int[m][n];

        int longest = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                longest = Math.max(longest, dfs(matrix, i, j));
            }
        }
        return longest;
    }

    private int dfs(int[][] matrix, int i, int j) {
        if (memo[i][j] != 0) return memo[i][j];

        int best = 1; // At least the current cell
        for (int[] d : DIRS) {
            int ni = i + d[0], nj = j + d[1];
            if (ni >= 0 && nj >= 0 && ni < m && nj < n && matrix[ni][nj] > matrix[i][j]) {
                best = Math.max(best, 1 + dfs(matrix, ni, nj));
            }
        }

        return memo[i][j] = best;
    }
}

// iterative - Using Kahn's algorithm for topological sort (helps with recursice space)
class Solution {
    private static final int[][] DIRS = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    public int longestIncreasingPath(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        int[][] outdegree = new int[m][n];

        // Step 1: Compute outdegree for each cell
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                for (int[] d : DIRS) {
                    int ni = i + d[0], nj = j + d[1];
                    if (ni >= 0 && nj >= 0 && ni < m && nj < n && matrix[ni][nj] > matrix[i][j]) {
                        outdegree[i][j]++;
                    }
                }
            }
        }

        // Step 2: Start from cells with outdegree 0 (local maxima)
        Queue<int[]> queue = new ArrayDeque<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (outdegree[i][j] == 0) {
                    queue.offer(new int[]{i, j});
                }
            }
        }

        // Step 3: BFS layer by layer
        int pathLength = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            pathLength++;  // Each level = one step in path

            for (int s = 0; s < size; s++) {
                int[] cell = queue.poll();
                int i = cell[0], j = cell[1];

                for (int[] d : DIRS) {
                    int ni = i + d[0], nj = j + d[1];
                    if (ni >= 0 && nj >= 0 && ni < m && nj < n && matrix[ni][nj] < matrix[i][j]) {
                        if (--outdegree[ni][nj] == 0) {
                            queue.offer(new int[]{ni, nj});
                        }
                    }
                }
            }
        }

        return pathLength;
    }
}