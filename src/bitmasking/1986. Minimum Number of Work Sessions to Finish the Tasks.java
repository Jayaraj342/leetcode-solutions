// n* 2^n
class Solution {
    int n, sessionTime;
    Integer[][] memo;

    public int minSessions(int[] tasks, int sessionTime) {
        n = tasks.length;
        this.sessionTime = sessionTime;

        memo = new Integer[1 << n][sessionTime + 1];

        return dfs(tasks, 0, sessionTime);
    }

    private int dfs(int[] tasks, int mask, int remainTime) {
        if (mask == (1 << n) - 1) {
            return 1;
        }

        if (memo[mask][remainTime] != null) {
            return memo[mask][remainTime];
        }

        int ans = n;  // There is up to N work sessions
        for (int i = 0; i < n; ++i) {
            if (((mask >> i) & 1) == 0) {// current num not used
                int newMask = mask | (1 << i);
                if (remainTime >= tasks[i]) {
                    ans = Math.min(ans, dfs(tasks, newMask, remainTime - tasks[i])); // Consume current session
                } else {
                    ans = Math.min(ans, 1 + dfs(tasks, newMask, sessionTime - tasks[i])); // Create a new session
                }
            }
        }

        return memo[mask][remainTime] = ans;
    }
}