class Solution {
    public String shortestCommonSupersequence(String str1, String str2) {
        int m = str1.length(), n = str2.length();
        int[][] dp = new int[m + 1][n + 1];

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        // Backtracking to get the LCS string - also add redundant chars too
        StringBuilder lcs = new StringBuilder();
        int i = m, j = n;
        while (i > 0 && j > 0) {
            if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                lcs.append(str1.charAt(i - 1));
                i--;
                j--;
            } else if (dp[i - 1][j] > dp[i][j - 1]) {
                lcs.append(str1.charAt(i - 1));
                i--;
            } else {
                lcs.append(str2.charAt(j - 1));
                j--;
            }
        }
        while (i > 0) {
            lcs.append(str1.charAt(i - 1));
            i--;
        }
        while (j > 0) {
            lcs.append(str2.charAt(j - 1));
            j--;
        }

        return lcs.reverse().toString();
    }
}

// memory issues
class Solution {
    String[][] memo;

    public String shortestCommonSupersequence(String str1, String str2) {
        memo = new String[str1.length()][str2.length()];
        return dfs(str1, str2, 0, 0);
    }

    private String dfs(String text1, String text2, int i, int j) {
        if (i == text1.length()) {
            return text2.substring(j);
        }
        if (j == text2.length()) {
            return text1.substring(i);
        }

        if (memo[i][j] != null) {
            return memo[i][j];
        }

        String res;
        if (text1.charAt(i) == text2.charAt(j)) {
            res = text1.charAt(i) + dfs(text1, text2, i + 1, j + 1);
        } else {
            String res1 = text1.charAt(i) + dfs(text1, text2, i + 1, j);
            String res2 = text2.charAt(j) + dfs(text1, text2, i, j + 1);

            if (res1.length() > res2.length()) {
                res = res2; // shorter one instead of longer
            } else {
                res = res1;
            }
        }
        memo[i][j] = res;
        return res;
    }
}