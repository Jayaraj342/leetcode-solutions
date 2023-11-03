class Solution {
    public int islandPerimeter(int[][] grid) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    Set<String> visited = new HashSet<>();
                    return dfs(grid, i, j, visited);
                }
            }
        }

        return 0;
    }

    private int dfs(int[][] grid, int i, int j, Set<String> visited) {
        String key = i + "," + j;
        if (visited.contains(key)) {
            return 0;
        }

        if (i == -1 || i == grid.length || j == -1 || j == grid[0].length || grid[i][j] == 0) {
            return 1;
        }
        visited.add(key);

        int perimeter = dfs(grid, i + 1, j, visited);
        perimeter += dfs(grid, i - 1, j, visited);
        perimeter += dfs(grid, i, j + 1, visited);
        perimeter += dfs(grid, i, j - 1, visited);

        return perimeter;
    }
}