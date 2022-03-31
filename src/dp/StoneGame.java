class Solution {
    int[][] memo;

    public boolean stoneGame(int[] piles) {
        int sum = 0;
        for (int stone : piles) {
            sum += stone;
        }
        memo = new int[piles.length][piles.length];
        return dfs(piles, 0, piles.length - 1) > sum / 2;
    }

    private int dfs(int[] piles, int i, int j) {
        if (i > j) {
            return 0;
        }
        if (memo[i][j] != 0) {
            return memo[i][j];
        }

        boolean aliceTurn = (j - i + 1) % 2 == 0;

        int firstElement = aliceTurn ? piles[i] : 0;
        int lastElement = aliceTurn ? piles[j] : 0;

        memo[i][j] = Math.max(firstElement + dfs(piles, i + 1, j), lastElement + dfs(piles, i, j - 1));
        return memo[i][j];
    }
}