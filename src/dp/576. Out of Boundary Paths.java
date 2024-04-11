// SC : O(m.n)
class Solution {
    public int findPaths(int m, int n, int maxMove, int startRow, int startColumn) {
        int MOD = 1000_000_007;
        int[][] dp = new int[m][n];
        for (int moves = 0; moves < maxMove; moves++) {
            int[][] temp = new int[m][n];
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (i + 1 == m) {
                        temp[i][j] = (temp[i][j] + 1) % MOD;
                    } else {
                        temp[i][j] = (temp[i][j] + dp[i + 1][j]) % MOD;
                    }
                    if (j + 1 == n) {
                        temp[i][j] = (temp[i][j] + 1) % MOD;
                    } else {
                        temp[i][j] = (temp[i][j] + dp[i][j + 1]) % MOD;
                    }
                    if (i - 1 == -1) {
                        temp[i][j] = (temp[i][j] + 1) % MOD;
                    } else {
                        temp[i][j] = (temp[i][j] + dp[i - 1][j]) % MOD;
                    }
                    if (j - 1 == -1) {
                        temp[i][j] = (temp[i][j] + 1) % MOD;
                    } else {
                        temp[i][j] = (temp[i][j] + dp[i][j - 1]) % MOD;
                    }
                }
            }
            dp = temp;
        }

        return dp[startRow][startColumn];
    }
}

// SC : O(m.n.k)
class Solution {
    Integer[][][] memo;
    int MOD = 1000_000_007;

    public int findPaths(int m, int n, int maxMove, int startRow, int startColumn) {
        memo = new Integer[m][n][maxMove + 1];
        return dfs(startRow, startColumn, m, n, maxMove);
    }

    private int dfs(int i, int j, int m, int n, int moves) {
        if (i == -1 || i == m || j == -1 || j == n) {
            return 1;
        }
        if (moves == 0) {
            return 0;
        }
        if (memo[i][j][moves] != null) {
            return memo[i][j][moves];
        }

        memo[i][j][moves] = (dfs(i, j + 1, m, n, moves - 1) + dfs(i + 1, j, m, n, moves - 1)) % MOD +
                (dfs(i, j - 1, m, n, moves - 1) + dfs(i - 1, j, m, n, moves - 1)) % MOD;
        memo[i][j][moves] %= MOD;

        return memo[i][j][moves];
    }
}