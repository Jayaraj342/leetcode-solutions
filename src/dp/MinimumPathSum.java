class Solution {
    int[][] memo;
    public int minPathSum(int[][] grid) {
        memo = new int[grid.length][grid[0].length];
        return dfs(grid, 0, 0);
    }

    private int dfs(int[][] grid, int i, int j) {
        if (i == grid.length - 1 && j == grid[0].length - 1) {
            return grid[i][j];
        }
        if(memo[i][j] != 0) {
            return memo[i][j];
        }

        int down = Integer.MAX_VALUE, right = Integer.MAX_VALUE;
        if (i < grid.length - 1) {
            down = dfs(grid, i + 1, j);
        }
        if (j < grid[0].length - 1) {
            right = dfs(grid, i, j + 1);
        }

        memo[i][j] = grid[i][j] + Math.min(down, right);
        return memo[i][j];
    }
}