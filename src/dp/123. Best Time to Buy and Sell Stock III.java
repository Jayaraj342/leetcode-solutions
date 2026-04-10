class Solution {
    public int maxProfit(int[] prices) {
        int n = prices.length;
        if (n == 0) return 0;

        int[][] dp = new int[3][n]; // dp[k][i] = max profit up to day i with at most k transactions

        for (int k = 1; k <= 2; k++) {
            int bestBuy = -prices[0]; // best value of (dp[k-1][j-1] - prices[j])
            for (int i = 1; i < n; i++) {
                // either do nothing or sell today
                dp[k][i] = Math.max(dp[k][i - 1], prices[i] + bestBuy);
                // update Best Buy for future
                bestBuy = Math.max(bestBuy, dp[k - 1][i - 1] - prices[i]);
            }
        }

        return dp[2][n - 1];
    }
}