import java.util.*;

class Main {
    public static void main(String[] args) {
        int[][] arr = {{2, 4, 5, 6}, {3, 2, 1, 3}, {4, 5, 4, 4}, {4, 5, 4, 4}};
        System.out.println(Arrays.deepToString(arr));
        System.out.println(trapRainWater(arr));
        System.out.println(Arrays.deepToString(arr));
    }

    public static int trapRainWater(int[][] arr) {
        int n = arr.length;

        boolean[][] visited = new boolean[n][n];
        PriorityQueue<int[]> minHeap = new PriorityQueue<>((a, b) -> a[0] - b[0]);

        for (int i = 0; i < n; i++) {
            minHeap.add(new int[]{arr[i][0], i, 0});
            visited[i][0] = true;

            minHeap.add(new int[]{arr[i][n - 1], i, n - 1});
            visited[i][n - 1] = true;
        }

        for (int j = 1; j < n - 1; j++) {
            minHeap.add(new int[]{arr[0][j], 0, j});
            visited[0][j] = true;

            minHeap.add(new int[]{arr[n - 1][j], n - 1, j});
            visited[n - 1][j] = true;
        }

        int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        int curMax = 0, res = 0;
        while (!minHeap.isEmpty()) {
            int[] curr = minHeap.poll();
            curMax = Math.max(curMax, curr[0]);
            for (int[] dir : dirs) {
                int i = curr[1] + dir[0];
                int j = curr[2] + dir[1];
                if (i >= 0 && i < n && j >= 0 && j < n && !visited[i][j]) {
                    if (curMax > arr[i][j]) {
                        res += curMax - arr[i][j];
                        arr[i][j] += curMax - arr[i][j];
                    }
                    minHeap.add(new int[]{arr[i][j], i, j});
                    visited[i][j] = true;
                }
            }
        }

        return res;
    }
}