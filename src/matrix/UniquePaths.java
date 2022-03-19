class Solution {
    Integer[][] memo;

    public int uniquePaths(int m, int n) {
        memo = new Integer[m][n];
        return dfs(m, n, 0, 0);
    }

    private int dfs(int m, int n, int i, int j) {
        if (i == m - 1 && j == n - 1) {
            return 1;
        }
        boolean inBounds = i < m && j < n;
        if (!inBounds) {
            return 0;
        }

        if (memo[i][j] != null) {
            return memo[i][j];
        }

        memo[i][j] = dfs(m, n, i + 1, j) + dfs(m, n, i, j + 1);

        return memo[i][j];
    }
}