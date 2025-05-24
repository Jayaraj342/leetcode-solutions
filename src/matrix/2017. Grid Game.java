class Solution {
    public long gridGame(int[][] grid) {
        int m = grid[0].length;
        long[] prefixSum = new long[m], postfixSum = new long[m];

        // Compute prefix sum for top row
        prefixSum[0] = grid[0][0];
        for (int i = 1; i < m; i++) {
            prefixSum[i] = prefixSum[i - 1] + grid[0][i];
        }

        // Compute postfix sum for bottom row
        postfixSum[m - 1] = grid[1][m - 1];
        for (int j = m - 2; j >= 0; j--) {
            postfixSum[j] = postfixSum[j + 1] + grid[1][j];
        }

        long result = Long.MAX_VALUE;

        // Try stopping at each column and calculate the worst remaining path for robot 2
        for (int i = 0; i < m; i++) {
            long topRemaining = prefixSum[m - 1] - prefixSum[i]; // Remaining on top
            long bottomRemaining = (i > 0) ? postfixSum[0] - postfixSum[i] : 0; // Remaining on bottom
            result = Math.min(result, Math.max(topRemaining, bottomRemaining));
        }

        return result;
    }
}
