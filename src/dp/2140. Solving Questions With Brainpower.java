class Solution {
    public long mostPoints(int[][] questions) {
        int n = questions.length;
        long[] dp = new long[n + 1];
        for (int i = n - 1; i >= 0; i--) {
            int points = questions[i][0], jumps = questions[i][1];
            dp[i] = Math.max(dp[i + 1], points + dp[Math.min(i + jumps + 1, n)]);
        }

        return dp[0];
    }
}

class Solution {
    long[] dp;

    public long mostPoints(int[][] questions) {
        dp = new long[questions.length];
        return dfs(questions, 0);
    }

    public long dfs(int[][] questions, int idx) {
        if (idx >= questions.length) {
            return 0;
        }
        if (dp[idx] > 0) {
            return dp[idx];
        }

        int points = questions[idx][0], jump = questions[idx][1];
        return dp[idx] = Math.max(dfs(questions, idx + 1), points + dfs(questions, idx + jump + 1));
    }
}