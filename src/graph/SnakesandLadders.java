class Solution {
    public int snakesAndLadders(int[][] board) {
        int n = board.length;

        // Reverse the board vertically to make bottom row index = 0 (start position)
        for (int i = 0; i < n / 2; i++) {
            int[] temp = board[i];
            board[i] = board[n - 1 - i];
            board[n - 1 - i] = temp;
        }

        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[] {1, 0}); // {square, moves}

        boolean[] visited = new boolean[n * n + 1];
        visited[1] = true;

        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int square = curr[0], moves = curr[1];

            for (int dice = 1; dice <= 6; dice++) {
                int next = square + dice;
                if (next > n * n) break; // out of bounds

                next = getBoardValue(next, n, board); // handle snake/ladder

                if (next == n * n) return moves + 1;
                if (!visited[next]) {
                    visited[next] = true;
                    queue.offer(new int[] {next, moves + 1});
                }
            }
        }

        return -1;
    }

    // Converts square number -> (row, col), handles zigzag pattern
    private int getBoardValue(int square, int n, int[][] board) {
        int row = (square - 1) / n;
        int col = (square - 1) % n;
        if (row % 2 == 1) col = n - 1 - col; // zigzag direction
        return board[row][col] == -1 ? square : board[row][col];
    }
}