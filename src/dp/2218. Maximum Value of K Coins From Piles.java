class Solution {
    int n;
    int[][] memo;

    public int maxValueOfCoins(List<List<Integer>> piles, int k) {
        n = piles.size();
        memo = new int[n][k + 1];
        return dfs(piles, 0, k);
    }

    private int dfs(List<List<Integer>> piles, int currPile, int coins) {
        if (currPile == n) {
            return 0;
        }
        if (memo[currPile][coins] != 0) {
            return memo[currPile][coins];
        }

        int m = piles.get(currPile).size(), sum = 0;
        int max = dfs(piles, currPile + 1, coins);
        for (int i = 0; i < Math.min(coins, m); i++) {
            sum += piles.get(currPile).get(i);
            max = Math.max(max, sum + dfs(piles, currPile + 1, coins - i - 1));
        }

        memo[currPile][coins] = max;
        return max;
    }
}