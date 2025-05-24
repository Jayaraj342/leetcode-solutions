class Solution {
    int m, n;
    private static final int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public int largestIsland(int[][] grid) {
        m = grid.length;
        n = grid[0].length;

        int len = m * n;
        // stores cell no. -> which island the cell belongs (the parent as in union find)
        // also used to track visited
        int[] visitedParentMap = new int[len];
        Arrays.fill(visitedParentMap, -1);

        Map<Integer, Integer> islandSizeMap = new HashMap<>();

        // Find all islands and record their sizes
        int maxIsland = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int parent = cellNo(i, j);
                if (grid[i][j] == 1 && visitedParentMap[parent] == -1) {
                    int currIslandSize = dfs(grid, visitedParentMap, i, j, parent);
                    islandSizeMap.put(parent, currIslandSize);

                    // track if whole grid is an island
                    maxIsland = Math.max(maxIsland, currIslandSize);
                }
            }
        }

        // Try flipping each 0 to 1 and compute the largest island possible from 4 sides
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 0) {
                    Set<Integer> set = new HashSet<>();
                    for (int[] dir : dirs) {
                        int x = i + dir[0], y = j + dir[1];
                        if (isValidIdx(x, y) && grid[x][y] != 0) {
                            set.add(visitedParentMap[cellNo(x, y)]);
                        }
                    }

                    int currSize = 1; // consider flipped island size
                    for (int parent : set) {
                        currSize += islandSizeMap.get(parent);
                    }

                    maxIsland = Math.max(maxIsland, currSize);
                }
            }
        }

        return maxIsland;
    }

    private int dfs(int[][] grid, int[] visitedParentMap, int i, int j, int parent) {
        if (!isValidIdx(i, j) || grid[i][j] == 0 || visitedParentMap[cellNo(i, j)] != -1) {
            return 0;
        }

        // it's an island as grid[i][j] = 1
        visitedParentMap[cellNo(i, j)] = parent;
        int res = 0;
        for (int[] dir : dirs) {
            res += dfs(grid, visitedParentMap, i + dir[0], j + dir[1], parent);
        }

        return res + 1;
    }

    // (0, 0) -> 0, (0, 1) -> 1 ...
    private int cellNo(int i, int j) {
        return i * n + j;
    }

    private boolean isValidIdx(int i, int j) {
        return i >= 0 && j >= 0 && i < m && j < n;
    }
}