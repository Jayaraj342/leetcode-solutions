// m * n, m * n
class Solution {
    public int countSubIslands(int[][] grid1, int[][] grid2) {
        int m = grid1.length;
        int n = grid1[0].length;

        int islandCount = 0;
        boolean[][] visited = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (!visited[i][j] && grid2[i][j] == 1 && dfs(i, j, grid1, grid2, visited)) {
                    islandCount++;
                }
            }
        }

        return islandCount;
    }

    private boolean dfs(int i, int j, int[][] grid1, int[][] grid2, boolean[][] visited) {
        boolean isOutOfBounds = i < 0 || j < 0 || i >= grid1.length || j >= grid1[0].length;
        if (isOutOfBounds || visited[i][j] || grid2[i][j] == 0) {
            return true;
        }
        if (grid1[i][j] == 0) {
            return false;
        }
        visited[i][j] = true;

        // Recurse in 4 directions
        boolean up = dfs(i - 1, j, grid1, grid2, visited);
        boolean down = dfs(i + 1, j, grid1, grid2, visited);
        boolean left = dfs(i, j - 1, grid1, grid2, visited);
        boolean right = dfs(i, j + 1, grid1, grid2, visited);

        return up && down && left && right;
    }
}