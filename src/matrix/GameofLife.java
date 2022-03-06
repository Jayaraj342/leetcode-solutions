class Solution {
    public void gameOfLife(int[][] board) {
        if (board == null || board.length == 0) return;
        int m = board.length, n = board[0].length;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int nei = liveNeighbors(board, m, n, i, j);

                if (board[i][j] == 1 && (nei == 2 || nei == 3)) {
                    board[i][j] = 3;
                }
                if (board[i][j] == 0 && nei == 3) {
                    board[i][j] = 2;
                }
            }
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] /= 2;
            }
        }
    }

    public int liveNeighbors(int[][] board, int m, int n, int i, int j) {
        int nei = 0;
        int[] rd = {0, 1, 0, -1, 1, -1, 1, -1};
        int[] cd = {1, 0, -1, 0, 1, -1, -1, 1};
        for (int d = 0; d < 8; d++) {
            int nr = i + rd[d];
            int nc = j + cd[d];

            boolean inbounds = nr >= 0 && nc >= 0 && nr < m && nc < n;
            if (inbounds && (board[nr][nc] % 2) == 1) {
                nei++;
            }
        }
        return nei;
    }
}
// 00 -> 0
// 10 -> 1
// 01 -> 2
// 11 -> 3
