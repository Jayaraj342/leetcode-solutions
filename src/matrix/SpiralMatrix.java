class Solution {
    public List<Integer> spiralOrder(int[][] matrix) {
        int n = matrix.length;
        int m = matrix[0].length;

        int[] dr = new int[]{0, 1, 0, -1};
        int[] dc = new int[]{1, 0, -1, 0};

        boolean[][] visited = new boolean[n][m];

        int di = 0, c = 0, r = 0, count = 0;
        List<Integer> result = new ArrayList<>();
        while (count < m * n) {
            result.add(matrix[r][c]);
            visited[r][c] = true;
            count++;

            int nr = r + dr[di];
            int nc = c + dc[di];

            boolean inBounds = nr >= 0 && nr < n && nc >= 0 && nc < m;
            if (!inBounds || visited[nr][nc]) {
                di = (di + 1) % 4;
                r += dr[di];
                c += dc[di];
            } else {
                r = nr;
                c = nc;
            }
        }

        return result;
    }
}