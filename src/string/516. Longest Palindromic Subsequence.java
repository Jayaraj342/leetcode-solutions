class Solution {
    public int longestPalindromeSubseq(String s) {
        int n = s.length();
        int[][] dp = new int[n + 1][n + 1];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                dp[i + 1][j + 1] = s.charAt(i) == s.charAt(n - 1 - j) ? dp[i][j] + 1 : Math.max(dp[i][j + 1], dp[i + 1][j]);
            }
        }

        return dp[n][n];
    }
}

class Solution {
    Integer[][] memo;

    public int longestPalindromeSubseq(String s) {
        int n = s.length();
        memo = new Integer[n][n];

        return helper(s, 0, n - 1);
    }

    private int helper(String s, int i, int j) {
        if (memo[i][j] != null) {
            return memo[i][j];
        }

        if (i > j) return 0;
        if (i == j) return 1;

        if (s.charAt(i) == s.charAt(j)) {
            memo[i][j] = helper(s, i + 1, j - 1) + 2;
        } else {
            memo[i][j] = Math.max(helper(s, i + 1, j), helper(s, i, j - 1));
        }

        return memo[i][j];
    }
}