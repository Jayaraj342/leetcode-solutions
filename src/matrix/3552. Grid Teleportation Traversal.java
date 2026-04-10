class Solution {
    private static final int[][] DIRS = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public int minMoves(String[] matrix) {
        int rows = matrix.length, cols = matrix[0].length();
        char[][] grid = new char[rows][cols];
        Map<Character, List<int[]>> charLocations = new HashMap<>();

        // Initialize grid and map letter locations
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                char ch = matrix[r].charAt(c);
                grid[r][c] = ch;
                if (Character.isLetter(ch)) {
                    charLocations.putIfAbsent(ch, new ArrayList<>());
                    charLocations.get(ch).add(new int[]{r, c});
                }
            }
        }

        Queue<int[]> queue = new LinkedList<>();
        // Start BFS from (0, 0) or all instances of the letter if it is one
        if (Character.isLetter(grid[0][0])) {
            for (int[] loc : charLocations.get(grid[0][0])) {
                queue.add(loc);
                grid[loc[0]][loc[1]] = '*';
            }
        } else {
            queue.add(new int[]{0, 0});
            grid[0][0] = '*';
        }

        if (grid[rows - 1][cols - 1] == '*') return 0;

        int steps = 1;
        // BFS to find the shortest path
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] loc = queue.remove();
                int r = loc[0], c = loc[1];

                for (int[] dir : DIRS) {
                    int newR = r + dir[0], newC = c + dir[1];

                    if (inBounds(newR, newC, grid)) {
                        if (grid[newR][newC] == '.') {
                            if (newR == rows - 1 && newC == cols - 1) {
                                return steps;
                            }
                            grid[newR][newC] = '*';
                            queue.add(new int[]{newR, newC});
                        } else if (Character.isLetter(grid[newR][newC])) {
                            for (int[] portal : charLocations.get(grid[newR][newC])) {
                                if (portal[0] == rows - 1 && portal[1] == cols - 1) {
                                    return steps;
                                }
                                queue.add(portal);
                                grid[portal[0]][portal[1]] = '*';
                            }
                        }
                    }
                }
            }
            steps++;
        }

        return -1;
    }

    private boolean inBounds(int r, int c, char[][] grid) {
        return r >= 0 && r < grid.length && c >= 0 && c < grid[0].length;
    }

    public static void main(String[] args) {
        new Solution().minMoves(new String[]{"A..", ".A.", "..."});
    }
}