class Solution {
    int m, n;
    int[] dir;
    Integer[][][] memo;

    public int cherryPickup(int[][] grid) {
        m = grid.length;
        n = grid[0].length;
        dir = new int[]{1, 0, -1};
        int[][] dp = new int[n][n];
        for (int r = m - 1; r >= 0; r--) {
            int[][] temp = new int[n][n];
            int[] currRow = grid[r];
            for (int c1 = 0; c1 < n - 1; c1++) {
                for (int c2 = c1 + 1; c2 < n; c2++) {
                    int max = 0;
                    for (int i : dir) {
                        for (int j : dir) {
                            int dc1 = c1 + i, dc2 = c2 + j;
                            max = Math.max(max, dc1 >= 0 && dc2 < n ? dp[dc1][dc2] : 0);
                        }
                    }
                    temp[c1][c2] = currRow[c1] + currRow[c2] + max;
                }
            }
            dp = temp;
        }

        return dp[0][n - 1];
    }
}

class Solution {
    int m, n;
    int[] dir;
    Integer[][][] memo;

    public int cherryPickup(int[][] grid) {
        m = grid.length;
        n = grid[0].length;
        dir = new int[] {1, 0, -1};
        memo = new Integer[m][n][n];

        return dfs(grid, 0, n - 1, 0);
    }

    private int dfs(int[][] grid, int c1, int c2, int r) {
        if (c1 == c2 || Math.min(c1, c2) == -1 || Math.max(c1, c2) == n || r == m) {
            return 0;
        }
        if (memo[r][c1][c2] != null) {
            return memo[r][c1][c2];
        }

        int res = 0;
        for (int i : dir) {
            for (int j : dir) {
                res = Math.max(res, grid[r][c1] + grid[r][c2] + dfs(grid, c1 + i, c2 + j, r + 1));
            }
        }

        memo[r][c1][c2] = res;
        return res;
    }
}