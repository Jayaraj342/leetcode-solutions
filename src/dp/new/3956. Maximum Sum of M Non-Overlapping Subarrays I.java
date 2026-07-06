// https://leetcode.com/problems/maximum-sum-of-m-non-overlapping-subarrays-i/description/
// https://github.com/Jayaraj342/leetcode-solutions/blob/master/src/dp/new/3956. Maximum Sum of M Non-Overlapping Subarrays I.java
class Solution {
    public long maximumSum(int[] nums, int m, int l, int r) {
        int n = nums.length;
        long min = 1000L * Integer.MIN_VALUE;

        long[] prefix = new long[n + 1];
        for (int i = 0; i < n; i++) {
            prefix[i + 1] = prefix[i] + nums[i];
        }

        long[][] dp = new long[m + 1][n + 1];
        for (int t = 0; t <= m; t++) {
            Arrays.fill(dp[t], min);
        }

        // Base case
        for (int i = 0; i <= n; i++) {
            dp[0][i] = 0;
        }

        long ans = min;
        for (int t = 1; t <= m; t++) {
            Deque<Integer> dq = new LinkedList<>();

            for (int i = 1; i <= n; i++) {
                // Skip i-th position
                dp[t][i] = dp[t][i - 1];

                int pos = i - l; // new element entering the window
                if (pos >= 0) {
                    long val = dp[t - 1][pos] - prefix[pos];

                    // Maintain decreasing order
                    while (!dq.isEmpty()) {
                        int last = dq.peekLast();
                        if (dp[t - 1][last] - prefix[last] >= val) {
                            break;
                        }

                        dq.pollLast();
                    }

                    dq.offerLast(pos);
                }

                // Remove expired indices
                while (!dq.isEmpty() && dq.peekFirst() < i - r) {
                    dq.pollFirst();
                }

                if (!dq.isEmpty()) {
                    int start = dq.peekFirst();

                    long candidate = dp[t - 1][start] - prefix[start] + prefix[i];
                    dp[t][i] = Math.max(dp[t][i], candidate);
                }
            }

            ans = Math.max(ans, dp[t][n]);
        }

        return ans;
    }
}