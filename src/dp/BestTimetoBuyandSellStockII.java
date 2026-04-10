// https://leetcode.com/problems/best-time-to-buy-and-sell-stock-ii/
class Solution {
    public int maxProfit(int[] prices) {
        int profit = 0;
        for (int i = 0; i < prices.length - 1; i++) {
            if (prices[i + 1] > prices[i]) {
                profit += prices[i + 1] - prices[i];
            }
        }

        return profit;
    }
}
class Solution {
    public int maxProfit(int[] prices) {
        int prev = prices[0], profit = 0;
        for (int curr : prices) {
            if (curr > prev) {
                profit += curr - prev;
            }
            prev = curr;
        }

        return profit;
    }
}

// https://leetcode.com/problems/best-time-to-buy-and-sell-stock/
class Solution {
    public int maxProfit(int[] prices) {
        int dip = prices[0], profit = 0;
        for (int curr : prices) {
            profit = Math.max(profit, curr - dip);
            dip = Math.min(dip, curr);
        }

        return profit;
    }
}