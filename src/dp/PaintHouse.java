// Failing
public class Solution {
    Map<String, Integer> memo = new HashMap<>();

    public int minCost(int[][] costs) {
        return dfs(costs, 0, -1);
    }

    private int dfs(int[][] costs, int start, int prev) {
        if (start == costs.length) {
            return 0;
        }
        String key = start + "," + prev;
        if (memo.containsKey(key)) {
            return memo.get(key);
        }

        int minCost = Integer.MAX_VALUE;
        for (int i = 0; i < 3; i++) {
            if (prev != i) {
                minCost = Math.min(minCost, costs[start][i] + dfs(costs, start + 1, i));
            }
        }
        memo.put(key, minCost);

        return minCost;
    }
}

public class Solution {
    public int minCost(int[][] costs) {
        int[] dp = new int[3];
        for (int[] cost : costs) {
            int dp0 = cost[0] + Math.min(dp[1], dp[2]);
            int dp1 = cost[1] + Math.min(dp[0], dp[2]);
            int dp2 = cost[2] + Math.min(dp[0], dp[1]);

            dp = new int[]{dp0, dp1, dp2};
        }

        return Math.min(dp[0], Math.min(dp[1], dp[2]));
    }
}