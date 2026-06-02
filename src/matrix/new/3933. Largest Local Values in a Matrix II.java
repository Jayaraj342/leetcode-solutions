class Solution {
    int m, n;

    public int countLocalMaximums(int[][] matrix) {
        m = matrix.length;
        n = matrix[0].length;

        // prefix[k][i][j]
        // number of cells > k in rectangle (0,0) -> (i-1,j-1)
        int[][][] prefix = new int[201][m + 1][n + 1];
        for (int k = 0; k <= 200; k++) {
            for (int i = 1; i <= m; i++) {
                for (int j = 1; j <= n; j++) {
                    prefix[k][i][j] = prefix[k][i - 1][j] + prefix[k][i][j - 1] - prefix[k][i - 1][j - 1] + (matrix[i - 1][j - 1] > k ? 1 : 0);
                }
            }
        }

        int ans = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int k = matrix[i][j];

                if (k == 0) {
                    continue;
                }

                int r1 = Math.max(0, i - k);
                int c1 = Math.max(0, j - k);

                int r2 = Math.min(m - 1, i + k);
                int c2 = Math.min(n - 1, j + k);

                // count cells > k inside square
                int cnt = query(prefix[k], r1, c1, r2, c2);

                // exclude corners
                cnt -= isGreater(matrix, i - k, j - k, k);
                cnt -= isGreater(matrix, i - k, j + k, k);
                cnt -= isGreater(matrix, i + k, j - k, k);
                cnt -= isGreater(matrix, i + k, j + k, k);

                if (cnt == 0) {
                    ans++;
                }
            }
        }

        return ans;
    }

    private int query(int[][] prefix, int r1, int c1, int r2, int c2) {
        return prefix[r2 + 1][c2 + 1] - prefix[r1][c2 + 1] - prefix[r2 + 1][c1] + prefix[r1][c1];
    }

    private int isGreater(int[][] matrix, int r, int c, int k) {
        if (r < 0 || c < 0 || r >= m || c >= n) {
            return 0;
        }

        return matrix[r][c] > k ? 1 : 0;
    }
}