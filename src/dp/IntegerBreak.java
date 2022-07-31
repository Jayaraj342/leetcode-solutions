class Solution {
    public int integerBreak(int n) {
        int[] dp = new int[n + 1];
        // min for any i except n is i only
        for (int i = 1; i < n; i++) {
            dp[i] = i;
        }
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j < i; j++) {
                dp[i] = Math.max(dp[i], dp[j] * dp[i - j]);
            }
        }

        return dp[n];
    }
}

class Solution {
    Map<Integer, Integer> memo = new HashMap<>();

    public int integerBreak(int n) {
        return dfs(n, n);
    }

    private int dfs(int n, int curr) {
        if (memo.containsKey(curr)) {
            return memo.get(curr);
        }
        int max = n == curr ? 1 : curr;
        for (int i = 1; i < curr; i++) {
            max = Math.max(max, dfs(n, i) * dfs(n, curr - i));
        }

        memo.put(curr, max);
        return max;
    }
}