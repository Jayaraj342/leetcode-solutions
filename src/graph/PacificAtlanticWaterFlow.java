class Solution {
    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        Set<List<Integer>> pacific = new HashSet<>();
        Set<List<Integer>> atlantic = new HashSet<>();

        int rows = heights.length, columns = heights[0].length;
        for (int r = 0; r < rows; r++) {
            dfs(r, 0, pacific, heights, heights[r][0]);
            dfs(r, columns - 1, atlantic, heights, heights[r][columns - 1]);
        }
        for (int c = 0; c < columns; c++) {
            dfs(0, c, pacific, heights, heights[0][c]);
            dfs(rows - 1, c, atlantic, heights, heights[rows - 1][c]);
        }

        List<List<Integer>> res = new ArrayList<>();
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < columns; c++) {
                if (pacific.contains(List.of(r, c)) && atlantic.contains(List.of(r, c))) {
                    res.add(List.of(r, c));
                }
            }
        }

        return res;
    }

    private void dfs(int r, int c, Set<List<Integer>> set, int[][] heights, int prev) {
        boolean inbounds = r >= 0 && c >= 0 && r < heights.length && c < heights[0].length;
        if (set.contains(List.of(r, c)) || !inbounds || heights[r][c] < prev) {
            return;
        }
        set.add(List.of(r, c));

        dfs(r + 1, c, set, heights, heights[r][c]);
        dfs(r - 1, c, set, heights, heights[r][c]);
        dfs(r, c + 1, set, heights, heights[r][c]);
        dfs(r, c - 1, set, heights, heights[r][c]);
    }
}