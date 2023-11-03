class Solution {
    public double knightProbability(int n, int k, int row, int column) {
        return dfs(n, k, row, column) / Math.pow(8, k);
    }

    Map<String, Double> memo = new HashMap<>();

    private double dfs(int n, int k, int r, int c) {
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

        double move1 = dfs(n, k - 1, r - 2, c + 1);
        double move2 = dfs(n, k - 1, r - 1, c + 2);
        double move3 = dfs(n, k - 1, r + 1, c + 2);
        double move4 = dfs(n, k - 1, r + 2, c + 1);
        double move5 = dfs(n, k - 1, r + 2, c - 1);
        double move6 = dfs(n, k - 1, r + 1, c - 2);
        double move7 = dfs(n, k - 1, r - 1, c - 2);
        double move8 = dfs(n, k - 1, r - 2, c - 1);

        double result = move1 + move2 + move3 + move4 + move5 + move6 + move7 + move8;
        memo.put(key, result);
        return result;
    }
}