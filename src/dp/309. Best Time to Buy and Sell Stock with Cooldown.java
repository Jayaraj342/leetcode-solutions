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
        int[] buy = new int[n + 2], sell = new int[n + 2];
        buy[2] = -prices[0];
        for (int i = 1; i < n; i++) {
            // raise i by 2 => just to skip index checks
            int price = prices[i];
            int tempI = i;
            i += 2;

            buy[i] = Math.max(buy[i - 1], -price + sell[i - 2]);
            sell[i] = Math.max(sell[i - 1], price + buy[i - 1]);

            i = tempI;
        }

        return sell[n + 1];
    }
}

class Solution {
    public int maxProfit(int[] prices) {
        int n = prices.length;
        int buy = 0, prevBuy = 0;
        int sell = 0, prevSell = 0;

        buy = -prices[0];
        for (int i = 1; i < n; i++) {
            int price = prices[i];

            prevBuy = buy;
            buy = Math.max(prevBuy, -price + prevSell);

            prevSell = sell;
            sell = Math.max(prevSell, price + prevBuy);
        }

        return sell;
    }
}