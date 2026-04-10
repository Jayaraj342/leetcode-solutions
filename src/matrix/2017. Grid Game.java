class Solution {
    public long gridGame(int[][] grid) {
        int n = grid[0].length;

        // Total sum of the top row
        long topSum = 0;
        for (int val : grid[0]) topSum += val;

        long bottomSum = 0, result = Long.MAX_VALUE;

        // Iterate column by column
        for (int i = 0; i < n; i++) {
            topSum -= grid[0][i];         // Remaining top path if robot goes down here
            result = Math.min(result, Math.max(topSum, bottomSum));
            bottomSum += grid[1][i];      // Accumulate bottom path for next iteration
        }

        return result;
    }
}

class Solution {
    public long gridGame(int[][] grid) {
        int n = grid[0].length;
        long[] firstRow = new long[n], secondRow = new long[n];

        // Compute prefix sum for top row
        firstRow[n - 1] = grid[0][n - 1];
        for (int i = n - 2; i >= 0; i--) {
            firstRow[i] = firstRow[i + 1] + grid[0][i];
        }

        // Compute suffix sum for bottom row
        secondRow[0] = grid[1][0];
        for (int i = 1; i < n - 1; i++) {
            secondRow[i] = secondRow[i - 1] + grid[1][i];
        }

        long result = Long.MAX_VALUE;

        // Try intersection at each column and calculate the worst remaining path for robot 2
        for (int i = 0; i < n; i++) {
            long topRemaining = i < n - 1 ? firstRow[i + 1] : 0; // Remaining on top
            long bottomStarting = i > 0 ? secondRow[i - 1] : 0; // Starting on bottom
            result = Math.min(result, Math.max(topRemaining, bottomStarting));
        }

        return result;
    }
}