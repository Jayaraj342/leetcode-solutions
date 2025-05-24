class Solution {
    public int trapRainWater(int[][] heightMap) {
        int m = heightMap.length;
        int n = heightMap[0].length;

        PriorityQueue<int[]> minHeap = new PriorityQueue<>((a, b) -> Integer.compare(a[2], b[2]));
        initializeMinHeap(heightMap, m, n, minHeap);

        int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        return calculateTrappedWater(heightMap, m, n, minHeap, directions);
    }

    private void initializeMinHeap(int[][] heightMap, int m, int n, PriorityQueue<int[]> minHeap) {
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (isBoundary(i, j, m, n)) {
                    minHeap.add(new int[]{i, j, heightMap[i][j]});
                    heightMap[i][j] = -1;
                }
            }
        }
    }

    private boolean isBoundary(int i, int j, int m, int n) {
        return i == 0 || j == 0 || i == m - 1 || j == n - 1;
    }

    private int calculateTrappedWater(int[][] heightMap, int m, int n, PriorityQueue<int[]> minHeap, int[][] directions) {
        int max = 0;
        int result = 0;

        while (!minHeap.isEmpty()) {
            int[] cell = minHeap.poll();
            int i = cell[0], j = cell[1], height = cell[2];

            max = Math.max(max, height);
            result += max - height;

            for (int[] dir : directions) {
                int x = i + dir[0];
                int y = j + dir[1];
                if (isValidCell(x, y, m, n, heightMap)) {
                    minHeap.add(new int[]{x, y, heightMap[x][y]});
                    heightMap[x][y] = -1;
                }
            }
        }

        return result;
    }

    private boolean isValidCell(int x, int y, int m, int n, int[][] heightMap) {
        return x >= 0 && y >= 0 && x < m && y < n && heightMap[x][y] != -1;
    }
}
