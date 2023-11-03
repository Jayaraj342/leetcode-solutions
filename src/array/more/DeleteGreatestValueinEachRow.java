class Solution {
    public int deleteGreatestValue(int[][] grid) {
        for (int[] row : grid) {
            Arrays.sort(row);
        }
        int m = grid.length, n = grid[0].length;
        int sum = 0;
        for (int c = 0; c < n; c++) {
            int max = 0;
            for (int r = 0; r < m; r++) {
                max = Math.max(max, grid[r][c]);
            }
            sum += max;
        }

        return sum;
    }
}