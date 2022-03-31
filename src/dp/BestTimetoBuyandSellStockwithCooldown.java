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