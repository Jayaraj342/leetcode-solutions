// m * n^2, n
class Solution {
    public int maxConsistentColumns(int[][] grid, int limit) {
        int m = grid.length, n = grid[0].length;

        int[] dp = new int[n];
        Arrays.fill(dp, 1);
        for (int c1 = 0; c1 < n; c1++) {
            for (int c2 = c1 + 1; c2 < n; c2++) {
                boolean compatible = isCompatible(grid, c1, c2, m, limit);

                if (compatible) {
                    dp[c2] = Math.max(dp[c2], 1 + dp[c1]);
                }
            }
        }

        int res = 1;
        for (int i = 1; i < n; i++) {
            res = Math.max(res, dp[i]);
        }

        return res;
    }

    private boolean isCompatible(int[][] grid, int c1, int c2, int m, int limit) {
        for (int r = 0; r < m; r++) {
            if (Math.abs(grid[r][c1] - grid[r][c2]) > limit) {
                return false;
            }
        }

        return true;
    }
}

// m * n^2, n^2
class Solution {
    public int maxConsistentColumns(int[][] grid, int limit) {
        int m = grid.length, n = grid[0].length;

        boolean[][] compatible = new boolean[n][n];
        for (int c1 = 0; c1 < n; c1++) {
            compatible[c1][c1] = true;

            for (int c2 = c1 + 1; c2 < n; c2++) {
                compatible[c1][c2] = isCompatible(grid, c1, c2, m, limit);
            }
        }

        int[] dp = new int[n];
        Arrays.fill(dp, 1);

        int res = 1;
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (compatible[j][i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            res = Math.max(res, dp[i]);
        }

        return res;
    }

    private boolean isCompatible(int[][] grid, int c1, int c2, int m, int limit) {
        for (int r = 0; r < m; r++) {
            if (Math.abs(grid[r][c1] - grid[r][c2]) > limit) {
                return false;
            }
        }

        return true;
    }
}