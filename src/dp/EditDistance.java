class Solution {
    public int minDistance(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();

        int[][] dp = new int[m + 1][n + 1];
        for (int i = m; i >= 0; i--) {
            dp[i][n] = m - i;
        }
        for (int j = n; j >= 0; j--) {
            dp[m][j] = n - j;
        }

        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                if (word1.charAt(i) == word2.charAt(j)) {
                    dp[i][j] = dp[i + 1][j + 1];
                } else {
                    dp[i][j] = 1 + Math.min(dp[i + 1][j + 1], Math.min(dp[i + 1][j], dp[i][j + 1]));
                }
            }
        }

        return dp[0][0];
    }
}

class Solution {
    public int minDistance(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();

        int[][] cost = new int[m + 1][n + 1];
        for (int i = 0; i <= m; i++)
            cost[i][0] = i;
        for (int i = 1; i <= n; i++)
            cost[0][i] = i;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (word1.charAt(i) == word2.charAt(j))
                    cost[i + 1][j + 1] = cost[i][j];
                else {
                    int a = cost[i][j];
                    int b = cost[i][j + 1];
                    int c = cost[i + 1][j];
                    cost[i + 1][j + 1] = 1 + Math.min(a, Math.min(b, c));
                }
            }
        }
        return cost[m][n];
    }
}