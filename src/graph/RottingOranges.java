class Solution {
    private static final int[][] DIRS = {{1,0},{-1,0},{0,1},{0,-1}};

    public int orangesRotting(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        Queue<int[]> queue = new LinkedList<>();
        int fresh = 0;

        // Step 1: Collect all rotten oranges and count fresh ones
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 2) queue.offer(new int[]{i, j});
                else if (grid[i][j] == 1) fresh++;
            }
        }

        int minutes = 0;

        // Step 2: BFS — process level by level (each level = 1 minute)
        while (!queue.isEmpty()) {
            int size = queue.size();
            boolean rottedThisMinute = false;

            for (int i = 0; i < size; i++) {
                int[] curr = queue.remove();
                for (int[] dir : DIRS) {
                    int r = curr[0] + dir[0], c = curr[1] + dir[1];
                    if (r >= 0 && r < m && c >= 0 && c < n && grid[r][c] == 1) {
                        grid[r][c] = 2;
                        queue.add(new int[]{r, c});
                        fresh--;

                        rottedThisMinute = true;
                    }
                }
            }

            if (rottedThisMinute) {
                minutes++;
            }
        }

        // Step 3: If there are still fresh oranges, impossible
        return fresh == 0 ? minutes : -1;
    }
}

// don't use list - use string concatination
// m * n, m * n
class Solution {
    int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public int orangesRotting(int[][] grid) {
        int m = grid.length, n = grid[0].length;

        Set<List<Integer>> freshSet = new HashSet<>();
        Set<List<Integer>> rottenSet = new HashSet<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    freshSet.add(List.of(i, j));
                }
                if (grid[i][j] == 2) {
                    rottenSet.add(List.of(i, j));
                }
            }
        }

        int mins = 0;
        while (!freshSet.isEmpty()) {
            Set<List<Integer>> newRottenOnes = new HashSet<>();
            for (List<Integer> rottenOrange : rottenSet) {
                for (int[] dir : directions) {
                    int nextRow = rottenOrange.get(0) + dir[0];
                    int nextCol = rottenOrange.get(1) + dir[1];
                    if (freshSet.contains(List.of(nextRow, nextCol))) {
                        newRottenOnes.add(List.of(nextRow, nextCol));
                        freshSet.remove(List.of(nextRow, nextCol));
                    }
                }
            }
            if (newRottenOnes.isEmpty()) {
                return -1;
            }
            mins++;
            rottenSet = newRottenOnes;
        }

        return mins;
    }
}