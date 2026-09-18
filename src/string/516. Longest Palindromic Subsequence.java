class Solution {
    public int longestPalindromeSubseq(String s) {
        int n = s.length();
        int[][] dp = new int[n][n];

        // Base case: single character is a palindrome of length 1
        for (int i = 0; i < n; i++) {
            dp[i][i] = 1;
        }

        // Fill by increasing substring length
        for (int len = 2; len <= n; len++) {
            for (int i = 0; i + len - 1 < n; i++) {
                int j = i + len - 1;

                if (s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = (len == 2) ? 2 : dp[i + 1][j - 1] + 2;
                } else {
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
                }
            }
        }

        return dp[0][n - 1];
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