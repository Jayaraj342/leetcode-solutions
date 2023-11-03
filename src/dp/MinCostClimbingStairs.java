class Solution {
    Map<Integer, Integer> memo = new HashMap<>();

    public int minCostClimbingStairs(int[] cost) {
        return Math.min(dfs(cost, cost.length - 1), dfs(cost, cost.length - 2));
    }

    private int dfs(int[] cost, int n) {
        if (memo.containsKey(n)) {
            return memo.get(n);
        }
        if (n < 0) {
            return 0;
        }
        if (n == 0 || n == 1) {
            return cost[n];
        }
        memo.put(n, cost[n] + Math.min(dfs(cost, n - 1), dfs(cost, n - 2)));

        return memo.get(n);
    }
}

class Solution {
    public int minCostClimbingStairs(int[] cost) {
        int len = cost.length;

        for (int i = len - 3; i >= 0; i--) {
            cost[i] += Math.min(cost[i + 1], cost[i + 2]);
        }

        return Math.min(cost[0], cost[1]);
    }
}