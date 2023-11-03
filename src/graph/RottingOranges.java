// don't use list - use string concatination
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