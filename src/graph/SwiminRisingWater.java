// n^2 * log(n^2), n^2
class Solution {
    public int swimInWater(int[][] grid) {
        int n = grid.length;

        PriorityQueue<int[]> minHeap = new PriorityQueue<>((a, b) -> Integer.compare(a[0], b[0]));
        minHeap.offer(new int[]{grid[0][0], 0, 0});

        boolean[][] visited = new boolean[n][n];
        visited[0][0] = true;

        int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

        while (!minHeap.isEmpty()) {
            int[] current = minHeap.remove();
            int time = current[0], row = current[1], col = current[2];

            if (row == n - 1 && col == n - 1) {
                return time;
            }

            for (int[] dir : directions) {
                int newRow = row + dir[0];
                int newCol = col + dir[1];

                if (!isValid(newRow, newCol, n, visited)) {
                    continue;
                }
                visited[newRow][newCol] = true;
                int newTime = Math.max(time, grid[newRow][newCol]);
                minHeap.add(new int[]{newTime, newRow, newCol});
            }
        }

        return -1;
    }

    private boolean isValid(int row, int col, int n, boolean[][] visited) {
        return row >= 0 && row < n && col >= 0 && col < n && !visited[row][col];
    }
}
