class Solution {
    public int numSubmatrixSumTarget(int[][] matrix, int target) {
        int m = matrix.length, n = matrix[0].length;
        int[][] pf = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int top = i > 0 ? pf[i - 1][j] : 0;
                int left = j > 0 ? pf[i][j - 1] : 0;
                int topLeft = Math.min(i, j) > 0 ? pf[i - 1][j - 1] : 0;
                pf[i][j] = matrix[i][j] + top + left - topLeft;
            }
        }

        int res = 0;
        for (int r1 = 0; r1 < m; r1++) {
            for (int r2 = r1; r2 < m; r2++) {
                Map<Integer, Integer> map = new HashMap<>();
                map.put(0, 1);
                for (int c = 0; c < n; c++) {
                    // sub-matrix's ending at (r1,r2,c) that have target sum (remove top and find sum - tgt in map)
                    int currSum = pf[r2][c] - (r1 > 0 ? pf[r1 - 1][c] : 0);
                    res += map.getOrDefault(currSum - target, 0);
                    map.put(currSum, map.getOrDefault(currSum, 0) + 1);
                }
            }
        }

        return res;
    }
}