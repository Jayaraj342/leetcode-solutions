// space O(n)
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

// space O(1)
class Solution {
    public List<Integer> spiralOrder(int[][] matrix) {
        int rowBegin = 0, rowEnd = matrix.length - 1;
        int columnBegin = 0, columnEnd = matrix[0].length - 1;

        List<Integer> res = new ArrayList<>();
        while (rowBegin <= rowEnd && columnBegin <= columnEnd) {
            for (int i = columnBegin; i <= columnEnd; i++) {
                res.add(matrix[rowBegin][i]);
            }
            rowBegin++;

            for (int i = rowBegin; i <= rowEnd; i++) {
                res.add(matrix[i][columnEnd]);
            }
            columnEnd--;

            if (rowBegin <= rowEnd)
                for (int i = columnEnd; i >= columnBegin; i--) {
                    res.add(matrix[rowEnd][i]);
                }
            rowEnd--;

            if (columnBegin <= columnEnd)
                for (int i = rowEnd; i >= rowBegin; i--) {
                    res.add(matrix[i][columnBegin]);
                }
            columnBegin++;
        }

        return res;
    }
}