class Solution {
    Set<Integer> col = new HashSet<>();
    Set<Integer> posDiag = new HashSet<>();
    Set<Integer> negDiag = new HashSet<>();

    public int totalNQueens(int n) {
        return dfs(0, n);
    }

    private int dfs(int r, int n) {
        if (r == n) {
            return 1;
        }
        int count = 0;
        for (int c = 0; c < n; c++) {
            if (col.contains(c) || posDiag.contains(r + c) || negDiag.contains(r - c)) {
                continue;
            }
            col.add(c);
            posDiag.add(r + c);
            negDiag.add(r - c);

            count += dfs(r + 1, n);

            col.remove(c);
            posDiag.remove(r + c);
            negDiag.remove(r - c);
        }

        return count;
    }
}