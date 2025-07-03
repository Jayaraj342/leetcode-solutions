class Solution {
    private Integer[][] memo;

    public int numDistinct(String s, String t) {
        memo = new Integer[s.length()][t.length()];
        return dfs(s, t, 0, 0);
    }

    private int dfs(String s, String t, int sIdx, int tIdx) {
        if (tIdx == t.length()) {
            // All of t is matched
            return 1;
        }
        if (sIdx == s.length()) {
            // Reached the end of s without matching all of t
            return 0;
        }
        if (memo[sIdx][tIdx] != null) {
            return memo[sIdx][tIdx];
        }

        int count = dfs(s, t, sIdx + 1, tIdx);
        if (s.charAt(sIdx) == t.charAt(tIdx)) {
            count += dfs(s, t, sIdx + 1, tIdx + 1);
        }

        memo[sIdx][tIdx] = count;
        return count;
    }
}

class Solution {
    public int numDistinct(String s, String t) {
        int m = s.length(), n = t.length();

        if (n == 0) return 1;
        if (m == 0) return 0;

        // dp[j] denotes number of subsequences of first j chars of t in first i chars of s
        long[][] dp = new long[m + 1][n + 1];

        // An empty pattern is a subsequence of any prefix of s
        for (int i = 0; i <= m; i++) {
            dp[i][0] = 1;
        }

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                dp[i][j] = dp[i - 1][j];

                if (s.charAt(i - 1) == t.charAt(j - 1)) {
                    dp[i][j] += dp[i - 1][j - 1];
                }
            }
        }

        return (int) dp[m][n];
    }
}

// TODO
class Solution {
    public int numDistinct(String s, String t) {
        int m = s.length(), n = t.length();

        if (n == 0) return 1;
        if (m == 0) return 0;

        long[] dp = new long[n + 1];
        dp[0] = 1; // An empty pattern is a subsequence of any prefix of s

        for (int i = 1; i <= m; i++) {
            // Loop backwards to avoid overwriting dp[j-1] we need for this iteration
            for (int j = n; j >= 1; j--) {
                if (s.charAt(i - 1) == t.charAt(j - 1)) {
                    dp[j] += dp[j - 1];
                }
            }
        }

        return (int) dp[n];
    }
}