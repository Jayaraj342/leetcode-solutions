class Solution {
    public int maxAreaOfIsland(int[][] grid) {
        int max = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    max = Math.max(max, dfs(grid, i, j));
                }
            }
        }

        return max;
    }

    private int dfs(int[][] grid, int i, int j) {
        if (!inbounds(i, j, grid.length, grid[0].length) || grid[i][j] == 0) {
            return 0;
        }
        grid[i][j] = 0;

        int area = 1;
        area += dfs(grid, i + 1, j);
        area += dfs(grid, i - 1, j);
        area += dfs(grid, i, j + 1);
        area += dfs(grid, i, j - 1);

        return area;
    }

    private boolean inbounds(int i, int j, int n, int m) {
        return i >= 0 && i < n && j >= 0 && j < m;
    }
}