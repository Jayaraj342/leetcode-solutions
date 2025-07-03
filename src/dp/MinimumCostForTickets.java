class Solution {
    public int mincostTickets(int[] days, int[] costs) {
        int n = days.length;
        int[] dp = new int[n + 1];

        for (int i = n - 1; i >= 0; i--) {
            int cost1 = costs[0] + dp[i + 1];

            int j = i;
            while (j < n && days[j] < days[i] + 7) j++;
            int cost7 = costs[1] + dp[j];

            j = i;
            while (j < n && days[j] < days[i] + 30) j++;
            int cost30 = costs[2] + dp[j];

            dp[i] = Math.min(cost1, Math.min(cost7, cost30));
        }

        return dp[0];
    }
}

class Solution {
    public int mincostTickets(int[] days, int[] costs) {
        int n = days.length;
        int[] dp = new int[n + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;

        for (int i = 1; i <= n; i++) {
            dp[i] = dp[i - 1] + costs[0]; // 1-day pass for current day

            int j = i - 1;
            while (j >= 0 && days[i - 1] - days[j] < 7) j--;
            dp[i] = Math.min(dp[i], dp[j + 1] + costs[1]); // 7-day pass for current day
            // (dp[j+1] because days[j] is stored in dp[j+1])

            j = i - 1;
            while (j >= 0 && days[i - 1] - days[j] < 30) j--;
            dp[i] = Math.min(dp[i], dp[j + 1] + costs[2]); // 30-day pass for current day
        }

        return dp[n];
    }

    public static void main(String[] args) {
        new Solution().mincostTickets(new int[]{1, 4, 5, 6, 7, 8, 20}, new int[]{2, 7, 15});
    }
}

class Solution {
    Map<Integer, Integer> memo = new HashMap<>();
    int[] dayPasses = {1, 7, 30};

    public int mincostTickets(int[] days, int[] costs) {
        return dfs(days, costs, 0);
    }

    private int dfs(int[] days, int[] costs, int index) {
        if (memo.containsKey(index)) {
            return memo.get(index);
        }
        if (index == days.length) {
            return 0;
        }

        int minForCurrentIndex = Integer.MAX_VALUE;
        for (int i = 0; i < costs.length; i++) {
            int newIndex = index;
            while (newIndex < days.length && days[newIndex] < days[index] + dayPasses[i]) {
                newIndex++;
            }
            minForCurrentIndex = Math.min(minForCurrentIndex, costs[i] + dfs(days, costs, newIndex));
        }

        memo.put(index, minForCurrentIndex);
        return minForCurrentIndex;
    }
}