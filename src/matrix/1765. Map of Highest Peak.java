class Solution {
    public int[][] highestPeak(int[][] isWater) {
        int m = isWater.length, n = isWater[0].length;
        Queue<int[]> queue = new LinkedList<>();
        int[][] res = new int[m][n];

        // Initialize result grid and queue
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (isWater[i][j] == 1) {
                    queue.add(new int[]{i, j});
                } else {
                    res[i][j] = -1; // Mark land cells as unvisited
                }
            }
        }

        // Directions for BFS traversal
        int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

        // BFS traversal
        while (!queue.isEmpty()) {
            int[] cell = queue.poll();
            int i = cell[0], j = cell[1];

            for (int[] dir : directions) {
                int x = i + dir[0], y = j + dir[1];

                if (x >= 0 && x < m && y >= 0 && y < n && res[x][y] == -1) {
                    res[x][y] = res[i][j] + 1; // Set height
                    queue.add(new int[]{x, y});
                }
            }
        }

        return res;
    }
}