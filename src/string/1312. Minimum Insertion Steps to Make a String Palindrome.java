class Solution {
    public int minInsertions(String s) {
        return s.length() - longestPalindromeSubseq(s);
    }

    private int longestPalindromeSubseq(String s) {
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