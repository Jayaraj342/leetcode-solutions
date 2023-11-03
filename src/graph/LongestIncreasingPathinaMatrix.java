class Solution {
    public int longestIncreasingPath(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;

        int[][] lip = new int[m][n];
        int max = 1;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                max = Math.max(max, dfs(i, j, -1, matrix, lip));
            }
        }

        return max;
    }

    private int dfs(int i, int j, int prev, int[][] matrix, int[][] lip) {
        if (i < 0 || i >= matrix.length || j < 0 || j >= matrix[0].length || matrix[i][j] <= prev) {
            return 0;
        }

        if (lip[i][j] > 0) {
            return lip[i][j];
        }

        int max = 1;

        max = Math.max(max, 1 + dfs(i + 1, j, matrix[i][j], matrix, lip));
        max = Math.max(max, 1 + dfs(i - 1, j, matrix[i][j], matrix, lip));
        max = Math.max(max, 1 + dfs(i, j + 1, matrix[i][j], matrix, lip));
        max = Math.max(max, 1 + dfs(i, j - 1, matrix[i][j], matrix, lip));

        lip[i][j] = max;
        return max;
    }
}