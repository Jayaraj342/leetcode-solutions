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
                    int replace = cost[i][j];
                    int insert = cost[i][j + 1];
                    int delete = cost[i + 1][j];
                    cost[i + 1][j + 1] = 1 + Math.min(replace, Math.min(insert, delete));
                }
            }
        }
        return cost[m][n];
    }
}

class Solution {
    Integer[][] memo;

    public int minDistance(String word1, String word2) {
        memo = new Integer[word1.length()][word2.length()];
        return dfs(word1, word2, 0, 0);
    }

    private int dfs(String word1, String word2, int i, int j) {
        if (i == word1.length()) {
            return word2.length() - j;
        }
        if (j == word2.length()) {
            return word1.length() - i;
        }

        if (memo[i][j] != null) {
            return memo[i][j];
        }

        int res;
        if (word1.charAt(i) == word2.charAt(j)) {
            res = dfs(word1, word2, i + 1, j + 1);
        } else {
            int insert = dfs(word1, word2, i, j + 1);
            int delete = dfs(word1, word2, i + 1, j);
            int replace = dfs(word1, word2, i + 1, j + 1);

            res = 1 + Math.min(insert, Math.min(delete, replace));
        }
        memo[i][j] = res;
        return res;
    }
}

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