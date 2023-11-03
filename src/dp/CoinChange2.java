class Solution {
    public int change(int amount, int[] coins) {
        int n = coins.length;
        int[][] dp = new int[n + 1][amount + 1];

        for (int i = 0; i <= n; i++) {
            dp[i][0] = 1;
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j < amount + 1; j++) {
                dp[i][j] = dp[i - 1][j] + (j - coins[i - 1] >= 0 ? dp[i][j - coins[i - 1]] : 0);
            }
        }

        return dp[n][amount];
    }
}

class Solution {
    public int change(int amount, int[] coins) {
        return dfs(amount, 0, coins);
    }

    Map<String, Integer> memo = new HashMap<>();

    public int dfs(int amount, int start, int[] coins) {
        if (amount < 0) {
            return 0;
        }

        if (amount == 0) {
            return 1;
        }

        String key = amount + "," + start;
        if (memo.containsKey(key)) {
            return memo.get(key);
        }

        int count = 0;
        for (int i = start; i < coins.length; ++i) {
            count += dfs(amount - coins[i], i, coins);
        }

        memo.put(key, count);
        return count;
    }
}

class Solution {
    public int change(int amount, int[] coins) {
        int[] dp = new int[amount + 1];
        dp[0] = 1;

        for (int coin : coins) {
            for (int i = coin; i <= amount; i++) {
                dp[i] += dp[i - coin];
            }
        }

        return dp[amount];
    }
}
