class Solution {
    public double knightProbability(int n, int k, int row, int column) {
        int[][] dp = new int[n][n];
        return dfs(dp, k, row, column) / Math.pow(8, k);
    }

    Map<String, Double> memo = new HashMap<>();

    private double dfs(int[][] dp, int k, int r, int c) {
        int n = dp.length;
        if (r < 0 || c < 0 || r >= n || c >= n) {
            return 0;
        }
        if (k == 0) {
            return 1;
        }
        String key = k + "," + r + "," + c;
        if (memo.containsKey(key)) {
            return memo.get(key);
        }

        double move1 = dfs(dp, k - 1, r - 2, c + 1);
        double move2 = dfs(dp, k - 1, r - 1, c + 2);
        double move3 = dfs(dp, k - 1, r + 1, c + 2);
        double move4 = dfs(dp, k - 1, r + 2, c + 1);
        double move5 = dfs(dp, k - 1, r + 2, c - 1);
        double move6 = dfs(dp, k - 1, r + 1, c - 2);
        double move7 = dfs(dp, k - 1, r - 1, c - 2);
        double move8 = dfs(dp, k - 1, r - 2, c - 1);

        double result = move1 + move2 + move3 + move4 + move5 + move6 + move7 + move8;
        memo.put(key, result);
        return result;
    }
}