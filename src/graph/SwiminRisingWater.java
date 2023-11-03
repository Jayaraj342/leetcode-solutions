class Solution {
    public int swimInWater(int[][] grid) {
        int n = grid.length;
        PriorityQueue<int[]> minHeap = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        minHeap.add(new int[]{grid[0][0], 0, 0});

        Set<String> set = new HashSet<>();
        set.add("0,0");

        int[][] directions = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

        while (!minHeap.isEmpty()) {
            int[] lastCell = minHeap.remove();
            if (lastCell[1] == n - 1 && lastCell[2] == n - 1) {
                return lastCell[0];
            }
            for (int[] dir : directions) {
                int nr = lastCell[1] + dir[0];
                int nc = lastCell[2] + dir[1];
                boolean outOfBounds = nr < 0 || nc < 0 || nr >= n || nc >= n;
                if (outOfBounds || set.contains(nr + "," + nc)) {
                    continue;
                }
                set.add(nr + "," + nc);
                minHeap.add(new int[]{Math.max(lastCell[0], grid[nr][nc]), nr, nc});
            }
        }

        return -1;
    }
}