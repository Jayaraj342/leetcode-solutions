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
        if (i < 0 || j < 0 || i >= grid1.length || j >= grid1[0].length || visited[i][j] || grid2[i][j] == 0) {
            return true;
        }
        if (grid1[i][j] == 0) {
            return false;
        }
        visited[i][j] = true;

        boolean r = dfs(i + 1, j, grid1, grid2, visited);
        boolean l = dfs(i - 1, j, grid1, grid2, visited);
        boolean d = dfs(i, j + 1, grid1, grid2, visited);
        boolean u = dfs(i, j - 1, grid1, grid2, visited);

        return r && l && d && u;
    }
}