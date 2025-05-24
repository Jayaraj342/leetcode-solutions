class Solution {
    public int countServers(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int[] rowCount = new int[m], columnCount = new int[n];
        int totalServers = 0;

        // First pass: Count servers in each row and column
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    rowCount[i]++;
                    columnCount[j]++;
                    totalServers++;
                }
            }
        }

        // Second pass: Count servers that can communicate
        int res = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1 && (rowCount[i] > 1 || columnCount[j] > 1)) {
                    res++;
                }
            }
        }

        return res;
    }
}

// SC : O(1)
class Solution {
    public int countServers(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int res = 0;

        // First pass: Count row servers and mark them (-1) if more than one
        for (int i = 0; i < m; i++) {
            int rowCount = 0;
            for (int j = 0; j < n; j++) {
                rowCount += grid[i][j];
            }
            if (rowCount > 1) {
                for (int j = 0; j < n; j++) {
                    if (grid[i][j] == 1) {
                        grid[i][j] = -1; // Mark as counted
                    }
                }
                res += rowCount; // Add all servers in the row
            }
        }

        // Second pass: Count column servers, unmarking previously marked ones
        for (int j = 0; j < n; j++) {
            int colCount = 0, unmarked = 0;
            for (int i = 0; i < m; i++) {
                if (grid[i][j] != 0) {
                    colCount++;
                    if (grid[i][j] == 1) unmarked++; // Count unmarked ones
                    grid[i][j] = 1; // Restore original value
                }
            }
            if (colCount > 1) res += unmarked;
        }

        return res;
    }
}

// without changing input and SC = O(1)
class Solution {
    public int countServers(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int res = 0;

        // Iterate through the grid and count servers in each row and column
        for (int i = 0; i < m; i++) {
            int rowCount = 0, firstServerCol = -1;
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    rowCount++;
                    firstServerCol = j;
                }
            }
            // If there is more than one server in the row, mark them for counting
            if (rowCount > 1) {
                res += rowCount;
            }
            // If there is exactly one server in the row, we need to check its column
            else if (rowCount == 1) {
                int colCount = 0;
                for (int k = 0; k < m; k++) {
                    if (grid[k][firstServerCol] == 1) {
                        colCount++;
                    }
                }
                if (colCount > 1) {
                    res++;
                }
            }
        }

        return res;
    }
}
