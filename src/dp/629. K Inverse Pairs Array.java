// TC : O(n*k) - SC can be reduced
class Solution {
    public int kInversePairs(int n, int k) {
        int MOD = 1000_000_007;
        int[][] dp = new int[n + 1][k + 1];
        dp[0][0] = 1;
        for (int i = 1; i <= n; i++) {
            int window = 0;
            for (int j = 0; j <= k; j++) {
                if (j >= i) {
                    window = (window - dp[i - 1][j - i] + MOD) % MOD;
                }
                window = (window + dp[i - 1][j]) % MOD;
                dp[i][j] = window;
            }
        }

        return dp[n][k];
    }
}

class Solution {
    public int kInversePairs(int n, int k) {
        int MOD = 1_000_000_007;
        int[][] dp = new int[n + 1][k + 1];

        dp[0][0] = 1;

        for (int i = 1; i <= n; i++) {
            dp[i][0] = 1; // Only one way to form 0 inverse pairs: sorted order
            for (int j = 1; j <= k; j++) {
                int val = (dp[i][j - 1] + dp[i - 1][j]) % MOD;
                if (j >= i) {
                    val = (val - dp[i - 1][j - i] + MOD) % MOD;
                }
                dp[i][j] = val;
            }
        }

        return dp[n][k];
    }
}

// TC : O(n*n*k) - Time limit exceeded
class Solution {
    Integer[][] memo;

    public int kInversePairs(int n, int k) {
        memo = new Integer[n + 1][k + 1];
        return dfs(n, k);
    }

    private int dfs(int n, int k) {
        if (n == 0) {
            return k == 0 ? 1 : 0;
        }
        if (k < 0) {
            return 0;
        }
        if (memo[n][k] != null) {
            return memo[n][k];
        }

        int res = 0;
        for (int i = 0; i < n; i++) {
            res = (res + dfs(n - 1, k - i)) % 1000_000_007;
        }
        memo[n][k] = res;

        return res;
    }
}