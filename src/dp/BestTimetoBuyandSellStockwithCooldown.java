class Solution {
    Integer[] dp;
    public int maxProfit(int[] prices) {
        dp = new Integer[prices.length];
        int max = 0;
        for (int i = 0; i < prices.length; i++) {
            max = Math.max(max, dfs(prices, i));
        }
        return max;
    }

    private int dfs(int[] prices, int buyAt) {
        if (buyAt >= prices.length - 1) {
            return 0;
        }
        if(dp[buyAt] != null) {
            return dp[buyAt];
        }

        int max = 0;
        for (int sellAt = buyAt + 1; sellAt < prices.length; sellAt++) {
            int currentProfit = prices[sellAt] - prices[buyAt];
            max = Math.max(max, currentProfit);
            for (int buyAgainAt = sellAt + 2; buyAgainAt < prices.length - 1; buyAgainAt++) {
                max = Math.max(max, currentProfit + dfs(prices, buyAgainAt));
            }
        }
        dp[buyAt] = max;
        return max;
    }
}

class Solution {
    Map<String, Integer> memo = new HashMap<>();

    public int maxProfit(int[] prices) {
        return dfs(prices, 0, true);
    }

    private int dfs(int[] prices, int idx, boolean isBuy) {
        if (idx >= prices.length) {
            return 0;
        }
        String key = idx + "," + isBuy;
        if (memo.containsKey(key)) {
            return memo.get(key);
        }
        int max;
        if (isBuy) {
            max = Math.max(-prices[idx] + dfs(prices, idx + 1, !isBuy), dfs(prices, idx + 1, isBuy));
        } else {
            max = Math.max(prices[idx] + dfs(prices, idx + 2, !isBuy), dfs(prices, idx + 1, isBuy));
        }

        memo.put(key, max);
        return max;
    }
}