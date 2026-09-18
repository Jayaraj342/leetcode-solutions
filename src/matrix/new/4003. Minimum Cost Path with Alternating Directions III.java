// https://leetcode.com/problems/minimum-cost-path-with-alternating-directions-iii
// https://github.com/Jayaraj342/leetcode-solutions/blob/master/src/matrix/new/4003. Minimum Cost Path with Alternating Directions III.java

class Solution {
    private static final int[][] DIRS = new int[][]{{0, -1}, {-1, 0}, {0, 1}, {1, 0}};// [even, even, odd, odd]

    public long minCost(int m, int n, int[][] penalty) {
        long[][][] dist = new long[m][n][2];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                Arrays.fill(dist[i][j], Long.MAX_VALUE);
            }
        }

        PriorityQueue<State> pq = new PriorityQueue<>(Comparator.comparingLong(a -> a.cost));// [cost, i, j, parity]
        pq.add(new State(1, 0, 0, 0));
        dist[0][0][0] = 1;

        while (!pq.isEmpty()) {
            State last = pq.remove();
            long cost = last.cost;
            int i = last.i, j = last.j, parity = last.parity;

            if (i == m - 1 && j == n - 1) {
                return cost;
            }

            int np = parity ^ 1;// new parity

            // wait in the same cell - flip parity
            if (cost + penalty[i][j] < dist[i][j][np]) {
                dist[i][j][np] = cost + penalty[i][j];
                pq.add(new State(dist[i][j][np], i, j, np));
            }

            // try all neighbors
            for (int d = 0; d < 4; d++) {
                int ni = i + DIRS[d][0], nj = j + DIRS[d][1];

                if (ni < 0 || nj < 0 || ni >= m || nj >= n) {
                    continue;
                }

                boolean isPenaltyReq = (np == 1) ? (d >= 2) : d < 2;
                long moveCost = cost + (long) (ni + 1) * (nj + 1);
                moveCost += (isPenaltyReq ? 0 : penalty[i][j]);// penalty is of src cell

                if (moveCost < dist[ni][nj][np]) {
                    dist[ni][nj][np] = moveCost;
                    pq.add(new State(dist[ni][nj][np], ni, nj, np));
                }
            }
        }

        return -1;
    }

    static class State {
        long cost;
        int i, j, parity;

        public State(long cost, int i, int j, int parity) {
            this.cost = cost;
            this.i = i;
            this.j = j;
            this.parity = parity;
        }
    }
}