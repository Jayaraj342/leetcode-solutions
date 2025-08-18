class Solution {
    public int firstCompleteIndex(int[] arr, int[][] mat) {
        int m = mat.length, n = mat[0].length;

        // Map to store the row and column indices of each number in the matrix
        Map<Integer, int[]> indexMap = new HashMap<>();

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                indexMap.put(mat[i][j], new int[]{i, j});
            }
        }

        // Arrays to track the count of filled cells in each row and column
        int[] rowCount = new int[m];
        int[] columnCount = new int[n];

        for (int i = 0; i < arr.length; i++) {
            int num = arr[i];

            // Retrieve the row and column indices from the map
            int[] indices = indexMap.get(num);
            int row = indices[0],column = indices[1];

            // Increment counts for the corresponding row and column
            if (++rowCount[row] == n || ++columnCount[column] == m) {
                return i; // Return the index if a row or column is completely filled
            }
        }

        return -1; // No row or column is completely filled
    }
}

// save little memory - but SC remains same
class Solution {
    public int firstCompleteIndex(int[] arr, int[][] mat) {
        int m = mat.length, n = mat[0].length;

        int total = m * n;
        int[] pos = new int[total + 1]; // number -> encoded position (row * n + col)

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                pos[mat[i][j]] = i * n + j;
            }
        }

        int[] rowCount = new int[m];
        int[] colCount = new int[n];

        for (int i = 0; i < arr.length; i++) {
            int encoded = pos[arr[i]];
            int r = encoded / n;
            int c = encoded % n;

            if (++rowCount[r] == n || ++colCount[c] == m) {
                return i;
            }
        }

        return -1;
    }
}