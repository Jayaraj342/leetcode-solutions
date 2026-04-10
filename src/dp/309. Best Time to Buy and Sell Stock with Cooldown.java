// TC : O(n), SC : O(n)
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

class Solution {
    public int maxProfit(int[] prices) {
        int n = prices.length;
        int buy = -prices[0], sell = 0, cooldown = 0;
        for (int i = 1; i < n; i++) {
            int curr = prices[i];
            // At current index
            int bestBuy = Math.max(cooldown - curr, buy);
            int bestSell = Math.max(buy + curr, sell);
            int bestCooldown = Math.max(sell, cooldown);

            buy = bestBuy;
            sell = bestSell;
            cooldown = bestCooldown;
        }

        return Math.max(sell, cooldown);
    }
}