class Solution {
    public int snakesAndLadders(int[][] board) {
        int n = board.length;
        for (int i = 0; i < n / 2; i++) {
            int[] temp = board[i];
            board[i] = board[n - i - 1];
            board[n - i - 1] = temp;
        }
        // [square, jumps]
        Queue<List<Integer>> queue = new ArrayDeque<>();
        queue.add(List.of(1, 0));

        Set<Integer> visited = new HashSet<>();
        while (!queue.isEmpty()) {
            List<Integer> lastSquare = queue.remove();

            for (int i = 1; i < 7; i++) {
                int nextSquare = lastSquare.get(0) + i;
                List<Integer> indexes = squareToIndexes(nextSquare, n);
                int r = indexes.get(0), c = indexes.get(1);
                if (board[r][c] != -1) {
                    nextSquare = board[r][c];
                }
                if (nextSquare == n * n) {
                    return lastSquare.get(1) + 1;
                }
                if (!visited.contains(nextSquare)) {
                    queue.add(List.of(nextSquare, lastSquare.get(1) + 1));
                    visited.add(nextSquare);
                }
            }
        }
        return -1;
    }

    private List<Integer> squareToIndexes(int square, int n) {
        int r = (square - 1) / n;
        int c = (square - 1) % n;
        if (r % 2 != 0) {
            c = n - 1 - c;
        }

        return List.of(r, c);
    }
}