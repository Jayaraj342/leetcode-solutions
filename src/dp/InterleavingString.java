class Solution {
    Boolean[][] memo;

    public boolean isInterleave(String s1, String s2, String s3) {
        int n1 = s1.length(), n2 = s2.length();
        if (n1 + n2 != s3.length()) {
            return false;
        }

        memo = new Boolean[n1 + 1][n2 + 1];
        return dfs(s1, s2, s3, 0, 0);
    }

    private boolean dfs(String s1, String s2, String s3, int i, int j) {
        int n1 = s1.length(), n2 = s2.length();
        if (i == n1 && j == n2) {
            return true;
        }
        if (memo[i][j] != null) {
            return memo[i][j];
        }

        boolean res = false;
        if (i < n1 && s1.charAt(i) == s3.charAt(i + j)) {
            res = dfs(s1, s2, s3, i + 1, j);
        }
        if (j < n2 && s2.charAt(j) == s3.charAt(i + j)) {
            res = res || dfs(s1, s2, s3, i, j + 1);
        }

        memo[i][j] = res;
        return res;
    }
}

class Solution {
    public boolean isInterleave(String s1, String s2, String s3) {
        int n1 = s1.length();
        int n2 = s2.length();

        if (n1 + n2 != s3.length()) {
            return false;
        }

        boolean[][] dp = new boolean[n1 + 1][n2 + 1];
        dp[0][0] = true;

        for (int i = 1; i <= n1; i++) {
            dp[i][0] = dp[i - 1][0] && s1.charAt(i - 1) == s3.charAt(i - 1);
        }
        for (int j = 1; j <= n2; j++) {
            dp[0][j] = dp[0][j - 1] && s2.charAt(j - 1) == s3.charAt(j - 1);
        }

        for (int i = 1; i <= n1; i++) {
            for (int j = 1; j <= n2; j++) {
                dp[i][j] = dp[i - 1][j] && s1.charAt(i - 1) == s3.charAt(i + j - 1) || dp[i][j - 1] && s2.charAt(j - 1) == s3.charAt(i + j - 1);
            }
        }

        return dp[n1][n2];
    }
}