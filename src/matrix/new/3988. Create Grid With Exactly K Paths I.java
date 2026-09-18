// https://leetcode.com/problems/create-grid-with-exactly-k-paths-i
// https://github.com/Jayaraj342/leetcode-solutions/blob/master/src/matrix/new/3988. Create Grid With Exactly K Paths I.java

class Solution {
    public String[] createGrid(int m, int n, int k) {
        Map<Integer, String[][]> templates = new HashMap<>();
        templates.put(1, new String[][]{
                {"."}
        });
        templates.put(2, new String[][]{
                {"..", ".."}
        });
        templates.put(3, new String[][]{
                {"..", "..", ".."},
                {"...", "..."}
        });
        templates.put(4, new String[][]{
                {"..", "..", "..", ".."},
                {"....", "...."},
                {"..#", "...", "#.."}
        });

        String[][] patterns = templates.get(k);
        if (patterns == null) {
            return new String[0];
        }

        for (String[] pattern : patterns) {
            int patternM = pattern.length;
            int patternN = pattern[0].length();

            if (patternM > m || patternN > n) {
                continue;
            }

            char[][] grid = new char[m][n];
            for (char[] row : grid) {
                Arrays.fill(row, '#');
            }

            // Place the template
            for (int i = 0; i < patternM; i++) {
                for (int j = 0; j < patternN; j++) {
                    grid[i][j] = pattern[i].charAt(j);
                }
            }

            // Extend the last column downward
            for (int i = patternM; i < m; i++) {
                grid[i][patternN - 1] = '.';
            }

            // Extend the last row to the right
            for (int j = patternN; j < n; j++) {
                grid[m - 1][j] = '.';
            }

            String[] res = new String[m];
            for (int i = 0; i < m; i++) {
                res[i] = new String(grid[i]);
            }

            return res;
        }

        return new String[0];
    }
}