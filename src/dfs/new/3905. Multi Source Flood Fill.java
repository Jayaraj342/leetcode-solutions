// https://leetcode.com/problems/multi-source-flood-fill/
// https://github.com/Jayaraj342/leetcode-solutions/blob/master/src/dfs/new/3905. Multi Source Flood Fill.java

class Solution {
    public int[][] colorGrid(int n, int m, int[][] sources) {
        List<int[]> queue = new ArrayList<>();
        for (int[] src : sources) {
            queue.add(new int[]{src[0], src[1], src[2]});
        }
        queue.sort((a, b) -> Integer.compare(b[2], a[2]));

        int[][] res = new int[n][m];
        for (int[] idx : queue) {
            res[idx[0]][idx[1]] = idx[2];
        }

        int[] dx = {1, -1, 0, 0}, dy = {0, 0, 1, -1};
        for (int k = 0; k < queue.size(); k++) {
            int[] curr = queue.get(k);
            int i = curr[0], j = curr[1], val = curr[2];
            for (int d = 0; d < 4; d++) {
                int ni = i + dx[d], nj = j + dy[d];
                if (ni >= 0 && ni < n && nj >= 0 && nj < m && res[ni][nj] == 0) {
                    res[ni][nj] = val;
                    queue.add(new int[]{ni, nj, val});
                }
            }
        }

        return res;
    }
}

class Solution {
    int[][] dirs = new int[][]{{0, -1}, {0, 1}, {-1, 0}, {1, 0}};

    public int[][] colorGrid(int n, int m, int[][] sources) {
        int[][] res = new int[n][m];
        Map<Pair, Integer> queue = new HashMap<>();
        for (int[] src : sources) {
            int i = src[0], j = src[1], val = src[2];
            queue.put(new Pair(i, j), val);
        }

        while (!queue.isEmpty()) {
            for (Map.Entry<Pair, Integer> entry : queue.entrySet()) {
                int i = entry.getKey().i, j = entry.getKey().j, val = entry.getValue();
                res[i][j] = val;
            }

            Map<Pair, Integer> temp = new HashMap<>();
            for (Map.Entry<Pair, Integer> entry : queue.entrySet()) {
                int i = entry.getKey().i, j = entry.getKey().j, val = entry.getValue();

                for (int[] dir : dirs) {
                    int ni = i + dir[0], nj = j + dir[1];
                    if (ni >= 0 && ni < n && nj >= 0 && nj < m && res[ni][nj] == 0) {
                        Pair pair = new Pair(ni, nj);
                        temp.put(pair, Math.max(temp.getOrDefault(pair, 0), val));
                    }
                }
            }

            queue = temp;
        }

        return res;
    }

    static class Pair {
        int i, j;

        Pair(int i, int j) {
            this.i = i;
            this.j = j;
        }

        @Override
        public boolean equals(Object obj) {
            Pair pair = (Pair) obj;
            return pair.i == i && pair.j == j;
        }

        @Override
        public int hashCode() {
            return (i + "," + j).hashCode();
        }
    }

    public static void main(String[] args) {
        new Solution().colorGrid(3, 3, new int[][]{{0, 1, 3}, {1, 1, 5}});
    }
}