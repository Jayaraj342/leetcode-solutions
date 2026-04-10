class Solution {
    int max = 2;

    public int almostPalindromic(String s) {
        int n = s.length();
        for (int i = 0; i < n - 1; i++) {
            // Even center
            expandAndStep(s, i, i + 1, n);

            // Odd center
            expandAndStep(s, i - 1, i + 1, n);
        }

        return Math.min(max, n);
    }

    private void expandAndStep(String s, int i, int j, int n) {
        int[] pair = expand(s, i, j, n);

        int l = pair[0], r = pair[1];
        expand(s, l, r + 1, n);
        expand(s, l - 1, r, n);
    }

    private int[] expand(String s, int i, int j, int n) {
        while (i >= 0 && j < n && s.charAt(i) == s.charAt(j)) {
            i--;
            j++;
        }

        // string between i & j is a palindrome
        max = Math.max(max, j - i - 1);
        return new int[] {i, j};
    }
}

class Solution {
    private int[][] dp;

    public int almostPalindromic(String s) {
        int n = s.length();
        dp = new int[n][n];

        // fill with -1
        for (int[] row : dp) {
            Arrays.fill(row, -1);
        }

        int max = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                int len = j - i + 1;

                if (len >= 2 && solve(s, i, j) <= 1) {
                    max = Math.max(max, len);
                }
            }
        }

        return max;
    }

    private int solve(String s, int i, int j) {
        if (i >= j) return 0;

        if (dp[i][j] != -1)
            return dp[i][j];

        // characters match -> continue inward
        if (s.charAt(i) == s.charAt(j))
            return dp[i][j] = solve(s, i + 1, j - 1);

        // mismatch -> delete one side
        return dp[i][j] = 1 + Math.min(solve(s, i + 1, j), solve(s, i, j - 1));
    }
}