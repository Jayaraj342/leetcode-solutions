// SC : O(1)
class Solution {
    public int[][] imageSmoother(int[][] img) {
        int[][] dirs = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}, {1, 1}, {1, -1}, {-1, 1}, {-1, -1}};

        int m = img.length, n = img[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int total = img[i][j], cnt = 1;
                for (int[] dir : dirs) {
                    int r = i + dir[0], c = j + dir[1];
                    if (r >= 0 && r < m && c >= 0 && c < n) {
                        total += img[r][c] % 256;
                        cnt++;
                    }
                }
                img[i][j] ^= (total / cnt) << 8;
            }
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                img[i][j] = img[i][j] >> 8;
            }
        }

        return img;
    }
}

// SC : O(m.n)
class Solution {
    public int[][] imageSmoother(int[][] img) {
        int[][] dirs = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}, {1, 1}, {1, -1}, {-1, 1}, {-1, -1}};

        int m = img.length, n = img[0].length;
        int[][] res = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int total = img[i][j], cnt = 1;
                for (int[] dir : dirs) {
                    int r = i + dir[0], c = j + dir[1];
                    if (r >= 0 && r < m && c >= 0 && c < n) {
                        total += img[r][c];
                        cnt++;
                    }
                }
                res[i][j] = total / cnt;
            }
        }

        return res;
    }
}