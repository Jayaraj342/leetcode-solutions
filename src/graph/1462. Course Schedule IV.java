class Solution {
    public List<Boolean> checkIfPrerequisite(int numCourses, int[][] prerequisites, int[][] queries) {
        boolean[][] dp = new boolean[numCourses][numCourses];// Floyd-Warshall Algorithm to compute transitive closure

        // Initialize direct prerequisites
        for (int[] edge : prerequisites) {
            int u = edge[0], v = edge[1];
            dp[u][v] = true;
        }

        int n = numCourses;
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    boolean pathThroughK = dp[i][k] && dp[k][j];
                    dp[i][j] = dp[i][j] || pathThroughK;
                }
            }
        }

        // Process queries
        List<Boolean> res = new ArrayList<>();
        for (int[] query : queries) {
            int u = query[0], v = query[1];
            res.add(dp[u][v]);
        }

        return res;
    }
}