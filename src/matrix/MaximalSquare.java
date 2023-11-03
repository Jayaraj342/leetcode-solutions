class Solution {
    public int maximalSquare(char[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;

        int max = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == '1') {
                    int curr = 1;
                    boolean both = true;
                    while (i + curr < m && j + curr < n && both) {
                        for (int k = j; k <= j + curr; k++) {
                            if (matrix[i + curr][k] == '0') {
                                both = false;
                                break;
                            }
                        }
                        for (int k = i; k <= i + curr; k++) {
                            if (matrix[k][j + curr] == '0') {
                                both = false;
                                break;
                            }
                        }
                        if (both) {
                            curr++;
                        }
                    }
                    if (curr > max) {
                        max = curr;
                    }
                }
            }
        }

        return max * max;
    }
}

class Solution {
    public int maximalSquare(char[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] dp = new int[m + 1][n + 1];

        int max = 0;
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (matrix[i - 1][j - 1] == '1') {
                    dp[i][j] = Math.min(Math.min(dp[i - 1][j], dp[i][j - 1]), dp[i - 1][j - 1]) + 1;
                    max = Math.max(dp[i][j], max);
                }
            }
        }

        return max * max;
    }
}